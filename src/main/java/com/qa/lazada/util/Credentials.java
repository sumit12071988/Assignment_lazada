package com.qa.lazada.util;

/**
 * 
 * @author Sumit
 *	used to GET and SET user credentials programatically.
 */
public class Credentials {

	String appUsername;
	String appPassword;
	
	public Credentials(String appUsername, String appPassword) {
		this.appUsername = appUsername;
		this.appPassword = appPassword;
	}
	
	
	//*********************************************************************//
	
	// GETTERS
	public String getAppUsername() {
		return appUsername;
	}
	
	// SETTERS -- Used at runtime to set new values to same method
	public void setAppUsername(String appUsername) {
		this.appUsername = appUsername;
	}
	
	
	//*********************************************************************//
	
	
	
	// GETTERS
	public String getAppPassword() {
		return appPassword;
	}
	
	// SETTERS -- Used at runtime to set new values to same method
	public void setAppPassword(String appPassword) {
		this.appPassword = appPassword;
	}
	
	
	
	
}
