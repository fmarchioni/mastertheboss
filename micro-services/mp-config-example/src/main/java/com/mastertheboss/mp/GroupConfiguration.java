package com.mastertheboss.mp;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;

import java.util.Optional;

@ConfigMapping(prefix = "example") 
public interface GroupConfiguration {
    Optional<String> format(); 

    @WithDefault("false") 
    boolean debugFlag(); 

    String unit();
}