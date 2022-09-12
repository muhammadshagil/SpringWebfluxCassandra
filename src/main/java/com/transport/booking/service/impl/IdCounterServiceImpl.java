package com.transport.booking.service.impl;

import com.transport.booking.service.IdCounterService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class IdCounterServiceImpl implements IdCounterService {

    private MeterRegistry meterRegistry;
    private Counter counter;

    //Get it from DB while startup
    private Long currentId = 957000000L;

    public IdCounterServiceImpl(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        counter = this.meterRegistry.counter("booking.counter", "type", "light");
    }

    @Override
    public Long getNextId() {
        counter.increment();
        return currentId + Double.valueOf(counter.count()).longValue();
    }
}
