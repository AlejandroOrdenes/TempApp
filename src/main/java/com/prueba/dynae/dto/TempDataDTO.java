package com.prueba.dynae.dto;

import java.util.Date;


public class TempDataDTO {
    private int id;
    private int sensorElementId;
    private Double magnitude;
    private float variation;
    private String timestamp;

    public TempDataDTO() {
    }

    public TempDataDTO(int id, int sensorElementId, Double magnitude, float variation, String timestamp) {
        this.id = id;
        this.sensorElementId = sensorElementId;
        this.magnitude = magnitude;
        this.variation = variation;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSensorElementId() {
        return sensorElementId;
    }

    public void setSensorElementId(int sensorElementId) {
        this.sensorElementId = sensorElementId;
    }

    public Double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Double magnitude) {
        this.magnitude = magnitude;
    }

    public float getVariation() {
        return variation;
    }

    public void setVariation(float variation) {
        this.variation = variation;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "TempDataDTO{" +
                "id=" + id +
                ", sensorElementId=" + sensorElementId +
                ", magnitude=" + magnitude +
                ", variation=" + variation +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }


}
