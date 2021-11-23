package framework.templates.ratpack.service.web;


import framework.templates.ratpack.service.clients.httpclient.QuoteRandomDownstreamService;
import ratpack.exec.Promise;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import javax.inject.Inject;

public class QuotesHandler implements Handler {

    private final QuoteRandomDownstreamService quoteRandomDownstreamService;

    @Inject
    public QuotesHandler(QuoteRandomDownstreamService quoteRandomDownstreamService) {
        this.quoteRandomDownstreamService = quoteRandomDownstreamService;
    }

    @Override
    public void handle(Context context) {
        Promise<String> randomQuote = quoteRandomDownstreamService.getRandomQuote();

        randomQuote.then(quote -> context.getResponse().send(quote));
    }
}
