package com.trainingmanager.repository;

import com.trainingmanager.domain.TrainingEntity;
import com.trainingmanager.repository.adapter.AdapterTrainingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends AdapterTrainingRepository, JpaRepository<TrainingEntity, Long> {

}
