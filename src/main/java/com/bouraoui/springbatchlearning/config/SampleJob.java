package com.bouraoui.springbatchlearning.config;

import com.bouraoui.springbatchlearning.service.SecondTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private SecondTasklet secondTasklet;

    public SampleJob() {
    }


    @Bean
    public Job firstJob()
    {
        return jobBuilderFactory.get("First Job")
                .start(firstStep())
                .next(secondStep())
                .build();
    }

    private Step firstStep(){
        return stepBuilderFactory.get("First step")
                .tasklet(firstTask())
                .build();
    }

    private Step secondStep(){
        return stepBuilderFactory.get("Second step")
                .tasklet(secondTasklet)
                .build();
    }

    private Tasklet firstTask() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("This is first tasklet step");
                return RepeatStatus.FINISHED;
            }
        };
    }

   /* private Tasklet secondTask() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("This is second tasklet step");
                return RepeatStatus.FINISHED;
            }
        };
    }*/
}

