package com.fattoria.url.usecase.converter;

import com.fattoria.url.gateway.data.request.URLGatewayRequest;
import com.fattoria.url.gateway.data.response.URLGatewayResponse;
import com.fattoria.url.usecase.data.response.URLsUsecaseResponse;

import java.util.List;

public interface URLUsecaseConverter {
    URLGatewayRequest toGatewayInsertRequest(String urlOriginal, String urlModificada);
    List<URLsUsecaseResponse> toUsecaseResponseListUrls(List<URLGatewayResponse> data);
    URLsUsecaseResponse toUsecaseResponseUrl(URLGatewayResponse data);
}
