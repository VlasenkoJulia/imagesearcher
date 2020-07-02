package com.yuliia.vlasenko.imagesearcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories("com.yuliia.vlasenko.imagesearcher.repository")
@EnableScheduling
public class ImagesearcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImagesearcherApplication.class, args);
	}

}
