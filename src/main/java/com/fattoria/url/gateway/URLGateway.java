package com.fattoria.url.gateway;

import com.fattoria.url.gateway.data.request.URLGatewayRequest;
import com.fattoria.url.gateway.data.response.URLGatewayResponse;

import java.util.List;

public interface URLGateway {
    Boolean insert(URLGatewayRequest request) throws Exception;
    List<URLGatewayResponse> urls() throws Exception;
    URLGatewayResponse url(int id) throws Exception;
}
