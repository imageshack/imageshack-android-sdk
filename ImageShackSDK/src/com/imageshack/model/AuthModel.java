package com.imageshack.model;

public class AuthModel extends SimpleUserModel {

	private int processTime;
	private boolean success;
	private String error;

	private String authToken;
	private String email;
	private String membershipItemNumber;
	private long userId;
	private String membership;

	public AuthModel() {
		super();
		authToken = null;
		email = null;
		membership = null;
		membershipItemNumber = null;
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
	 * @return the authToken
	 */
	public String getAuthToken() {
		return authToken;
	}

	/**
	 * @param authToken
	 *            the authToken to set
	 */
	public void setAuthToken(String authToken) {
		if (authToken == null || "".equals(authToken)) {
			authToken = null;
			success = false;
			error = "Invalid authentication token";
		} else {
			this.authToken = authToken;
		}
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		if ("".equals(email) || "null".equals(email)) {
			this.email = null;
		} else {
			this.email = email;
		}
	}

	/**
	 * @return the membership
	 */
	public String getMembership() {
		return membership;
	}

	/**
	 * @param membership
	 *            the membership to set
	 */
	public void setMembership(String membership) {
		if ("".equals(membership) || "null".equals(membership)) {
			this.membership = null;
		} else {
			this.membership = membership;
		}
	}

	/**
	 * @return the membershipItemNumber
	 */
	public String getMembershipItemNumber() {
		return membershipItemNumber;
	}

	/**
	 * @param membershipItemNumber
	 *            the membershipItemNumber to set
	 */
	public void setMembershipItemNumber(String membershipItemNumber) {
		if ("".equals(membershipItemNumber)
				|| "null".equals(membershipItemNumber)) {
			this.membershipItemNumber = null;
		} else {
			this.membershipItemNumber = membershipItemNumber;
		}
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AuthModel [processTime=" + processTime + ", success=" + success
				+ ", error=" + error + ", authToken=" + authToken + ", email="
				+ email + ", userId=" + userId + ", username=" + username
				+ ", membership=" + membership + ", membershipItemNumber="
				+ membershipItemNumber + ", avatar=" + avatar + "]";
	}

}
