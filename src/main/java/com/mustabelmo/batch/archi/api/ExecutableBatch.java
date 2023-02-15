package com.mustabelmo.batch.archi.api;

import org.springframework.batch.core.Job;

public interface ExecutableBatch {

    Job getJob();

    String getName();
}
