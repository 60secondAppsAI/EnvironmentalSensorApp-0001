package com.EnvironmentalSensorApp.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EnvironmentalSensorSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer sensorId;
	
	private String name;
	
	private String sensorType;
	
	private String locationCode;
	
	private String status;
	
	private Integer environmentalSensorId;
	
}
