package com.fattoria.url.usecase.data.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class URLUsecaseRequest {
    private String urlOriginal;
}
