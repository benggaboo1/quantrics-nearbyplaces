package com.quantrics.core.location.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
@AllArgsConstructor
public class NearbyPlaceWrapper {

    private List<NearbyPlace> results;

    @Data
    @AllArgsConstructor
    public static class NearbyPlace {

        private String title;
        private double longitude;
        private double latitude;
        private  String country;
    }

}
