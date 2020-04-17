package com.mperozo.consultorio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableWebMvc
public class ConsultorioApplication implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//TODO colocar url específica
		registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ConsultorioApplication.class, args);
	}

}
