package com.bouraoui.springbatchlearning.controller;

import com.bouraoui.springbatchlearning.request.JobParamsRequest;
import com.bouraoui.springbatchlearning.service.JobService;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.batch.core.Job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/job")
public class JobController {


    @Autowired
    JobService jobService;

    @Autowired
    JobOperator jobOperator;

    @GetMapping("/start/{jobName}")
    public String startJob(@PathVariable String jobName, @RequestBody List<JobParamsRequest> jobParamsRequestList) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        jobService.StartJob(jobName, jobParamsRequestList);
        return "job started ... ";
    }

    @GetMapping("/stop/{jobExecutionId}")
    public String stop(@PathVariable long jobExecutionId) {
        try {
            jobOperator.stop(jobExecutionId);
        } catch (NoSuchJobExecutionException e) {
            throw new RuntimeException(e);
        } catch (JobExecutionNotRunningException e) {
            throw new RuntimeException(e);
        }
        return "job Stopped ... ";
    }

}
