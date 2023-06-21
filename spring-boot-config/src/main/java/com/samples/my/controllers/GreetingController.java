package com.samples.my.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samples.my.models.DBSettings;

@RestController
public class GreetingController {

	// Pick the value from the property "my.greeting" from application.properties file
	@Value("${my.greeting: default value}")
	private String greetingMessage;

	@Value("${my.list.values}")
	private List<String> listValues;

	@Value("#{${dbValues}}")
	private Map<String, String> dbValues;

	@Value("Hello Prem")
	private String staticMessage;

	@Autowired
	private DBSettings dbSettings;
	
	@Autowired
	private Environment environment;

	@GetMapping("/greeting")
	public String greeting() {
		return this.greetingMessage + " " + staticMessage + " " + listValues + " " + dbValues 
				+ " " + dbSettings.getConnection()
				+ " " + dbSettings.getHost() 
				+ " " + dbSettings.getPort();
	}
	
	
	@GetMapping("/envdetails")
	public String envDetails() {
		return environment.toString();
	}
}