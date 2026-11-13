package com.EnvironmentalSensorApp.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class SensorReadingDTO {

	private Integer sensorReadingId;

	private Integer sensorId;

	private Double value;

	private String unit;

	private Date recordedAt;






}
