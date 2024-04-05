package com.quantrics.core.location.controller;

import com.quantrics.core.location.model.dto.NearbyPlaceWrapper;
import com.quantrics.core.location.service.NearbyPlaceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.quantrics.core.constants.QuantricsConstants.URL_NEARBY_PLACES;

@RestController
@RequestMapping(URL_NEARBY_PLACES)
@RequiredArgsConstructor
public class NearbyPlaceController {

    private final NearbyPlaceServiceImpl nearbyPlaceService;

    @GetMapping
    public NearbyPlaceWrapper getNearbyPlacesIss() {
        return nearbyPlaceService.getNearbyPlaces();
    }
}
