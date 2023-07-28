package com.mymusic.gateway.Utils;

import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpHeaders;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class HttpUtils {

    public HttpResponse<String> sendPostRequest(String url, Map<String, String> headers, String body) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .headers(headers.entrySet().stream()
                        .flatMap(entry -> entry.getValue().isEmpty()
                                ? Stream.empty()
                                : Stream.of(entry.getKey(), entry.getValue()))
                        .toArray(String[]::new))
                .build();

        return client.send(request, BodyHandlers.ofString());
    }
}

