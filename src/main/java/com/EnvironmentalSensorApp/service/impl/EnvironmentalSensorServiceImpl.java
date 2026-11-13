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
import com.EnvironmentalSensorApp.dao.EnvironmentalSensorDAO;
import com.EnvironmentalSensorApp.domain.EnvironmentalSensor;
import com.EnvironmentalSensorApp.dto.EnvironmentalSensorDTO;
import com.EnvironmentalSensorApp.dto.EnvironmentalSensorSearchDTO;
import com.EnvironmentalSensorApp.dto.EnvironmentalSensorPageDTO;
import com.EnvironmentalSensorApp.dto.EnvironmentalSensorConvertCriteriaDTO;
import com.EnvironmentalSensorApp.dto.common.RequestDTO;
import com.EnvironmentalSensorApp.dto.common.ResultDTO;
import com.EnvironmentalSensorApp.service.EnvironmentalSensorService;
import com.EnvironmentalSensorApp.util.ControllerUtils;


@Service
public class EnvironmentalSensorServiceImpl extends GenericServiceImpl<EnvironmentalSensor, Integer> implements EnvironmentalSensorService {

    private final static Logger logger = LoggerFactory.getLogger(EnvironmentalSensorServiceImpl.class);

	@Autowired
	EnvironmentalSensorDAO environmentalSensorDao;

	

	@Override
	public GenericDAO<EnvironmentalSensor, Integer> getDAO() {
		return (GenericDAO<EnvironmentalSensor, Integer>) environmentalSensorDao;
	}
	
	public List<EnvironmentalSensor> findAll () {
		List<EnvironmentalSensor> environmentalSensors = environmentalSensorDao.findAll();
		
		return environmentalSensors;	
		
	}

