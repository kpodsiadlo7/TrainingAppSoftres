package com.mainapp.service;

import com.mainapp.service.data.Training;
import com.mainapp.service.data.User;
import com.mainapp.service.mapper.TrainingMapper;
import com.mainapp.web.dto.TrainingDto;
import com.mainapp.web.feign.FeignTrainingManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainingService {

    private final FeignTrainingManager feignTrainingManager;
    private final TrainingMapper trainingMapper;

    public String getTraining(ModelMap modelMap) {
        modelMap.put("trainingDto", new TrainingDto());
        return "training";
    }

    public String validateData(final User user, final TrainingDto trainingDto, ModelMap modelMap) {
        if (trainingDto.getTrainingDate().isAfter(LocalDate.now())) {
            modelMap.put("trainingDto", new TrainingDto());
            modelMap.put("error", "Sorry but you can't plan your training for next days ;)");
            log.warn("date is future");
            return "training";
        }
        return createNewTraining(user, trainingDto, modelMap);
    }

    public String createNewTraining(final User user, final TrainingDto trainingDto, ModelMap modelMap) {
        trainingDto.setUserId(user.getId());
        try {
            feignTrainingManager.createNewTraining(trainingDto);
        } catch (Exception e) {
            modelMap.put("trainingDto", new TrainingDto());
            modelMap.put("error", "Problem with fetching your trainings list");
            log.warn("problem connecting with training manager");
            return "training";
        }
        return "redirect:/main";
    }

    public String updateTraining(TrainingDto trainingDto, final Long trainingId, final ModelMap modelMap) {
        Training training;
        try {
            training = trainingMapper.mapToTrainingFromTrainingDto(feignTrainingManager.updateTrainingById(trainingDto, trainingId));
        } catch (Exception e) {
            log.warn("failed connecting with training manager");
            modelMap.put("error", "Problem with fetching your training");
            modelMap.put("trainingDto", new TrainingDto());
            return "training";
        }
        modelMap.put("trainingDto", training);
        return "redirect:/main";
    }

    public String getTrainingById(final Long trainingId, ModelMap modelMap) {
        Training trainingToUpdate;
        try {
            trainingToUpdate = trainingMapper.mapToTrainingFromTrainingDto(feignTrainingManager.getTrainingById(trainingId));
        } catch (Exception e) {
            log.warn("failed connecting with training manager");
            modelMap.put("error", "Problem with fetching your training");
            modelMap.put("trainingDto", new TrainingDto());
            return "uptraining";
        }
        modelMap.put("trainingDto", trainingToUpdate);
        return "uptraining";
    }

    public void deleteTrainingById(final Long trainingId, final ModelMap modelMap) {
        try {
            if (!feignTrainingManager.deleteTrainingById(trainingId)) {
                modelMap.put("error", "Training with given id doesn't exist!");
                modelMap.put("trainingDto", new TrainingDto());
            }
        } catch (Exception e) {
            log.warn("failed connecting with training manager");
            modelMap.put("error", "Problem connecting with training manager");
            modelMap.put("trainingDto", new TrainingDto());
        }
    }
}
