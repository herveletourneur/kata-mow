package org.mow.it.now.application.port;

public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
