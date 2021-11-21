package framework.templates.ratpack.functional.steps;

import framework.templates.ratpack.functional.metrics.ServiceMetricsProvider;
import io.cucumber.java8.En;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.HashMap;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

public class MetricsSteps implements En {

    private static final String APPLICATION_RESPONSES_METRIC_TEMPLATE = "application_responses_total\\{endpointName=\"%s\",status=\"%s\",}";

    private final HashMap<String, BigDecimal> initialMetrics = new HashMap<>();

    @Inject
    public MetricsSteps(ServiceMetricsProvider serviceMetricsProvider) {
        Given("^application response metric for (.*) with response status (.*) gets initialised$", (String endpoint, String status) -> {
            String metricKey = format(APPLICATION_RESPONSES_METRIC_TEMPLATE, endpoint, status);
            initialMetrics.put(metricKey, serviceMetricsProvider.getCurrentMetricValue(metricKey));
        });

        Then("^application response metric for (.*) with response status (.*) is increased$", (String endpoint, String status) -> {
            String metricKey = format(APPLICATION_RESPONSES_METRIC_TEMPLATE, endpoint, status);
            if (initialMetrics.get(metricKey) == null) throw new Exception("metric is not initialised");

            assertThat(serviceMetricsProvider.getCurrentMetricValue(metricKey).subtract(initialMetrics.get(metricKey)))
                    .isEqualTo(BigDecimal.valueOf(1.0));
        });
    }
}
