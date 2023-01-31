package com.example.bookMyShow.Model;

import com.example.bookMyShow.enums.SeatType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "theater_seat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TheaterSeatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private int rate;

    @ManyToOne
    @JoinColumn
    private TheaterEntity theater;

    public TheaterSeatEntity(String seatNo, SeatType seatType, int rate) {
        this.seatNo = seatNo;
        this.seatType = seatType;
        this.rate = rate;
    }
}
