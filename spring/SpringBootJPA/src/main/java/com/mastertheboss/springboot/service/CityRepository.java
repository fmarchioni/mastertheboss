package com.mastertheboss.springboot.service;

import java.util.List;

//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.mastertheboss.springboot.model.City;

interface CityRepository extends Repository<City, Long> {
/*
	Page<City> findAll(Pageable pageable);

	Page<City> findByNameContainingAndCountryContainingAllIgnoringCase(String name,
			String country, Pageable pageable);
*/
	City findByNameAndCountryAllIgnoringCase(String name, String country);
	List<City> findAll();

}