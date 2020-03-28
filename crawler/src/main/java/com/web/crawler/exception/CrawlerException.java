package com.web.crawler.exception;

import org.springframework.http.HttpStatus;

public class CrawlerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public HttpStatus getStatus() {
		return status;
	}

	private HttpStatus status;

	public CrawlerException(HttpStatus status) {
		super();
		this.status = status;
	}

	public CrawlerException(String message) {
		super(message);
	}

	public CrawlerException(String message, Throwable cause) {
		super(message, cause);
	}

	public CrawlerException(String message, HttpStatus httpStatus) {
		super(message);
		this.status = httpStatus;
	}
}
