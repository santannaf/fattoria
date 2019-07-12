package com.fattoria.url.usecase;

import com.fattoria.url.usecase.data.request.URLUsecaseRequest;
import com.fattoria.url.usecase.data.response.URLUsecaseResponse;
import com.fattoria.url.usecase.data.response.URLsUsecaseResponse;

import java.util.List;

public interface URLUsecase {

    URLUsecaseResponse url(String url, String urlPai) throws Exception;
    List<URLsUsecaseResponse> listURLs() throws Exception;
    URLsUsecaseResponse url(int id) throws Exception;
    Boolean deleteURL(int id) throws Exception;
    URLsUsecaseResponse updateURL(String url, String urlPai, int id) throws Exception;
}
