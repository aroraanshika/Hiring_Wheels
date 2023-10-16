package org.ncu.hirewheels.dao;

import org.ncu.hirewheels.entities.VehicleCategory;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VehicleCategoryDao extends JpaRepository<VehicleCategory, Long> {

}
