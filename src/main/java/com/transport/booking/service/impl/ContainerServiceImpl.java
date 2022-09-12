package com.transport.booking.service.impl;

import com.transport.booking.model.Container;
import com.transport.booking.repository.ContainerRepository;
import com.transport.booking.service.ContainerService;
import lombok.AllArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ContainerServiceImpl implements ContainerService {

    private final ContainerRepository containerRepository;
    private final WebClient webClient;

    @Override
    public Flux<Container> saveAll(Publisher<Container> containers) {
        return containerRepository.saveAll(containers);
    }

    @Override
    public Flux<Container> loadAll() {
        return containerRepository.findAll();
    }

    @Override
    public Mono<Integer> loadContainer(Container container) {
        Mono<Container> availableContainer = containerRepository.findContainers(container.getQuantity(),
                container.getContainerType(), container.getOrigin(),
                container.getDestination(), container.getContainerSize());

        return availableContainer.switchIfEmpty(Mono.just(Container.builder().quantity(0).build()))
                .map(container1 -> container1.getQuantity());
    }

    @Override
    public Mono<Integer> checkAvailable(Container container) {
        return webClient.post()
                .uri("/api/bookings/checkAvailableSpace")
                .body(Mono.just(container), Container.class)
                .retrieve()
                .bodyToMono(Integer.class);
    }
}
