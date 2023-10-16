package org.ncu.hirewheels.services;

import org.ncu.hirewheels.entities.Vehicle;
import org.ncu.hirewheels.requests.VehicleRequest;

public interface AdminService {
    Vehicle registerVehicle(Vehicle vehicle);
    Vehicle changeAvailability(long vehicleId, int status);
    Vehicle convertToVehicleEntity(VehicleRequest vehicleRequest);
}
