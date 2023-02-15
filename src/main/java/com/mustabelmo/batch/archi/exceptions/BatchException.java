package com.mustabelmo.batch.archi.exceptions;

public class BatchException extends RuntimeException {
    private final BatchPhase phase;

    public BatchException(String message, BatchPhase phase, Throwable causedBy) {
        super(message, causedBy);
        this.phase = phase;

    }

    @Override
    public String getMessage() {
        return super.getMessage() + " at phase " + phase;
    }
}
