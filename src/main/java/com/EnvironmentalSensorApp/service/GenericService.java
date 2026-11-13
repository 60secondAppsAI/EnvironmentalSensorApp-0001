package com.EnvironmentalSensorApp.service;

import com.EnvironmentalSensorApp.dao.GenericDAO;

public interface GenericService<T, ID> {

    abstract GenericDAO<T, ID> getDAO();

    T getById(Integer id) ;

}