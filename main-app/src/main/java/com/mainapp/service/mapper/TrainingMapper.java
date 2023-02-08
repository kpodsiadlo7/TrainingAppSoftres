package com.mainapp.service.mapper;

import com.mainapp.service.data.Training;
import com.mainapp.web.dto.TrainingDto;
import org.springframework.stereotype.Service;

@Service
public class TrainingMapper {
    public Training mapToTrainingFromTrainingDto(final TrainingDto trainingDto) {
        return new Training(
                trainingDto.getId(),
                trainingDto.getUserId(),
                trainingDto.getHours(),
                trainingDto.getMinutes(),
                trainingDto.getSeconds(),
                trainingDto.getKcal(),
                trainingDto.getKilometers(),
                trainingDto.getAvgSpeed(),
                trainingDto.getDescription(),
                trainingDto.getTrainingDate(),
                trainingDto.getTrainingTime()
        );
    }
}
