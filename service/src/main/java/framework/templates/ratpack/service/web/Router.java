package framework.templates.ratpack.service.web;

import ratpack.handling.Context;
import ratpack.handling.Handler;

import javax.inject.Inject;

public class Router implements Handler {

    private final HealthCheckHandler healthCheckHandler;

    @Inject
    public Router(HealthCheckHandler healthCheckHandler) {
        this.healthCheckHandler = healthCheckHandler;
    }

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
