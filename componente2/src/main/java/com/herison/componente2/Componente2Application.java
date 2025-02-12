package com.herison.componente2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class Componente2Application {

	public static void main(String[] args) {
		SpringApplication.run(Componente2Application.class, args);
	}

}
