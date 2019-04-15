package com.retask.game.services;

import java.util.Date;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ToolsService {

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
