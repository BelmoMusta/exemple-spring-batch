package com.mustabelmo.batch.archi.config;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

@Component
@StepScope
public abstract class AbstractListener implements BatchCommonListener {
	@Override
	public void afterRead() {
		System.out.println("afterRead");
	}
}
