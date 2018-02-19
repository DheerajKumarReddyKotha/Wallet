package com.project.wallet;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AccessLogHandler {

	private AccessLogReader accessLogReader;
	
	public void handleAccessLog() {
		accessLogReader = new AccessLogReader();
		
		Path path = Paths.get("src/main/resources/access.log");
		Long count = accessLogReader.readAccessLog(path);
		
	}
}
