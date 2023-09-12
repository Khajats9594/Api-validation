package com.testvagrant.ApiValidationn.repository;

import com.testvagrant.ApiValidationn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserId(int id);
}
