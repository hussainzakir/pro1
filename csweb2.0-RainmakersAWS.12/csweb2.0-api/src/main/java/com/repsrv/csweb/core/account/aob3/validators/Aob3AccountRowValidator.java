package com.repsrv.csweb.core.account.aob3.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.repsrv.csweb.core.model.account.aob3.Aob3AccountInfo;

public class Aob3AccountRowValidator {
    
	private static final String ACCT_OPEN_DATE = "Account Open Date can not be older than 90 days from today.";

	public static List<String> validateRow(Aob3AccountInfo row) {
		List<String> errors = new ArrayList<>();
		
		String accountStartDate = row.getAccountOpenDate();
		isValidDate(accountStartDate, errors);

        return errors;
    }

	 public static void isValidDate(String dateStr, List<String> errors) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return; 
        }
    
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.setLenient(false); // Prevents invalid dates like 20211332

        try {
            // Parse the input date string into a Date object
            Date inputDate = sdf.parse(dateStr);
            
            // Get the current date
            Date currentDate = new Date();
            
            // Create a Calendar instance and set it to 90 days ago
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            calendar.add(Calendar.DAY_OF_YEAR, -90);
            Date ninetyDaysAgo = calendar.getTime();

            // Check if the input date is older than 90 days
            if (inputDate.before(ninetyDaysAgo)) {
				errors.add(ACCT_OPEN_DATE);
			}
        } catch (ParseException e) {
            // If parsing fails, the date is invalid
            errors.add("Invalid Account Open Date format: " + dateStr);
        }
	}
}

