package framework.templates.ratpack.service;

import framework.templates.ratpack.service.modules.ApplicationModule;
import ratpack.guice.Guice;
import framework.templates.ratpack.service.web.Router;
import ratpack.server.RatpackServer;

public class RatpackApplication {

    public static void main(String[] args) throws Exception {
        RatpackServer.start(server -> server
                .registry(Guice.registry(bindings -> bindings.module(ApplicationModule.class)))
                .handlers(chain -> chain.all(Router.class))
        );
    }
}
