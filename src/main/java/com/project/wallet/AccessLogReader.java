package com.project.wallet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.file.Path;

public class AccessLogReader {

	public Long readAccessLog(Path path) {
		try {
			FileInputStream filInputStream = new FileInputStream(path.toFile());
			BufferedReader br = new BufferedReader(new InputStreamReader(filInputStream));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
