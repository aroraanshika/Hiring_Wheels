package org.ncu.hirewheels.dao;

import java.util.List;

import org.ncu.hirewheels.entities.Vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDao extends JpaRepository<Vehicle, Long> {

	 @Query("SELECT v FROM Vehicle v " +
	            "JOIN FETCH v.vehicleSubcategory subcategory " +
	            "JOIN FETCH subcategory.vehicleCategory category " +
	            "WHERE category.id = :categoryId " +
	            "AND v.availabilityStatus = 1")
	 List<Vehicle> findAvailableVehiclesByCategory(@Param("categoryId") Long categoryId);
}
