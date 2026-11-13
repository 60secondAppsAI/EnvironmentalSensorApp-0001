package com.EnvironmentalSensorApp.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.EnvironmentalSensorApp.domain.SensorReading;
import com.EnvironmentalSensorApp.dto.SensorReadingDTO;
import com.EnvironmentalSensorApp.dto.SensorReadingSearchDTO;
import com.EnvironmentalSensorApp.dto.SensorReadingPageDTO;
import com.EnvironmentalSensorApp.dto.SensorReadingConvertCriteriaDTO;
import com.EnvironmentalSensorApp.service.GenericService;
import com.EnvironmentalSensorApp.dto.common.RequestDTO;
import com.EnvironmentalSensorApp.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SensorReadingService extends GenericService<SensorReading, Integer> {

	List<SensorReading> findAll();

	ResultDTO addSensorReading(SensorReadingDTO sensorReadingDTO, RequestDTO requestDTO);

	ResultDTO updateSensorReading(SensorReadingDTO sensorReadingDTO, RequestDTO requestDTO);

    Page<SensorReading> getAllSensorReadings(Pageable pageable);

    Page<SensorReading> getAllSensorReadings(Specification<SensorReading> spec, Pageable pageable);

	ResponseEntity<SensorReadingPageDTO> getSensorReadings(SensorReadingSearchDTO sensorReadingSearchDTO);
	
	List<SensorReadingDTO> convertSensorReadingsToSensorReadingDTOs(List<SensorReading> sensorReadings, SensorReadingConvertCriteriaDTO convertCriteria);

	SensorReadingDTO getSensorReadingDTOById(Integer sensorReadingId);



	
}
