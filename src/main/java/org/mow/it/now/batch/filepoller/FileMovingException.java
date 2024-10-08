package org.mow.it.now.batch.filepoller;

public class FileMovingException extends RuntimeException {
    public FileMovingException(String message, Throwable t) {
        super(message, t);
    }
}
