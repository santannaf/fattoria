package com.fattoria.url.gateway.data.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class URLGatewayRequest {
    private String urlOriginal;
    private String urlModificada;
}
