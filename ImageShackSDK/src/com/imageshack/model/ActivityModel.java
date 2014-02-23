package com.imageshack.model;

public class ActivityModel extends SimpleAlbumModel {

	private SimpleUserModel actor;
	private int actionType;
	private ImageShackModel object;
	private int objectType;
	private int date;
	private String text;
	private boolean viewed;

	public ActivityModel() {
		actor = null;
		actionType = 0;
		object = null;
		objectType = 0;
		date = 0;
		text = null;
		viewed = false;
	}

	/**
	 * @return the actor
	 */
	public SimpleUserModel getActor() {
		return actor;
	}

	/**
	 * @param actor
	 *            the actor to set
	 */
	public void setActor(SimpleUserModel actor) {
		this.actor = actor;
	}

	/**
	 * @return the actionType
	 */
	public int getActionType() {
		return actionType;
	}

	/**
	 * @param actionType
	 *            the actionType to set
	 */
	public void setActionType(int actionType) {
		this.actionType = actionType;
	}

	/**
	 * @return the object
	 */
	public ImageShackModel getObject() {
		return object;
	}

	/**
	 * @param object
	 *            the object to set
	 */
	public void setObject(ImageShackModel object) {
		this.object = object;
	}

	/**
	 * @return the objectType
	 */
	public int getObjectType() {
		return objectType;
	}

	/**
	 * @param objectType
	 *            the objectType to set
	 */
	public void setObjectType(int objectType) {
		this.objectType = objectType;
	}

	/**
	 * @return the date
	 */
	public int getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(int date) {
		this.date = date;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the viewed
	 */
	public boolean isViewed() {
		return viewed;
	}

	/**
	 * @param viewed
	 *            the viewed to set
	 */
	public void setViewed(boolean viewed) {
		this.viewed = viewed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ActivityModel [actor=" + actor + ", actionType=" + actionType
				+ ", object=" + object + ", objectType=" + objectType
				+ ", date=" + date + ", text=" + text + ", viewed=" + viewed
				+ "]";
	}

}
