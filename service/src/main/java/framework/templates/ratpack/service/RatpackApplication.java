package framework.templates.ratpack.service;

import framework.templates.ratpack.service.modules.ApplicationModule;
import framework.templates.ratpack.service.properties.ApplicationProperties;
import framework.templates.ratpack.service.web.EndpointMetricsRecordingHandler;
import framework.templates.ratpack.service.web.HealthCheckHandler;
import framework.templates.ratpack.service.web.PrometheusMetricsHandler;
import framework.templates.ratpack.service.web.QuotesHandler;
import ratpack.config.ConfigData;
import ratpack.guice.Guice;
import ratpack.handling.Chain;
import ratpack.server.RatpackServer;

import java.net.URL;
import java.util.Objects;

import static framework.templates.ratpack.service.web.model.Endpoint.*;


public class RatpackApplication {

    public static void main(String[] args) throws Exception {
        ApplicationProperties applicationProperties = populateFromPropertiesYmlFile();

        RatpackServer.start(server -> server
                .registry(Guice.registry(bindings -> bindings.module(new ApplicationModule(applicationProperties))))
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

    private static ApplicationProperties populateFromPropertiesYmlFile() {
        URL applicationYmlUrl = Objects.requireNonNull(RatpackApplication.class.getClassLoader().getResource("application.yml"));
        ConfigData configData = ConfigData
                .builder()
                .yaml(applicationYmlUrl)
                .env()
                .build();

        return configData.get(ApplicationProperties.class);
    }
}
