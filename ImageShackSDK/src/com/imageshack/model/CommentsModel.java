package com.imageshack.model;

import java.util.ArrayList;

public class CommentsModel implements ImageShackModel {
	
	private ArrayList<CommentModel> comments;
	private int limit;
	private String startOffset;
	private String endOffset;
	private int total;
	
	private int processTime;
	private boolean success;
	private String error;
	
	public CommentsModel() {
		comments = new ArrayList<CommentModel>();
		limit = 0;
		total = 0;
		startOffset = null;
		endOffset = null;
	}

	/**
	 * @return the comments
	 */
	public ArrayList<CommentModel> getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(ArrayList<CommentModel> comments) {
		this.comments = comments;
	}
	
	/**
	 * Adds a comment to the ArrayList
	 * 
	 * @param comment
	 */
	public void add(CommentModel comment) {
		this.comments.add(comment);
	}

	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * @param limit the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * @return the startOffset
	 */
	public String getStartOffset() {
		return startOffset;
	}

	/**
	 * @param startOffset the startOffset to set
	 */
	public void setStartOffset(String startOffset) {
		this.startOffset = startOffset;
	}

	/**
	 * @return the endOffset
	 */
	public String getEndOffset() {
		return endOffset;
	}

	/**
	 * @param endOffset the endOffset to set
	 */
	public void setEndOffset(String endOffset) {
		this.endOffset = endOffset;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
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
	 * @param success the success to set
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
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CommentsModel [comments=" + comments + ", limit=" + limit
				+ ", startOffset=" + startOffset + ", endOffset=" + endOffset
				+ ", total=" + total + ", processTime=" + processTime
				+ ", success=" + success + ", error=" + error + "]";
	}

}
