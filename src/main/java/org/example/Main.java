package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {

    public static final String REMOTE_SERVICE_URL = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";
    public static final ObjectMapper mapper = new ObjectMapper();

    //выводим в отдельный метод создание и настройка объекта класса CloseableHttpClient
    private static CloseableHttpClient getCloseableHttpClient() {
        return HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();
    }

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = getCloseableHttpClient();

        HttpGet request = new HttpGet(REMOTE_SERVICE_URL);

        CloseableHttpResponse response = httpClient.execute(request);

        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);

        List<CatsToJSON> cats = mapper.readValue(body, new TypeReference<>() {
        });
        cats.stream().filter(vote -> vote.getUpvote() > 0).forEach(System.out::println);
    }
}



