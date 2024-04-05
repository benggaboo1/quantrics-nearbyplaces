package com.quantrics.core.location.gateway;

import com.quantrics.core.exception.ApiError;
import com.quantrics.core.location.mapper.NearbyPlaceMapper;
import com.quantrics.core.location.model.dto.NearbyPlaceWrapper;
import com.quantrics.core.location.model.dto.WikiPediaResp;
import com.quantrics.core.location.remote.WikipediaRemoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.Map;

import static com.quantrics.core.constants.QuantricsConstants.FAILED_TO_GET_WIKI_NEARBY_PLACES_API_ERROR;
import static com.quantrics.core.constants.QuantricsConstants.FAILED_TO_GET_WIKI_NEARBY_PLACES_GENERIC;

@Service
@RequiredArgsConstructor
public class WikipediaGatewayImpl {

    private final WikipediaRemoteService wikipediaRemoteService;
    private final NearbyPlaceMapper nearbyPlaceMapper;

    public NearbyPlaceWrapper getPlacesNearby(Map<String, String> params) {
        try {
            WikiPediaResp response = execute(params);
            return nearbyPlaceMapper.map(response);
        } catch (Exception e) {
            throw new ApiError(HttpStatus.BAD_REQUEST, FAILED_TO_GET_WIKI_NEARBY_PLACES_GENERIC, e);
        }
    }

    private WikiPediaResp execute(Map<String, String> params) throws IOException {
        Response<WikiPediaResp> response = wikipediaRemoteService.queryNearbyLocation(params).execute();

        if (!response.isSuccessful()) {
            throw new ApiError(HttpStatus.UNPROCESSABLE_ENTITY, FAILED_TO_GET_WIKI_NEARBY_PLACES_API_ERROR + response.code());
        }

        return response.body();
    }
}
