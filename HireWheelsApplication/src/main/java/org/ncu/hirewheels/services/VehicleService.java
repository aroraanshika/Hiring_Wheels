package org.ncu.hirewheels.services;

import java.time.ZonedDateTime;
import java.util.List;

import org.ncu.hirewheels.dto.VehicleDto;
import org.ncu.hirewheels.entities.Vehicle;

public interface VehicleService {
	VehicleDto convertToDTO(Vehicle vehicle);
	List<Vehicle> getAllVehicles();
	boolean isVehicleAvailableForBooking(Long vehicleId, ZonedDateTime pickupDate, ZonedDateTime dropoffDate);
	List<Vehicle> getAvailableVehicles(Long categoryId, String pickupLocation, ZonedDateTime pickupDate, ZonedDateTime dropoffDate);
}
