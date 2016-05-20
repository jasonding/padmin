package com.hrin.admin.exception;

/**
 * Created by jason on 14-12-2.
 */
public class ApiRemoteCallFailedException extends RuntimeException {
    public ApiRemoteCallFailedException() {
    }

    public ApiRemoteCallFailedException(String message) {
        super(message);
    }

    public ApiRemoteCallFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiRemoteCallFailedException(Throwable cause) {
        super(cause);
    }

    public ApiRemoteCallFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
