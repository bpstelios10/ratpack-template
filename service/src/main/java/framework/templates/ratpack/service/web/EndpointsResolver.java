package framework.templates.ratpack.service.web;

import framework.templates.ratpack.service.web.error.EndpointNotFound;
import framework.templates.ratpack.service.web.model.Endpoint;
import ratpack.handling.Context;

import java.util.Arrays;

public class EndpointsResolver {

    public Endpoint getEndpoint(Context context) throws EndpointNotFound {
        return Arrays
                .stream(Endpoint.values())
                .filter(endPoint -> endPoint.getPath().equals(context.getRequest().getPath())
                        && endPoint.getHttpMethod().equals(context.getRequest().getMethod())
                ).findFirst()
                .orElseThrow(() -> new EndpointNotFound(context.getRequest().getMethod(), context.getRequest().getPath()));
    }
}
