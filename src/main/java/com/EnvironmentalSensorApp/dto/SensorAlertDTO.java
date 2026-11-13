package com.EnvironmentalSensorApp.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class SensorAlertDTO {

	private Integer sensorAlertId;

	private Integer sensorId;

	private String message;

	private String severity;

	private Date createdAt;

	private Boolean acknowledged;






}
