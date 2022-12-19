package com.thehackschool.webcrawler.seedurlservice.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<Object> handleNoDataFoundException(NoDataFoundException ex, WebRequest webRequest) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", ex.getMessage());
		return ResponseEntity.status(503).body(body);
	}

	@ExceptionHandler(RepositoryException.class)
	public ResponseEntity<Object> handleRepositoryException(RepositoryException ex, WebRequest webRequest) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", ex.getMessage());
		return ResponseEntity.status(502).body(body);
	}

}
