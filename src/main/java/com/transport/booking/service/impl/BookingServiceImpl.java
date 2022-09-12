package com.transport.booking.service.impl;

import com.transport.booking.model.Booking;
import com.transport.booking.repository.BookingRepository;
import com.transport.booking.service.BookingService;
import com.transport.booking.service.IdCounterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    private final IdCounterService idCounterService;

    @Override
    public Mono<String> save(Booking booking) {
        booking.setId(idCounterService.getNextId());
        return bookingRepository.save(booking)
                .flatMap(s -> Mono.just(s.getId().toString()))
                .onErrorResume(throwable -> {
                    saveFallback(booking);
                    System.out.println(throwable);
                    return Mono.just("Sorry there was a problem processing your request");
                });
    }

    private void saveFallback(Booking booking) {
        System.out.println("save failed : call back method called");
    }
}
