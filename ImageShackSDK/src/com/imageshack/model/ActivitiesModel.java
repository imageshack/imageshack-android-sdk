package com.imageshack.model;

import java.util.ArrayList;

public class ActivitiesModel implements ImageShackModel {

	private ArrayList<ActivityModel> activities;
	private int limit;
	private int offset;
	private int total;

	private int processTime;
	private boolean success;
	private String error;

	public ActivitiesModel() {
		activities = new ArrayList<ActivityModel>();
		processTime = 0;
		success = true;
		error = null;
	}

	/**
	 * @return the activities
	 */
	public ArrayList<ActivityModel> getActivities() {
		return activities;
	}

	/**
	 * @param activities
	 *            the activities to set
	 */
	public void setActivities(ArrayList<ActivityModel> activities) {
		this.activities = activities;
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
	 * @param offest
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ActivitiesModel [activities=" + activities + ", limit=" + limit
				+ ", offset=" + offset + ", total=" + total + ", processTime="
				+ processTime + ", success=" + success + ", error=" + error
				+ "]";
	}

}
