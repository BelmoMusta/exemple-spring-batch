package com.mustabelmo.batch.traiterdocuments.listener;

import com.mustabelmo.batch.archi.config.AbstractListener;
import org.springframework.batch.core.annotation.AfterRead;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class TraitementDocumentsListener extends AbstractListener {
	@Override
	@AfterRead
	public void afterRead() {
		System.out.println("afterRead");
	}
}
