package com.mustabelmo.batch.archi.config;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;


public abstract class AbstractChunkConfig<I, O> {
	
	@StepScope
	protected abstract ItemReader<I> reader();
	
	@StepScope
	protected abstract ItemProcessor<I, O> processor();
	
	@StepScope
	protected abstract ItemWriter<O> writer();
	
	protected abstract int getChunkSize();
	
}
