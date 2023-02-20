package com.mustabelmo.batch.archi.config;

public interface BatchCommonListener {
	
	default void beforeRead() {
	
	}
	
	default void beforeWrite() {
	
	}
	
	default void beforeProcess() {
	
	}
	
	default void afterRead() {
	
	}
	
	default void afterWrite() {
	
	}
	
	default void afterProcess() {
	
	}
}
