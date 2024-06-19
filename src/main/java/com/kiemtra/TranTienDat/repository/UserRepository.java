package com.kiemtra.TranTienDat.repository;

import com.kiemtra.TranTienDat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}