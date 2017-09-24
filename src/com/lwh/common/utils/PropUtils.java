package com.lwh.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropUtils {
	private static Logger logger = Logger.getLogger(PropUtils.class);
	private static Properties properties = new Properties();
	static {
		try {
			InputStream in = PropUtils.class
					.getResourceAsStream("/lwh.properties");
			properties.load(in);
		} catch (IOException e) {
			logger.error(e);
		}
	}

	public static String read(String key) {
		if (null != properties.get(key)) {
			return properties.get(key).toString();
		}
		return "";
	}
}
