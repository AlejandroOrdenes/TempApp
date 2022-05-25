package com.prueba.dynae.dto;

public class DataResultDTO {

    private Double minTemp;
    private Double maxTemp;
    private Double seconds;

    private Double tempObjt;

    public Double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(Double minTemp) {
        this.minTemp = minTemp;
    }

    public Double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(Double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public Double getSeconds() {
        return seconds;
    }

    public void setSeconds(Double seconds) {
        this.seconds = seconds;
    }

    public Double getTempObjt() {
        return tempObjt;
    }

    public void setTempObjt(Double tempObjt) {
        this.tempObjt = tempObjt;
    }
}
