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

import com.EnvironmentalSensorApp.domain.SensorAlert;
import com.EnvironmentalSensorApp.dto.SensorAlertDTO;
import com.EnvironmentalSensorApp.dto.SensorAlertSearchDTO;
import com.EnvironmentalSensorApp.dto.SensorAlertPageDTO;
import com.EnvironmentalSensorApp.service.SensorAlertService;
import com.EnvironmentalSensorApp.dto.common.RequestDTO;
import com.EnvironmentalSensorApp.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/sensorAlert")
@RestController
public class SensorAlertController {

	private final static Logger logger = LoggerFactory.getLogger(SensorAlertController.class);

	@Autowired
	SensorAlertService sensorAlertService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<SensorAlert> getAll() {

		List<SensorAlert> sensorAlerts = sensorAlertService.findAll();
		
		return sensorAlerts;	
	}

	@GetMapping(value = "/{sensorAlertId}")
	@ResponseBody
	public SensorAlertDTO getSensorAlert(@PathVariable Integer sensorAlertId) {
		
		return (sensorAlertService.getSensorAlertDTOById(sensorAlertId));
	}

 	@RequestMapping(value = "/addSensorAlert", method = RequestMethod.POST)
	public ResponseEntity<?> addSensorAlert(@RequestBody SensorAlertDTO sensorAlertDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = sensorAlertService.addSensorAlert(sensorAlertDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/sensorAlerts")
	public ResponseEntity<SensorAlertPageDTO> getSensorAlerts(SensorAlertSearchDTO sensorAlertSearchDTO) {
 
		return sensorAlertService.getSensorAlerts(sensorAlertSearchDTO);
	}	

	@RequestMapping(value = "/updateSensorAlert", method = RequestMethod.POST)
	public ResponseEntity<?> updateSensorAlert(@RequestBody SensorAlertDTO sensorAlertDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = sensorAlertService.updateSensorAlert(sensorAlertDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
