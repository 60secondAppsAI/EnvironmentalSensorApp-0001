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

import com.EnvironmentalSensorApp.domain.SensorThreshold;
import com.EnvironmentalSensorApp.dto.SensorThresholdDTO;
import com.EnvironmentalSensorApp.dto.SensorThresholdSearchDTO;
import com.EnvironmentalSensorApp.dto.SensorThresholdPageDTO;
import com.EnvironmentalSensorApp.service.SensorThresholdService;
import com.EnvironmentalSensorApp.dto.common.RequestDTO;
import com.EnvironmentalSensorApp.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/sensorThreshold")
@RestController
public class SensorThresholdController {

	private final static Logger logger = LoggerFactory.getLogger(SensorThresholdController.class);

	@Autowired
	SensorThresholdService sensorThresholdService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<SensorThreshold> getAll() {

		List<SensorThreshold> sensorThresholds = sensorThresholdService.findAll();
		
		return sensorThresholds;	
	}

	@GetMapping(value = "/{sensorThresholdId}")
	@ResponseBody
	public SensorThresholdDTO getSensorThreshold(@PathVariable Integer sensorThresholdId) {
		
		return (sensorThresholdService.getSensorThresholdDTOById(sensorThresholdId));
	}

 	@RequestMapping(value = "/addSensorThreshold", method = RequestMethod.POST)
	public ResponseEntity<?> addSensorThreshold(@RequestBody SensorThresholdDTO sensorThresholdDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = sensorThresholdService.addSensorThreshold(sensorThresholdDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/sensorThresholds")
	public ResponseEntity<SensorThresholdPageDTO> getSensorThresholds(SensorThresholdSearchDTO sensorThresholdSearchDTO) {
 
		return sensorThresholdService.getSensorThresholds(sensorThresholdSearchDTO);
	}	

	@RequestMapping(value = "/updateSensorThreshold", method = RequestMethod.POST)
	public ResponseEntity<?> updateSensorThreshold(@RequestBody SensorThresholdDTO sensorThresholdDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = sensorThresholdService.updateSensorThreshold(sensorThresholdDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
