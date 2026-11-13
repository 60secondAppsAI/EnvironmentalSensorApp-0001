package com.EnvironmentalSensorApp.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseEnvironmentalSensorDTO {

	private Integer ownerId;

	private Integer environmentalSensorId;

	private Integer environmentalSensorStatus;
	
	private Integer nextOrPrevious;
}

