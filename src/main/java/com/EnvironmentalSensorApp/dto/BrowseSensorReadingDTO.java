package com.EnvironmentalSensorApp.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseSensorReadingDTO {

	private Integer ownerId;

	private Integer sensorReadingId;

	private Integer sensorReadingStatus;
	
	private Integer nextOrPrevious;
}

