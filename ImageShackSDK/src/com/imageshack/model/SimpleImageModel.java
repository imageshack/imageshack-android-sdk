package com.imageshack.model;

import com.imageshack.constant.Const;

public class SimpleImageModel extends MiniImageModel {

	protected String id;
	protected int bucket;
	protected int width;
	protected int height;

	/**
	 * Constructor
	 */
	public SimpleImageModel() {
		bucket = 0;
		width = 0;
		height = 0;
		id = null;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		if ("".equals(id) || "null".equals(id)) {
			this.id = null;
		} else {
			this.id = id;
		}
	}

	/**
	 * Generates direct link
	 * 
	 * @return URL
	 */
	public String directLink() {
		StringBuilder url = new StringBuilder("http://imageshack.com/a/img");
		url.append(server).append("/").append(bucket).append("/")
				.append(filename);
		return url.toString();
	}

	/**
	 * Generate an Imagizer URL making the image fit into the specified box. The
	 * box is defined by width and height. If autoCrop is disable, the image
	 * will preserve the original aspect ration. Otherwise it will be cropped to
	 * fully fill the space.
	 * 
	 * @param width
	 *            the image width
	 * @param height
	 *            the image height
	 * @param autoCrop
	 *            enables auto-crop
	 * @return URL
	 */
	public String imagizer(int width, int height, boolean autoCrop) {
		StringBuilder url = new StringBuilder(Const.PROTOCOL + Const.IMAGIZER);

		url.append(width).append("x").append(height).append("q90").append("/");

		if (autoCrop) {
			url.append("c/");
		}

		url.append(this.server).append("/").append(this.filename);

		return url.toString();
	}

	/**
	 * Generate an Imagizer URL making the image fit into the specified box. See
	 * <code>imagizer(width, height, autoCrop)</code> for usage. This method
	 * allows to apply one of 21 available image filters.
	 * <code>filter = 21</code> will convert the source file to grayscale.
	 * 
	 * @param width
	 *            the image width
	 * @param height
	 *            the image height
	 * @param autoCrop
	 *            enables auto-crop
	 * @param quality
	 *            integer between 1 and 100
	 * @param filter
	 *            unique image filter
	 * @return URL String URL
	 */
	public String imagizer(int width, int height, boolean autoCrop,
			int quality, int filter) {
		StringBuilder url = new StringBuilder(Const.PROTOCOL + Const.IMAGIZER);

		url.append(width).append("x").append(height);

		if (quality > 0 && quality < 100) {
			url.append("q").append(quality);
		} else {
			url.append("q90");
		}

		url.append("f").append(filter).append("/");

		if (autoCrop) {
			url.append("c/");
		}

		url.append(this.server).append("/").append(this.filename);

		return url.toString();
	}

	/**
	 * @return the bucket
	 */
	public int getBucket() {
		return bucket;
	}

	/**
	 * @param bucket
	 *            the bucket to set
	 */
	public void setBucket(int bucket) {
		this.bucket = bucket;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SimpleImageModel [id=" + id + ", server=" + server
				+ ", bucket=" + bucket + ", filename=" + filename + ", width="
				+ width + ", height=" + height + "]";
	}

}
