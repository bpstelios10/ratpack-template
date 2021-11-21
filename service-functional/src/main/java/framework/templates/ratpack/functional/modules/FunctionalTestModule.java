package framework.templates.ratpack.functional.modules;

import com.google.inject.AbstractModule;
import framework.templates.ratpack.functional.config.LocalApplicationInstanceManager;
import framework.templates.ratpack.functional.metrics.ServiceMetricsProvider;

import javax.inject.Singleton;

public class FunctionalTestModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(LocalApplicationInstanceManager.class).in(Singleton.class);
        bind(ServiceMetricsProvider.class).in(Singleton.class);
    }
}
