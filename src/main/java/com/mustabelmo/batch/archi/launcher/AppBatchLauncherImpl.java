package com.mustabelmo.batch.archi.launcher;

import com.mustabelmo.batch.archi.api.AppBatchLauncher;
import com.mustabelmo.batch.archi.api.ExecutableBatch;
import com.mustabelmo.batch.archi.exceptions.BatchException;
import com.mustabelmo.batch.archi.exceptions.BatchPhase;
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

    @Override
    public void launch(LaunchParams launchParams) {
        final JobParameters jobParameters = launchParams.convertToJobParams();
        try {
            for (ExecutableBatch batch : executableBatches) {
                if (batch.getName().equals(launchParams.getBatchName())) {
                    jobLauncher.run(batch.getJob(), jobParameters);
                }
            }
        } catch (JobExecutionException e) {
            throw new BatchException(e.getMessage(), BatchPhase.INIT, e);
        }
    }
}
