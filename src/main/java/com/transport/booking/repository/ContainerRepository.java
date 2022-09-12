package com.transport.booking.repository;

import com.transport.booking.model.Container;
import com.transport.booking.model.ContainerType;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ContainerRepository extends ReactiveCassandraRepository<Container, Long> {


    @Query("SELECT * FROM container WHERE quantity >= ?0 and containerType = ?1 and origin = ?2 and " +
            "destination = ?3 and containerSize = ?4 ALLOW FILTERING")
    Mono<Container> findContainers(Integer quantity, ContainerType containerType, String origin,
                                   String destination, Integer containerSize);
}
