package ru.avanesyan.vkr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class VkrApplication {

	public static void main(String[] args) {
		SpringApplication.run(VkrApplication.class, args);
	}

}
