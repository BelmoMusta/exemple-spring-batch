package com.mustabelmo.batch.archi.exceptions;

public class BatchNotFoundException extends RuntimeException {
	public BatchNotFoundException(String batchName) {
		super("Batch with name '" + batchName + "' is not found");
	}
}
