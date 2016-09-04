package com.mastertheboss.springboot.service;

import java.util.List;

import com.mastertheboss.springboot.model.City;

public interface CityService {

	City getCity(String name, String country);
    List<City> findAll();	 

}