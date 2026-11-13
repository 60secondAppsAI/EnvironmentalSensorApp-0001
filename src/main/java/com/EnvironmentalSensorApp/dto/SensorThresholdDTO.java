package com.EnvironmentalSensorApp.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class SensorThresholdDTO {

	private Integer sensorThresholdId;

	private Integer sensorId;

	private Double minimumValue;

	private Double maximumValue;

	private String severity;






}
