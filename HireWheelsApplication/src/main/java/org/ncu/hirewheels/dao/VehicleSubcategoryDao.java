package org.ncu.hirewheels.dao;

import org.ncu.hirewheels.entities.VehicleSubcategory;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface VehicleSubcategoryDao extends JpaRepository<VehicleSubcategory, Long> {

}
