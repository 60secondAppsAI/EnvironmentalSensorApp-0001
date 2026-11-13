package com.EnvironmentalSensorApp.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import com.EnvironmentalSensorApp.dao.GenericDAO;
import com.EnvironmentalSensorApp.service.GenericService;
import com.EnvironmentalSensorApp.service.impl.GenericServiceImpl;
import com.EnvironmentalSensorApp.dao.SensorAlertDAO;
import com.EnvironmentalSensorApp.domain.SensorAlert;
import com.EnvironmentalSensorApp.dto.SensorAlertDTO;
import com.EnvironmentalSensorApp.dto.SensorAlertSearchDTO;
import com.EnvironmentalSensorApp.dto.SensorAlertPageDTO;
import com.EnvironmentalSensorApp.dto.SensorAlertConvertCriteriaDTO;
import com.EnvironmentalSensorApp.dto.common.RequestDTO;
import com.EnvironmentalSensorApp.dto.common.ResultDTO;
import com.EnvironmentalSensorApp.service.SensorAlertService;
import com.EnvironmentalSensorApp.util.ControllerUtils;


@Service
public class SensorAlertServiceImpl extends GenericServiceImpl<SensorAlert, Integer> implements SensorAlertService {

    private final static Logger logger = LoggerFactory.getLogger(SensorAlertServiceImpl.class);

	@Autowired
	SensorAlertDAO sensorAlertDao;

	

	@Override
	public GenericDAO<SensorAlert, Integer> getDAO() {
		return (GenericDAO<SensorAlert, Integer>) sensorAlertDao;
	}
	
	public List<SensorAlert> findAll () {
		List<SensorAlert> sensorAlerts = sensorAlertDao.findAll();
		
		return sensorAlerts;	
		
	}

	public ResultDTO addSensorAlert(SensorAlertDTO sensorAlertDTO, RequestDTO requestDTO) {

		SensorAlert sensorAlert = new SensorAlert();

		sensorAlert.setSensorAlertId(sensorAlertDTO.getSensorAlertId());

		sensorAlert.setSensorId(sensorAlertDTO.getSensorId());

		sensorAlert.setMessage(sensorAlertDTO.getMessage());

		sensorAlert.setSeverity(sensorAlertDTO.getSeverity());

		sensorAlert.setCreatedAt(sensorAlertDTO.getCreatedAt());

		sensorAlert.setAcknowledged(sensorAlertDTO.getAcknowledged());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		sensorAlert = sensorAlertDao.save(sensorAlert);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<SensorAlert> getAllSensorAlerts(Pageable pageable) {
		return sensorAlertDao.findAll(pageable);
	}

	public Page<SensorAlert> getAllSensorAlerts(Specification<SensorAlert> spec, Pageable pageable) {
		return sensorAlertDao.findAll(spec, pageable);
	}

	public ResponseEntity<SensorAlertPageDTO> getSensorAlerts(SensorAlertSearchDTO sensorAlertSearchDTO) {
	
			Integer sensorAlertId = sensorAlertSearchDTO.getSensorAlertId(); 
 			Integer sensorId = sensorAlertSearchDTO.getSensorId(); 
 			String message = sensorAlertSearchDTO.getMessage(); 
 			String severity = sensorAlertSearchDTO.getSeverity(); 
 			Date createdAt = sensorAlertSearchDTO.getCreatedAt();
  			String sortBy = sensorAlertSearchDTO.getSortBy();
			String sortOrder = sensorAlertSearchDTO.getSortOrder();
			String searchQuery = sensorAlertSearchDTO.getSearchQuery();
			Integer page = sensorAlertSearchDTO.getPage();
			Integer size = sensorAlertSearchDTO.getSize();

	        Specification<SensorAlert> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, sensorAlertId, "sensorAlertId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, sensorId, "sensorId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, message, "message"); 
			
			spec = ControllerUtils.andIfNecessary(spec, severity, "severity"); 
			
			spec = ControllerUtils.andIfNecessary(spec, createdAt, "createdAt");
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("message")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("severity")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<SensorAlert> sensorAlerts = this.getAllSensorAlerts(spec, pageable);
		
		//System.out.println(String.valueOf(sensorAlerts.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(sensorAlerts.getTotalPages()));
		
		List<SensorAlert> sensorAlertsList = sensorAlerts.getContent();
		
		SensorAlertConvertCriteriaDTO convertCriteria = new SensorAlertConvertCriteriaDTO();
		List<SensorAlertDTO> sensorAlertDTOs = this.convertSensorAlertsToSensorAlertDTOs(sensorAlertsList,convertCriteria);
		
		SensorAlertPageDTO sensorAlertPageDTO = new SensorAlertPageDTO();
		sensorAlertPageDTO.setSensorAlerts(sensorAlertDTOs);
		sensorAlertPageDTO.setTotalElements(sensorAlerts.getTotalElements());
		return ResponseEntity.ok(sensorAlertPageDTO);
	}

