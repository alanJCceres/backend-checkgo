package com.acmsoft.checkgo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //modifica automaticamente los campos updated_at al hacer un update
public class CheckgoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheckgoApplication.class, args);
	}

}
