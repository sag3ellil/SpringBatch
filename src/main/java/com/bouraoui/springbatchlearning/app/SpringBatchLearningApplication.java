package com.bouraoui.springbatchlearning.app;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableBatchProcessing
@ComponentScan({"com.bouraoui.springbatchlearning.config","com.bouraoui.springbatchlearning.service"})
public class SpringBatchLearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchLearningApplication.class, args);
	}

}
