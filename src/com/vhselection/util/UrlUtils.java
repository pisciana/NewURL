package com.vhselection.util;

import java.util.Random;

/**
 * Class with URL utilities
 * 
 * @author ana claudia Costa
 *
 */
public class UrlUtils {

	private char urlChars[];							
	private Random urlRand;
	private int keyLength; 

	/**
	 * Constructor
	 */
	public UrlUtils() {
		urlRand = new Random();
		keyLength = 8;
		urlChars = new char[62];
		for (int i = 0; i < 62; i++) {
			int j = 0;
			if (i < 10) {
				j = i + 48;
			} else if (i > 9 && i <= 35) {
				j = i + 55;
			} else {
				j = i + 61;
			}
			urlChars[i] = (char) j;
		}
	}//UrlUtils

	/**
	 * Method responsible for shortening the url
	 * 
	 * @param <String> longURL to be shortened
	 * @return <String> shortURL  the url shortened
	 */
	public String shortenURL(String longURL) {		
		return getKey(longURL);
	}//shortenURL	
	 
	/**
	 * Method responsible for generate key
	 * 
	 * @param longURL
	 * @return <String> key the generated key
	 */
	private String getKey(String longURL) {
		String key;
		key = generateKey();
		return key;
	}//getKey
	
	/**
	 * 
	 * @return <String> key
	 */
	private String generateKey() {
		String key = "";
		key = "";
		for (int i = 0; i <= keyLength; i++) {
			key += urlChars[urlRand.nextInt(62)];
		}		
		return key;
	}
	 
}