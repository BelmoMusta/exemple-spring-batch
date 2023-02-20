package com.mustabelmo.batch.archi.dto;

import org.springframework.batch.core.BatchStatus;

public class LaunchResult {
	private BatchStatus batchStatus;
	private String batchName;
	
	public void setBatchStatus(BatchStatus batchStatus) {
		this.batchStatus = batchStatus;
	}
	
	public BatchStatus getBatchStatus() {
		return batchStatus;
	}
	
	public String getBatchName() {
		return batchName;
	}
	
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	public int getBatchStatusAsInt() {
		if (batchStatus.isUnsuccessful()) {
			return 1;
		}
		return 0;
	}
}
