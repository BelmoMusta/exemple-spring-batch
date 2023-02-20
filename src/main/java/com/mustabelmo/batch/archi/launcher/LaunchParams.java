package com.mustabelmo.batch.archi.launcher;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

import java.sql.Timestamp;
import java.util.concurrent.ConcurrentHashMap;

public class LaunchParams extends ConcurrentHashMap<String, String> {
    
    public static final String EXECUTABLE_PREFIX = "-FLUX";
    
    public LaunchParams() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        super.put("JOB_TIMESTAMP", timestamp.toString());
    }
    
    public boolean isFromCommandLine() {
        return super.containsKey(EXECUTABLE_PREFIX);
    }
    
    public String getBatchName() {
        return super.getOrDefault(EXECUTABLE_PREFIX, "");
    }

    public JobParameters convertToJobParams() {
        final JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        this.forEach(jobParametersBuilder::addString);
        return jobParametersBuilder.toJobParameters();
    }
    
    public boolean isRandomPortConfigured() {
        return containsKey("--server.port");
    }
}
