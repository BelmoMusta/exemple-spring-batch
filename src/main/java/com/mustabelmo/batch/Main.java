package com.mustabelmo.batch;

import com.mustabelmo.batch.archi.api.AppBatchLauncher;
import com.mustabelmo.batch.archi.dto.LaunchResult;
import com.mustabelmo.batch.archi.launcher.LaunchParams;
import com.mustabelmo.batch.archi.launcher.ParamsExtractor;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;
import java.util.Random;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableBatchProcessing
@EnableScheduling

public class Main {
	
	public static void main(String[] args) {
		final LaunchParams launchParams = ParamsExtractor.createLaunchParams(args);
		String[] newArgs = args;
		if (launchParams.isFromCommandLine() && !launchParams.isRandomPortConfigured()) {
			newArgs = Arrays.copyOf(args, args.length + 1);
			final int randomPort = new Random(1).nextInt(65000);
			newArgs[newArgs.length - 1] = "--server.port=" + randomPort;
		}
		final ConfigurableApplicationContext context = SpringApplication.run(Main.class, newArgs);
		
		if (launchParams.isFromCommandLine()) {
			final AppBatchLauncher launcher = context.getBean(AppBatchLauncher.class);
			final LaunchResult launchResult = launcher.launch(launchParams);
			System.exit(SpringApplication.exit(context, launchResult::getBatchStatusAsInt));
		}
		
	}
}