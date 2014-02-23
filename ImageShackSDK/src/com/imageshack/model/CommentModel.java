package com.imageshack.model;

public class CommentModel implements ImageShackModel {
	
	private String id; 
	private String comment;
	private String source;
	private boolean isOwner;
	private SimpleUserModel owner;
	private int likes;
	private boolean isLiked;
	private int creationDate;
	
	public CommentModel() {
		super();
		id = null;
		comment = null;
		source = null;
		isOwner = false;
		owner = null;
		likes = 0;
		isLiked = false;
		creationDate = 0;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the isOwner
	 */
	public boolean isOwner() {
		return isOwner;
	}

	/**
	 * @param isOwner the isOwner to set
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
	 * @param owner the owner to set
	 */
	public void setOwner(SimpleUserModel owner) {
		this.owner = owner;
	}

	/**
	 * @return the likes
	 */
	public int getLikes() {
		return likes;
	}

	/**
	 * @param likes the likes to set
	 */
	public void setLikes(int likes) {
		this.likes = likes;
	}

	/**
	 * @return the isLiked
	 */
	public boolean isLiked() {
		return isLiked;
	}

	/**
	 * @param isLiked the isLiked to set
	 */
	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}

	/**
	 * @return the creationDate
	 */
	public int getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(int creationDate) {
		this.creationDate = creationDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CommentModel [id=" + id + ", comment=" + comment + ", source="
				+ source + ", isOwner=" + isOwner + ", owner=" + owner
				+ ", likes=" + likes + ", isLiked=" + isLiked
				+ ", creationDate=" + creationDate + "]";
	}

}