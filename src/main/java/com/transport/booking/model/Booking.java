package com.transport.booking.model;

import com.transport.booking.validator.ValuesAllowed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking implements Serializable {

    @PrimaryKey
    private Long id;

    @ValuesAllowed(values = {"DRY", "REEFER"})
    private ContainerType containerType;

    @ValuesAllowed(values = {"20", "40"}, message = "Value given not allowed for containerSize, allowed values are")
    private Integer containerSize;

    @Size(min = 5)
    @Size(max = 20)
    private String origin;

    @Size(min = 5)
    @Size(max = 20)
    private String destination;

    @Min(1)
    @Max(100)
    private Integer quantity;

    private LocalDateTime timestamp;
}
