package com.prueba.dynae.dto;

import org.springframework.lang.NonNull;

import java.util.Date;

public class FormDTO {

    @NonNull
    private String from;
    @NonNull
    private String to;
    @NonNull
    private Double tempObj;

    @NonNull
    public String getFrom() {
        return from;
    }

    public void setFrom(@NonNull String from) {
        this.from = from;
    }

    @NonNull
    public String getTo() {
        return to;
    }

    public void setTo(@NonNull String to) {
        this.to = to;
    }

    @NonNull
    public Double getTempObj() {
        return tempObj;
    }

    public void setTempObj(@NonNull Double tempObj) {
        this.tempObj = tempObj;
    }
}
