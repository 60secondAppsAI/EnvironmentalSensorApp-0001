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
import com.EnvironmentalSensorApp.dao.SensorReadingDAO;
import com.EnvironmentalSensorApp.domain.SensorReading;
import com.EnvironmentalSensorApp.dto.SensorReadingDTO;
import com.EnvironmentalSensorApp.dto.SensorReadingSearchDTO;
import com.EnvironmentalSensorApp.dto.SensorReadingPageDTO;
import com.EnvironmentalSensorApp.dto.SensorReadingConvertCriteriaDTO;
import com.EnvironmentalSensorApp.dto.common.RequestDTO;
import com.EnvironmentalSensorApp.dto.common.ResultDTO;
import com.EnvironmentalSensorApp.service.SensorReadingService;
import com.EnvironmentalSensorApp.util.ControllerUtils;


@Service
public class SensorReadingServiceImpl extends GenericServiceImpl<SensorReading, Integer> implements SensorReadingService {

    private final static Logger logger = LoggerFactory.getLogger(SensorReadingServiceImpl.class);

	@Autowired
	SensorReadingDAO sensorReadingDao;

	

	@Override
	public GenericDAO<SensorReading, Integer> getDAO() {
		return (GenericDAO<SensorReading, Integer>) sensorReadingDao;
	}
	
	public List<SensorReading> findAll () {
		List<SensorReading> sensorReadings = sensorReadingDao.findAll();
		
		return sensorReadings;	
		
	}

	public ResultDTO addSensorReading(SensorReadingDTO sensorReadingDTO, RequestDTO requestDTO) {

		SensorReading sensorReading = new SensorReading();

		sensorReading.setSensorReadingId(sensorReadingDTO.getSensorReadingId());

		sensorReading.setSensorId(sensorReadingDTO.getSensorId());

		sensorReading.setValue(sensorReadingDTO.getValue());

		sensorReading.setUnit(sensorReadingDTO.getUnit());

		sensorReading.setRecordedAt(sensorReadingDTO.getRecordedAt());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		sensorReading = sensorReadingDao.save(sensorReading);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<SensorReading> getAllSensorReadings(Pageable pageable) {
		return sensorReadingDao.findAll(pageable);
	}

	public Page<SensorReading> getAllSensorReadings(Specification<SensorReading> spec, Pageable pageable) {
		return sensorReadingDao.findAll(spec, pageable);
	}

	public ResponseEntity<SensorReadingPageDTO> getSensorReadings(SensorReadingSearchDTO sensorReadingSearchDTO) {
	
			Integer sensorReadingId = sensorReadingSearchDTO.getSensorReadingId(); 
 			Integer sensorId = sensorReadingSearchDTO.getSensorId(); 
  			String unit = sensorReadingSearchDTO.getUnit(); 
 			Date recordedAt = sensorReadingSearchDTO.getRecordedAt();
 			String sortBy = sensorReadingSearchDTO.getSortBy();
			String sortOrder = sensorReadingSearchDTO.getSortOrder();
			String searchQuery = sensorReadingSearchDTO.getSearchQuery();
			Integer page = sensorReadingSearchDTO.getPage();
			Integer size = sensorReadingSearchDTO.getSize();

	        Specification<SensorReading> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, sensorReadingId, "sensorReadingId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, sensorId, "sensorId"); 
			
			
			spec = ControllerUtils.andIfNecessary(spec, unit, "unit"); 
			
			spec = ControllerUtils.andIfNecessary(spec, recordedAt, "recordedAt");
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("unit")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<SensorReading> sensorReadings = this.getAllSensorReadings(spec, pageable);
		
		//System.out.println(String.valueOf(sensorReadings.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(sensorReadings.getTotalPages()));
		
		List<SensorReading> sensorReadingsList = sensorReadings.getContent();
		
		SensorReadingConvertCriteriaDTO convertCriteria = new SensorReadingConvertCriteriaDTO();
		List<SensorReadingDTO> sensorReadingDTOs = this.convertSensorReadingsToSensorReadingDTOs(sensorReadingsList,convertCriteria);
		
		SensorReadingPageDTO sensorReadingPageDTO = new SensorReadingPageDTO();
		sensorReadingPageDTO.setSensorReadings(sensorReadingDTOs);
		sensorReadingPageDTO.setTotalElements(sensorReadings.getTotalElements());
		return ResponseEntity.ok(sensorReadingPageDTO);
	}

	public List<SensorReadingDTO> convertSensorReadingsToSensorReadingDTOs(List<SensorReading> sensorReadings, SensorReadingConvertCriteriaDTO convertCriteria) {
		
		List<SensorReadingDTO> sensorReadingDTOs = new ArrayList<SensorReadingDTO>();
		
		for (SensorReading sensorReading : sensorReadings) {
			sensorReadingDTOs.add(convertSensorReadingToSensorReadingDTO(sensorReading,convertCriteria));
		}
		
		return sensorReadingDTOs;

	}
	
	public SensorReadingDTO convertSensorReadingToSensorReadingDTO(SensorReading sensorReading, SensorReadingConvertCriteriaDTO convertCriteria) {
		
		SensorReadingDTO sensorReadingDTO = new SensorReadingDTO();

		sensorReadingDTO.setSensorReadingId(sensorReading.getSensorReadingId());

		sensorReadingDTO.setSensorId(sensorReading.getSensorId());

		sensorReadingDTO.setValue(sensorReading.getValue());

		sensorReadingDTO.setUnit(sensorReading.getUnit());

		sensorReadingDTO.setRecordedAt(sensorReading.getRecordedAt());
		
		return sensorReadingDTO;
	}

	public ResultDTO updateSensorReading(SensorReadingDTO sensorReadingDTO, RequestDTO requestDTO) {
		
		SensorReading sensorReading = sensorReadingDao.getById(sensorReadingDTO.getSensorReadingId());
		
		sensorReading.setSensorReadingId(ControllerUtils.setValue(sensorReading.getSensorReadingId(), sensorReadingDTO.getSensorReadingId()));
		
		sensorReading.setSensorId(ControllerUtils.setValue(sensorReading.getSensorId(), sensorReadingDTO.getSensorId()));
		
		sensorReading.setValue(ControllerUtils.setValue(sensorReading.getValue(), sensorReadingDTO.getValue()));
		
		sensorReading.setUnit(ControllerUtils.setValue(sensorReading.getUnit(), sensorReadingDTO.getUnit()));
		
		sensorReading.setRecordedAt(ControllerUtils.setValue(sensorReading.getRecordedAt(), sensorReadingDTO.getRecordedAt()));

        sensorReading = sensorReadingDao.save(sensorReading);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public SensorReadingDTO getSensorReadingDTOById(Integer sensorReadingId) {
	
		SensorReading sensorReading = sensorReadingDao.getById(sensorReadingId);
		
		SensorReadingConvertCriteriaDTO convertCriteria = new SensorReadingConvertCriteriaDTO();
		return(this.convertSensorReadingToSensorReadingDTO(sensorReading,convertCriteria));
	}

}
