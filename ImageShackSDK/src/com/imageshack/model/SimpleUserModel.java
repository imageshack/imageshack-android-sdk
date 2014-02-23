package com.imageshack.model;

public class SimpleUserModel implements ImageShackModel {

	protected String username;
	protected MiniImageModel avatar;

	/**
	 * Constructor
	 */
	public SimpleUserModel() {
		username = null;
		avatar = null;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		if ("".equals(username) || "null".equals(username)) {
			this.username = null;
		} else {
			this.username = username;
		}
	}

	/**
	 * @return the avatar
	 */
	public MiniImageModel getAvatar() {
		return avatar;
	}

	/**
	 * @param avatar
	 *            the avatar to set
	 */
	public void setAvatar(MiniImageModel avatar) {
		this.avatar = avatar;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SimpleUserModel [username=" + username + ", avatar=" + avatar
				+ "]";
	}

}
