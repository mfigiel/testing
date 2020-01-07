package com.testing.service.metrics;

import io.micrometer.core.instrument.Tag;

/**
 *
 *
 * Created by abudzinski on 8/29/2018.
 */
public interface CounterService {

    void increment(String name);

    void increment(String metricName, Iterable<Tag> tags);

}
