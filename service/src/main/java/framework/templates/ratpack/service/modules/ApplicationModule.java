package framework.templates.ratpack.service.modules;

import com.google.inject.AbstractModule;
import framework.templates.ratpack.service.web.HealthCheckHandler;
import framework.templates.ratpack.service.web.Router;

import javax.inject.Singleton;

public class ApplicationModule extends AbstractModule {
    @Override
    public void configure() {
        bind(HealthCheckHandler.class).toInstance(new HealthCheckHandler());

        bind(Router.class).in(Singleton.class);
    }
}
