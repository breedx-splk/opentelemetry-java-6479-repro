package io.opentelemetry.repro;

import io.opentelemetry.api.metrics.LongCounter;
import io.opentelemetry.api.metrics.Meter;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.sdk.testing.junit5.OpenTelemetryExtension;
import io.opentelemetry.sdk.trace.ReadableSpan;
import io.opentelemetry.sdk.trace.data.SpanData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.assertj.core.api.Assertions.assertThat;


public class CoolTest {
    @RegisterExtension
    static final OpenTelemetryExtension otelTesting = OpenTelemetryExtension.create();

    private final Tracer tracer = otelTesting.getOpenTelemetry().getTracer("test");
    private final Meter meter = otelTesting.getOpenTelemetry().getMeter("test");

    @Test
    public void test() {
        Span span = tracer.spanBuilder("name").startSpan();
        span.end();
        SpanData expected = ((ReadableSpan)span).toSpanData();
        assertThat(otelTesting.getSpans()).containsExactly(expected);

        LongCounter counter = meter.counterBuilder("counter-name").build();
        counter.add(1);
        assertThat(otelTesting.getMetrics())
                .satisfiesExactlyInAnyOrder(metricData -> {
                });
    }
}