package com.lwh.common.exception;

import org.apache.log4j.Logger;

public class AcceptedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger
			.getLogger(AcceptedException.class);

	public static void info(Exception e) {
		logger.info(e.getMessage());
		e.printStackTrace();
	}

	public static void error(Exception e) {
		logger.info(e.getMessage());
		e.printStackTrace();
	}
}
