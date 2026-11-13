package com.EnvironmentalSensorApp.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.ZoneId;


import com.EnvironmentalSensorApp.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.EnvironmentalSensorApp.domain.SensorReading;
import com.EnvironmentalSensorApp.dto.SensorReadingDTO;
import com.EnvironmentalSensorApp.dto.SensorReadingSearchDTO;
import com.EnvironmentalSensorApp.dto.SensorReadingPageDTO;
import com.EnvironmentalSensorApp.service.SensorReadingService;
import com.EnvironmentalSensorApp.dto.common.RequestDTO;
import com.EnvironmentalSensorApp.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/sensorReading")
@RestController
public class SensorReadingController {

	private final static Logger logger = LoggerFactory.getLogger(SensorReadingController.class);

	@Autowired
	SensorReadingService sensorReadingService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<SensorReading> getAll() {

		List<SensorReading> sensorReadings = sensorReadingService.findAll();
		
		return sensorReadings;	
	}

	@GetMapping(value = "/{sensorReadingId}")
	@ResponseBody
	public SensorReadingDTO getSensorReading(@PathVariable Integer sensorReadingId) {
		
		return (sensorReadingService.getSensorReadingDTOById(sensorReadingId));
	}

 	@RequestMapping(value = "/addSensorReading", method = RequestMethod.POST)
	public ResponseEntity<?> addSensorReading(@RequestBody SensorReadingDTO sensorReadingDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = sensorReadingService.addSensorReading(sensorReadingDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/sensorReadings")
	public ResponseEntity<SensorReadingPageDTO> getSensorReadings(SensorReadingSearchDTO sensorReadingSearchDTO) {
 
		return sensorReadingService.getSensorReadings(sensorReadingSearchDTO);
	}	

	@RequestMapping(value = "/updateSensorReading", method = RequestMethod.POST)
	public ResponseEntity<?> updateSensorReading(@RequestBody SensorReadingDTO sensorReadingDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = sensorReadingService.updateSensorReading(sensorReadingDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
