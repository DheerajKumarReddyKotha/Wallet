package com.project.wallet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import com.google.common.base.Splitter;
import com.project.wallet.model.Access;

public class AccessLogReader {

	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	public int readAccessLog(Path path){
		List<Access> inputData = new ArrayList<>();
		JDBC jdbc = new JDBC();
		try (Stream<String> stream = Files.lines(path.getFileName())) {

			stream.forEach(line -> {
				List<String> l = Splitter.on("|").splitToList(line);
				Access access = new Access();
				
				access.setStartDate(getEpoch(l.get(0)));//2017-01-01 00:00:11.763
				access.setIp(l.get(1));
				access.setRequest(l.get(2));
				access.setStatus(Integer.parseInt(l.get(3)));
				access.setUserAgent(l.get(4));
				
				inputData.add(access);
				System.out.println("Access "+access);
			});

			jdbc.insertData(inputData);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inputData.size();
	}

	public Long getEpoch(String dateStr) {

		Date date = null;
		try {
		date = df.parse(dateStr);
		
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	    return date.getTime();

	}
}
