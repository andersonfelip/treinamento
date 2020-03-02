package com.pitang.treinamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackageClasses = {
		SmsApplication.class,
})
public class SmsApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SmsApplication.class, args);
	}

}	