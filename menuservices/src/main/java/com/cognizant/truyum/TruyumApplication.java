package com.cognizant.truyum;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
//@ComponentScan("com.cognizant.truyum.controller,com.cognizant.truyum.service,com.cognizant.truyum.dao,com.cognizant.truyum,com.cognizant.truyum.exception")
public class TruyumApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(TruyumApplication.class);
	public static void main(String[] args) throws ParseException {
		SpringApplication.run(TruyumApplication.class, args);
	}

}
