package com.mastertheboss.springboot.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.mastertheboss.springboot.model.City;

@Component("cityService")
@Transactional
class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;


	public CityServiceImpl( ) {	}
	 
	@Override
	public City getCity(String name, String country) {
		Assert.notNull(name, "Name must not be null");
		Assert.notNull(country, "Country must not be null");
		return this.cityRepository.findByNameAndCountryAllIgnoringCase(name, country);
	}

	@Override
	public List<City> findAll() {
		 
		return this.cityRepository.findAll();
	}

 }