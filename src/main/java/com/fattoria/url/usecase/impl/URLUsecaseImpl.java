package com.fattoria.url.usecase.impl;

import com.fattoria.url.gateway.URLGateway;
import com.fattoria.url.gateway.data.request.URLGatewayRequest;
import com.fattoria.url.usecase.URLUsecase;
import com.fattoria.url.usecase.converter.URLUsecaseConverter;
import com.fattoria.url.usecase.data.response.ErrorResponse;
import com.fattoria.url.usecase.data.response.URLUsecaseResponse;
import com.fattoria.url.usecase.data.response.URLsUsecaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class URLUsecaseImpl implements URLUsecase {

    private final URLUsecaseConverter converter;
    private final URLGateway gateway;

    @Override
    public URLUsecaseResponse url(String url, String modificada) throws Exception {
        try {
            URLGatewayRequest gatewayRequest = converter.toGatewayInsertRequest(url, modificada);
            gateway.insert(gatewayRequest);

            return URLUsecaseResponse.builder().message("URL modificada com sucesso !").build();
        } catch (Exception error) {
            log.error(error.getMessage());
            List<ErrorResponse> resp = new ArrayList<>();
            resp.add(ErrorResponse.builder().message(error.getMessage()).build());

            return URLUsecaseResponse.builder().erros(resp).build();
        }
    }

    @Override
    public List<URLsUsecaseResponse> listURLs() throws Exception {
        try {
            return null;
        } catch (Exception error) {
            log.error(error.getMessage());
            throw new Exception(error.getMessage());
        }
    }

    @Override
    public Boolean deleteURL(int id) throws Exception {
        try {
            return null;
        } catch (Exception error) {
            log.error(error.getMessage());
            throw new Exception(error.getMessage());
        }
    }

    @Override
    public Boolean updateURL(int id) throws Exception {
        try {
            return null;
        } catch (Exception error) {
            log.error(error.getMessage());
            throw new Exception(error.getMessage());
        }
    }

    private String formatLocalURLFromShortener(String localURL) {
        String[] addressComponents = localURL.split("/");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < addressComponents.length - 1; ++i) {
            sb.append(addressComponents[i]);
        }
        sb.append('/');
        return sb.toString();
    }
}
