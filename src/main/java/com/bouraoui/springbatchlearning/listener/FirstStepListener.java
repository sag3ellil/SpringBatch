package com.bouraoui.springbatchlearning.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstStepListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
     System.out.println("before step name "+stepExecution.getStepName());
        System.out.println("step exec cont "+stepExecution.getJobExecution().getExecutionContext());
        System.out.println("step context "+ stepExecution.getExecutionContext());
        stepExecution.getExecutionContext().put("fis", "fis value");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        System.out.println("after step name "+stepExecution.getStepName());
        System.out.println("step exec cont "+stepExecution.getJobExecution().getExecutionContext());
        System.out.println("step context "+ stepExecution.getExecutionContext());
        return null;
    }
}
