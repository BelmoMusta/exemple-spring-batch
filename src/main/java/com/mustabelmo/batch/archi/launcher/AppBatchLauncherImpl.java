package com.mustabelmo.batch.archi.launcher;

import com.mustabelmo.batch.archi.api.AppBatchLauncher;
import com.mustabelmo.batch.archi.api.ExecutableBatch;
import com.mustabelmo.batch.archi.dto.LaunchResult;
import com.mustabelmo.batch.archi.exceptions.BatchException;
import com.mustabelmo.batch.archi.exceptions.BatchNotFoundException;
import com.mustabelmo.batch.archi.exceptions.BatchPhase;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppBatchLauncherImpl implements AppBatchLauncher {
    @Autowired
    private JobLauncher jobLauncher;
    
    @Autowired
    private List<ExecutableBatch> executableBatches;
    
    private ExecutableBatch getBatchToExecute(String batchName) {
        for (ExecutableBatch batch : executableBatches) {
            if (batch.getName().equals(batchName)) {
                return batch;
            }
        }
        throw new BatchNotFoundException(batchName);
    }
    @Override
    public LaunchResult launch(LaunchParams launchParams) {
        final JobParameters jobParameters = launchParams.convertToJobParams();
        LaunchResult launchResult = new LaunchResult();
        try {
            ExecutableBatch batch = getBatchToExecute(launchParams.getBatchName());
            JobExecution run = jobLauncher.run(batch.getJob(), jobParameters);
            launchResult.setBatchName(batch.getName());
            launchResult.setBatchStatus(run.getStatus());
        } catch (JobExecutionException e) {
            throw new BatchException(e.getMessage(), BatchPhase.INIT, e);
        }
        
       return launchResult;
    }
}
