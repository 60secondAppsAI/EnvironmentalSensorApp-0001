package com.EnvironmentalSensorApp.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseSensorThresholdDTO {

	private Integer ownerId;

	private Integer sensorThresholdId;

	private Integer sensorThresholdStatus;
	
	private Integer nextOrPrevious;
}

