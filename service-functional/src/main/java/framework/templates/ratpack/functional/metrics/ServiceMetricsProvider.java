package framework.templates.ratpack.functional.metrics;

import com.google.inject.Inject;
import framework.templates.ratpack.functional.config.LocalApplicationInstanceManager;
import framework.templates.ratpack.functional.model.ServiceEndpoints;
import ratpack.http.client.ReceivedResponse;
import ratpack.test.http.TestHttpClient;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

public class ServiceMetricsProvider {

    private TestHttpClient serviceClient;
    private final LocalApplicationInstanceManager localApplicationInstanceManager;

    @Inject
    public ServiceMetricsProvider(LocalApplicationInstanceManager localApplicationInstanceManager) {
        this.localApplicationInstanceManager = localApplicationInstanceManager;
    }

    public BigDecimal getCurrentMetricValue(String metricKey) {
        Matcher matcher = matchMetric(metricKey);

        return matcher.find() ? new BigDecimal(matcher.group(2)) : ZERO;
    }

    private Matcher matchMetric(String metric) {
        String metricPattern = "\\b(%s)\\s(-?[0-9]+\\.[0-9]+)";

        return Pattern.compile(format(metricPattern, metric)).matcher(getMetrics());
    }

    private String getMetrics() {
        if (serviceClient == null) {
            serviceClient = localApplicationInstanceManager.getApplicationUnderTest().getHttpClient();
        }
        ReceivedResponse receivedResponse = serviceClient.get(ServiceEndpoints.PRIVATE_METRICS.getPath());

        assertThat(receivedResponse.getStatusCode())
                .overridingErrorMessage("Unable to obtain metrics. Endpoint returned %s", receivedResponse.getStatusCode())
                .isEqualTo(200);

        return receivedResponse.getBody().getText();
    }
}
