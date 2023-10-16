package org.ncu.hirewheels.services;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.ncu.hirewheels.dao.BookingDao;
import org.ncu.hirewheels.dao.VehicleDao;
import org.ncu.hirewheels.dto.VehicleDto;
import org.ncu.hirewheels.entities.Booking;
import org.ncu.hirewheels.entities.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {
	
	@Autowired
	private BookingDao bookingDao;
	
	@Autowired
	private VehicleDao vehicleDao;

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleDao.findAll();
    }
    
    @Override
    public VehicleDto convertToDTO(Vehicle vehicle) {
        VehicleDto dto = new VehicleDto();
        dto.setVehicleId(vehicle.getVehicleId());
        dto.setVehicleModel(vehicle.getVehicleModel());
        dto.setVehicleNumber(vehicle.getVehicleNumber());
        dto.setVehicleSubcategoryName(vehicle.getVehicleSubcategory().getVehicleSubcategoryName());
        dto.setColor(vehicle.getColor());
        dto.setLocationName(vehicle.getLocation().getLocationName());
        dto.setFuelTypeName(vehicle.getFuelType() != null ? vehicle.getFuelType().getFuelType() : "N/A");
        dto.setAvailabilityStatus(vehicle.getAvailabilityStatus());
        dto.setVehicleImageUrl(vehicle.getVehicleImageUrl());
        return dto;
    }
    
    @Override
    public List<Vehicle> getAvailableVehicles(Long categoryId, String pickupLocation, ZonedDateTime pickupDate, ZonedDateTime dropoffDate) {
        // Fetch a list of available vehicles for the input Vehicle Category
        List<Vehicle> availableVehicles = vehicleDao.findAvailableVehiclesByCategory(categoryId);

        // Create a list to store the filtered vehicles
        List<Vehicle> filteredVehicles = new ArrayList<>();

        // Iterate through available vehicles and filter based on location and booking availability
        for (Vehicle vehicle : availableVehicles) {
            if (vehicle.getAvailabilityStatus() == 1 &&
                vehicle.getLocation().getLocationName().equalsIgnoreCase(pickupLocation) &&
                isVehicleAvailableForBooking(vehicle.getVehicleId(), pickupDate, dropoffDate)) {
                filteredVehicles.add(vehicle);
            }
        }

        return filteredVehicles;
    }

    
    public boolean isVehicleAvailableForBooking(Long vehicleId, ZonedDateTime pickupDate, ZonedDateTime dropoffDate) {
        // Query the booking table for bookings with the given vehicle ID
        List<Booking> bookings = bookingDao.findByVehicleId(vehicleId);

        // Check if the vehicle is available for the provided date range
        for (Booking booking : bookings) {
        	ZonedDateTime bookingPickupDate = booking.getPickupDate();
        	ZonedDateTime bookingDropoffDate = booking.getDropoffDate();

            // If the provided drop-off date is before the booking's pickup date
            // or the provided pickup date is after the booking's drop-off date, the vehicle is available
        	if (dropoffDate.isBefore(bookingPickupDate) || pickupDate.isAfter(bookingDropoffDate)) {
        	    // Vehicle is available for this booking
        	    continue;
        	} else {
        	    // Vehicle is not available for this booking
        	    return false;
        	}


        }

        // If no conflicting bookings are found, the vehicle is available
        return true;
    }
    
  
}
