package com.github.app.infra;

import com.github.app.exception.HttpRequestException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.io.IOException;
import java.net.URI;

public record HttpClientProvider(HttpClient httpClient) implements AutoCloseable {

    @Contract(" -> new")
    public static @NotNull HttpClientProvider create() {
        return new HttpClientProvider(HttpClient.newHttpClient());
    }

    public <R> R execute(String url, @NotNull Function<HttpResponse<String>, R> handler) {
        try {
            HttpRequest request = HttpRequest
                    .newBuilder(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return handler.apply(response);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new HttpRequestException("Thread interrupted when trying to access the URL: " + url, e);
        } catch (IOException e) {
            throw new HttpRequestException("Error when accessing the URL: " + url, e);
        }
    }

    public String body(String url) {
        return execute(url, HttpResponse::body);
    }

    public int statusCode(String url) {
        return execute(url, HttpResponse::statusCode);
    }

    @Override
    public void close() {
        this.httpClient.close();
    }
}
