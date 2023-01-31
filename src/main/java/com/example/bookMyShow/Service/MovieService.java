package com.example.bookMyShow.Service;

import com.example.bookMyShow.Model.MovieEntity;
import com.example.bookMyShow.Repository.MovieRepository;
import com.example.bookMyShow.dto.MovieRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public String addMovie(@RequestBody MovieRequestDto movieRequestDto){
        MovieEntity movieEntity = MovieEntity.builder().movieName(movieRequestDto.getMoviename())
                .releaseDate(movieRequestDto.getReleaseDate()).build();

        movieRepository.save(movieEntity);

        return "Movie added successfully";
    }
}
