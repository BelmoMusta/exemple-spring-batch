package com.mustabelmo.batch.archi.launcher;

import com.mustabelmo.batch.archi.api.AppBatchLauncher;
import com.mustabelmo.batch.archi.api.ExecutableBatch;
import com.mustabelmo.batch.archi.exceptions.BatchException;
import com.mustabelmo.batch.archi.exceptions.BatchPhase;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppBatchLauncherImpl implements AppBatchLauncher {
    @Autowired
    private JobLauncher jobLauncher;
    
    @Autowired
    private List<ExecutableBatch> executableBatches;

    @Override
    public int launch(LaunchParams launchParams) {
        final JobParameters jobParameters = launchParams.convertToJobParams();
        final List<BatchStatus> statuses = new ArrayList<>();
        try {
            for (ExecutableBatch batch : executableBatches) {
                if (batch.getName().equals(launchParams.getBatchName())) {
                    JobExecution run = jobLauncher.run(batch.getJob(), jobParameters);
                    statuses.add(run.getStatus());
                }
            }
        } catch (JobExecutionException e) {
            statuses.add(BatchStatus.FAILED);
            throw new BatchException(e.getMessage(), BatchPhase.INIT, e);
        }
        
        for (BatchStatus status : statuses) {
            if(status.isUnsuccessful()){
                return 1;
            }
        }
        return 0;
    }
}
