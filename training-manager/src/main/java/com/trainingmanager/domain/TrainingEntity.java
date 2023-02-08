package com.trainingmanager.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trainings_db")
public class TrainingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public TrainingEntity(final Long userId, final int hours, final int minutes, final int seconds, final int kcal, final double kilometers, final double avgSpeed, final String description, final LocalDate trainingDate, final LocalTime trainingTime) {
        this.userId = userId;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.kcal = kcal;
        this.kilometers = kilometers;
        this.avgSpeed = avgSpeed;
        this.description = description;
        this.trainingDate = trainingDate;
        this.trainingTime = trainingTime;
    }
}
