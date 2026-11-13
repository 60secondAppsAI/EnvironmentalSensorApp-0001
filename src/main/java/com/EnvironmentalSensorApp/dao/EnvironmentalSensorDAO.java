package com.EnvironmentalSensorApp.dao;

import java.util.List;
import java.util.Date;

import com.EnvironmentalSensorApp.dao.GenericDAO;
import com.EnvironmentalSensorApp.domain.EnvironmentalSensor;




import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface EnvironmentalSensorDAO extends GenericDAO<EnvironmentalSensor, Integer> {
  
	List<EnvironmentalSensor> findAll();
	


}

