package framework.templates.ratpack.functional.model;

import lombok.Getter;

@Getter
public enum ServiceEndpoints {
    PRIVATE_STATUS("/private/status"),
    PRIVATE_METRICS("/private/metrics"),
    PRIVATE_HEALTHCHECK("/private/healthcheck");

    private final String path;

    ServiceEndpoints(String path) {
        this.path = path;
    }
}
