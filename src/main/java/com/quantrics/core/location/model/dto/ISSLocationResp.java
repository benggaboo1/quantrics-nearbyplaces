package com.quantrics.core.location.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
@AllArgsConstructor
public class ISSLocationResp {

    @SerializedName("iss_position")
    private IssPosition issPosition;

    public IssPosition getIss_position() {
        return issPosition;
    }

    public double getLongitude() {
        return issPosition.getLongitude();
    }

    public double getLatitude() {
        return issPosition.getLatitude();
    }

    public void setLongitude(double longitude) {
        this.issPosition.setLongitude(longitude);
    }

    public void setLatitude(double latitude) {
        this.issPosition.setLatitude(latitude);
    }

    @Data
    @AllArgsConstructor
    public static class IssPosition {
        private double longitude;
        private double latitude;
    }
}
