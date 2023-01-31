package com.example.bookMyShow.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,unique = true)
    private String movieName;

    private  int duration;

    private Date releaseDate;



    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private List<ShowEntity> listOfShows;


}
