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

import com.EnvironmentalSensorApp.domain.EnvironmentalSensor;
import com.EnvironmentalSensorApp.dto.EnvironmentalSensorDTO;
import com.EnvironmentalSensorApp.dto.EnvironmentalSensorSearchDTO;
import com.EnvironmentalSensorApp.dto.EnvironmentalSensorPageDTO;
import com.EnvironmentalSensorApp.service.EnvironmentalSensorService;
import com.EnvironmentalSensorApp.dto.common.RequestDTO;
import com.EnvironmentalSensorApp.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/environmentalSensor")
@RestController
public class EnvironmentalSensorController {

	private final static Logger logger = LoggerFactory.getLogger(EnvironmentalSensorController.class);

	@Autowired
	EnvironmentalSensorService environmentalSensorService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<EnvironmentalSensor> getAll() {

		List<EnvironmentalSensor> environmentalSensors = environmentalSensorService.findAll();
		
		return environmentalSensors;	
	}

	@GetMapping(value = "/{environmentalSensorId}")
	@ResponseBody
	public EnvironmentalSensorDTO getEnvironmentalSensor(@PathVariable Integer environmentalSensorId) {
		
		return (environmentalSensorService.getEnvironmentalSensorDTOById(environmentalSensorId));
	}

 	@RequestMapping(value = "/addEnvironmentalSensor", method = RequestMethod.POST)
	public ResponseEntity<?> addEnvironmentalSensor(@RequestBody EnvironmentalSensorDTO environmentalSensorDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = environmentalSensorService.addEnvironmentalSensor(environmentalSensorDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/environmentalSensors")
	public ResponseEntity<EnvironmentalSensorPageDTO> getEnvironmentalSensors(EnvironmentalSensorSearchDTO environmentalSensorSearchDTO) {
 
		return environmentalSensorService.getEnvironmentalSensors(environmentalSensorSearchDTO);
	}	

	@RequestMapping(value = "/updateEnvironmentalSensor", method = RequestMethod.POST)
	public ResponseEntity<?> updateEnvironmentalSensor(@RequestBody EnvironmentalSensorDTO environmentalSensorDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = environmentalSensorService.updateEnvironmentalSensor(environmentalSensorDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
