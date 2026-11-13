package com.EnvironmentalSensorApp.dao;

import java.util.List;
import java.util.Date;

import com.EnvironmentalSensorApp.dao.GenericDAO;
import com.EnvironmentalSensorApp.domain.SensorThreshold;




import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface SensorThresholdDAO extends GenericDAO<SensorThreshold, Integer> {
  
	List<SensorThreshold> findAll();
	


}

