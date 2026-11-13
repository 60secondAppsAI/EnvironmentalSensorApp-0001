package com.EnvironmentalSensorApp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;

@Entity
@Table(name="environmental_sensors")
@Getter @Setter @NoArgsConstructor
public class EnvironmentalSensor {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="sensor_id")
	private Integer sensorId;
    
  	@Column(name="name")
	private String name;
    
  	@Column(name="sensor_type")
	private String sensorType;
    
  	@Column(name="location_code")
	private String locationCode;
    
  	@Column(name="status")
	private String status;
    
  	@Column(name="environmental_sensor_id")
	private Integer environmentalSensorId;
    

}
