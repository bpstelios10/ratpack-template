package framework.templates.ratpack.service.modules;

import com.google.inject.AbstractModule;
import framework.templates.ratpack.service.clients.httpclient.HttpClientFactory;
import framework.templates.ratpack.service.clients.httpclient.QuoteRandomDownstreamProperties;

import javax.inject.Singleton;

public class ClientsModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(HttpClientFactory.class).in(Singleton.class);
        bind(QuoteRandomDownstreamProperties.class).toInstance( //TODO pass as properties
                new QuoteRandomDownstreamProperties("http", "localhost", 9090, "/random/quote"));
    }
}
