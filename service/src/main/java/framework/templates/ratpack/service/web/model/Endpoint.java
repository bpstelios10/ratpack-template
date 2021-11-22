package framework.templates.ratpack.service.web.model;

import lombok.Getter;
import ratpack.http.HttpMethod;

import static ratpack.http.HttpMethod.GET;

@Getter
public enum Endpoint {
    PRIVATE_STATUS("trolltrump/private", "status", GET),
    PRIVATE_METRICS("trolltrump/private", "metrics", GET),
    PRIVATE_HEALTHCHECK("trolltrump/private", "healthcheck", GET),
    QUOTE_RANDOM("trolltrump", "quote/random", GET);

    private final String prefix;
    private final String suffix;
    private final HttpMethod httpMethod;

    Endpoint(String prefix, String suffix, HttpMethod httpMethod) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.httpMethod = httpMethod;
    }

    public String getPath() {
        return prefix + "/" + suffix;
    }
}
