package org.ncu.hirewheels.dao;

import java.util.List;

import org.ncu.hirewheels.entities.Booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDao extends JpaRepository<Booking, Long> {
	
	// Define a custom query to find bookings by vehicleId
    @Query("SELECT b FROM Booking b WHERE b.vehicle.id = :vehicleId")
    List<Booking> findByVehicleId(@Param("vehicleId") Long vehicleId);

}
