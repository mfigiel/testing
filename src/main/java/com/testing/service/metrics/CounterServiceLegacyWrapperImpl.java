package com.testing.service.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class CounterServiceLegacyWrapperImpl implements CounterService {

    @Autowired
    private MeterRegistry meterRegistry;

    @Override
    public void increment(String name) {
        meterRegistry.counter(name, Tags.empty()).increment();
    }

    @Override
    public void increment(String metricName, Iterable<Tag> tags) {
        meterRegistry.counter(metricName, tags).increment();
    }

}