package com.EnvironmentalSensorApp.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.EnvironmentalSensorApp.domain.SensorThreshold;
import com.EnvironmentalSensorApp.dto.SensorThresholdDTO;
import com.EnvironmentalSensorApp.dto.SensorThresholdSearchDTO;
import com.EnvironmentalSensorApp.dto.SensorThresholdPageDTO;
import com.EnvironmentalSensorApp.dto.SensorThresholdConvertCriteriaDTO;
import com.EnvironmentalSensorApp.service.GenericService;
import com.EnvironmentalSensorApp.dto.common.RequestDTO;
import com.EnvironmentalSensorApp.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SensorThresholdService extends GenericService<SensorThreshold, Integer> {

	List<SensorThreshold> findAll();

	ResultDTO addSensorThreshold(SensorThresholdDTO sensorThresholdDTO, RequestDTO requestDTO);

	ResultDTO updateSensorThreshold(SensorThresholdDTO sensorThresholdDTO, RequestDTO requestDTO);

    Page<SensorThreshold> getAllSensorThresholds(Pageable pageable);

    Page<SensorThreshold> getAllSensorThresholds(Specification<SensorThreshold> spec, Pageable pageable);

	ResponseEntity<SensorThresholdPageDTO> getSensorThresholds(SensorThresholdSearchDTO sensorThresholdSearchDTO);
	
	List<SensorThresholdDTO> convertSensorThresholdsToSensorThresholdDTOs(List<SensorThreshold> sensorThresholds, SensorThresholdConvertCriteriaDTO convertCriteria);

	SensorThresholdDTO getSensorThresholdDTOById(Integer sensorThresholdId);



	
}
