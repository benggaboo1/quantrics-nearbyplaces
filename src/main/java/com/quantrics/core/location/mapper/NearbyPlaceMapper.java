package com.quantrics.core.location.mapper;

import com.quantrics.core.location.model.dto.NearbyPlaceWrapper;
import com.quantrics.core.location.model.dto.WikiPediaResp;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NearbyPlaceMapper {

    public NearbyPlaceWrapper map(WikiPediaResp wikiPediaResp) {
        if (wikiPediaResp == null || wikiPediaResp.getQuery() == null) {
            return new NearbyPlaceWrapper(Collections.emptyList());
        }
        return new NearbyPlaceWrapper(mapQueryResp(wikiPediaResp.getQuery()));
    }

    private List<NearbyPlaceWrapper.NearbyPlace> mapQueryResp(WikiPediaResp.QueryWrapper queryWrapperResp) {
        if (queryWrapperResp == null || queryWrapperResp.getGeoSearchResult() == null) {
            return Collections.emptyList();
        }
        return queryWrapperResp.getGeoSearchResult().stream()
                .map(this::mapGeoSearchResult)
                .collect(Collectors.toList());
    }

    private NearbyPlaceWrapper.NearbyPlace mapGeoSearchResult(WikiPediaResp.QueryWrapper.GeoSearchResultResp result) {
        return new NearbyPlaceWrapper.NearbyPlace(
                result.getTitle(),
                result.getLongitude(),
                result.getLatitude(),
                result.getCountry()
        );
    }
}
