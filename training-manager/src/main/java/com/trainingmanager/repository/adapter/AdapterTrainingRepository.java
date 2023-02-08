package com.trainingmanager.repository.adapter;

import com.trainingmanager.domain.TrainingEntity;

import java.util.Set;

public interface AdapterTrainingRepository {
    TrainingEntity findById(long id);
    Set<TrainingEntity> findAllByUserId(long userId);

    TrainingEntity save(TrainingEntity trainingEntity);
    boolean existsById(long id);
    void deleteById(long trainingId);
}
