package com.mustabelmo.batch.archi.config;

import com.mustabelmo.batch.archi.api.ExecutableBatch;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;


public abstract class AbstractBatchConfig<I, O> extends AbstractChunkConfig<I, O> implements ExecutableBatch {
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	@Autowired
	private JobRepository jobRepository;
	
	public Step step() {
		return new StepBuilder("step1", jobRepository)
				.<I, O>chunk(getChunkSize(), transactionManager)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.build();
	}
	
	@Override
	public Job getJob() {
		return new JobBuilder(getName(), jobRepository)
				.incrementer(new RunIdIncrementer())
				.listener(listener())
				.flow(step())
				.end()
				.build();
	}
	
	protected JobExecutionListener listener() {
		return new DefaultJobCompletionListener();
	}
}