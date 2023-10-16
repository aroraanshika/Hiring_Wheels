package org.ncu.hirewheels.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.ncu.hirewheels.dao.BookingDao;
import org.ncu.hirewheels.dao.LocationDao;
import org.ncu.hirewheels.dao.UsersDao;
import org.ncu.hirewheels.dao.VehicleDao;
import org.ncu.hirewheels.entities.Booking;
import org.ncu.hirewheels.entities.Location;
import org.ncu.hirewheels.entities.Users;
import org.ncu.hirewheels.entities.Vehicle;
import org.ncu.hirewheels.requests.BookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {
	
	@Autowired
    private  BookingDao bookingDao;
	@Autowired
    private  UsersDao usersDao;
	@Autowired
    private  LocationDao locationDao;
	@Autowired
    private  VehicleDao vehicleDao;
	
	
	 @Override
	    public Booking convertToEntity(BookingRequest request) {
		 	
		 	Booking booking = new Booking();
		 	System.out.println("PRINTING DETAILSSS");
		 	System.out.println(request.getLocationId());
		 	System.out.println(request.getUserId());
		 	System.out.println(request.getVehicleId());
		 	System.out.println("PRINTING DETAILSSS ENDD");
		 	
	        Location location = locationDao.findById(request.getLocationId()).get();

		 	System.out.println("PRINTING DETAILSSS - 0");
	        System.out.println(location.toString());
		 	System.out.println("PRINTING DETAILSSS ENDD");
	        
		 	
	        Vehicle vehicle = vehicleDao.findById(request.getVehicleId()).get();
	        

		 	System.out.println("PRINTING DETAILSSS - 2");
	        System.out.println(vehicle.toString());
		 	System.out.println("PRINTING DETAILSSS ENDD");
		 	
	        Users user = usersDao.findById(request.getUserId()).get();
	        

		 	System.out.println("PRINTING DETAILSSS - 1");
	        System.out.println(user.toString());
		 	System.out.println("PRINTING DETAILSSS ENDD");
	        

	        
	        booking.setUser(user);
	        booking.setLocation(location);
	        booking.setVehicle(vehicle);
	        
	        booking.setAmount(request.getAmount());
	        booking.setBookingDate(LocalDate.parse(request.getBookingDate()));
	       
	        booking.setDropoffDate(request.getDropoffDate());
	        booking.setPickupDate(request.getPickupDate());
	        
	        return booking;
	        
	    }
	
	@Override
    public Booking addBooking(Booking booking) {
        
		System.out.println(booking.getUser().getUserId());
		// Find the user by their ID
        @SuppressWarnings("deprecation")
		Users user = usersDao.getById(booking.getUser().getUserId());

        // Check if the user has sufficient balance
        if (BigDecimal.valueOf(user.getWalletMoney()).compareTo(booking.getAmount()) >= 0) {
            
        	// Deduct the booking amount from the user's wallet
            BigDecimal newBalance = BigDecimal.valueOf(user.getWalletMoney()).subtract(booking.getAmount());
            user.setWalletMoney(newBalance.doubleValue());
            
            // Save the updated user to the database
            usersDao.save(user);

            //Add the booking
            return bookingDao.save(booking);
        } else {
            // Insufficient balance
            System.out.println("Insufficient Balance. Please Check With Admin");
            return null;
        }
    }

}
