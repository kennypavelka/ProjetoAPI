package org.serratec.projetoapi.exception;

public class ControllerExceptionHandler extends RuntimeException {
    public ControllerExceptionHandler(String message) {
        super(message);
    }
}
