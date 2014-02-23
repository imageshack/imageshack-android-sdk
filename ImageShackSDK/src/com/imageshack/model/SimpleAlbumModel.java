package com.imageshack.model;

public class SimpleAlbumModel implements ImageShackModel {

	protected String id;
	protected String title;
	protected boolean isPublic;

	/**
	 * Constructor
	 */
	public SimpleAlbumModel() {
		id = null;
		title = null;
		isPublic = true;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		if ("".equals(title) || "null".equals(title)) {
			this.title = null;
		} else {
			this.title = title;
		}
	}

	/**
	 * @return the isPublic
	 */
	public boolean isPublic() {
		return isPublic;
	}

	/**
	 * @param isPublic
	 *            the isPublic to set
	 */
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SimpleAlbumModel [id=" + id + ", title=" + title
				+ ", isPublic=" + isPublic + "]";
	}

}
