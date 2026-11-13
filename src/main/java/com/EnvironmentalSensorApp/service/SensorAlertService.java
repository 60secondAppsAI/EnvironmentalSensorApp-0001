package com.EnvironmentalSensorApp.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.EnvironmentalSensorApp.domain.SensorAlert;
import com.EnvironmentalSensorApp.dto.SensorAlertDTO;
import com.EnvironmentalSensorApp.dto.SensorAlertSearchDTO;
import com.EnvironmentalSensorApp.dto.SensorAlertPageDTO;
import com.EnvironmentalSensorApp.dto.SensorAlertConvertCriteriaDTO;
import com.EnvironmentalSensorApp.service.GenericService;
import com.EnvironmentalSensorApp.dto.common.RequestDTO;
import com.EnvironmentalSensorApp.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SensorAlertService extends GenericService<SensorAlert, Integer> {

	List<SensorAlert> findAll();

	ResultDTO addSensorAlert(SensorAlertDTO sensorAlertDTO, RequestDTO requestDTO);

	ResultDTO updateSensorAlert(SensorAlertDTO sensorAlertDTO, RequestDTO requestDTO);

    Page<SensorAlert> getAllSensorAlerts(Pageable pageable);

    Page<SensorAlert> getAllSensorAlerts(Specification<SensorAlert> spec, Pageable pageable);

	ResponseEntity<SensorAlertPageDTO> getSensorAlerts(SensorAlertSearchDTO sensorAlertSearchDTO);
	
	List<SensorAlertDTO> convertSensorAlertsToSensorAlertDTOs(List<SensorAlert> sensorAlerts, SensorAlertConvertCriteriaDTO convertCriteria);

	SensorAlertDTO getSensorAlertDTOById(Integer sensorAlertId);



	
}
