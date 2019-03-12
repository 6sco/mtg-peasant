package com.mtgpeasant.exception;

public enum Code {

    // === 400 ===
    BAD_REQUEST(1, "Bad Request"),

    // === 401 ===
    UNAUTHORIZED(1, "Unauthorized"),

    // === 402 ===
    PAYMENT_REQUIRED(1, "Payment Required"),

    // === 403 ===
    FORBIDDEN(1, "Forbidden"),

    // === 404 ===
    NOT_FOUND(1, "Not found"),
    CARD_NOT_FOUND(2, "Card not found"),

    // === 405 ===
    METHOD_NOT_ALLOWED(1, "Method not allowed"),

    // === 406 ===
    NOT_ACCEPTABLE(1, "Not acceptable"),

    // === 407 ===
    PROXY_AUTHENTICATION_REQUIRED(1, "Proxy Authentication Required"),

    // === 408 ===
    REQUEST_TIME_OUT(1, "Request time out"),

    // === 411 ===
    LENGHT_REQUIRED(1, "Lenght required"),

    // === 412 ===
    PRECONDITION_FAILED(1, "Precondition failed"),

    // === 413 ===
    REQUEST_ENTITY_TOO_LARGE(1, "Request entity too large"),

    // === 414 ===
    REQUEST_URI_TOO_LONG(1, "Request entity too long"),

    // === 415 ===
    UNSUPPORTED_MEDIA_TYPE(1, "Unsupported media type"),

    // === 429 ===
    TOO_MANY_REQUEST(1, "Too many request"),

    // === 500 ===
    INTERNAL_SERVER_ERROR(1, "Internal Server Error"),

    // === 503 ===
    SERVICE_UNAVAILABLE(1, "The server is temporarily unavailable");

    private int code;

    private String message;

    Code() {
    }

    Code(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
