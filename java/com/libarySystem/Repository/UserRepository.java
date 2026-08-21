package com.libarySystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libarySystem.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}