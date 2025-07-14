package org.jboss.mequal.errors;

import org.slf4j.helpers.MessageFormatter;

public class ApplicationException extends RuntimeException {
    private final String message;

    public ApplicationException(String msg, Object... params) {
        super(msg, MessageFormatter.getThrowableCandidate(params));
        this.message = (params != null && params.length != 0)
                ? MessageFormatter.arrayFormat(super.getMessage(), params).getMessage()
                : super.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}

