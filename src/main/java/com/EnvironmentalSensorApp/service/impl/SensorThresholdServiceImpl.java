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
import com.EnvironmentalSensorApp.dao.SensorThresholdDAO;
import com.EnvironmentalSensorApp.domain.SensorThreshold;
import com.EnvironmentalSensorApp.dto.SensorThresholdDTO;
import com.EnvironmentalSensorApp.dto.SensorThresholdSearchDTO;
import com.EnvironmentalSensorApp.dto.SensorThresholdPageDTO;
import com.EnvironmentalSensorApp.dto.SensorThresholdConvertCriteriaDTO;
import com.EnvironmentalSensorApp.dto.common.RequestDTO;
import com.EnvironmentalSensorApp.dto.common.ResultDTO;
import com.EnvironmentalSensorApp.service.SensorThresholdService;
import com.EnvironmentalSensorApp.util.ControllerUtils;


@Service
public class SensorThresholdServiceImpl extends GenericServiceImpl<SensorThreshold, Integer> implements SensorThresholdService {

    private final static Logger logger = LoggerFactory.getLogger(SensorThresholdServiceImpl.class);

	@Autowired
	SensorThresholdDAO sensorThresholdDao;

	

	@Override
	public GenericDAO<SensorThreshold, Integer> getDAO() {
		return (GenericDAO<SensorThreshold, Integer>) sensorThresholdDao;
	}
	
	public List<SensorThreshold> findAll () {
		List<SensorThreshold> sensorThresholds = sensorThresholdDao.findAll();
		
		return sensorThresholds;	
		
	}

	public ResultDTO addSensorThreshold(SensorThresholdDTO sensorThresholdDTO, RequestDTO requestDTO) {

		SensorThreshold sensorThreshold = new SensorThreshold();

		sensorThreshold.setSensorThresholdId(sensorThresholdDTO.getSensorThresholdId());

		sensorThreshold.setSensorId(sensorThresholdDTO.getSensorId());

		sensorThreshold.setMinimumValue(sensorThresholdDTO.getMinimumValue());

		sensorThreshold.setMaximumValue(sensorThresholdDTO.getMaximumValue());

		sensorThreshold.setSeverity(sensorThresholdDTO.getSeverity());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		sensorThreshold = sensorThresholdDao.save(sensorThreshold);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<SensorThreshold> getAllSensorThresholds(Pageable pageable) {
		return sensorThresholdDao.findAll(pageable);
	}

	public Page<SensorThreshold> getAllSensorThresholds(Specification<SensorThreshold> spec, Pageable pageable) {
		return sensorThresholdDao.findAll(spec, pageable);
	}

	public ResponseEntity<SensorThresholdPageDTO> getSensorThresholds(SensorThresholdSearchDTO sensorThresholdSearchDTO) {
	
			Integer sensorThresholdId = sensorThresholdSearchDTO.getSensorThresholdId(); 
 			Integer sensorId = sensorThresholdSearchDTO.getSensorId(); 
   			String severity = sensorThresholdSearchDTO.getSeverity(); 
 			String sortBy = sensorThresholdSearchDTO.getSortBy();
			String sortOrder = sensorThresholdSearchDTO.getSortOrder();
			String searchQuery = sensorThresholdSearchDTO.getSearchQuery();
			Integer page = sensorThresholdSearchDTO.getPage();
			Integer size = sensorThresholdSearchDTO.getSize();

	        Specification<SensorThreshold> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, sensorThresholdId, "sensorThresholdId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, sensorId, "sensorId"); 
			
			
			
			spec = ControllerUtils.andIfNecessary(spec, severity, "severity"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("severity")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<SensorThreshold> sensorThresholds = this.getAllSensorThresholds(spec, pageable);
		
		//System.out.println(String.valueOf(sensorThresholds.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(sensorThresholds.getTotalPages()));
		
		List<SensorThreshold> sensorThresholdsList = sensorThresholds.getContent();
		
		SensorThresholdConvertCriteriaDTO convertCriteria = new SensorThresholdConvertCriteriaDTO();
		List<SensorThresholdDTO> sensorThresholdDTOs = this.convertSensorThresholdsToSensorThresholdDTOs(sensorThresholdsList,convertCriteria);
		
		SensorThresholdPageDTO sensorThresholdPageDTO = new SensorThresholdPageDTO();
		sensorThresholdPageDTO.setSensorThresholds(sensorThresholdDTOs);
		sensorThresholdPageDTO.setTotalElements(sensorThresholds.getTotalElements());
		return ResponseEntity.ok(sensorThresholdPageDTO);
	}

	public List<SensorThresholdDTO> convertSensorThresholdsToSensorThresholdDTOs(List<SensorThreshold> sensorThresholds, SensorThresholdConvertCriteriaDTO convertCriteria) {
		
		List<SensorThresholdDTO> sensorThresholdDTOs = new ArrayList<SensorThresholdDTO>();
		
		for (SensorThreshold sensorThreshold : sensorThresholds) {
			sensorThresholdDTOs.add(convertSensorThresholdToSensorThresholdDTO(sensorThreshold,convertCriteria));
		}
		
		return sensorThresholdDTOs;

	}
	
	public SensorThresholdDTO convertSensorThresholdToSensorThresholdDTO(SensorThreshold sensorThreshold, SensorThresholdConvertCriteriaDTO convertCriteria) {
		
		SensorThresholdDTO sensorThresholdDTO = new SensorThresholdDTO();

		sensorThresholdDTO.setSensorThresholdId(sensorThreshold.getSensorThresholdId());

		sensorThresholdDTO.setSensorId(sensorThreshold.getSensorId());

		sensorThresholdDTO.setMinimumValue(sensorThreshold.getMinimumValue());

		sensorThresholdDTO.setMaximumValue(sensorThreshold.getMaximumValue());

		sensorThresholdDTO.setSeverity(sensorThreshold.getSeverity());
		
		return sensorThresholdDTO;
	}

	public ResultDTO updateSensorThreshold(SensorThresholdDTO sensorThresholdDTO, RequestDTO requestDTO) {
		
		SensorThreshold sensorThreshold = sensorThresholdDao.getById(sensorThresholdDTO.getSensorThresholdId());
		
		sensorThreshold.setSensorThresholdId(ControllerUtils.setValue(sensorThreshold.getSensorThresholdId(), sensorThresholdDTO.getSensorThresholdId()));
		
		sensorThreshold.setSensorId(ControllerUtils.setValue(sensorThreshold.getSensorId(), sensorThresholdDTO.getSensorId()));
		
		sensorThreshold.setMinimumValue(ControllerUtils.setValue(sensorThreshold.getMinimumValue(), sensorThresholdDTO.getMinimumValue()));
		
		sensorThreshold.setMaximumValue(ControllerUtils.setValue(sensorThreshold.getMaximumValue(), sensorThresholdDTO.getMaximumValue()));
		
		sensorThreshold.setSeverity(ControllerUtils.setValue(sensorThreshold.getSeverity(), sensorThresholdDTO.getSeverity()));

        sensorThreshold = sensorThresholdDao.save(sensorThreshold);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public SensorThresholdDTO getSensorThresholdDTOById(Integer sensorThresholdId) {
	
		SensorThreshold sensorThreshold = sensorThresholdDao.getById(sensorThresholdId);
		
		SensorThresholdConvertCriteriaDTO convertCriteria = new SensorThresholdConvertCriteriaDTO();
		return(this.convertSensorThresholdToSensorThresholdDTO(sensorThreshold,convertCriteria));
	}

}
