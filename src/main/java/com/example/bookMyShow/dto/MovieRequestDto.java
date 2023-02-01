package com.example.bookMyShow.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MovieRequestDto {
    private String movieName;

    private Date releaseDate;
}
