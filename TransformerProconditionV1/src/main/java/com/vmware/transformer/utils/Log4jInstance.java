package com.vmware.transformer.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Log4jInstance {
	private static Logger logger = null;

	public static Logger getLoggerInstance(){
		logger = Logger.getRootLogger();
//		String log4jXml = System.getProperty("user.dir") + "\\src\\main\\resources\\log4j.xml";
		String log4jXml = Config.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "/log4j.xml";
		DOMConfigurator.configure(log4jXml);
		return logger;
	}
}
