package com.project.wallet;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AccessLogHandler {

	private AccessLogReader accessLogReader;
	
	public void handleAccessLog() {
		System.out.println("started");
		accessLogReader = new AccessLogReader();
		
		Path path = Paths.get("access.log");
		Long count = accessLogReader.readAccessLog(path);
		
	}
	
	public static void main(String[] args) {
		AccessLogHandler accessLogHandler = new AccessLogHandler();
		accessLogHandler.handleAccessLog();
	}
}
