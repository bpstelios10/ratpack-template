package framework.templates.ratpack.service;

import framework.templates.ratpack.service.modules.ApplicationModule;
import framework.templates.ratpack.service.web.EndpointMetricsRecordingHandler;
import framework.templates.ratpack.service.web.HealthCheckHandler;
import framework.templates.ratpack.service.web.PrometheusMetricsHandler;
import framework.templates.ratpack.service.web.QuotesHandler;
import ratpack.guice.Guice;
import ratpack.handling.Chain;
import ratpack.server.RatpackServer;

import static framework.templates.ratpack.service.web.model.Endpoint.*;


public class RatpackApplication {

    public static void main(String[] args) throws Exception {
        RatpackServer.start(server -> server
                .registry(Guice.registry(bindings -> bindings.module(ApplicationModule.class)))
                .handlers(chain -> chain
                        .all(EndpointMetricsRecordingHandler.class)
                        .prefix(PRIVATE_STATUS.getPrefix(), RatpackApplication::privateHandlers)
                        .get(QUOTE_RANDOM.getPath(), QuotesHandler.class)
                )
        );
    }

    private static void privateHandlers(Chain chain) {
        chain
                .get(PRIVATE_HEALTHCHECK.getSuffix(), HealthCheckHandler.class)
                .get(PRIVATE_METRICS.getSuffix(), PrometheusMetricsHandler.class)
                .get(PRIVATE_STATUS.getSuffix(), HealthCheckHandler.class);
    }
}
