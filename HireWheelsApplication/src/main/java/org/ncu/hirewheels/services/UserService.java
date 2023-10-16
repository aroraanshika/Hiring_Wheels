package org.ncu.hirewheels.services;

import org.ncu.hirewheels.entities.Users;

public interface UserService {
	
	// Registration Functionality	
    Users createUser(Users user);
    
    // Login Functionality    
    Users getUser(String email, String password);
}
