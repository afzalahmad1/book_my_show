package com.example.bookMyShow.dto;
import lombok.Data;
import java.util.List;

@Data
public class BookTicketRequestDto {

    private int userId;

    private int showId;

    private List<String> requestedSeats;
}
