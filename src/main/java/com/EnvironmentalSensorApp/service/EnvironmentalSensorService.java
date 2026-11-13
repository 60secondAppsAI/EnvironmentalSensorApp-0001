package com.EnvironmentalSensorApp.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.EnvironmentalSensorApp.domain.EnvironmentalSensor;
import com.EnvironmentalSensorApp.dto.EnvironmentalSensorDTO;
import com.EnvironmentalSensorApp.dto.EnvironmentalSensorSearchDTO;
import com.EnvironmentalSensorApp.dto.EnvironmentalSensorPageDTO;
import com.EnvironmentalSensorApp.dto.EnvironmentalSensorConvertCriteriaDTO;
import com.EnvironmentalSensorApp.service.GenericService;
import com.EnvironmentalSensorApp.dto.common.RequestDTO;
import com.EnvironmentalSensorApp.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface EnvironmentalSensorService extends GenericService<EnvironmentalSensor, Integer> {

	List<EnvironmentalSensor> findAll();

	ResultDTO addEnvironmentalSensor(EnvironmentalSensorDTO environmentalSensorDTO, RequestDTO requestDTO);

	ResultDTO updateEnvironmentalSensor(EnvironmentalSensorDTO environmentalSensorDTO, RequestDTO requestDTO);

    Page<EnvironmentalSensor> getAllEnvironmentalSensors(Pageable pageable);

    Page<EnvironmentalSensor> getAllEnvironmentalSensors(Specification<EnvironmentalSensor> spec, Pageable pageable);

	ResponseEntity<EnvironmentalSensorPageDTO> getEnvironmentalSensors(EnvironmentalSensorSearchDTO environmentalSensorSearchDTO);
	
	List<EnvironmentalSensorDTO> convertEnvironmentalSensorsToEnvironmentalSensorDTOs(List<EnvironmentalSensor> environmentalSensors, EnvironmentalSensorConvertCriteriaDTO convertCriteria);

	EnvironmentalSensorDTO getEnvironmentalSensorDTOById(Integer environmentalSensorId);



	
}
