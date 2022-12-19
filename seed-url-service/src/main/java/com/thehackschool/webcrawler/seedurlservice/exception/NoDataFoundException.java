package com.thehackschool.webcrawler.seedurlservice.exception;

public class NoDataFoundException extends RuntimeException {

	private static final long serialVersionUID = -5135068196874029337L;
	
	public NoDataFoundException(String msg) {
		super(msg);
	}
}
