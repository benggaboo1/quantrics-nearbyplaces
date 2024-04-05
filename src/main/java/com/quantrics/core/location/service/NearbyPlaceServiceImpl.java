package com.quantrics.core.location.service;

import com.quantrics.core.exception.ApiError;
import com.quantrics.core.location.gateway.ISSLocationGatewayImpl;
import com.quantrics.core.location.gateway.WikipediaGatewayImpl;
import com.quantrics.core.location.model.dto.ISSLocationResp;
import com.quantrics.core.location.model.dto.NearbyPlaceWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.quantrics.core.constants.QuantricsConstants.*;

@Service
@RequiredArgsConstructor
public class NearbyPlaceServiceImpl {

    private final WikipediaGatewayImpl wikipediaGateway;
    private final ISSLocationGatewayImpl issLocationGateway;

    public NearbyPlaceWrapper getNearbyPlaces() {

        ISSLocationResp issLocation = getIssCoordinates();

        if (issLocation == null) {
            throw new ApiError(HttpStatus.BAD_REQUEST, FAILED_TO_GET_ISS_COORDS_GENERIC);
        }

        Map<String, String> geoQueryParams = buildGeoQueryParams(issLocation);

        return wikipediaGateway.getPlacesNearby(geoQueryParams);
    }

    private ISSLocationResp getIssCoordinates() {
        return issLocationGateway.getIssCoordinates();
    }

    private Map<String, String> buildGeoQueryParams(ISSLocationResp issLocation) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put(WIKI_PARAM_LIST, WIKI_PARAM_GEOSEARCH_VAL);
        queryParams.put(WIKI_PARAM_GSLIMIT, WIKI_PARAM_GSLIMIT_VAL);
        queryParams.put(WIKI_PARAM_GSRADIUS, WIKI_PARAM_GSRADIUS_VAL);
        queryParams.put(WIKI_PARAM_GSPROP, WIKI_PARAM_GSPROP_VAL);
        queryParams.put(WIKI_PARAM_FORMAT, WIKI_PARAM_FORMAT_VAL);
        queryParams.put(WIKI_PARAM_ACTION, WIKI_PARAM_ACTION_VAL);
        queryParams.put(WIKI_PARAM_GSCOORD, buildGsCoordParam(issLocation.getLatitude(), issLocation.getLongitude()));

        return Collections.unmodifiableMap(queryParams);
    }

    private String buildGsCoordParam(double latitude, double longitude) {
        return latitude + "|" + longitude;
    }
}
