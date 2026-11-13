package com.EnvironmentalSensorApp.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseSensorAlertDTO {

	private Integer ownerId;

	private Integer sensorAlertId;

	private Integer sensorAlertStatus;
	
	private Integer nextOrPrevious;
}

