package framework.templates.ratpack.service.web;

import ratpack.handling.Context;
import ratpack.handling.Handler;

public class HealthCheckHandler implements Handler {

    @Override
    public void handle(Context context) {
        context.getResponse().send("OK");
    }
}
