package com.fattoria.url.usecase.data.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class URLsUsecaseResponse {
    private int id;
    private String urlOriginal;
    private String urlModificada;
}
