package com.ttms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ttms.model.AdminUser;

public interface AdminRepository extends JpaRepository<AdminUser, Integer> {
    // Custom query to find an Admin by email and password
    public AdminUser findByAdminEmailAndAdminPassword(String email, String password);
}
