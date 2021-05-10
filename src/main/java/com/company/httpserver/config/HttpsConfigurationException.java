package com.company.httpserver.config;

public class HttpsConfigurationException extends RuntimeException {
    public HttpsConfigurationException() {
    }

    public HttpsConfigurationException(String message) {
        super(message);
    }

    public HttpsConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpsConfigurationException(Throwable cause) {
        super(cause);
    }

}
