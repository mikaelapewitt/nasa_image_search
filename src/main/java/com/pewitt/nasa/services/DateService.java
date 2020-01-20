package com.pewitt.nasa.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j //Using Lombok to inject Logger
@Getter 
public class DateService {
		
	private List<String> dates = new ArrayList<>();
	private final static String FILENAME = "/dates.txt";
	private String[] dateFormats = {"MM/dd/yy", "MMM d, yyyy", "MMM-d-yyyy"};
	
	@PostConstruct
	public void init() {
		try {
					
			InputStream inputStream = getClass().getResourceAsStream(FILENAME);
			Stream<String> stream = new BufferedReader(new InputStreamReader(inputStream)).lines();
			
			stream.forEach(date -> {
				try {
					log.trace(String.format("Raw date from file: %s", date));
					Date parsedDate = DateUtils.parseDateStrictly(date, dateFormats);
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					dates.add(formatter.format(parsedDate));
				} catch (ParseException e) {
					log.error(String.format("%s is an invalid date.", date), e);
				}
			});
			
					
		}
		catch(Exception e) {
			log.error(String.format("Error reading file %s", FILENAME), e);
		}
	}

}
