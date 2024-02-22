/*
 * JBoss, Home of Professional Open Source
 * Copyright 2021, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mastertheboss.mp.reactive.messaging.model;

import java.sql.Timestamp;

 
 

 public class WeatherData {
     private Integer temperature;
     private Timestamp time;
     private String city;
 
     public WeatherData(Timestamp time2, String city, Integer temperature) {
        this.time = time2;
        this.city = city;
        this.temperature = temperature;
    }

    public WeatherData() {
        //TODO Auto-generated constructor stub
    }

    public Integer getTemperature() {
         return temperature;
     }
 
     public void setTemperature(Integer temperature) {
         this.temperature = temperature;
     }
 
     public Timestamp getTime() {
         return time;
     }
 
     public void setTime(Timestamp time) {
         this.time = time;
     }
 
     public String getCity() {
         return city;
     }
 
     public void setCity(String city) {
         this.city = city;
     }

    @Override
    public String toString() {
        return "WeatherData [temperature=" + temperature + ", time=" + time + ", city=" + city + "]";
    }
 }
 