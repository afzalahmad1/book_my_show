package com.example.bookMyShow.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowRequestDto {
    private LocalDate showDate;

    private LocalTime showTime;

    private int theaterId;

    private String movieName;

    private double multiplier;
}
