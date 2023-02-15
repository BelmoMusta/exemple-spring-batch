package com.mustabelmo.batch;

import com.mustabelmo.batch.archi.api.AppBatchLauncher;
import com.mustabelmo.batch.archi.launcher.LaunchParams;
import com.mustabelmo.batch.archi.launcher.ParamsExtractor;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableBatchProcessing
public class Main implements CommandLineRunner {

    @Autowired
    private AppBatchLauncher appBatchLauncher;
    
    @Autowired
    ParamsExtractor paramsExtractor;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        
        final LaunchParams launchParams = paramsExtractor.createLaunchParams(args);
        if (launchParams.isFromCommandLine()) {
            appBatchLauncher.launch(launchParams);
            System.exit(0);
        }
    }
}