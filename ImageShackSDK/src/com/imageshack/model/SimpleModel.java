package com.imageshack.model;

public class SimpleModel implements ImageShackModel {
	
	protected boolean isSuccess;
	protected String error;
	protected int processTime;
	
	public SimpleModel() {
		isSuccess = true;
		error = null;
		processTime = 0;
	}

	/**
	 * @return the isSuccess
	 */
	public boolean isSuccess() {
		return isSuccess;
	}

	/**
	 * @param isSuccess the isSuccess to set
	 */
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SimpleModel [isSuccess=" + isSuccess + ", error=" + error
				+ ", processTime=" + processTime + "]";
	}
	
}
