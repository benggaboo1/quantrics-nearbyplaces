package com.quantrics.core.location.gateway;

import com.quantrics.core.exception.ApiError;
import com.quantrics.core.location.model.dto.ISSLocationResp;
import com.quantrics.core.location.remote.ISSLocationRemoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;

import static com.quantrics.core.constants.QuantricsConstants.FAILED_TO_GET_ISS_COORDS_API_ERROR;
import static com.quantrics.core.constants.QuantricsConstants.FAILED_TO_GET_ISS_COORDS_GENERIC;

@Service
@RequiredArgsConstructor
public class ISSLocationGatewayImpl {

    private final ISSLocationRemoteService issLocationRemoteService;

    public ISSLocationResp getIssCoordinates() {
        try {
            return execute();
        } catch (Exception e) {
            throw new ApiError(HttpStatus.BAD_REQUEST, FAILED_TO_GET_ISS_COORDS_GENERIC, e);
        }
    }

    private ISSLocationResp execute() throws IOException {
        Response<ISSLocationResp> response = issLocationRemoteService.getIssLocation().execute();

        if (!response.isSuccessful()) {
            throw new ApiError(HttpStatus.UNPROCESSABLE_ENTITY, FAILED_TO_GET_ISS_COORDS_API_ERROR + response.code());
        }

        return response.body();
    }
}
