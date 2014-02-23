package com.imageshack.model;

import java.util.ArrayList;

/**
 * @author msmirnov
 * 
 */
public class ImagesModel implements ImageShackModel {

	private ArrayList<BasicImageModel> images;
	private int limit;
	private int offset;
	private int total;

	private int processTime;
	private boolean success;
	private String error;

	public ImagesModel() {
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
	 * @param processTime the processTime to set
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
	 * Adds an image to the ArrayList
	 * 
	 * @param image
	 */
	public void add(BasicImageModel image) {
		images.add(image);
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
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * @param limit
	 *            the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * @param offset
	 *            the offset to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ImagesModel [processTime=" + processTime + ", success="
				+ success + ", error=" + error + ", limit=" + limit
				+ ", offset=" + offset + ", total=" + total + ", images="
				+ images + "]";
	}
}