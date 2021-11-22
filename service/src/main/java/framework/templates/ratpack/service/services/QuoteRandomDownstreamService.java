package framework.templates.ratpack.service.services;

import ratpack.exec.Promise;

public class QuoteRandomDownstreamService {
    public Promise<String> getRandomQuote() {
        return Promise.value("random quote");
    }
}
