package com.EnvironmentalSensorApp.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SensorAlertPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<SensorAlertDTO> sensorAlerts;
}





