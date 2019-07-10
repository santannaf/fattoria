package com.fattoria.url.gateway.impl;

import com.fattoria.url.gateway.URLGateway;
import com.fattoria.url.gateway.converter.URLGatewayConverter;
import com.fattoria.url.gateway.data.request.URLGatewayRequest;
import com.fattoria.url.gateway.data.response.URLGatewayResponse;
import com.fattoria.url.gateway.database.repository.URLRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class URLGatewayImpl implements URLGateway {

    private final URLRepository repository;
    private final URLGatewayConverter converter;

    @Override
    public Boolean insert(URLGatewayRequest request) throws Exception {
        try {
            repository.save(converter.toDatabase(request));

            return true;
        } catch (Exception error) {
            log.error(error.getMessage());
            return false;
        }
    }

    @Override
    public List<URLGatewayResponse> urls() throws Exception {
        try {
            return converter.toListGatewayResponse(repository.findAll());
        } catch (Exception error) {
            log.error(error.getMessage());
            throw new Exception(error.getMessage());
        }
    }

    @Override
    public URLGatewayResponse url(int id) throws Exception {
        try {
            return converter.toGatewayResponse(repository.findById(id));
        } catch (Exception error) {
            log.error(error.getMessage());
            throw new Exception(error.getMessage());
        }
    }
}
