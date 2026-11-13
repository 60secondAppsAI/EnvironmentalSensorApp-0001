package com.EnvironmentalSensorApp.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SensorReadingSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer sensorReadingId;
	
	private Integer sensorId;
	
	private Double value;
	
	private String unit;
	
	private Date recordedAt;
	
}
