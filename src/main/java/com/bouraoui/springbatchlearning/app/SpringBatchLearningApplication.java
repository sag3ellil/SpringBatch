package com.bouraoui.springbatchlearning.app;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableBatchProcessing
@ComponentScan({"com.bouraoui.springbatchlearning.config","com.bouraoui.springbatchlearning.service","com.bouraoui.springbatchlearning.listener","com.bouraoui.springbatchlearning.reader","com.bouraoui.springbatchlearning.writer","com.bouraoui.springbatchlearning.processor","com.bouraoui.springbatchlearning.controller"})
@EnableAsync
//@EnableScheduling
public class SpringBatchLearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchLearningApplication.class, args);
	}

}
