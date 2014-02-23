package com.imageshack.demo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.imageshack.client.ImageShackClient;
import com.imageshack.listener.ResponseListener;
import com.imageshack.model.ActivitiesModel;
import com.imageshack.model.AlbumModel;
import com.imageshack.model.AlbumsModel;
import com.imageshack.model.CommentsModel;
import com.imageshack.model.ImageModel;
import com.imageshack.model.ImageShackModel;
import com.imageshack.model.ImagesModel;
import com.imageshack.model.SimpleModel;
import com.imageshack.model.UploadModel;
import com.imageshack.model.UserSettingsModel;

public class MainActivity extends Activity {

	private static final String LOADING = "Loading...";
	private TextView terminal;

	private TextView authTokenTextView;
	private TextView apiKeyTextView;

	private String apiKey;
	private String authToken;

	private Button getDataButton;
	private Button browseButton;

	private Uri targetUri;

	private Spinner apiSelector;

	private ImageShackClient isClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Intent intent = getIntent();
		String auth = intent.getStringExtra(LoginActivity.AUTH_OBJECT_EXTRA);

		terminal = (TextView) findViewById(R.id.terminal);
		terminal.setText(formatObjectString(auth));

		authTokenTextView = (TextView) findViewById(R.id.authTokenTextView);
		authToken = intent.getStringExtra(LoginActivity.AUTH_TOKEN_EXTRA);
		authTokenTextView.setText(authToken);

		apiKeyTextView = (TextView) findViewById(R.id.apiKeyTextView);
		apiKey = intent.getStringExtra(LoginActivity.API_KEY_EXTRA);

		if (apiKey == null) {
			apiKeyTextView.setText(R.string.api_key_hint_value);
		} else {
			apiKeyTextView.setText(apiKey);
		}

		apiSelector = (Spinner) findViewById(R.id.apiSelector);
		apiSelector.setOnItemSelectedListener(apiSelectorListener);

		getDataButton = (Button) findViewById(R.id.getDataButton);
		getDataButton.setOnClickListener(getDataButtonListener);

		browseButton = (Button) findViewById(R.id.browseButton);
		browseButton.setVisibility(Button.INVISIBLE);
		browseButton.setOnClickListener(browseImagesListener);

