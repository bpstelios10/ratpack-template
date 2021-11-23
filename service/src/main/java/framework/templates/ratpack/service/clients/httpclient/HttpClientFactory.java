package framework.templates.ratpack.service.clients.httpclient;

import ratpack.http.client.HttpClient;
import ratpack.http.client.internal.HttpClientBuilder;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.MILLIS;

public class HttpClientFactory {

    public HttpClient get() { //TODO provide properties per client
        HttpClientBuilder httpClientBuilder = (HttpClientBuilder) new HttpClientBuilder()
                .poolSize(10)
//                .enableMetricsCollection(true)
                .connectTimeout(Duration.of(200, MILLIS))
                .readTimeout(Duration.of(400, MILLIS));

        return httpClientBuilder.build();
    }
}
