package com.mainapp.service;

import com.mainapp.service.data.User;
import com.mainapp.web.dto.TrainingDto;
import com.mainapp.web.feign.FeignTrainingManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.Set;
import java.util.TreeSet;

@Slf4j
@Service
@RequiredArgsConstructor
public class MainService {

    private final FeignTrainingManager feignTrainingManager;

    public String getMain(final User user, final ModelMap modelMap) {
        try {
            Set<TrainingDto> trainings = feignTrainingManager.getAllTrainingByUserId(user.getId());
            TreeSet<TrainingDto> sortedTrainingById = new TreeSet<>(trainings);
            modelMap.put("trainingsDto", sortedTrainingById);
        } catch (Exception e) {
            modelMap.put("error", "Problem with fetching your training list");
            modelMap.put("trainingsDto", new TrainingDto());
            log.warn("problem connecting with training manager");
            return "main";
        }
        return "main";
    }
}
