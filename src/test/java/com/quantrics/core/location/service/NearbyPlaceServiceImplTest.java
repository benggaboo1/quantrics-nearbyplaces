package com.quantrics.core.location.service;

import com.quantrics.core.exception.ApiError;
import com.quantrics.core.exception.ISSCoordinatesNullException;
import com.quantrics.core.exception.WikipediaQueryRemoteException;
import com.quantrics.core.location.gateway.ISSLocationGatewayImpl;
import com.quantrics.core.location.gateway.WikipediaGatewayImpl;
import com.quantrics.core.location.model.dto.ISSLocationResp;
import com.quantrics.core.location.model.dto.NearbyPlaceWrapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.quantrics.core.constants.QuantricsConstants.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NearbyPlaceServiceImplTest {

    @Mock
    private WikipediaGatewayImpl wikipediaGateway;

    @Mock
    private ISSLocationGatewayImpl issLocationGateway;

    @InjectMocks
    private NearbyPlaceServiceImpl nearbyPlaceService;

    private static ISSLocationResp.IssPosition issPosition = new ISSLocationResp.IssPosition(10.0, 20.0);
    private static ISSLocationResp issLocationResp = new ISSLocationResp(issPosition);

    private static NearbyPlaceWrapper mockNullResponse = new NearbyPlaceWrapper(Collections.EMPTY_LIST);

    @Test
    public void testGetNearbyPlaces_Success() {
        when(issLocationGateway.getIssCoordinates()).thenReturn(issLocationResp);
        when(wikipediaGateway.getPlacesNearby(anyMap())).thenReturn(mockNullResponse);

        Map<String, String> geoQueryParams = new HashMap<>();
        geoQueryParams.put(WIKI_PARAM_LIST, WIKI_PARAM_GEOSEARCH_VAL);
        geoQueryParams.put(WIKI_PARAM_GSLIMIT, WIKI_PARAM_GSLIMIT_VAL);
        geoQueryParams.put(WIKI_PARAM_GSRADIUS, WIKI_PARAM_GSRADIUS_VAL);
        geoQueryParams.put(WIKI_PARAM_GSPROP, WIKI_PARAM_GSPROP_VAL);
        geoQueryParams.put(WIKI_PARAM_FORMAT, WIKI_PARAM_FORMAT_VAL);
        geoQueryParams.put(WIKI_PARAM_ACTION, WIKI_PARAM_ACTION_VAL);
        geoQueryParams.put(WIKI_PARAM_GSCOORD, issPosition.getLatitude() + "|" + issPosition.getLongitude());

        nearbyPlaceService.getNearbyPlaces();

        verify(issLocationGateway, times(1)).getIssCoordinates();
        verify(wikipediaGateway, times(1)).getPlacesNearby(geoQueryParams);
    }

    @Test
    void testGetNearbyPlaces_IssCoordinatesNullException() {
        when(issLocationGateway.getIssCoordinates()).thenReturn(null);

        assertThrows(ISSCoordinatesNullException.class, () -> {
            nearbyPlaceService.getNearbyPlaces();
        });

        verify(issLocationGateway, times(1)).getIssCoordinates();
        verify(wikipediaGateway, never()).getPlacesNearby(anyMap());
    }

    @Test
    public void testGetNearbyPlaces_FailedToGetIssCoords() {
        when(issLocationGateway.getIssCoordinates()).thenReturn(null);

        assertThrows(ApiError.class, () -> nearbyPlaceService.getNearbyPlaces());

        verify(issLocationGateway, times(1)).getIssCoordinates();
        verifyNoInteractions(wikipediaGateway);
    }

    @Test
    void testGetNearbyPlaces_WikipediaQueryRemoteException() {
        when(issLocationGateway.getIssCoordinates()).thenReturn(issLocationResp);
        when(wikipediaGateway.getPlacesNearby(anyMap())).thenThrow(new WikipediaQueryRemoteException("Test exception"));

        assertThrows(ApiError.class, () -> {
            nearbyPlaceService.getNearbyPlaces();
        });

        verify(issLocationGateway, times(1)).getIssCoordinates();
        verify(wikipediaGateway, times(1)).getPlacesNearby(anyMap());
    }
}