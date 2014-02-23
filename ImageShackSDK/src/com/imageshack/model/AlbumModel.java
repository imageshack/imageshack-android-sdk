package com.imageshack.model;

import java.util.ArrayList;

public class AlbumModel extends SimpleAlbumModel {

	// private String description;
	private int creationDate;
	private boolean isOwner;
	private SimpleUserModel owner;
	private ArrayList<BasicImageModel> images;

	private int processTime;
	private boolean success;
	private String error;

	public AlbumModel() {
		creationDate = 0;
		isOwner = false;
		owner = null;
		images = new ArrayList<BasicImageModel>();
		processTime = 0;
		success = true;
		error = null;
	}

	/**
	 * @return the processTime
	 */
	public int getProcessTime() {
		return processTime;
	}

	/**
	 * @param processTime
	 *            the processTime to set
	 */
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
	 * @return the owner
	 */
	public SimpleUserModel getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(SimpleUserModel owner) {
		this.owner = owner;
	}

	/**
	 * @return the images
	 */
	public ArrayList<BasicImageModel> getImages() {
		return images;
	}

	/**
	 * @param images
	 *            the images to set
	 */
	public void setImages(ArrayList<BasicImageModel> images) {
		this.images = images;
	}

	/**
	 * Add an image to album images.
	 * 
	 * @param image
	 *            the image to add
	 */
	public void add(BasicImageModel image) {
		images.add(image);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AlbumModel [processTime=" + processTime + ", success="
				+ success + ", error=" + error + ", id=" + id + ", title="
				+ title + ", isPublic=" + isPublic + ", creationDate="
				+ creationDate + ", isOwner=" + isOwner + ", owner=" + owner
				+ ", images=" + images + "]";
	}

}
