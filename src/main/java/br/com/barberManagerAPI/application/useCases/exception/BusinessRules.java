package br.com.barberManagerAPI.application.useCases.exception;

public class BusinessRules extends RuntimeException {
    public BusinessRules(String message) {
        super(message);
    }
}
