package com.transport.booking.controller;

import com.transport.booking.model.Booking;
import com.transport.booking.model.Container;
import com.transport.booking.service.BookingService;
import com.transport.booking.service.ContainerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final ContainerService containerService;

    @PostMapping("/checkAvailable")
    public Mono<Boolean> checkAvailable(@Valid @RequestBody Container container) {
        Mono<Integer> availabeContainer = containerService.checkAvailable(container);
        return availabeContainer.map(c -> c > 0 ? Boolean.TRUE : Boolean.FALSE);
    }


    /**
     * Assume this is an external service, this api supposed to be added in another microservice
     *
     * @param container
     * @return
     */
    @PostMapping("/checkAvailableSpace")
    public Mono<Integer> checkAvailableSpace(@RequestBody Container container) {
        return containerService.loadContainer(container);
    }

    @PostMapping
    public Mono<String> addBooking(@Valid @RequestBody Booking booking) {
        return bookingService.save(booking);
    }

    @GetMapping("/load")
    public Flux<Container> load() {
        return containerService.loadAll();
    }
}
