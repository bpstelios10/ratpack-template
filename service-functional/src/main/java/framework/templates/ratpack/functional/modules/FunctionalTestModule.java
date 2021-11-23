package framework.templates.ratpack.functional.modules;

import com.google.inject.AbstractModule;
import framework.templates.ratpack.functional.config.LocalStartupManager;
import framework.templates.ratpack.functional.metrics.ServiceMetricsProvider;

import javax.inject.Singleton;

public class FunctionalTestModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(LocalStartupManager.class).in(Singleton.class);
        bind(ServiceMetricsProvider.class).in(Singleton.class);
    }
}
