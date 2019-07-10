package com.fattoria.url.http.data.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class URLHttpResponse {
    private int id;
    private String fullUrl;
    private String shortUrl;
}
