package com.vhselection.util;

import java.util.Random;

import com.vhselection.model.Url;

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
	 * @return <Objest> url  the url shortened
	 */
	public Url shortenURL(String longURL) {	
		longURL = cleanURL(longURL);
		String shortUrl = getKey(longURL);
		Url url = new Url(shortUrl,longURL);
		return url;
	}//shortenURL	
	
	private String cleanURL(String longUrl) {
		
		if(longUrl.length()>9) {
		
			if (longUrl.substring(0, 7).equals("http://"))
				longUrl = longUrl.substring(7);
	
			if (longUrl.substring(0, 8).equals("https://"))
				longUrl = longUrl.substring(8);
	
			if (longUrl.charAt(longUrl.length() - 1) == '/')
				longUrl = longUrl.substring(0, longUrl.length() - 1);
		}
		return longUrl;
	}

	 
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