package framework.templates.ratpack.service.modules;

import com.google.inject.AbstractModule;
import framework.templates.ratpack.service.web.EndpointMetricsRecordingHandler;
import framework.templates.ratpack.service.web.EndpointsResolver;
import framework.templates.ratpack.service.web.HealthCheckHandler;
import framework.templates.ratpack.service.web.PrometheusMetricsHandler;

import javax.inject.Singleton;

public class WebModule extends AbstractModule {
    @Override
    public void configure() {
        bind(HealthCheckHandler.class).in(Singleton.class);
        bind(PrometheusMetricsHandler.class).in(Singleton.class);
        bind(EndpointMetricsRecordingHandler.class).in(Singleton.class);
        bind(EndpointsResolver.class).in(Singleton.class);
    }
}
