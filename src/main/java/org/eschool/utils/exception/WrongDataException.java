package org.eschool.utils.exception;

public class WrongDataException extends RuntimeException {
    public WrongDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
