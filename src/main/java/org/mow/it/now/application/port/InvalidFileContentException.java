package org.mow.it.now.application.port;

public class InvalidFileContentException extends RuntimeException {
    public InvalidFileContentException(String message) {
        super(message);
    }
}
