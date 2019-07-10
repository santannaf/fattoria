package com.fattoria.url.http.converter;

import com.fattoria.url.http.data.response.URLHttpResponse;
import com.fattoria.url.usecase.data.response.URLsUsecaseResponse;

import java.util.List;

public interface URLHttpConverter {
    List<URLHttpResponse> toHttpResponseListUrls(List<URLsUsecaseResponse> response);
}