	public List<SensorAlertDTO> convertSensorAlertsToSensorAlertDTOs(List<SensorAlert> sensorAlerts, SensorAlertConvertCriteriaDTO convertCriteria) {
		
		List<SensorAlertDTO> sensorAlertDTOs = new ArrayList<SensorAlertDTO>();
		
		for (SensorAlert sensorAlert : sensorAlerts) {
			sensorAlertDTOs.add(convertSensorAlertToSensorAlertDTO(sensorAlert,convertCriteria));
		}
		
		return sensorAlertDTOs;

	}
	
	public SensorAlertDTO convertSensorAlertToSensorAlertDTO(SensorAlert sensorAlert, SensorAlertConvertCriteriaDTO convertCriteria) {
		
		SensorAlertDTO sensorAlertDTO = new SensorAlertDTO();

		sensorAlertDTO.setSensorAlertId(sensorAlert.getSensorAlertId());

		sensorAlertDTO.setSensorId(sensorAlert.getSensorId());

		sensorAlertDTO.setMessage(sensorAlert.getMessage());

		sensorAlertDTO.setSeverity(sensorAlert.getSeverity());

		sensorAlertDTO.setCreatedAt(sensorAlert.getCreatedAt());

		sensorAlertDTO.setAcknowledged(sensorAlert.getAcknowledged());
		
		return sensorAlertDTO;
	}

	public ResultDTO updateSensorAlert(SensorAlertDTO sensorAlertDTO, RequestDTO requestDTO) {
		
		SensorAlert sensorAlert = sensorAlertDao.getById(sensorAlertDTO.getSensorAlertId());
		
		sensorAlert.setSensorAlertId(ControllerUtils.setValue(sensorAlert.getSensorAlertId(), sensorAlertDTO.getSensorAlertId()));
		
		sensorAlert.setSensorId(ControllerUtils.setValue(sensorAlert.getSensorId(), sensorAlertDTO.getSensorId()));
		
		sensorAlert.setMessage(ControllerUtils.setValue(sensorAlert.getMessage(), sensorAlertDTO.getMessage()));
		
		sensorAlert.setSeverity(ControllerUtils.setValue(sensorAlert.getSeverity(), sensorAlertDTO.getSeverity()));
		
		sensorAlert.setCreatedAt(ControllerUtils.setValue(sensorAlert.getCreatedAt(), sensorAlertDTO.getCreatedAt()));
		
		sensorAlert.setAcknowledged(ControllerUtils.setValue(sensorAlert.getAcknowledged(), sensorAlertDTO.getAcknowledged()));

        sensorAlert = sensorAlertDao.save(sensorAlert);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public SensorAlertDTO getSensorAlertDTOById(Integer sensorAlertId) {
	
		SensorAlert sensorAlert = sensorAlertDao.getById(sensorAlertId);
		
		SensorAlertConvertCriteriaDTO convertCriteria = new SensorAlertConvertCriteriaDTO();
		return(this.convertSensorAlertToSensorAlertDTO(sensorAlert,convertCriteria));
	}

}
