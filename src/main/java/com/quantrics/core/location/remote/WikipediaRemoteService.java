package com.quantrics.core.location.remote;

import com.quantrics.core.location.model.dto.WikiPediaResp;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import java.util.HashMap;
import java.util.Map;

public interface WikipediaRemoteService {

    @GET(".")
    Call<WikiPediaResp> queryNearbyLocation(@QueryMap(encoded = true) Map<String, String> params);
}
