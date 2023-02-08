package com.trainingmanager.service;

import com.trainingmanager.repository.adapter.AdapterTrainingRepository;
import com.trainingmanager.service.data.Training;
import com.trainingmanager.service.mapper.TrainingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainingService {

    private final AdapterTrainingRepository adapterTrainingRepository;
    private final TrainingMapper trainingMapper;

    public Training getTrainingByTrainingId(final Long trainingId) {
        return trainingMapper.mapToTrainingFromTrainingEntity
                (adapterTrainingRepository.findById(trainingId));
    }

    public Set<Training> getAllTrainingByUserId(final Long userId) {
        return trainingMapper.mapToTrainingSetFromTrainingEntitySet
                (adapterTrainingRepository.findAllByUserId(userId));
    }

    public Training createNewTraining(final Training training) {
        log.info(training.toString());
        training.setAvgSpeed(calculateAvgSpeed(training));
        training.setTrainingTime(trainingTime(training));
        return trainingMapper.mapToTrainingFromTrainingEntity
                (adapterTrainingRepository.save(trainingMapper.mapToTrainingEntityFromTraining(training)));
    }

    public double calculateAvgSpeed(final Training training) {
        double time = training.getHours() * 3600 + training.getMinutes() * 60 + training.getSeconds();
        if (time == 0)
            return 0.0;
        double avgSpeed = training.getKilometers() / (time / 3600);
        return Math.round(avgSpeed * 100.0) / 100.0;
    }

    public LocalTime trainingTime(final Training training){
        LocalTime localTime = LocalTime.of(
                training.getHours(),
                training.getMinutes(),
                training.getSeconds());
        log.info(localTime.toString());
        return localTime;
    }

    public Training updateTrainingById(final Training training, final Long trainingId) {
        if (!adapterTrainingRepository.existsById(trainingId)) {
            return new Training();
        }
        return trainingMapper.mapToTrainingFromTrainingEntity
                (adapterTrainingRepository.save
                        (trainingMapper.updateTrainingEntity(updateTraining(training, trainingId))));
    }

    public Training updateTraining(final Training training, final Long trainingId) {
        Training toUpdate = trainingMapper.mapToTrainingFromTrainingEntity(adapterTrainingRepository.findById(trainingId));
        toUpdate.setKcal(training.getKcal());
        toUpdate.setHours(training.getHours());
        toUpdate.setMinutes(training.getMinutes());
        toUpdate.setSeconds(training.getSeconds());
        toUpdate.setKilometers(training.getKilometers());
        toUpdate.setAvgSpeed(calculateAvgSpeed(training));
        toUpdate.setDescription(training.getDescription());
        toUpdate.setTrainingTime(trainingTime(training));
        return toUpdate;
    }

    public boolean deleteTrainingById(final Long trainingId) {
        if (!adapterTrainingRepository.existsById(trainingId))
            return false;
        adapterTrainingRepository.deleteById(trainingId);
        return true;
    }
}
