package framework.templates.ratpack.service.clients.httpclient;

import org.json.JSONObject;
import ratpack.exec.Promise;
import ratpack.http.client.HttpClient;
import ratpack.http.client.ReceivedResponse;

import javax.inject.Inject;
import java.net.URI;

import static ratpack.http.MediaType.APPLICATION_JSON;
import static ratpack.http.internal.HttpHeaderConstants.ACCEPT;

public class QuoteRandomDownstreamService {

    private static final String JSON_QUOTE_ATTRIBUTE_KEY = "value";

    private final HttpClient httpClient;
    private final URI httpRequest;

    @Inject
    public QuoteRandomDownstreamService(HttpClientFactory httpClientFactory, QuoteRandomDownstreamProperties quoteRandomDownstreamProperties) {
        this.httpClient = httpClientFactory.get();
        this.httpRequest = URI.create( //TODO inject it instead of properties? since its always the same
                quoteRandomDownstreamProperties.getScheme() + "://"
                        + quoteRandomDownstreamProperties.getHost() + ":"
                        + quoteRandomDownstreamProperties.getPort()
                        + quoteRandomDownstreamProperties.getPath());
    }

    public Promise<String> getRandomQuote() {
        Promise<ReceivedResponse> receivedResponsePromise =
                httpClient.get(httpRequest,
                        req -> req.headers(
                                headers -> headers.set(ACCEPT, APPLICATION_JSON)
                        )
                );

        return receivedResponsePromise.map(receivedResponse -> {
            JSONObject jsonObject = new JSONObject(receivedResponse.getBody().getText());
            return jsonObject.getString(JSON_QUOTE_ATTRIBUTE_KEY);
        });
    }
}
