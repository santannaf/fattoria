package com.fattoria.url.http;

import com.fattoria.url.http.data.request.URLHttpRequest;
import com.fattoria.url.usecase.URLUsecase;
import com.fattoria.url.usecase.data.response.URLUsecaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.hv.URLValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class URLHttp {

    private final URLUsecase usecase;
    private Map<String, URLHttpRequest> shortenUrlList = new HashMap<>();

    @RequestMapping(value="/url/{randomstring}", method=RequestMethod.GET)
    public void getFullUrl(HttpServletResponse response, @PathVariable("randomstring") String randomString) throws IOException {
        response.sendRedirect(shortenUrlList.get(randomString).getUrlOriginal());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/url")
    public ResponseEntity<URLUsecaseResponse> createUrl(@Valid @RequestBody URLHttpRequest body) {
        try {
            if (!isValid(body.getUrlOriginal())) throw new Exception("URL Inválida !");

            String randomChar = getRandomChars();
            String urlModificada = "http://localhost:8080/url/" + randomChar;
            shortenUrlList.put(randomChar, body);

            log.info(shortenUrlList.toString());
            log.info(shortenUrlList.get(randomChar).getUrlOriginal());

            log.info(urlModificada);
            URLUsecaseResponse response = usecase.url(body.getUrlOriginal(), urlModificada);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception error) {
            log.error("Erro ao criar um contato > " + error.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "URL não foi criada > " + error.getMessage() + " body: " + body.getUrlOriginal());
        }
    }

    private String getRandomChars() {
        String randomStr = "";
        String possibleChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < 7; i++)
            randomStr += possibleChars.charAt((int) Math.floor(Math.random() * possibleChars.length()));
        return randomStr;
    }

    public static boolean isValid(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}