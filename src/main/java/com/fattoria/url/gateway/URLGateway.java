package com.fattoria.url.gateway;

import com.fattoria.url.gateway.data.request.URLGatewayRequest;
import com.fattoria.url.gateway.data.response.URLGatewayResponse;
import com.fattoria.url.gateway.database.model.URLModel;

import java.util.List;
import java.util.Optional;

public interface URLGateway {
    Boolean insert(URLGatewayRequest request) throws Exception;
    List<URLGatewayResponse> urls() throws Exception;
    URLGatewayResponse url(int id) throws Exception;
    Boolean deleteUrl(int id) throws Exception;
    URLGatewayResponse updateUrl(URLGatewayRequest request, int id) throws Exception;
}
