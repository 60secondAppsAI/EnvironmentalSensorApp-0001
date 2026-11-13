package com.EnvironmentalSensorApp.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SensorAlertSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer sensorAlertId;
	
	private Integer sensorId;
	
	private String message;
	
	private String severity;
	
	private Date createdAt;
	
	private Boolean acknowledged;
	
}
