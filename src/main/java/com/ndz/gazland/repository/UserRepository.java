package com.ndz.gazland.repository;

import com.ndz.gazland.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
