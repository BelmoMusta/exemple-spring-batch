package com.mustabelmo.batch;

import com.mustabelmo.batch.archi.api.AppBatchLauncher;
import com.mustabelmo.batch.archi.launcher.LaunchParams;
import com.mustabelmo.batch.archi.launcher.ParamsExtractor;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableBatchProcessing
public class Main implements CommandLineRunner, ApplicationContextAware {

    @Autowired
    private AppBatchLauncher appBatchLauncher;
    
    @Autowired
    ParamsExtractor paramsExtractor;
    private ApplicationContext applicationContext;
    
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        
        final LaunchParams launchParams = paramsExtractor.createLaunchParams(args);
        if (launchParams.isFromCommandLine()) {
            int runResult = appBatchLauncher.launch(launchParams);
            SpringApplication.exit(applicationContext, () -> runResult);
        }
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}