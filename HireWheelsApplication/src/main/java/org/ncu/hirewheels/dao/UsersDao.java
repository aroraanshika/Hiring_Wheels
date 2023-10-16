package org.ncu.hirewheels.dao;

import java.util.List;

import org.ncu.hirewheels.entities.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDao extends JpaRepository<Users, Long> {
	
	 // Find users by first name (case-insensitive)
    List<Users> findByFirstNameIgnoreCase(String firstName);

    // Find users by first name or last name (case-insensitive)
    List<Users> findByFirstNameIgnoreCaseOrLastNameIgnoreCase(String firstName, String lastName);

    // Find users by email (case-insensitive)
    Users findByEmailIgnoreCase(String email);

    // Find users by mobile number (case-insensitive)
    List<Users> findByMobileNoIgnoreCase(String mobileNo);

}
