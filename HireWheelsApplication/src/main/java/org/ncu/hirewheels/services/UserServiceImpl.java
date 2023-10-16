package org.ncu.hirewheels.services;

import org.ncu.hirewheels.dao.UsersDao;
import org.ncu.hirewheels.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private  UsersDao usersDao;

//    @Autowired
//    public UserServiceImpl(UsersDao usersDao) {
//        this.usersDao = usersDao;
//    }

	@Override
	public Users createUser(Users user) {
		
		// Perform validation
        if (user.getFirstName() == null || user.getLastName() == null || user.getPassword() == null) {
            throw new IllegalArgumentException("Username and password are required.");
        }

        // Saving the user to the database.
        Users savedUser = usersDao.save(user);

        // Return the saved user
        return savedUser;
	}
	
	@Override
    public Users getUser(String email, String password) {
        // Find the user by email ID from the database
        Users user = usersDao.findByEmailIgnoreCase(email);

        if (user == null) {
            System.out.println("User not Registered");
            return null; // User with the given email is not registered
        }

        // Check if the password matches
        if (!user.getPassword().equals(password)) {
            System.out.println("Unauthorized User");
            return null; // Password is incorrect
        }

        // If email and password are correct, return the user
        return user;
    }
}
