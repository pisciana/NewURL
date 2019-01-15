package com.vhselection.model;

/**
 * Class Url
 * 
 * @author ana claudia costa
 *
 */
public class Url {
	private int urlId;
	private String urlShort;
	private String urlLong;
 
    /**
     * Constructor
     */
	public Url() {
    }
    
    /**
     * Constructor with parameter
     * 
     * @param urlShort
     * @param urlLong
     */
    public Url( String urlShort, String urlLong) {
    	this.urlShort = urlShort;
    	this.urlLong = urlLong;
    }

	/**
	 * @return the urlId
	 */
	public int getUrlId() {
		return urlId;
	}

	/**
	 * @param urlId the urlId to set
	 */
	public void setUrlId(int urlId) {
		this.urlId = urlId;
	}

	/**
	 * @return the urlShort
	 */
	public String getUrlShort() {
		return urlShort;
	}

	/**
	 * @param urlShort the urlShort to set
	 */
	public void setUrlShort(String urlShort) {
		this.urlShort = urlShort;
	}

	/**
	 * @return the urlLong
	 */
	public String getUrlLong() {
		return urlLong;
	}

	/**
	 * @param urlLong the urlLong to set
	 */
	public void setUrlLong(String urlLong) {
		this.urlLong = urlLong;
	}

}
