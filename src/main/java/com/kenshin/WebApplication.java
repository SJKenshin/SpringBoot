package com.kenshin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.kenshin")
public class WebApplication  {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class,args);
	}
}
