package com.fattoria.url.http;

import com.fattoria.url.http.converter.URLHttpConverter;
import com.fattoria.url.http.data.request.URLHttpRequest;
import com.fattoria.url.http.data.response.URLHttpResponse;
import com.fattoria.url.usecase.URLUsecase;
import com.fattoria.url.usecase.data.response.URLUsecaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class URLHttp {

    private final URLUsecase usecase;
    private final URLHttpConverter converter;
    private Map<String, URLHttpRequest> shortenUrlList = new HashMap<>();

    @RequestMapping(value = "/{randomstring}", method = RequestMethod.GET)
    public void getFullUrl(HttpServletResponse response, @PathVariable("randomstring") String randomString, HttpServletRequest request) throws Exception {
        List<URLHttpResponse> data = converter.toHttpResponseListUrls(usecase.listURLs());

        List<URLHttpResponse> list = data.stream()
                .filter(item -> item.getShortUrl().equals(request.getRequestURL().toString()))
                .collect(Collectors.toList());

        response.sendRedirect(list.get(0).getFullUrl());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/", method = {RequestMethod.POST, RequestMethod.OPTIONS})
    @CrossOrigin(origins = "*")
    public ResponseEntity<URLUsecaseResponse> createUrl(@Valid @RequestBody URLHttpRequest body, HttpServletRequest request) {
        try {
            if (!isValid(body.getUrlOriginal())) throw new Exception("URL Inválida !");

            String urlRequest = request.getRequestURL().toString();

            URLUsecaseResponse response = usecase.url(body.getUrlOriginal(), urlRequest);
            log.info("Short URL: " + response.getUrls().getShortUrl());

            shortenUrlList.put(response.getUrls().getRandomChart(), body);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception error) {
            log.error("Erro ao criar uma url > " + error.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "URL não foi criada > " + error.getMessage() + " body: " + body.getUrlOriginal());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/url/list")
    public ResponseEntity<List<URLHttpResponse>> listURLs() {
        try {
            List<URLHttpResponse> response = converter.toHttpResponseListUrls(usecase.listURLs());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception error) {
            log.error("Erro ao retornar urls > " + error.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "" + error.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/url/{id}")
    public ResponseEntity<URLHttpResponse> url(@PathVariable int id) {
        try {
            URLHttpResponse response = converter.toHttpResponseUrl(usecase.url(id));

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception error) {
            log.error("Erro ao retornar url > " + error.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "" + error.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path = "/url/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<URLHttpResponse> updateUrl(@PathVariable int id, @Valid @RequestBody URLHttpRequest body, HttpServletRequest request) {
        try {
            String[] urlRequest = request.getRequestURL().toString().split("url");
            log.info(urlRequest[0]);

            URLHttpResponse response = converter.toHttpResponseUrl(usecase.updateURL(body.getUrlOriginal(), urlRequest[0] + urlRequest[1], id));

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception error) {
            log.error("Erro ao atualizar a url > " + error.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "" + error.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/url/{id}")
    public ResponseEntity<Boolean> deleteUrl(@PathVariable int id) {
        try {
            Boolean response = usecase.deleteURL(id);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception error) {
            log.error("Erro ao deletar a url > " + error.getMessage());
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