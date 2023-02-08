package com.trainingmanager.service.mapper;

import com.trainingmanager.domain.TrainingEntity;
import com.trainingmanager.service.data.Training;
import com.trainingmanager.web.dto.TrainingDto;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TrainingMapper {

    public TrainingDto mapToTrainingDtoFromTraining(final Training training){
        return new TrainingDto(
                training.getId(),
                training.getUserId(),
                training.getHours(),
                training.getMinutes(),
                training.getSeconds(),
                training.getKcal(),
                training.getKilometers(),
                training.getAvgSpeed(),
                training.getDescription(),
                training.getTrainingDate(),
                training.getTrainingTime()
        );
    }

    public Training mapToTrainingFromTrainingEntity(final TrainingEntity trainingEntity) {
        return new Training(
                trainingEntity.getId(),
                trainingEntity.getUserId(),
                trainingEntity.getHours(),
                trainingEntity.getMinutes(),
                trainingEntity.getSeconds(),
                trainingEntity.getKcal(),
                trainingEntity.getKilometers(),
                trainingEntity.getAvgSpeed(),
                trainingEntity.getDescription(),
                trainingEntity.getTrainingDate(),
                trainingEntity.getTrainingTime()
        );
    }

    public Set<Training> mapToTrainingSetFromTrainingEntitySet(final Set<TrainingEntity> trainingEntities) {
        Set<Training> trainings = new HashSet<>();
        for (TrainingEntity e: trainingEntities){
            trainings.add(mapToTrainingFromTrainingEntity(e));
        }
        return trainings;
    }

    public Set<TrainingDto> mapToTrainingDtoSetFromTrainingSet(final Set<Training> trainings) {
        Set<TrainingDto> trainingDtos = new HashSet<>();
        for (Training t: trainings){
            trainingDtos.add(mapToTrainingDtoFromTraining(t));
        }
        return trainingDtos;
    }

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

    public TrainingEntity mapToTrainingEntityFromTraining(final Training training) {
        return new TrainingEntity(
                training.getUserId(),
                training.getHours(),
                training.getMinutes(),
                training.getSeconds(),
                training.getKcal(),
                training.getKilometers(),
                training.getAvgSpeed(),
                training.getDescription(),
                training.getTrainingDate(),
                training.getTrainingTime()
        );
    }

    public TrainingEntity updateTrainingEntity(final Training training){
        return new TrainingEntity(
                training.getId(),
                training.getUserId(),
                training.getHours(),
                training.getMinutes(),
                training.getSeconds(),
                training.getKcal(),
                training.getKilometers(),
                training.getAvgSpeed(),
                training.getDescription(),
                training.getTrainingDate(),
                training.getTrainingTime()
        );
    }
}
