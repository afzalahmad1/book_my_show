package com.example.bookMyShow.Service;

import com.example.bookMyShow.Model.*;
import com.example.bookMyShow.Repository.MovieRepository;
import com.example.bookMyShow.Repository.ShowRepository;
import com.example.bookMyShow.Repository.ShowSeatRepository;
import com.example.bookMyShow.Repository.TheaterRepository;
import com.example.bookMyShow.dto.ShowRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    ShowSeatRepository showSeatRepository;
    public String addShow(ShowRequestDto showRequestDto){
        //Show Entity
        ShowEntity showEntity = ShowEntity.builder()
                .showDate(showRequestDto.getShowDate())
                .showTime(showRequestDto.getShowTime())
                .multiplier(showRequestDto.getMultiplier())
                .build();


        //You need to get the movieRepo
        MovieEntity movieEntity = movieRepository.findByMovieName(showRequestDto.getMovieName());

        //You need to get the theater Repository

        TheaterEntity theaterEntity = theaterRepository.findById(showRequestDto.getTheaterId()).get();

        showEntity.setTheater(theaterEntity);
        showEntity.setMovie(movieEntity);

        //bcz we are doing a bidirectional mapping :
        //Optional things :
//        List<ShowEntity> currentListOfShow = movieEntity.getListOfShows();
//        currentListOfShow.add(showEntity);
//        movieEntity.setListOfShows(currentListOfShow);
        movieEntity.getListOfShows().add(showEntity);
        theaterEntity.getListOfShows().add(showEntity);
        //theaterRepository.save(theaterEntity);

        List<ShowSeatEntity> seatEntityList = createShowSeats(theaterEntity.getTheaterSeatEntityList());

        showEntity.setListOfSeats(seatEntityList);

        //For each ShowSeat : we need to mark that to which show it belongs (foriegn key will be filled )
        for(ShowSeatEntity showSeat:seatEntityList){
            showSeat.setShow(showEntity);
        }

        movieRepository.save(movieEntity);
        theaterRepository.save(theaterEntity);
        //showRepository.save(showEntity); this doesnt need to be called bcz parent save function is being called.

        return "Show added successfully";
    }

    public List<ShowSeatEntity> createShowSeats(List<TheaterSeatEntity> theaterSeatEntityList){
        List<ShowSeatEntity> seats = new ArrayList<>();
        for(TheaterSeatEntity theaterSeat : theaterSeatEntityList){
            ShowSeatEntity showSeat = ShowSeatEntity.builder().seatNo(theaterSeat.getSeatNo())
                    .seatType(theaterSeat.getSeatType()).build();

            seats.add(showSeat);
        }


        showSeatRepository.saveAll(seats);

        return seats;
    }
}
