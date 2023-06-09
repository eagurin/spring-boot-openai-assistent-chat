package com.chatbot.openai.net;

import com.chatbot.openai.net.request.RequestBuilder;

import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

/**
 * HttpClientInstance
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
public class HttpClientInstance {

    private final HttpClient httpClient;

    public HttpClientInstance(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public CompletionStage<HttpResponse<String>> sendAsync(Object data, RequestBuilder requestBuilder) {
        HttpRequest request = requestBuilder.request(data);
        Objects.requireNonNull(request, "Request cannot be null");
        Objects.requireNonNull(httpClient, "HttpClient cannot be null");

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString(), pushPromiseHandler());
    }

    private HttpResponse.PushPromiseHandler<String> pushPromiseHandler() {
        return (HttpRequest initiatingRequest, HttpRequest pushPromiseRequest,
                Function<HttpResponse.BodyHandler<String>, CompletableFuture<HttpResponse<String>>> acceptor)
                -> acceptor.apply(HttpResponse.BodyHandlers.ofString());
    }

    public static HttpClient customHttpClient(String proxyIp, int proxyPort, Duration timeout) {
        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .connectTimeout(timeout)
                .proxy(proxyIp != null
                        ? ProxySelector.of(new InetSocketAddress(proxyIp, proxyPort))
                        : ProxySelector.getDefault()
                )
                .build();
    }

    public static HttpClient defaultHttpClient() {
        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .connectTimeout(Duration.ofSeconds(30)) //30 seconds timeout
                .build();
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

}
