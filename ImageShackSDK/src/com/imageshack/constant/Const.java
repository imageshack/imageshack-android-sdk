package com.imageshack.constant;

public class Const {
	
	// Common JSON fields
	public static final String PROCESS_TIME = "process_time";
	public static final String SUCCESS = "success";
	public static final String RESULT = "result";
	public static final String LIMIT = "limit";
	public static final String OFFSET = "offset";
	public static final String TOTAL = "total";
	public static final String DESCRIPTION = "description";
	public static final String ERROR = "error";
	public static final String ERROR_MESSAGE = "error_message";
	public static final String CREATION_DATE = "creation_date";
	public static final String IS_OWNER = "is_owner";
	public static final String TITLE = "title";
	public static final String PUBLIC = "public";
	
	// Images JSON fields
	public static final String IMAGE = "image";
	public static final String IMAGES = "images";
	public static final String PASSED = "passed";
	public static final String FAILED = "failed";
	public static final String ID = "id";
	public static final String IDS = "ids";
	public static final String SERVER = "server";
	public static final String FILENAME = "filename";
	public static final String BUCKET = "bucket";
	public static final String WIDTH = "width";
	public static final String HEIGHT = "height";
	public static final String ORIGINAL_FILENAME = "original_filename";
	public static final String LIKES = "likes";
	public static final String LIKED = "liked";
	public static final String FILESIZE = "filesize";
	public static final String DIRECT_LINK = "direct_link";
	public static final String TAGS = "tags";
	public static final String VIEWS = "views";
	public static final String FILTER = "filter";
	public static final String COMMENTS_COUNT = "comments_count";
	public static final String COMMENTS_DISABLED = "comments_disabled";
	public static final String PREV_IMAGES = "prev_images";
	public static final String NEXT_IMAGES = "next_images";
	public static final String LATEST_IMAGES = "latest_images";
	public static final String IMAGE_LIMIT = "image_limit";
	public static final String FILE = "file";
	public static final String ANGLE = "angle";
	
	// Comments
	public static final String SOURCE = "source";
	public static final String COMMENT = "comment";
	public static final String START_OFFSET = "start_offset";
	public static final String END_OFFSET = "end_offset";
	
	// Albums JSON fields
	public static final String ALBUM = "album";
	public static final String ALBUMS = "albums";
	public static final String SHOW_PRIVATE = "show_private";
	
	// Activities JSON fields
	public static final String ACTOR = "actor";
	public static final String ACTION_TYPE = "actionType";
	public static final String OBJECT = "object";
	public static final String OBJECT_TYPE = "objectType";
	public static final String VIEWED = "viewed";
	public static final String DATE = "date";
	public static final String TEXT = "text";
	
	// Files
	public static final String FILES = "files";
	
	// User JSON fields
	public static final String API_KEY = "api_key";
	public static final String AUTH_TOKEN = "auth_token";
	public static final String USERID = "userid";
	public static final String EMAIL = "email";
	public static final String USERNMAE = "username";
	public static final String PASSWORD = "password";
	public static final String MEMBERSHIP = "membership";
	public static final String MEMBERSHIP_ITEM_NUM = "membership_item_number";
	public static final String OWNER = "owner";
	public static final String AVATAR = "avatar";
	public static final String IS_FOLLOWING = "is_following";
	public static final String FOLLOWING_ALLOWED = "following_allowed";
	public static final String GENDER = "gender";
	public static final String FIRST_NAME = "first_name";
	public static final String LAST_NAME = "last_name";
	public static final String LOCATION = "location";
	public static final String AVATAR_SERVER = "avatar_server";
	public static final String AVATAR_FILENAME = "avatar_filename";
	
	// Settings JSON fields
	public static final String PRIVATE_PROFILE = "private_profile";
	public static final String PRIVATE_IMAGES = "private_images";
	public static final String PRIVATE_ALBUMS = "private_albums";
	public static final String PRIVATE_FOLLOWING = "private_followings";
	public static final String UPLOAD_NOTIFICATIONS = "upload_notification";
	public static final String COMMENTS_NOTIFICATIONS = "comments_notification";
	public static final String LIKES_NOTIFICATIONS = "likes_notification";
	public static final String FOLLOWING_NOTIFICATIONS = "follows_notification";
	
	// Error messages
	public static final String JSON_PARSING_EXCEPTION = "Exception parsing json response";
	public static final String AUTH_ERROR = "Unable to login";
	public static final String IMAGE_ERROR = "Unable to retrieve image data";
	public static final String COMMENTS_ERROR = "Unable to retrieve comments";
	public static final String GENERIC_ERROR = "There was a problem processing your request. Please try again.";
	public static final String ALBUM_ERROR = "Unable to retrieve album data";
	public static final String USER_ERROR = "Unable to retrieve user data";
	public static final String USER_DELETE_ERROR = "Unable to delete user";
	public static final String UPLOAD_ERROR = "Unable to upload image";
	public static final String CHECK_NETWORK = "Something went wrong, please check your connection";
	public static final String MISSING_PARAMS = "Looks like you are missing some parameters. Please check the doc.";
	
	// API END POINTS
	public static final String ACTIVITIES = "activities";
	public static final String ACTIVITIES_NEW_COUNT = "new_count";
	public static final String ACTIVITIES_MARK_AS_VIEWED = "viewed";
	public static final String USER = "user";
	public static final String LOGIN = "login";
	public static final String ROTATE = "rotate";
	public static final String COMMENTS = "comments";
	public static final String SETTINGS = "settings";
	public static final String ALBUMS_ADD_IMAGES = "add_files";
	public static final String ALBUMS_DELETE_IMAGES = "delete_files";
	public static final String UNLIKE = "unlike";
	
	// API common
	public static final String API = "imageshack.com/rest_api/v2/";
	public static final String IMAGIZER = "imagizer.imageshack.com/v2/";
	
	// SSL
	public static final boolean SSL = true;
	public static final String PROTOCOL = "https://";
}
