package com.retask.game.services;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.retask.game.message.response.CalendarDateRangeResponse;

@Service
public class ToolsService {

	/**
	 * returns an array of start and enddates for the app calendar
	 * 
	 * @param error
	 * @param error_description
	 * @param httpStatus
	 * @return
	 */

	public List<CalendarDateRangeResponse> getCalendarDateRange(int months) {

		List<CalendarDateRangeResponse> dateRangeResponses = new ArrayList<CalendarDateRangeResponse>();
		CalendarDateRangeResponse dateRangeResponse;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate startDate, endDate;

		startDate = LocalDate.now();
		startDate = startDate.withDayOfMonth(1);

		startDate = startDate.minusMonths(months);
		endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

		for (int i = 0; i < months; i++) {
			dateRangeResponse = new CalendarDateRangeResponse();

			dateRangeResponse.setBeginDate(startDate.format(formatter));
			dateRangeResponse.setEndDate(endDate.format(formatter));

			dateRangeResponses.add(dateRangeResponse);
			
			startDate = startDate.plusMonths(1);
			endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
		}

		startDate = LocalDate.now();
		startDate = startDate.withDayOfMonth(1);
		endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
		
		for (int i = 0; i < months+1; i++) {
			dateRangeResponse = new CalendarDateRangeResponse();

			dateRangeResponse.setBeginDate(startDate.format(formatter));
			dateRangeResponse.setEndDate(endDate.format(formatter));

			dateRangeResponses.add(dateRangeResponse);
			
			startDate = startDate.plusMonths(1);
			endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
		}
		
		return dateRangeResponses;
	}

	/**
	 * 
	 * @param error
	 * @param error_description
	 * @param httpStatus
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ResponseEntity<Object> setupReponseEntity(String error, String error_description, HttpStatus httpStatus) {

		JSONObject entity = new JSONObject();

		entity.put("error_discription", error_description);
		entity.put("error", error);

		return new ResponseEntity<Object>(entity, httpStatus);
	}

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public JSONObject makeJSONObject(String name, String value) {

		JSONObject entity = new JSONObject();

		entity.put(name, value);

		return entity;

	}

	public long daysBetweenDates(Date d1, Date d2) {

		// in milliseconds
		long diff = d2.getTime() - d1.getTime();

		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);

		System.out.print(diffDays + " days, ");
		System.out.print(diffHours + " hours, ");
		System.out.print(diffMinutes + " minutes, ");
		System.out.print(diffSeconds + " seconds.");

		return diffDays;
	}

}
