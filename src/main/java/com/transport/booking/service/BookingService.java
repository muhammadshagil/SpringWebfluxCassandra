package com.transport.booking.service;

import com.transport.booking.model.Booking;
import reactor.core.publisher.Mono;

public interface BookingService {
    Mono<String> save(Booking booking);
}
