package org.ncu.hirewheels.services;

import org.ncu.hirewheels.entities.Booking;
import org.ncu.hirewheels.requests.BookingRequest;

public interface BookingService {
	
	public Booking convertToEntity(BookingRequest request);
    public Booking addBooking(Booking booking);
}
