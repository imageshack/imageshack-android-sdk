package com.imageshack.model;

public class BasicImageModel extends SimpleImageModel {

	protected int filesize;
	protected int likes;
	protected int creationDate;
	protected boolean isPublic;
	protected String originalFilename;
	protected String title;
	protected boolean isOwner;
	protected SimpleUserModel owner;
	protected SimpleAlbumModel album;

	public BasicImageModel() {
		super();
		originalFilename = null;
		title = null;
		likes = 0;
		filesize = 0;
		creationDate = 0;
		isPublic = true;
		isOwner = false;
		owner = null;
		album = null;
	}

	/**
	 * @return the originalFilename
	 */
	public String getOriginalFilename() {
		return originalFilename;
	}

	/**
	 * @param originalFilename
	 *            the originalFilename to set
	 */
	public void setOriginalFilename(String originalFilename) {
		if ("".equals(originalFilename) || "null".equals(originalFilename)) {
			this.originalFilename = null;
		} else {
			this.originalFilename = originalFilename;
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
	 * @return the likes
	 */
	public int getLikes() {
		return likes;
	}

	/**
	 * @param likes
	 *            the likes to set
	 */
	public void setLikes(int likes) {
		this.likes = likes;
	}

	/**
	 * @return the filesize
	 */
	public int getFilesize() {
		return filesize;
	}

	/**
	 * @param filesize
	 *            the filesize to set
	 */
	public void setFilesize(int filesize) {
		this.filesize = filesize;
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
	 * @return the album
	 */
	public SimpleAlbumModel getAlbum() {
		return album;
	}

	/**
	 * @param album
	 *            the album to set
	 */
	public void setAlbum(SimpleAlbumModel album) {
		this.album = album;
	}
	
	@Override
	public String toString() {
		return "BasicImageModel [id=" + id + ", server=" + server + ", bucket="
				+ bucket + ", filename=" + filename + ", width=" + width
				+ ", height=" + height + ", filesize=" + filesize + ", likes="
				+ likes + ", creationDate=" + creationDate + ", isPublic="
				+ isPublic + ", originalFilename=" + originalFilename
				+ ", title=" + title + ", isOwner=" + isOwner + ", owner="
				+ owner + ", album=" + album + "]";
	}

}
