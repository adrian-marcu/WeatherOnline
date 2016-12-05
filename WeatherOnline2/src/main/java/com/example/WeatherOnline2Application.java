package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;


@EnableAutoConfiguration
@SpringBootApplication
public class WeatherOnline2Application extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WeatherOnline2Application.class);
	}


	public static void main(String[] args) {
		SpringApplication.run(WeatherOnline2Application.class, args);
	}
}
