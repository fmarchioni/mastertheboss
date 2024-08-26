package com.example.demo.demo2_1.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(source = "name", target = "customerName")
    CustomerDTO toDTO(Customer customer);

    @Mapping(source = "customerName", target = "name")
    Customer toEntity(CustomerDTO customerDTO);
}

/*
Se campi identici:

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO toDTO(Customer customer);
    Customer toEntity(CustomerDTO customerDTO);
}

 */