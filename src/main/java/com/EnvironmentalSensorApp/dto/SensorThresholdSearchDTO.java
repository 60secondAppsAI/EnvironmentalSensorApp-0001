package com.EnvironmentalSensorApp.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SensorThresholdSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer sensorThresholdId;
	
	private Integer sensorId;
	
	private Double minimumValue;
	
	private Double maximumValue;
	
	private String severity;
	
}
