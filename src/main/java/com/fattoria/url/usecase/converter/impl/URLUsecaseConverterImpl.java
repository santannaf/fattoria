package com.fattoria.url.usecase.converter.impl;

import com.fattoria.url.gateway.data.request.URLGatewayRequest;
import com.fattoria.url.gateway.data.response.URLGatewayResponse;
import com.fattoria.url.usecase.converter.URLUsecaseConverter;
import com.fattoria.url.usecase.data.response.URLsUsecaseResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class URLUsecaseConverterImpl implements URLUsecaseConverter {

    @Override
    public URLGatewayRequest toGatewayInsertRequest(String urlOriginal, String urlModificada) {
        return URLGatewayRequest.builder()
                .urlOriginal(urlOriginal)
                .urlModificada(urlModificada)
                .build();
    }

    @Override
    public List<URLsUsecaseResponse> toUsecaseResponseListUrls(List<URLGatewayResponse> data) {
        List<URLsUsecaseResponse> list = new ArrayList<>();

        data.forEach(item -> {
            list.add(URLsUsecaseResponse.builder()
                    .id(item.getId())
                    .urlOriginal(item.getUrlOriginal())
                    .urlModificada(item.getUrlModificada())
                    .build());
        });

        return list;
    }
}
