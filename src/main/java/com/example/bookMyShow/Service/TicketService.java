package com.example.bookMyShow.Service;

import com.example.bookMyShow.Model.ShowEntity;
import com.example.bookMyShow.Model.ShowSeatEntity;
import com.example.bookMyShow.Model.TicketEntity;
import com.example.bookMyShow.Model.UserEntity;
import com.example.bookMyShow.Repository.ShowRepository;
import com.example.bookMyShow.Repository.TicketRepository;
import com.example.bookMyShow.Repository.UserRepository;
import com.example.bookMyShow.dto.BookTicketRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ShowRepository showRepository;
    @Autowired
    UserRepository userRepository;

    public String bookTicket(BookTicketRequestDto  bookTicketRequestDto)throws Exception{

        //Get the requested seats
        List<String> requestedSeats = bookTicketRequestDto.getRequestedSeats();
        ShowEntity showEntity = showRepository.findById(bookTicketRequestDto.getShowId()).get();
        UserEntity userEntity = userRepository.findById(bookTicketRequestDto.getUserId()).get();

        //get the show seats
        List<ShowSeatEntity> showSeats = showEntity.getListOfSeats();
        // validate seats are valid or not

        List<ShowSeatEntity> bookedSeats = new ArrayList<>();
        for(ShowSeatEntity showSeat : showSeats){
            String seatNo = showSeat.getSeatNo();
            if(showSeat.isBooked()==false && requestedSeats.contains(seatNo)){
                bookedSeats.add(showSeat);
            }
        }
        //failure case
        if(bookedSeats.size()!=requestedSeats.size()){
            //some seats that user requested are not available
            throw new Exception("Requested seats are not available");
        }

        //this means all seats are available
        //success case
        TicketEntity ticketEntity = new TicketEntity();
        double totalAmount = 0;
        double multiplier = showEntity.getMultiplier();
        int rate = 0;
        String allotedSeats = "";

        //calculating amount and setting ticket attribute
        for(ShowSeatEntity bookedSeat : bookedSeats){
            bookedSeat.setBooked(true);
            bookedSeat.setBookedAt(new Date());
            bookedSeat.setTicket(ticketEntity);
            bookedSeat.setShow(showEntity);

            String seatNo = bookedSeat.getSeatNo();
            allotedSeats = allotedSeats +seatNo + ",";
            if(seatNo.charAt(0)=='1'){
                rate = 100;
            }else{
                rate = 200;
            }
            totalAmount = totalAmount + multiplier*rate;
        }
        ticketEntity.setBookedAt(new Date());
        ticketEntity.setAmount((int) totalAmount);
        ticketEntity.setUser(userEntity);
        ticketEntity.setShow(showEntity);
        ticketEntity.setBookedSeats(bookedSeats);
        ticketEntity.setAllotted_seats(allotedSeats);

        ticketRepository.save(ticketEntity);

        return "Ticket created successfully";

    }
}
