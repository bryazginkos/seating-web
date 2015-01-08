package ru.seating.web.client.exception;

/**
 * Throws when something goes working due to user's actions
 */
public class BusinessException extends Exception {

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }
}
