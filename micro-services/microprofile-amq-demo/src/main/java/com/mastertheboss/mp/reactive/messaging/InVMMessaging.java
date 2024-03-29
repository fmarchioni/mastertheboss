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
package com.mastertheboss.mp.reactive.messaging;

import com.mastertheboss.mp.reactive.messaging.model.WeatherData;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.eclipse.microprofile.reactive.streams.operators.PublisherBuilder;

import java.util.concurrent.CompletionStage;



@ApplicationScoped
public class InVMMessaging {

    @Inject
  TemperatureGenerator producer;

 
 
    @Outgoing("source")
    public CompletionStage<WeatherData> sendInVm() {
        return producer.getWeatherData();
 
    }
  

    @Incoming("source")
    @Outgoing("filter")
    public WeatherData logAllMessages(WeatherData message) {
        System.out.println("Got Weather : " + message);
        return message;
    }

    @Incoming("filter")
    @Outgoing("sender")
    public PublisherBuilder<WeatherData> filterMessages(PublisherBuilder<WeatherData> messages) {
        return messages
                .filter(data -> !data.city().equals("Sydney"));
    }

   

    
}