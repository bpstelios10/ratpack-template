package framework.templates.ratpack.service.web;

import com.google.common.collect.ImmutableList;
import framework.templates.ratpack.service.services.MetricsService;
import framework.templates.ratpack.service.web.error.EndpointNotFound;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import javax.inject.Inject;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class EndpointMetricsRecordingHandler implements Handler {

    private static final List<String> METRIC_EXCLUDED_ENDPOINTS = ImmutableList.of("favicon");
    private static final String UNKNOWN_ENDPOINT = "UNKNOWN_ENDPOINT";

    private final EndpointsResolver endpointsResolver;
    private final MetricsService metricsService;

    @Inject
    public EndpointMetricsRecordingHandler(EndpointsResolver endpointsResolver, MetricsService metricsService) {
        this.endpointsResolver = endpointsResolver;
        this.metricsService = metricsService;
    }

    @Override
    public void handle(Context context) {
        if (METRIC_EXCLUDED_ENDPOINTS.stream().noneMatch(s -> context.getRequest().getPath().contains(s))) {
            Instant startTime = Instant.now();
            String endpoint = getEndPoint(context);

            context.onClose(requestOutcome -> {
                int code = requestOutcome.getResponse().getStatus().getCode();
                metricsService.recordApplicationResponseMetrics(endpoint, String.valueOf(code), getNanos(startTime));
            });
        }

        context.next();
    }

    private long getNanos(Instant startTime) {
        return Duration.between(startTime, Instant.now()).toNanos();
    }

    private String getEndPoint(Context context) {
        try {
            return endpointsResolver.getEndpoint(context).name();
        } catch (EndpointNotFound e) {
            return UNKNOWN_ENDPOINT;
        }
    }
}
