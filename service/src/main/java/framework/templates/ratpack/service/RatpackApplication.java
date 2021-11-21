package framework.templates.ratpack.service;

import framework.templates.ratpack.service.modules.ApplicationModule;
import framework.templates.ratpack.service.web.EndpointMetricsRecordingHandler;
import framework.templates.ratpack.service.web.HealthCheckHandler;
import framework.templates.ratpack.service.web.PrometheusMetricsHandler;
import ratpack.guice.Guice;
import ratpack.handling.Chain;
import ratpack.server.RatpackServer;

public class RatpackApplication {

    public static void main(String[] args) throws Exception {
        RatpackServer.start(server -> server
                .registry(Guice.registry(bindings -> bindings.module(ApplicationModule.class)))
                .handlers(chain -> chain
                        .all(EndpointMetricsRecordingHandler.class)
                        .prefix("private", RatpackApplication::privateHandlers)
                )
        );
    }

    private static void privateHandlers(Chain chain) {
        chain
                .get("healthcheck", HealthCheckHandler.class)
                .get("metrics", PrometheusMetricsHandler.class)
                .get("status", HealthCheckHandler.class);
    }
}
