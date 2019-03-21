package com.retask.game.services;

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
	
}
