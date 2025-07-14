package org.jboss.mequal.errors;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ClientException extends ApplicationException {
    private static final int DEFAULT_CODE = 400;

    private final List<String> errors;

    final String errorId;

    public int getCode() {
        return DEFAULT_CODE;
    }

    public ClientException(String message, Object... params) {
        super(message, params);
        this.errors = null;
        this.errorId = UUID.randomUUID().toString();
    }

    public ClientException(String message, List<String> errors, Object... params) {
        super(message, params);
        this.errors = errors;
        this.errorId = UUID.randomUUID().toString();
    }
}
