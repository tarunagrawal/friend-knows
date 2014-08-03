package com.system.you.review;

import org.slf4j.Logger;

public class LoggerUtils {
	
	public static void log(Logger log, Level level, String message){
		if(log !=null){
			switch (level) {
			case INFO:
				log.info(message);
				break;
			case WARN:
				log.warn(message);
				break;	
			case ERROR:
				log.warn(message);
				break;	
			}
		}
	} 
	public static enum Level{INFO, WARN, ERROR}
}
