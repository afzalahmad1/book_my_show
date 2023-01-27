package com.example.bookMyShow.Repository;

import com.example.bookMyShow.Model.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<ShowEntity,Integer> {
}
