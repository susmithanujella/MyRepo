package com.centime.CentimeDemo.swagger.controller;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController	
public class CentimeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String firstSeviceGet() {
		return "Up";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String firstSevicePost() {
		try {
			
			CloseableHttpClient httpclient = HttpClients.createDefault();
			StringBuilder str = new StringBuilder("");
            HttpGet request = new HttpGet("http://localhost:8090/hello");
            CloseableHttpResponse getResponse = httpclient.execute(request);
            HttpEntity responseEntity = getResponse.getEntity();
            if (responseEntity != null) 
            {
            	str.append(EntityUtils.toString(responseEntity));
            }
            
			HttpPost httpPost = new HttpPost("http://localhost:8090/Names");
			String json = "{\"Name\":\"John\",\"Surname\":\"Doe\"}";
		    httpPost.setEntity(new StringEntity(json));

		    CloseableHttpResponse postResponse = httpclient.execute(httpPost);
	        responseEntity = postResponse.getEntity();
	        if (responseEntity != null) 
            {
                str.append(" ").append(EntityUtils.toString(responseEntity));
            }
	        //return str.toString();
	        return "{ \n \"" + str.toString() + "\" \n}";

		} catch(Exception e) {
			System.out.println("Exception occured in firstSevicePost : " + e.getMessage());
		}
		return null;
	}
	
}
