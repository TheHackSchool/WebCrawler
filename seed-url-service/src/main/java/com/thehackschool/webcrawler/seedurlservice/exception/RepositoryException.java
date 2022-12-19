package com.thehackschool.webcrawler.seedurlservice.exception;

import lombok.RequiredArgsConstructor;

public class RepositoryException extends RuntimeException {
	private static final long serialVersionUID = 3227245003158115162L;
	
	public RepositoryException(String msg) {
		super(msg);
	}

}
