package com.fattoria.url.usecase;

import com.fattoria.url.usecase.data.response.URLUsecaseResponse;
import com.fattoria.url.usecase.data.response.URLsUsecaseResponse;

import java.util.List;

public interface URLUsecase {

    URLUsecaseResponse url(String url, String urlModificada) throws Exception;
    List<URLsUsecaseResponse> listURLs() throws Exception;
    Boolean deleteURL(int id) throws Exception;
    Boolean updateURL(int id) throws Exception;
}
