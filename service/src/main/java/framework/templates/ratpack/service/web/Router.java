package framework.templates.ratpack.service.web;

import ratpack.handling.Context;
import ratpack.handling.Handler;

public class Router implements Handler {

    private final HealthCheckHandler healthCheckHandler = new HealthCheckHandler();

    @Override
    public void handle(Context context) {

        String path = context.getRequest().getPath();

        switch (path) {
            case "status":
                context.insert(healthCheckHandler);
                break;
            default:
                context.clientError(404);
                break;
        }


    }
}
