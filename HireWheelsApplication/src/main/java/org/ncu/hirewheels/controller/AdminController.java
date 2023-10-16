package org.ncu.hirewheels.controller;
import org.springframework.web.bind.annotation.RestController;
import org.ncu.hirewheels.entities.Vehicle;
import org.ncu.hirewheels.requests.UpdateAvailabilityRequest;
import org.ncu.hirewheels.requests.VehicleRequest;
import org.ncu.hirewheels.services.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/hirewheels/v1")
public class AdminController {

	
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    
	@Autowired
    private AdminService adminService;


    @PostMapping(value = "/vehicles", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> AddVehicle(@RequestBody VehicleRequest vehicleRequest) {
    	


        logger.info("Received request body: {}",  vehicleRequest);
    	
        
        

        Vehicle vehicle = adminService.convertToVehicleEntity(vehicleRequest);  
        
        

    	adminService.registerVehicle(vehicle);


        return new ResponseEntity<>("Vehicle added successfully", HttpStatus.CREATED);
    }
    

    @PutMapping("/vehicles/{id}")
    public ResponseEntity<Vehicle> updateVehicleAvailability(
            @PathVariable Long id,
            @RequestBody UpdateAvailabilityRequest request) {

        if (request != null && request.getAvailabilityStatus() != null) {

            Vehicle updatedVehicle = adminService.changeAvailability(id, request.getAvailabilityStatus());


            if (updatedVehicle != null) {

                return new ResponseEntity<>(updatedVehicle, HttpStatus.ACCEPTED);
            } else {

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    
}

