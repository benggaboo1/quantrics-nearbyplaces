package com.quantrics.core.location.model.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class WikiPediaResp {

    private QueryWrapper query;

    @Data
    public static class QueryWrapper {

        @SerializedName("geosearch")
        private List<GeoSearchResultResp> geoSearchResult;

        @Data
        public static class GeoSearchResultResp {

            private String title;

            @SerializedName("lon")
            private double longitude;

            @SerializedName("lat")
            private double latitude;

            private String country;
        }

    }
}
