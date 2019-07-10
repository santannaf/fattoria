package com.fattoria.url.gateway.data.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class URLGatewayResponse {
    private int id;
    private String urlOriginal;
    private String urlModificada;
}
