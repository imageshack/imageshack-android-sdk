package com.imageshack.model;

import java.util.ArrayList;

public class ImageModel extends BasicImageModel {

	private String directLink;
	private String description;
	private ArrayList<String> tags;
	private boolean liked;
	private int views;
	private boolean moderated;
	private int commentsCount;
	private boolean commentsDisabled;
	private int filter;
	private ArrayList<SimpleImageModel> prev;
	private ArrayList<SimpleImageModel> next;

	private int processTime;
	private boolean success;
	private String error;

	/**
	 * Constructor
	 */
	public ImageModel() {
		super();
		directLink = null;
		description = null;
		liked = false;
		views = 0;
		moderated = false;
		commentsCount = 0;
		commentsDisabled = false;
		filter = 0;
		prev = new ArrayList<SimpleImageModel>();
		next = new ArrayList<SimpleImageModel>();
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
	 * @return the directLink
	 */
	public String getDirectLink() {
		return directLink;
	}

	/**
	 * @param directLink
	 *            the directLink to set
	 */
	public void setDirectLink(String directLink) {
		if ("".equals(directLink) || "null".equals(directLink)) {
			this.directLink = null;
		} else {
			this.directLink = directLink;
		}
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
			this.description = null;
		} else {
			this.description = description;
		}
	}

	/**
	 * @return the tags
	 */
	public ArrayList<String> getTags() {
		return tags;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

	/**
	 * @return the liked
	 */
	public boolean isLiked() {
		return liked;
	}

	/**
	 * @param liked
	 *            the liked to set
	 */
	public void setLiked(boolean liked) {
		this.liked = liked;
	}

	/**
	 * @return the views
	 */
	public int getViews() {
		return views;
	}

	/**
	 * @param views
	 *            the views to set
	 */
	public void setViews(int views) {
		this.views = views;
	}

	/**
	 * @return the moderated
	 */
	public boolean isModerated() {
		return moderated;
	}

	/**
	 * @param moderated
	 *            the moderated to set
	 */
	public void setModerated(boolean moderated) {
		this.moderated = moderated;
	}

	/**
	 * @return the commentsCount
	 */
	public int getCommentsCount() {
		return commentsCount;
	}

	/**
	 * @param commentsCount
	 *            the commentsCount to set
	 */
	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}

	/**
	 * @return the commentsDisabled
	 */
	public boolean isCommentsDisabled() {
		return commentsDisabled;
	}

	/**
	 * @param commentsDisabled
	 *            the commentsDisabled to set
	 */
	public void setCommentsDisabled(boolean commentsDisabled) {
		this.commentsDisabled = commentsDisabled;
	}

	/**
	 * @return the filter
	 */
	public int getFilter() {
		return filter;
	}

	/**
	 * @param filter
	 *            the filter to set
	 */
	public void setFilter(int filter) {
		this.filter = filter;
	}

	/**
	 * @return the prev
	 */
	public ArrayList<SimpleImageModel> getPrev() {
		return prev;
	}

	/**
	 * @param prev
	 *            the prev to set
	 */
	public void setPrev(ArrayList<SimpleImageModel> prev) {
		this.prev = prev;
	}

	/**
	 * @return the next
	 */
	public ArrayList<SimpleImageModel> getNext() {
		return next;
	}

	/**
	 * @param next
	 *            the next to set
	 */
	public void setNext(ArrayList<SimpleImageModel> next) {
		this.next = next;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ImageModel [processTime=" + processTime + ", success="
				+ success + ", error=" + error + ", directLink=" + directLink
				+ ", id=" + id + ", server=" + server + ", bucket=" + bucket
				+ ", filename=" + filename + ", width=" + width + ", height="
				+ height + "filesize=" + filesize + ", likes=" + likes
				+ ", liked=" + liked + ", creationDate=" + creationDate
				+ ", isPublic=" + isPublic + ", originalFilename="
				+ originalFilename + ", title=" + title + ", views=" + views
				+ ", moderated=" + moderated + ", isOwner=" + isOwner
				+ ", owner=" + owner + ", album=" + album + ", description="
				+ description + ", tags=" + tags + ", commentsCount="
				+ commentsCount + ", commentsDisabled=" + commentsDisabled
				+ ", filter=" + filter + ", prev=" + prev + ", next=" + next
				+ "]";
	}

}
