package com.fattoria.url.gateway.converter;

import com.fattoria.url.gateway.data.request.URLGatewayRequest;
import com.fattoria.url.gateway.data.response.URLGatewayResponse;
import com.fattoria.url.gateway.database.model.URLModel;

import java.util.List;
import java.util.Optional;

public interface URLGatewayConverter {
    List<URLGatewayResponse> toListGatewayResponse(List<URLModel> data);
    URLGatewayResponse toGatewayResponse(Optional<URLModel> data);
    URLModel toDatabase(URLGatewayRequest request);
}
