package com.individual.individual_project;

import com.individual.individual_project.config.UserBeanConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(UserBeanConfig.class)
@SpringBootApplication
public class IndividualProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(IndividualProjectApplication.class, args);
	}

}
