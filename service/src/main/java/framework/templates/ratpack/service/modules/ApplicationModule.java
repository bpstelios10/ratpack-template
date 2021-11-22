package framework.templates.ratpack.service.modules;

import com.google.inject.AbstractModule;

public class ApplicationModule extends AbstractModule {
    @Override
    public void configure() {
        install(new WebModule());
        install(new MetricsModule());
        install(new ServicesModule());
    }
}
