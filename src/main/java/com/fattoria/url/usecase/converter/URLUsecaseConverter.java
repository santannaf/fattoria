package com.fattoria.url.usecase.converter;

import com.fattoria.url.gateway.data.request.URLGatewayRequest;

public interface URLUsecaseConverter {
    URLGatewayRequest toGatewayInsertRequest(String urlOriginal, String urlModificada);
}
