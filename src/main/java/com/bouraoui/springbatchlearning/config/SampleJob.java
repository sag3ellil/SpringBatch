package com.bouraoui.springbatchlearning.config;

import com.bouraoui.springbatchlearning.listener.FirstJobListener;
import com.bouraoui.springbatchlearning.listener.FirstStepListener;
import com.bouraoui.springbatchlearning.processor.FirstItemProcessor;
import com.bouraoui.springbatchlearning.reader.FirstItemReader;
import com.bouraoui.springbatchlearning.service.SecondTasklet;
import com.bouraoui.springbatchlearning.writer.FirstItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
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

    @Autowired
    private FirstJobListener firstJobListener;

    @Autowired
    private FirstStepListener firstStepListener;

    @Autowired
    private FirstItemReader firstItemReader;

    @Autowired
    private FirstItemProcessor firstItemProcessor;

    @Autowired
    private FirstItemWriter firstItemWriter;

    public SampleJob() {
    }

    //task oriented step
    @Bean
    public Job firstJob()
    {
        return jobBuilderFactory.get("First Job")
                .incrementer(new RunIdIncrementer())
                .start(firstStep())
                .next(secondStep())
                .listener(firstJobListener)
                .build();
    }



    private Step firstStep(){
        return stepBuilderFactory.get("First step")
                .tasklet(firstTask())
                .listener(firstStepListener)
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
                System.out.println("sec ="+chunkContext.getStepContext().getStepExecutionContext());
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
//chunck oriented step
    @Bean
    public Job SecondtJob()
    {
        return jobBuilderFactory.get("Second Job")
                .incrementer(new RunIdIncrementer())
               /* .start(firstStep())
                .next(secondStep())
                .listener(firstJobListener)*/
                .start(firstChunckStep())
                .next(secondStep())
                .build();
    }

    private Step firstChunckStep()
    {
        return stepBuilderFactory.get("First Chunk Step")
                .<Integer,Long>chunk(3)
                .reader(firstItemReader)
                .processor(firstItemProcessor)
                .writer(firstItemWriter)
                .build();
    }
}