	public ResultDTO addEnvironmentalSensor(EnvironmentalSensorDTO environmentalSensorDTO, RequestDTO requestDTO) {

		EnvironmentalSensor environmentalSensor = new EnvironmentalSensor();

		environmentalSensor.setSensorId(environmentalSensorDTO.getSensorId());

		environmentalSensor.setName(environmentalSensorDTO.getName());

		environmentalSensor.setSensorType(environmentalSensorDTO.getSensorType());

		environmentalSensor.setLocationCode(environmentalSensorDTO.getLocationCode());

		environmentalSensor.setStatus(environmentalSensorDTO.getStatus());

		environmentalSensor.setEnvironmentalSensorId(environmentalSensorDTO.getEnvironmentalSensorId());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		environmentalSensor = environmentalSensorDao.save(environmentalSensor);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<EnvironmentalSensor> getAllEnvironmentalSensors(Pageable pageable) {
		return environmentalSensorDao.findAll(pageable);
	}

	public Page<EnvironmentalSensor> getAllEnvironmentalSensors(Specification<EnvironmentalSensor> spec, Pageable pageable) {
		return environmentalSensorDao.findAll(spec, pageable);
	}

	public ResponseEntity<EnvironmentalSensorPageDTO> getEnvironmentalSensors(EnvironmentalSensorSearchDTO environmentalSensorSearchDTO) {
	
			Integer sensorId = environmentalSensorSearchDTO.getSensorId(); 
 			String name = environmentalSensorSearchDTO.getName(); 
 			String sensorType = environmentalSensorSearchDTO.getSensorType(); 
 			String locationCode = environmentalSensorSearchDTO.getLocationCode(); 
 			String status = environmentalSensorSearchDTO.getStatus(); 
 			Integer environmentalSensorId = environmentalSensorSearchDTO.getEnvironmentalSensorId(); 
 			String sortBy = environmentalSensorSearchDTO.getSortBy();
			String sortOrder = environmentalSensorSearchDTO.getSortOrder();
			String searchQuery = environmentalSensorSearchDTO.getSearchQuery();
			Integer page = environmentalSensorSearchDTO.getPage();
			Integer size = environmentalSensorSearchDTO.getSize();

	        Specification<EnvironmentalSensor> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, sensorId, "sensorId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, sensorType, "sensorType"); 
			
			spec = ControllerUtils.andIfNecessary(spec, locationCode, "locationCode"); 
			
			spec = ControllerUtils.andIfNecessary(spec, status, "status"); 
			
			spec = ControllerUtils.andIfNecessary(spec, environmentalSensorId, "environmentalSensorId"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("sensorType")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("locationCode")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("status")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<EnvironmentalSensor> environmentalSensors = this.getAllEnvironmentalSensors(spec, pageable);
		
		//System.out.println(String.valueOf(environmentalSensors.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(environmentalSensors.getTotalPages()));
		
		List<EnvironmentalSensor> environmentalSensorsList = environmentalSensors.getContent();
		
		EnvironmentalSensorConvertCriteriaDTO convertCriteria = new EnvironmentalSensorConvertCriteriaDTO();
		List<EnvironmentalSensorDTO> environmentalSensorDTOs = this.convertEnvironmentalSensorsToEnvironmentalSensorDTOs(environmentalSensorsList,convertCriteria);
		
		EnvironmentalSensorPageDTO environmentalSensorPageDTO = new EnvironmentalSensorPageDTO();
		environmentalSensorPageDTO.setEnvironmentalSensors(environmentalSensorDTOs);
		environmentalSensorPageDTO.setTotalElements(environmentalSensors.getTotalElements());
		return ResponseEntity.ok(environmentalSensorPageDTO);
	}

	public List<EnvironmentalSensorDTO> convertEnvironmentalSensorsToEnvironmentalSensorDTOs(List<EnvironmentalSensor> environmentalSensors, EnvironmentalSensorConvertCriteriaDTO convertCriteria) {
		
		List<EnvironmentalSensorDTO> environmentalSensorDTOs = new ArrayList<EnvironmentalSensorDTO>();
		
		for (EnvironmentalSensor environmentalSensor : environmentalSensors) {
			environmentalSensorDTOs.add(convertEnvironmentalSensorToEnvironmentalSensorDTO(environmentalSensor,convertCriteria));
		}
		
		return environmentalSensorDTOs;

	}
	
	public EnvironmentalSensorDTO convertEnvironmentalSensorToEnvironmentalSensorDTO(EnvironmentalSensor environmentalSensor, EnvironmentalSensorConvertCriteriaDTO convertCriteria) {
		
		EnvironmentalSensorDTO environmentalSensorDTO = new EnvironmentalSensorDTO();

		environmentalSensorDTO.setSensorId(environmentalSensor.getSensorId());

		environmentalSensorDTO.setName(environmentalSensor.getName());

		environmentalSensorDTO.setSensorType(environmentalSensor.getSensorType());

		environmentalSensorDTO.setLocationCode(environmentalSensor.getLocationCode());

		environmentalSensorDTO.setStatus(environmentalSensor.getStatus());

		environmentalSensorDTO.setEnvironmentalSensorId(environmentalSensor.getEnvironmentalSensorId());
		
		return environmentalSensorDTO;
	}

	public ResultDTO updateEnvironmentalSensor(EnvironmentalSensorDTO environmentalSensorDTO, RequestDTO requestDTO) {
		
		EnvironmentalSensor environmentalSensor = environmentalSensorDao.getById(environmentalSensorDTO.getEnvironmentalSensorId());
		
		environmentalSensor.setSensorId(ControllerUtils.setValue(environmentalSensor.getSensorId(), environmentalSensorDTO.getSensorId()));
		
		environmentalSensor.setName(ControllerUtils.setValue(environmentalSensor.getName(), environmentalSensorDTO.getName()));
		
		environmentalSensor.setSensorType(ControllerUtils.setValue(environmentalSensor.getSensorType(), environmentalSensorDTO.getSensorType()));
		
		environmentalSensor.setLocationCode(ControllerUtils.setValue(environmentalSensor.getLocationCode(), environmentalSensorDTO.getLocationCode()));
		
		environmentalSensor.setStatus(ControllerUtils.setValue(environmentalSensor.getStatus(), environmentalSensorDTO.getStatus()));
		
		environmentalSensor.setEnvironmentalSensorId(ControllerUtils.setValue(environmentalSensor.getEnvironmentalSensorId(), environmentalSensorDTO.getEnvironmentalSensorId()));

        environmentalSensor = environmentalSensorDao.save(environmentalSensor);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public EnvironmentalSensorDTO getEnvironmentalSensorDTOById(Integer environmentalSensorId) {
	
		EnvironmentalSensor environmentalSensor = environmentalSensorDao.getById(environmentalSensorId);
		
		EnvironmentalSensorConvertCriteriaDTO convertCriteria = new EnvironmentalSensorConvertCriteriaDTO();
		return(this.convertEnvironmentalSensorToEnvironmentalSensorDTO(environmentalSensor,convertCriteria));
	}

}
