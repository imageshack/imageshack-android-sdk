package com.imageshack.model;

public class MiniImageModel implements ImageShackModel {

	protected int server;
	protected String filename;

	/**
	 * Constructor
	 */
	public MiniImageModel() {
		server = 0;
		filename = null;
	}

	/**
	 * @return the server
	 */
	public int getServer() {
		return server;
	}

	/**
	 * @param server
	 *            the server to set
	 */
	public void setServer(int server) {
		this.server = server;
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename
	 *            the filename to set
	 */
	public void setFilename(String filename) {
		if ("".equals(filename) || "null".equals(filename)) {
			this.filename = null;
		} else {
			this.filename = filename;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MiniImageModel [server=" + server + ", filename=" + filename
				+ "]";
	}

}
