package org.ncu.hirewheels.requests;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {
    private Long userId;
    private Long vehicleId;
    private ZonedDateTime pickupDate;
    private ZonedDateTime dropoffDate;
    private String bookingDate;
    private Long locationId;
    private BigDecimal amount;


   
}
