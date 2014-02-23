package com.imageshack.model;

import java.util.ArrayList;

public class UserModel extends SimpleUserModel {

	private String membership;
	private String email;
	private boolean isOwner;
	private String description;
	private int creationDate;
	private String location;
	private String gender;
	private boolean isFollowing;
	private boolean isFollowingAllowed;
	private ArrayList<SimpleImageModel> latestImages;

	private int processTime;
	private boolean success;
	private String error;

	/**
	 * Constructor
	 */
	public UserModel() {
		super();
		membership = null;
		email = null;
		isOwner = false;
		description = null;
		creationDate = 0;
		location = null;
		gender = null;
		isFollowing = false;
		isFollowingAllowed = true;
		latestImages = new ArrayList<SimpleImageModel>();
		processTime = 0;
		success = true;
		error = null;
	}

	/**
	 * @return the membership
	 */
	public String getMembership() {
		return membership;
	}

	/**
	 * @param membership
	 *            the membership to set
	 */
	public void setMembership(String membership) {
		if ("".equals(membership) || "null".equals(membership)) {
			this.membership = null;
		} else {
			this.membership = membership;
		}
	}

	public int getProcessTime() {
		return processTime;
	}

	public void setProcessTime(int processTime) {
		this.processTime = processTime;
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		if ("".equals(email) || "null".equals(email)) {
			email = null;
		}
		this.email = email;
	}

	/**
	 * @return the isOwner
	 */
	public boolean isOwner() {
		return isOwner;
	}

	/**
	 * @param isOwner
	 *            the isOwner to set
	 */
	public void setOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		if ("".equals(description) || "null".equals(description)) {
			description = null;
		}
		this.description = description;
	}

	/**
	 * @return the creationDate
	 */
	public int getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(int creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		if ("".equals(location) || "null".equals(location)) {
			location = null;
		}
		this.location = location;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the isFollowing
	 */
	public boolean isFollowing() {
		return isFollowing;
	}

	/**
	 * @param isFollowing
	 *            the isFollowing to set
	 */
	public void setFollowing(boolean isFollowing) {
		this.isFollowing = isFollowing;
	}

	/**
	 * @return the isFolloingAllowed
	 */
	public boolean isFollowingAllowed() {
		return isFollowingAllowed;
	}

	/**
	 * @param isFolloingAllowed
	 *            the isFolloingAllowed to set
	 */
	public void setFollowingAllowed(boolean isFolloingAllowed) {
		this.isFollowingAllowed = isFolloingAllowed;
	}

	/**
	 * @return the latestImages
	 */
	public ArrayList<SimpleImageModel> getLatestImages() {
		return latestImages;
	}

	/**
	 * @param latestImages
	 *            the latestImages to set
	 */
	public void setLatestImages(ArrayList<SimpleImageModel> latestImages) {
		this.latestImages = latestImages;
	}

	/**
	 * @param image
	 *            the image to add
	 */
	public void add(SimpleImageModel image) {
		latestImages.add(image);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserModel [procesTime=" + processTime + ", success=" + success
				+ ", error=" + error + "username=" + username + "email="
				+ email + ", membership=" + membership + ", avatar=" + avatar
				+ ", isOwner=" + isOwner + ", description=" + description
				+ ", creationDate=" + creationDate + ", location=" + location
				+ ", gender=" + gender + ", isFollowing=" + isFollowing
				+ ", isFollowingAllowed=" + isFollowingAllowed
				+ ", latestImages=" + latestImages + "]";
	}

}