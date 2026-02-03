package br.com.tenantSystem.application.useCases.scheduling.exception;

public class BusinessRules extends RuntimeException {
    public BusinessRules(String message) {
        super(message);
    }
}
