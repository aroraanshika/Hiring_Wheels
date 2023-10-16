package org.ncu.hirewheels.services;

import org.ncu.hirewheels.dao.FuelTypeDao;
import org.ncu.hirewheels.dao.LocationDao;
import org.ncu.hirewheels.dao.VehicleDao;
import org.ncu.hirewheels.dao.VehicleSubcategoryDao;
import org.ncu.hirewheels.entities.FuelType;
import org.ncu.hirewheels.entities.Location;
import org.ncu.hirewheels.entities.Vehicle;
import org.ncu.hirewheels.entities.VehicleSubcategory;
import org.ncu.hirewheels.requests.VehicleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private VehicleDao vehicleDao;
	
	@Autowired
	private LocationDao locationDao;
	
	@Autowired 
	private VehicleSubcategoryDao vehicleSubcategoryDao;
	
	@Autowired
	private FuelTypeDao fuelTypeDao;

	public Vehicle convertToVehicleEntity(VehicleRequest vehicleRequest) {
       
		Vehicle vehicle = new Vehicle();
       
        // Assuming you have service methods to retrieve these entities by their IDs
        VehicleSubcategory subcategory = vehicleSubcategoryDao.findById(vehicleRequest.getVehicleSubCategoryId()).get();
        vehicle.setVehicleSubcategory(subcategory);
        
        System.out.println("Get Location Id : ");
        System.out.println(vehicleRequest.getLocationId());
        
        Location location = locationDao.findById(vehicleRequest.getLocationId()).get();
        
//        logger.info("Location : ", location.toString());
        vehicle.setLocation(location);
        
        
        FuelType fuelType = fuelTypeDao.findById(vehicleRequest.getFuelTypeId()).get();
        vehicle.setFuelType(fuelType);
        
        vehicle.setColor(vehicleRequest.getColor());
        vehicle.setVehicleModel(vehicleRequest.getVehicleModel());
        vehicle.setVehicleNumber(vehicleRequest.getVehicleNumber());
        vehicle.setAvailabilityStatus(vehicleRequest.getAvailabilityStatus());
        vehicle.setVehicleImageUrl(vehicleRequest.getVehicleImageUrl());
        
        // You may need to set other properties or perform additional logic
        
        return vehicle;
    }
	
	@Override
	public Vehicle registerVehicle(Vehicle vehicle) {
		
		// Set availability_status to 1 by default
		vehicle.setAvailabilityStatus(1);

  		// Save the vehicle to the database
		return vehicleDao.save(vehicle);
	}
	
	
	  @Override
	    public Vehicle changeAvailability(long vehicleId, int status) {
	        // Find the vehicle by its ID
	        Vehicle vehicle = vehicleDao.findById(vehicleId).orElse(null);

	        if (vehicle != null) {
	       
	        	vehicle.setAvailabilityStatus(status);
	    
	            // Save the updated vehicle to the database
	            return vehicleDao.save(vehicle);
	        }

	        // Return null if the vehicle is not found
	        return null;
	    }
}
