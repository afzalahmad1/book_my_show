package com.example.bookMyShow.Repository;

import com.example.bookMyShow.Model.MovieEntity;
import com.example.bookMyShow.Model.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<ShowEntity,Integer> {

}

