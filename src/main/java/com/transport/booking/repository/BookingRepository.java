package com.transport.booking.repository;

import com.transport.booking.model.Booking;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends ReactiveCassandraRepository<Booking, Long> {
}
