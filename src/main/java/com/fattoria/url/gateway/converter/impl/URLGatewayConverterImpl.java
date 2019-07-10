package com.fattoria.url.gateway.converter.impl;

import com.fattoria.url.gateway.converter.URLGatewayConverter;
import com.fattoria.url.gateway.data.request.URLGatewayRequest;
import com.fattoria.url.gateway.data.response.URLGatewayResponse;
import com.fattoria.url.gateway.database.model.URLModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class URLGatewayConverterImpl implements URLGatewayConverter {

    @Override
    public List<URLGatewayResponse> toListGatewayResponse(List<URLModel> data) {
        List<URLGatewayResponse> list = new ArrayList<>();

        data.forEach(item -> {
            list.add(
                    URLGatewayResponse.builder()
                            .id(item.getId())
                            .urlOriginal(item.getUrlOrignal())
                            .urlModificada(item.getUrlModificada())
                            .build()
            );

        });

        return list;
    }

    @Override
    public URLGatewayResponse toGatewayResponse(Optional<URLModel> data) {
        return URLGatewayResponse.builder()
                .id(data.get().getId())
                .urlOriginal(data.get().getUrlOrignal())
                .urlModificada(data.get().getUrlModificada())
                .build();
    }

    @Override
    public URLModel toDatabase(URLGatewayRequest request) {
        return URLModel.builder()
                .urlOrignal(request.getUrlOriginal())
                .urlModificada(request.getUrlModificada())
                .build();
    }
}