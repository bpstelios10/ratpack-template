package framework.templates.ratpack.service.properties;

import framework.templates.ratpack.service.clients.httpclient.QuoteRandomDownstreamProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ServiceProperties {
    QuoteRandomDownstreamProperties quoteRandom;
}