		isClient = new ImageShackClient(apiKey, authToken);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {

			targetUri = data.getData();

			getDataButton.setEnabled(true);

			terminal.setText(targetUri.toString());

		}

	}

	public OnItemSelectedListener apiSelectorListener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {

			String apiCall = String.valueOf(apiSelector.getSelectedItem());

			if ("upload".equals(apiCall)) {

				getDataButton.setEnabled(false);

				browseButton.setVisibility(Button.VISIBLE);

			} else {

				if (browseButton.getVisibility() != Button.INVISIBLE) {

					browseButton.setVisibility(Button.INVISIBLE);

					getDataButton.setEnabled(true);

				}
			}

		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {

		}

	};

	public OnClickListener browseImagesListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			Intent intent = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

			startActivityForResult(intent, 0);

		}

	};

	public OnClickListener getDataButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			getDataButton.setEnabled(false);

			String apiCall = String.valueOf(apiSelector.getSelectedItem());

			if (apiCall.equals("get image")) {
				getImage();
			} else if (apiCall.equals("get images")) {
				getImages();
			} else if (apiCall.equals("get activities")) {
				getActivities();
			} else if (apiCall.equals("get albums")) {
				getAlbums();
			} else if (apiCall.equals("create album")) {
				createAlbum();
			} else if (apiCall.equals("delete album")) {
				removeAlbum();
			} else if (apiCall.equals("upload")) {
				uploadImage();
			} else if (apiCall.equals("get comments")) {
				getComments();
			} else if (apiCall.equals("get settings")) {
				getSettings();
			} else {
				getDataButton.setEnabled(true);
			}
			
		}

	};
	
	private void getSettings() {
		
		terminal.setText(LOADING);
		
		isClient.getUserSettings(new ResponseListener() {
			
			@Override
			public void onResponse(ImageShackModel model) {
				
				UserSettingsModel settings = (UserSettingsModel) model;
				terminal.setText(formatObjectString(settings.toString()));
				getDataButton.setEnabled(true);
				
			}
			
		});
		
	}
	
	private void getActivities() {
		
		terminal.setText(LOADING);
		
		isClient.getActivities(null, null, new ResponseListener() {
			
			@Override
			public void onResponse(ImageShackModel model) {
				
				ActivitiesModel activities = (ActivitiesModel) model;
				terminal.setText(formatObjectString(activities.toString()));
				getDataButton.setEnabled(true);
				
			}
			
		});
		
	}

	private void getAlbums() {

		terminal.setText(LOADING);

		isClient.getAlbums(Integer.valueOf(3), null, new ResponseListener() {

			@Override
			public void onResponse(ImageShackModel model) {

				AlbumsModel albums = (AlbumsModel) model;

				if (albums != null) {
					terminal.setText(formatObjectString(albums.toString()));
				}

				getDataButton.setEnabled(true);

			}

		});

	}

	private void createAlbum() {

		terminal.setText(LOADING);

		String title = "New album from API"; // required
		String description = "This album was created with ImageShack SDK for android."; // optional
		Boolean isPublic = null; // optional
		ArrayList<String> imageIds = null; // optional

		isClient.createAlbum(title, description, isPublic, imageIds,
				new ResponseListener() {

					@Override
					public void onResponse(ImageShackModel model) {

						AlbumModel album = (AlbumModel) model;
						terminal.setText(formatObjectString(album.toString()));
						getDataButton.setEnabled(true);

					}

				});

	}

	private void removeAlbum() {

		terminal.setText(LOADING);

		isClient.getAlbums(Integer.valueOf(1), null, new ResponseListener() {

			@Override
			public void onResponse(ImageShackModel model) {

				AlbumsModel albums = (AlbumsModel) model;

				if (albums.getAlbums().size() > 0) {
					final String albumId = albums.getAlbums().get(0).getId();

					isClient.removeAlbum(albumId, new ResponseListener() {

						@Override
						public void onResponse(ImageShackModel model) {

							SimpleModel result = (SimpleModel) model;
							terminal.setText(formatObjectString(result
									.toString()));
							getDataButton.setEnabled(true);

						}

					});

				} else {
					getDataButton.setEnabled(true);
				}
			}

		});
	}

	private void getImages() {

		terminal.setText(LOADING);

		isClient.getImages(Integer.valueOf(3), null, new ResponseListener() {

			@Override
			public void onResponse(ImageShackModel model) {

				ImagesModel images = (ImagesModel) model;

				if (images != null) {
					terminal.setText(formatObjectString(images.toString()));
				}

				getDataButton.setEnabled(true);

			}

		});

	}

	private void getImage() {

		terminal.setText(LOADING);

		String imageId = "nqxzeoj";

		isClient.getImage(imageId, new ResponseListener() {

			@Override
			public void onResponse(ImageShackModel model) {

				ImageModel image = (ImageModel) model;

				if (image != null) {
					terminal.setText(formatObjectString(image.toString()));
				}

				getDataButton.setEnabled(true);

			}

		});

	}

	private void uploadImage() {

		terminal.setText(LOADING);

		String[] tags = { "random", "tag", "random tag" };
		String title = "work station";
		String album = "Test album";
		Boolean commentsDisabled = null, isPublic = null;
		String path = getRealPathFromURI(targetUri);

		isClient.uploadImage(path, tags, album, title, commentsDisabled,
				isPublic, new ResponseListener() {

					@Override
					public void onResponse(ImageShackModel model) {

						UploadModel upload = (UploadModel) model;

						if (upload != null) {
							terminal.setText(formatObjectString(upload
									.toString()));
						}

						getDataButton.setEnabled(true);
					}

				});

	}

	private void getComments() {

		terminal.setText(LOADING);

		isClient.getComments("jnoyqpj", null, null, new ResponseListener() {

			@Override
			public void onResponse(ImageShackModel model) {

				CommentsModel comments = (CommentsModel) model;

				if (comments != null) {
					terminal.setText(formatObjectString(comments.toString()));
				}

				getDataButton.setEnabled(true);

			}

		});

	}

	@SuppressWarnings("deprecation")
	public String getRealPathFromURI(Uri contentUri) {

		// can post image
		String[] proj = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(contentUri, proj, // Which columns to
														// return
				null, // WHERE clause; which rows to return (all rows)
				null, // WHERE clause selection arguments (none)
				null); // Order-by clause (ascending by name)
		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();

		return cursor.getString(column_index);
	}

	private String formatObjectString(String model) {
		StringBuilder formatted = new StringBuilder();
		int offset = 0;

		for (int i = 0; i < model.length(); i++) {
			if (model.charAt(i) == '[') {
				if (model.charAt(i + 1) == ']') {
					formatted.append("[").append("]");
					i++;
				} else {
					offset++;
					formatted.append("[").append("\n");
					addPadding(formatted, offset);
				}
			} else if (model.charAt(i) == ']') {
				offset--;
				formatted.append("\n");
				addPadding(formatted, offset);
				formatted.append("]");
			} else if (model.charAt(i) == ',') {
				formatted.append(",").append("\n");
				addPadding(formatted, offset);
				i++;
			} else {
				formatted.append(model.charAt(i));
			}
		}

		return formatted.toString();
	}

	private void addPadding(StringBuilder sb, int offset) {
		for (int i = 0; i < offset; i++) {
			sb.append("\t");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
