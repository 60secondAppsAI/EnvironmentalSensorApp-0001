package com.EnvironmentalSensorApp.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class EnvironmentalSensorDTO {

	private Integer sensorId;

	private String name;

	private String sensorType;

	private String locationCode;

	private String status;

	private Integer environmentalSensorId;






}
