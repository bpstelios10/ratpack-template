package framework.templates.ratpack.service.modules;

import com.google.inject.AbstractModule;
import framework.templates.ratpack.service.properties.ApplicationProperties;

public class ApplicationModule extends AbstractModule {

    private final ApplicationProperties applicationProperties;

    public ApplicationModule(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Override
    public void configure() {
        install(new WebModule());
        install(new MetricsModule());
        install(new ServicesModule());
        install(new ClientsModule(applicationProperties.getService()));
    }
}
