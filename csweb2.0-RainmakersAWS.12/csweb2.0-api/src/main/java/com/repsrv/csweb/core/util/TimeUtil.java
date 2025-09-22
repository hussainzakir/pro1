package com.repsrv.csweb.core.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

	// convert a time string from military (1300) to standard time (01:00 PM)
		public static String formatFourDigitTime(String time) {

		try {
	
		Date newdate = new SimpleDateFormat("hhmm").parse(time);
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
		String formattedTime = sdf.format(newdate);
		
		return formattedTime;
	}
	
		catch (Exception e)
		{
			return "";
		}
	}
	
	// convert a time string from military (130042) to standard time (01:00 PM)
		public static String formatSixDigitTime(String time) {

		try {
		
		Date newdate = new SimpleDateFormat("hhmmss").parse(time);
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
		String formattedTime = sdf.format(newdate);
			
		return formattedTime;
	}
		
		catch (Exception e)
		{
			return "";
		}
	}
	//convert a as400 time stamp from '1319055746000' to 'yyyy-MM-dd-HH.mm.ss.SSSSSS' format 
	public static String formatDateToAs400timeStamp(String time){
		Timestamp tms = new Timestamp(Long.valueOf(time));
		Date date = new Date(tms.getTime());
		DateFormat metaModFormatter = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss.SSSSSS");
		return metaModFormatter.format(date);
	}	
}