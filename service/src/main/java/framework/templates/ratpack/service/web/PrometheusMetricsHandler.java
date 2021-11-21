package framework.templates.ratpack.service.web;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import javax.inject.Inject;
import java.io.StringWriter;

public class PrometheusMetricsHandler implements Handler {
    private final CollectorRegistry collectorRegistry;

    @Inject
    public PrometheusMetricsHandler(CollectorRegistry collectorRegistry) {
        this.collectorRegistry = collectorRegistry;
    }

    @Override
    public void handle(Context context) throws Exception {
        StringWriter writer = new StringWriter();
        TextFormat.write004(writer, collectorRegistry.metricFamilySamples());

        context.render(writer.toString());
    }
}
