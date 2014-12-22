package com.brndbot.client;

/** This class is used for exceptions relating to the client interface. */
public class ClientException extends Exception {

	/* To keep the compiler happy */
	private static final long serialVersionUID = 1L;

	public ClientException() {
	}

	public ClientException(String message) {
		super(message);
	}

	public ClientException(Throwable cause) {
		super(cause);
	}

	public ClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClientException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
