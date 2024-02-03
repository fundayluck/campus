package com.campus.app.response;

import lombok.Getter;

@Getter
public class StudyProgramResponse<T> {
    private final boolean status;
    private final int code;
    private final String message;
    private final T data;

    public StudyProgramResponse(boolean status, int code, String message, T data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

}

