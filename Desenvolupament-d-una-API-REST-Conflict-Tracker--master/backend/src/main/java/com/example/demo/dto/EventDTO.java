package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class EventDTO {

    private Long id;

    private LocalDate eventDate;

    private String location;

    private String description;


    private Long conflictId;

    private String conflictName;

}
