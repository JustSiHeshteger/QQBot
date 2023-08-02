package org.qqbot.exceptions;

import lombok.Getter;

public class NullNextTrack extends Exception {
    @Getter
    private String description;

    public NullNextTrack(String description) {
        super(description);
        this.description = description;
    }
}
