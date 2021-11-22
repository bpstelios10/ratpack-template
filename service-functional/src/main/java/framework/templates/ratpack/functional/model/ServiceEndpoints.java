package framework.templates.ratpack.functional.model;

import lombok.Getter;

@Getter
public enum ServiceEndpoints {
    PRIVATE_STATUS("/trolltrump/private/status"),
    PRIVATE_METRICS("/trolltrump/private/metrics"),
    PRIVATE_HEALTHCHECK("/trolltrump/private/healthcheck"),
    QUOTE_RANDOM("/trolltrump/quote/random");

    private final String path;

    ServiceEndpoints(String path) {
        this.path = path;
    }
}
