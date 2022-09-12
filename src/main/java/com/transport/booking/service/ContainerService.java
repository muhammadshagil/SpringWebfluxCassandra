package com.transport.booking.service;

import com.transport.booking.model.Container;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ContainerService {
    Flux<Container> saveAll(Publisher<Container> containers);

    Flux<Container> loadAll();

    Mono<Integer> loadContainer(Container container);

    Mono<Integer> checkAvailable(Container container);
}
