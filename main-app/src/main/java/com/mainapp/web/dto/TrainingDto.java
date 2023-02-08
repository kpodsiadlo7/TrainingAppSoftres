package com.mainapp.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainingDto implements Comparable<TrainingDto>{

    private Long id;
    private Long userId;
    private int hours;
    private int minutes;
    private int seconds;
    private int kcal;
    private double kilometers;
    private double avgSpeed;
    private String description;
    @DateTimeFormat(pattern= "yyyy-MM-dd")
    private LocalDate trainingDate;
    private LocalTime trainingTime;

    @Override
    public int compareTo(final TrainingDto o) {
        return this.id.compareTo(o.getId());
    }
}
