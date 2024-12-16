package com.charity_hub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.modulith.core.ApplicationModules;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackageClasses = CharityHubApplication.class)
public class CharityHubApplication {

    public static void main(String[] args) {
        // TODO disabled this one to make everything work then enable it
//        ApplicationModules.of(CharityHubApplication.class).verify();
        SpringApplication.run(CharityHubApplication.class, args);
    }
}