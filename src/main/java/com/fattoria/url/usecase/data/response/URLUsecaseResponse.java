package com.fattoria.url.usecase.data.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class URLUsecaseResponse {
    private String message;
    private List<ErrorResponse> erros;
}
