package com.quantrics.core.location.remote;

import com.quantrics.core.location.model.dto.ISSLocationResp;
import retrofit2.Call;
import retrofit2.http.GET;

import static com.quantrics.core.constants.QuantricsConstants.URL_GET_ISS_LOCATION;

public interface ISSLocationRemoteService {

    @GET(URL_GET_ISS_LOCATION)
    Call<ISSLocationResp> getIssLocation();
}
