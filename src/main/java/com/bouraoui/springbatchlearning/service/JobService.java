package com.bouraoui.springbatchlearning.service;

import com.bouraoui.springbatchlearning.request.JobParamsRequest;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JobService {
    @Autowired
    JobLauncher jobLauncher;
    @Qualifier("firstJob")
    @Autowired
    Job firstJob;

    @Qualifier("SecondtJob")
    @Autowired
    Job secondtJob;
    @Async
    public void StartJob(String jobName, List<JobParamsRequest> jobParamsRequestList)
    {
        Map<String, JobParameter> params = new HashMap<>();

        params.put("currentTime",new JobParameter(System.currentTimeMillis()));

        jobParamsRequestList.stream().forEach(jobParamReq -> {
            params.put(jobParamReq.getParamKey(), new JobParameter(jobParamReq.getParamValue()));
        });
        JobParameters jobParameters = new JobParameters(params);
        try{
            JobExecution jobExecution=null;
            if(jobName.equals("First Job")) {
                jobExecution=  jobLauncher.run(firstJob, jobParameters);
            }else if(jobName.equals("Second Job")) {
                jobExecution=   jobLauncher.run(secondtJob,jobParameters);
            }
            System.out.println("Job Exection ID = "+jobExecution.getId());
        } catch (JobExecutionAlreadyRunningException e) {
            throw new RuntimeException("Exception while exce job in controller "+e);
        } catch (JobRestartException e) {
            throw new RuntimeException(e);
        } catch (JobInstanceAlreadyCompleteException e) {
            throw new RuntimeException(e);
        } catch (JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }
    }
}
