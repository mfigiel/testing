package com.gateway.service.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
@RequiredArgsConstructor
public class CounterService {

    private final MeterRegistry meterRegistry;

    public void increment(String name) {
        meterRegistry.counter(name, Tags.empty()).increment();
    }

    public void increment(String metricName, Iterable<Tag> tags) {
        meterRegistry.counter(metricName, tags).increment();
    }

}