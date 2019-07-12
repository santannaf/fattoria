package com.fattoria.url.http.converter.impl;

import com.fattoria.url.http.converter.URLHttpConverter;
import com.fattoria.url.http.data.response.URLHttpResponse;
import com.fattoria.url.usecase.data.response.URLsUsecaseResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class URLHttpConverterImpl implements URLHttpConverter {
    @Override
    public List<URLHttpResponse> toHttpResponseListUrls(List<URLsUsecaseResponse> response) {
        List<URLHttpResponse> list = new ArrayList<>();

        response.forEach(item -> {
            list.add(
                    URLHttpResponse.builder()
                            .id(item.getId())
                            .fullUrl(item.getUrlOriginal())
                            .shortUrl(item.getUrlModificada())
                            .build()
            );
        });

        return list;
    }

    @Override
    public URLHttpResponse toHttpResponseUrl(URLsUsecaseResponse response) {
        return URLHttpResponse.builder()
                .id(response.getId())
                .fullUrl(response.getUrlOriginal())
                .shortUrl(response.getUrlModificada())
                .build();
    }
}
