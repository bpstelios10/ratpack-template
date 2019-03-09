package framework.templates.ratpack.service;

import framework.templates.ratpack.service.web.Router;
import ratpack.server.RatpackServer;

public class RatpackApplication {

    private final static Router ROUTER = new Router();

    public static void main(String[] args) throws Exception {
        RatpackServer.start(server -> server.handlers(
                chain -> chain.all(ROUTER))
        );
    }
}
