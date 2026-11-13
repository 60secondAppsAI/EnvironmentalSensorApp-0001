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
@Table(name="sensor_thresholds")
@Getter @Setter @NoArgsConstructor
public class SensorThreshold {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="sensor_threshold_id")
	private Integer sensorThresholdId;
    
  	@Column(name="sensor_id")
	private Integer sensorId;
    
  	@Column(name="minimum_value")
	private Double minimumValue;
    
  	@Column(name="maximum_value")
	private Double maximumValue;
    
  	@Column(name="severity")
	private String severity;
    

}
