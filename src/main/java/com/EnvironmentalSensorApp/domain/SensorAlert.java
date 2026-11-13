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
@Table(name="sensor_alerts")
@Getter @Setter @NoArgsConstructor
public class SensorAlert {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="sensor_alert_id")
	private Integer sensorAlertId;
    
  	@Column(name="sensor_id")
	private Integer sensorId;
    
  	@Column(name="message")
	private String message;
    
  	@Column(name="severity")
	private String severity;
    
  	@Column(name="created_at")
	private Date createdAt;
    
  	@Column(name="acknowledged")
	private Boolean acknowledged;
    

}
