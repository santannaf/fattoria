package com.fattoria.url.http;

import com.fattoria.url.http.converter.URLHttpConverter;
import com.fattoria.url.http.data.request.URLHttpRequest;
import com.fattoria.url.http.data.response.URLHttpResponse;
import com.fattoria.url.usecase.URLUsecase;
import com.fattoria.url.usecase.data.response.URLUsecaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.hv.URLValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class URLHttp {

    private final URLUsecase usecase;
    private final URLHttpConverter converter;
    private Map<String, URLHttpRequest> shortenUrlList = new HashMap<>();

    @RequestMapping(value="/{randomstring}", method=RequestMethod.GET)
    public void getFullUrl(HttpServletResponse response, @PathVariable("randomstring") String randomString) throws IOException {
        response.sendRedirect(shortenUrlList.get(randomString).getUrlOriginal());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/")
    public ResponseEntity<URLUsecaseResponse> createUrl(@Valid @RequestBody URLHttpRequest body, HttpServletRequest request) {
        try {
            if (!isValid(body.getUrlOriginal())) throw new Exception("URL Inválida !");

            String urlRequest = request.getRequestURL().toString();

            URLUsecaseResponse response = usecase.url(body.getUrlOriginal(), urlRequest);
            log.info("Short URL: " + response.getUrls().getShortUrl());

            shortenUrlList.put(response.getUrls().getRandomChart(), body);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception error) {
            log.error("Erro ao criar um contato > " + error.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "URL não foi criada > " + error.getMessage() + " body: " + body.getUrlOriginal());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(name = "/url/list")
    public ResponseEntity<List<URLHttpResponse>> listURLs() {
        try {
            List<URLHttpResponse> response = converter.toHttpResponseListUrls(usecase.listURLs());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception error) {
            log.error("Erro ao retornar urls > " + error.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "" + error.getMessage());
        }
    }





    private static boolean isValid(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}