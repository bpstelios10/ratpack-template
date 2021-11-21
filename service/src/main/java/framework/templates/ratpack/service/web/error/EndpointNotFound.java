package framework.templates.ratpack.service.web.error;

import ratpack.http.HttpMethod;

public class EndpointNotFound extends Exception {

    public EndpointNotFound(HttpMethod verb, String path) {
        super(String.format("Endpoint with verb [%s] and path [%s] was not found.", verb, path));
    }
}
