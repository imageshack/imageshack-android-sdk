package com.imageshack.utility;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.imageshack.constant.Const;
import com.imageshack.model.CommentModel;
import com.imageshack.model.CommentsModel;
import com.imageshack.model.SimpleUserModel;

public class CommentsJsonParser {

	public static CommentsModel getComments(String jsonString) {
		CommentsModel comments = new CommentsModel();
		ArrayList<CommentModel> commentsList = new ArrayList<CommentModel>();
		JSONObject commentsJson, json;

		try {
			commentsJson = new JSONObject(jsonString);

			comments.setProcessTime(commentsJson.getInt(Const.PROCESS_TIME));

			if (!commentsJson.getBoolean(Const.SUCCESS)) {
				setError(comments, commentsJson, false);
				return comments;
			}

			json = new JSONObject(commentsJson.getString(Const.RESULT));
			comments.setLimit(json.getInt(Const.LIMIT));
			comments.setStartOffset(json.getString(Const.START_OFFSET));
			comments.setEndOffset(json.getString(Const.END_OFFSET));
			comments.setTotal(json.getInt(Const.TOTAL));

			buildListOfCommentsFromJson(json.getJSONArray(Const.COMMENTS),
					commentsList);
			comments.setComments(commentsList);

		} catch (JSONException e) {
			setError(comments, null, true);
		}

		return comments;
	}

	public static void buildListOfCommentsFromJson(JSONArray commentsJsonArray,
			ArrayList<CommentModel> comments) {
		try {
			for (int i = 0; i < commentsJsonArray.length(); i++) {
				CommentModel comment = new CommentModel();
				buildCommentFromJson(commentsJsonArray.getJSONObject(i),
						comment);
				if (comment != null) {
					comments.add(comment);
				}
			}
		} catch (JSONException e) {
			// skip bad json, this should never happen
		}
	}

	
	private static void buildCommentFromJson(JSONObject json,
			CommentModel comment) {
		SimpleUserModel owner = new SimpleUserModel();
		
		try {
			comment.setId(json.getString(Const.ID));
			comment.setComment(json.getString(Const.COMMENT));
			comment.setSource(json.getString(Const.SOURCE));
			comment.setLikes(json.getInt(Const.LIKES));
			comment.setLiked(json.getBoolean(Const.LIKED));
			comment.setOwner(json.getBoolean(Const.IS_OWNER));
			comment.setCreationDate(json.getInt(Const.CREATION_DATE));
			
			UserJsonParser.buildSimpleUserFromJson(
					new JSONObject(json.getString(Const.OWNER)), owner);
			comment.setOwner(owner);
			
		} catch (JSONException e) {
			comment = null;
		}
	}

	public static void setError(CommentsModel model, JSONObject json,
			boolean isException) {
		model.setSuccess(false);

		if (isException) {
			model.setError(Const.JSON_PARSING_EXCEPTION);
		} else {
			try {
				model.setError(json.getJSONObject(Const.ERROR).getString(
						Const.ERROR_MESSAGE));
			} catch (JSONException e) {
				model.setError(Const.JSON_PARSING_EXCEPTION);
			}
		}
	}
}
