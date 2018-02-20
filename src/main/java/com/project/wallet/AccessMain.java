package com.project.wallet;

public class AccessMain {

	public static void main(String[] args) {
//			AccessLogHandler accessLogHandler = new AccessLogHandler();
//			accessLogHandler.handleAccessLog();
			AccessLogReader accessLog = new AccessLogReader();
			String date = args[0].replace("--startDate=", "")+" "+args[1];

			String duration = args[2].replace("--duration=", "");
			String threshold = args[3].replace("--threshold=", "");
				
			System.out.println(date);
			System.out.println(duration);
			System.out.println(threshold);
			
			Long start = accessLog.getEpoch(date);
			Long end = 0l;
			if(duration.equalsIgnoreCase("Daily")){
				end = start +86400L;
			}
			if(duration.equalsIgnoreCase("hourly")) {
				end = start + 3600000L;
			}
			System.out.println(start);
			SelectQuery selectQery = new SelectQuery();
			selectQery.selectData(start, end,threshold);
		}

}
