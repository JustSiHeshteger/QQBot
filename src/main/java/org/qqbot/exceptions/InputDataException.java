package org.qqbot.exceptions;

import lombok.Getter;

public class InputDataException extends Exception {
    @Getter
    private String description;

    public InputDataException(String description) {
        super(description);
        this.description = description;
    }
}
