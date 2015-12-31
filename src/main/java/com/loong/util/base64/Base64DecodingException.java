package com.loong.util.base64;

import java.io.*;

public final class Base64DecodingException extends IOException {
	private char c;

	/**
	 * Construct an new exception.
	 *
	 * @param message
	 *            message later to be returned by a getMessage() call.
	 * @param c
	 *            character that caused this error.
	 *
	 */
	public Base64DecodingException(String message, char c) {
		super(message);
		this.c = c;
	}

	/**
	 * Get the character that caused this error.
	 *
	 * @return the character that caused this error.
	 *
	 */
	public char getChar() {
		return c;
	}
}
