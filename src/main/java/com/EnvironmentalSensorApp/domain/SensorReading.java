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
@Table(name="sensor_readings")
@Getter @Setter @NoArgsConstructor
public class SensorReading {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="sensor_reading_id")
	private Integer sensorReadingId;
    
  	@Column(name="sensor_id")
	private Integer sensorId;
    
  	@Column(name="value")
	private Double value;
    
  	@Column(name="unit")
	private String unit;
    
  	@Column(name="recorded_at")
	private Date recordedAt;
    

}
