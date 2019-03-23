package com.stala.reverseproxy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ReverseProxyController {

    private static final String GET_METHOD = "GET";
    private static final String POST_METHOD = "POST";
    private static final String PUT_METHOD = "PUT";
    private static final String UNSUPPORTED_OPERATION_EXCEPTION_MSG = "HTTP method not supported";

    @Value("${destination.get}")
    private String getDestination;

    @Value("${destination.post}")
    private String postDestination;

    @Value("${destination.put}")
    private String putDestination;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/")
    public ResponseEntity<String> handleRequest(HttpServletRequest request) {

        ResponseEntity<String> response = null;
        String requestMethod = request.getMethod();

        if (requestMethod.equals(GET_METHOD)) {
            response = restTemplate.exchange(getDestination, HttpMethod.GET, null, String.class);
        } else if (requestMethod.equals(POST_METHOD)) {
            response = restTemplate.exchange(postDestination, HttpMethod.POST, null, String.class);
        } else if (requestMethod.equals(PUT_METHOD)) {
            response = restTemplate.exchange(putDestination, HttpMethod.PUT, null, String.class);
        } else throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_EXCEPTION_MSG);

        return response;
    }

}
