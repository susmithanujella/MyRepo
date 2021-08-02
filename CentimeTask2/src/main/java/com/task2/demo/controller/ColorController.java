package com.task2.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task2.demo.annotation.LogMethodParam;
import com.task2.demo.dao.ColorRepo;
import com.task2.demo.model.Color;
import com.task2.demo.service.ColorService;

@RestController
public class ColorController {

	private final static Logger logger = LoggerFactory.getLogger(ColorController.class);

	@Autowired
	ColorRepo repo;
	@Autowired
	ColorService service;

	@RequestMapping(value = "/branching", method = RequestMethod.GET)
	public JSONArray getBranchStructure() {
		JSONObject json = new JSONObject();
		List<Color> colors = repo.getColorsByParentid(0);
		JSONArray branchedArr = prepareJson(colors);
		System.out.println("Resultant branching : \n" + branchedArr);
		logger.info("Resultant branching : \n", branchedArr);
	    return branchedArr;
	}
	
	private JSONArray prepareJson(List<Color> baseColors) {
		JSONArray addChild = new JSONArray();
		for(Color color : baseColors) {
			JSONObject json = new JSONObject();
			json.put("Name", color.getName());
			List<Color> nextColors = repo.getColorsByParentid(color.getId());
			if (nextColors.size() > 0) {
	            JSONArray nextChilds = prepareJson(nextColors);
	            json.put("Sub Classes", nextChilds);
	        }
			addChild.add(json);
		}
		//System.out.println(addChild);
		return addChild;
	}
	
	@RequestMapping(value = "/colorObjectById", method = RequestMethod.GET)
	@LogMethodParam
	public Color getColorObjectById(@RequestParam(value = "id", required = true) int id) {
		Color c;
		try {
			c = repo.findById(id).get();
			return c;
		} catch(NoSuchElementException e) {
			logger.error("No object found with id :" + id);
		} catch(Exception e) {
			logger.error("Can't fetch color object with id :" + id);
		}
		return null;
	}
	
	@RequestMapping(value = "/allColorObjects", method = RequestMethod.GET)
	public List<Color> getAllColorObjects() {
		try {
			List<Color> coloredObjs = repo.findAll();
			return coloredObjs;
		} catch(Exception e) {
			logger.error("Unable to fetch all ColorObjects due to : ", e.getMessage(), e);
		}
		return new ArrayList<>();
		
	}
	
}
