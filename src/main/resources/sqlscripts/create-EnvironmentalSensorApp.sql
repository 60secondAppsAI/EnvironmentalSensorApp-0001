CREATE DATABASE IF NOT EXISTS EnvironmentalSensorApp;

CREATE TABLE EnvironmentalSensorApp.environmental_sensors (
    `sensor_id` INT,
    `name` VARCHAR(255),
    `sensor_type` VARCHAR(255),
    `location_code` VARCHAR(255),
    `status` VARCHAR(255),
PRIMARY KEY (environmental_sensor_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE EnvironmentalSensorApp.sensor_readings (
    `sensor_reading_id` INT AUTO_INCREMENT,
    `sensor_id` INT,
    `value` DOUBLE,
    `unit` VARCHAR(255),
    `recorded_at` DATETIME,
PRIMARY KEY (sensor_reading_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE EnvironmentalSensorApp.sensor_thresholds (
    `sensor_threshold_id` INT AUTO_INCREMENT,
    `sensor_id` INT,
    `minimum_value` DOUBLE,
    `maximum_value` DOUBLE,
    `severity` VARCHAR(255),
PRIMARY KEY (sensor_threshold_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE EnvironmentalSensorApp.sensor_alerts (
    `sensor_alert_id` INT AUTO_INCREMENT,
    `sensor_id` INT,
    `message` VARCHAR(255),
    `severity` VARCHAR(255),
    `created_at` DATETIME,
    `acknowledged` BOOLEAN,
PRIMARY KEY (sensor_alert_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

USE drcvelocity;
