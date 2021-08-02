package com.centime.CentimeDemo.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.centime.CentimeDemo.exception.RestApiException;

@RestController	
public class SecondaryController {

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ResponseEntity<String> secondService() {
		return ResponseEntity.ok("Hello");
	}
	
	@RequestMapping(value = "/Names", method = RequestMethod.POST)
	public String thirdService(@RequestBody String request) throws RestApiException {
		try {
			JSONObject json = new JSONObject(request);
			System.out.println("Request JSON : " + json);
			//return "{ \n \"" + json.getString("Name")+" "+json.getString("Surname") + "\" \n}";
			return json.getString("Name")+" "+json.getString("Surname");
		} catch(JSONException e) {
			return "Message : Please pass valid JSON Data";
		} catch(Exception e) {
			System.out.println("Exception occured in thirdService : " + e.getMessage());
		}
		return null;
	}
	
}
