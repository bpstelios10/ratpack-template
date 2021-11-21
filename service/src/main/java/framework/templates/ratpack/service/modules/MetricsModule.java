package framework.templates.ratpack.service.modules;

import com.google.inject.AbstractModule;
import framework.templates.ratpack.service.services.MetricsService;
import io.prometheus.client.CollectorRegistry;

public class MetricsModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CollectorRegistry.class).toInstance(CollectorRegistry.defaultRegistry);
        bind(MetricsService.class).asEagerSingleton();
    }
}
