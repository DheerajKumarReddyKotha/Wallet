package com.project.wallet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.google.common.base.Splitter;
import com.project.wallet.model.Access;

public class AccessLogReader {

	
	public Long readAccessLog(Path path){
		List<Access> inputData = new ArrayList<>();

		try (Stream<String> stream = Files.lines(path.getFileName())) {

			stream.forEach(line -> {
				List<String> l = Splitter.on("|").splitToList(line);
				Access access = new Access();
				access.setStartDate(l.get(0));
				access.setIp(l.get(1));
				access.setRequest(l.get(2));
				access.setStatus(Integer.parseInt(l.get(3)));
				access.setUserAgent(l.get(4));
				inputData.add(access);
				System.out.println("Access "+access);
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
