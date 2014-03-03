imageshack-android-sdk
======================

### ImageShack SDK for Android

ImageShack SDK is a lightweight library for Android. It lets you easily upload and manage your photos using ImageShack API. The SDK wraps all calls so that the devloper does not have to worry about sending HTTP requests and parsing JSON responses. Instead you will be working with ImageShack models which are just Java objects. 

### What can you do with SDK

1. Register/login to your ImageShack Account.
2. Upload/edit/delete images.
3. Like/unlike/comment/fetch images data.
4. Create/edit/delete albums.
5. Manage account settings.
6. View activities (likes, comments, etc).
7. Retrieve other users' images/albums. 

### Getting started.

1. Download or clone the reposotory to your computer. 
2. Start Eclipse.
3. Go to File -> Import -> Android -> Existing Android Code into Workspace.
	![ScreenShot](http://imageshack.com/a/img196/3982/ewl4.png)
5. Hit Browse and navigate to the location from step 1.
6. Make sure that Demo and ImageShackSDK are checked and hit Finish.
	![ScreenShot](http://imageshack.com/a/img802/8397/l5a5.png)

There is one more thing needed to run the Demo app.

1. Go to https://imageshack.com/contact/api to request your developer key. The key is required to instantiate ImageShackClient object. 
2. Open Demo/src/com/imageshack/demo/LoginActivity.java file and enter your key on line 42
```java
private final String API_KEY = null;
```
Now you are ready to launch Demo app.

### Example
This is how you can retrieve a list of images for authenticated user.
```java
// Get an instance of ImageShackClient with your API key and authToken. The
// authToken is retrieved with login() method. See Demo's LoginActivity.java
// for example.
ImageShackClient isClient = new ImageShackClient(API_KEY, authToken);

// Call getImages API with limit = 3 and no offset. The response listener 
// listens for async HTTP response. It needs to be passed for every API call.
isClient.getImages(Integer.valueOf(3), null, new ResponseListener() {

			@Override
			public void onResponse(ImageShackModel model) {

				ImagesModel images = (ImagesModel) model;
				// visualize images...

			}

		});
```

### Fetching images of different sizes.
We have built a dynamic image resizer so you can be as creative as you want when it comes to UI. The `ImageModel` has a method `imagizer()` with various parameter sets. For example:
```java
// image is of type ImageModel
// imagizer(int width, int height, boolean autoCrop)
image.imagizer(200, 200, true);
```
The above code will generate a URL that returns an image with 200x200 resolution. The `autoCrop` parameter tells the imagizer that you want an image of exact `width` and `height`. If `autoCrop == false` image will fit into the box defined by `width` and `height` while preserving the original dimensions. 
