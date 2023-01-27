package com.example.bookMyShow.Repository;

import com.example.bookMyShow.Model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
}
