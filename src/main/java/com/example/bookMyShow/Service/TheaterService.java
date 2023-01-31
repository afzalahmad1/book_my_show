package com.example.bookMyShow.Service;

import com.example.bookMyShow.Model.TheaterEntity;
import com.example.bookMyShow.Model.TheaterSeatEntity;
import com.example.bookMyShow.Repository.TheaterRepository;
import com.example.bookMyShow.Repository.TheaterSeatRepository;
import com.example.bookMyShow.dto.TheaterRequestDto;
import com.example.bookMyShow.enums.SeatType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    TheaterSeatRepository theaterSeatRepository;

    public String createTheater(TheaterRequestDto theaterRequestDto){
        TheaterEntity theater = TheaterEntity.builder().city(theaterRequestDto.getCity())
                .name(theaterRequestDto.getName()).address(theaterRequestDto.getAddress()).build();

        //create physical seat for theater
        List<TheaterSeatEntity> theaterSeats = createTheaterSeats();

        //set the seats list in the theaterEntity
        theater.setTheaterSeatEntityList(theaterSeats);

        //for each theater Seat we need to theaterEntity
        for(TheaterSeatEntity theaterSeat : theaterSeats){
            theaterSeat.setTheater(theater);
        }
        theaterRepository.save(theater);
        return  "Theater added successfully";
    }
    public List<TheaterSeatEntity> createTheaterSeats(){
        List<TheaterSeatEntity> seats = new ArrayList<>();
        TheaterSeatEntity theaterSeatEntity1 = new TheaterSeatEntity("1A", SeatType.CLASSIC,100);
        TheaterSeatEntity theaterSeatEntity2 = new TheaterSeatEntity("1B", SeatType.CLASSIC,100);
        TheaterSeatEntity theaterSeatEntity3 = new TheaterSeatEntity("1C", SeatType.CLASSIC,100);
        TheaterSeatEntity theaterSeatEntity4 = new TheaterSeatEntity("1D", SeatType.CLASSIC,100);
        TheaterSeatEntity theaterSeatEntity5 = new TheaterSeatEntity("1E", SeatType.CLASSIC,100);

        TheaterSeatEntity theaterSeatEntity6 = new TheaterSeatEntity("2A", SeatType.PLATINUM,200);
        TheaterSeatEntity theaterSeatEntity7 = new TheaterSeatEntity("2B", SeatType.PLATINUM,200);
        TheaterSeatEntity theaterSeatEntity8 = new TheaterSeatEntity("2C", SeatType.PLATINUM,200);
        TheaterSeatEntity theaterSeatEntity9 = new TheaterSeatEntity("2D", SeatType.PLATINUM,200);
        TheaterSeatEntity theaterSeatEntity10 = new TheaterSeatEntity("2E", SeatType.PLATINUM,200);

        seats.add(theaterSeatEntity1);
        seats.add(theaterSeatEntity2);
        seats.add(theaterSeatEntity3);
        seats.add(theaterSeatEntity4);
        seats.add(theaterSeatEntity5);
        seats.add(theaterSeatEntity6);
        seats.add(theaterSeatEntity7);
        seats.add(theaterSeatEntity8);
        seats.add(theaterSeatEntity9);
        seats.add(theaterSeatEntity10);

        theaterSeatRepository.saveAll(seats);

        return seats;

    }
}
