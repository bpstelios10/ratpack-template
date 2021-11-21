package framework.templates.ratpack.service.web;

import framework.templates.ratpack.service.web.error.EndpointNotFound;
import ratpack.handling.Context;
import ratpack.http.HttpMethod;

import java.util.Arrays;

import static ratpack.http.HttpMethod.GET;

public class EndpointsResolver {

    public Endpoint getEndpoint(Context context) throws EndpointNotFound {
        return Arrays.stream(Endpoint.values())
                .filter(endPoint -> endPoint.path.equals(context.getRequest().getPath())
                        && endPoint.httpMethod.equals(context.getRequest().getMethod())
                ).findFirst()
                .orElseThrow(() -> new EndpointNotFound(context.getRequest().getMethod(), context.getRequest().getPath())
                );
    }

    public enum Endpoint {
        PRIVATE_STATUS("private/status", GET),
        PRIVATE_METRICS("private/metrics", GET),
        PRIVATE_HEALTHCHECK("private/healthcheck", GET);

        private final String path;
        private final HttpMethod httpMethod;

        Endpoint(String path, HttpMethod httpMethod) {
            this.path = path;
            this.httpMethod = httpMethod;
        }
    }
}
