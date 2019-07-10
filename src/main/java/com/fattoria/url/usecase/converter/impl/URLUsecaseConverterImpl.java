package com.fattoria.url.usecase.converter.impl;

import com.fattoria.url.gateway.data.request.URLGatewayRequest;
import com.fattoria.url.usecase.converter.URLUsecaseConverter;
import org.springframework.stereotype.Component;

@Component
public class URLUsecaseConverterImpl implements URLUsecaseConverter {

    @Override
    public URLGatewayRequest toGatewayInsertRequest(String urlOriginal, String urlModificada) {
        return URLGatewayRequest.builder()
                .urlOriginal(urlOriginal)
                .urlModificada(urlModificada)
                .build();
    }
}
