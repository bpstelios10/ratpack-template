package framework.templates.ratpack.service.modules;

import com.google.inject.AbstractModule;
import framework.templates.ratpack.service.services.QuoteRandomDownstreamService;

import javax.inject.Singleton;

public class ServicesModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(QuoteRandomDownstreamService.class).in(Singleton.class);
    }
}
