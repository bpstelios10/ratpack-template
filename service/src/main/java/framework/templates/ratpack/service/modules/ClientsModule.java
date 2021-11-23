package framework.templates.ratpack.service.modules;

import com.google.inject.AbstractModule;
import framework.templates.ratpack.service.clients.httpclient.HttpClientFactory;
import framework.templates.ratpack.service.clients.httpclient.QuoteRandomDownstreamProperties;
import framework.templates.ratpack.service.clients.httpclient.QuoteRandomDownstreamService;
import framework.templates.ratpack.service.properties.ServiceProperties;

import javax.inject.Singleton;

public class ClientsModule extends AbstractModule {
    private final ServiceProperties serviceProperties;

    public ClientsModule(ServiceProperties serviceProperties) {
        this.serviceProperties = serviceProperties;
    }

    @Override
    protected void configure() {
        bind(HttpClientFactory.class).in(Singleton.class);
        bind(QuoteRandomDownstreamProperties.class).toInstance(serviceProperties.getQuoteRandom());
        bind(QuoteRandomDownstreamService.class).in(Singleton.class);
    }
}
