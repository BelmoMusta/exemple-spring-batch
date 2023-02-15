package com.mustabelmo.batch.archi.launcher;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class LaunchParams extends ConcurrentHashMap<String, String> {

    private boolean fromCommandLine;

    public LaunchParams() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        super.put("JOB_TIMESTAMP", timestamp.toString());
    }


    public boolean isFromCommandLine() {
        return fromCommandLine;
    }

    public void setFromCommandLine(boolean fromCommandLine) {
        this.fromCommandLine = fromCommandLine;
    }

    public String getBatchName() {
        return super.getOrDefault("-FLUX", "");
    }

    public JobParameters convertToJobParams() {
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        this.forEach(jobParametersBuilder::addString);
        return jobParametersBuilder.toJobParameters();
    }
}
