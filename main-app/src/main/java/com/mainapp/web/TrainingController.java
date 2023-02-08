package com.mainapp.web;

import com.mainapp.service.TrainingService;
import com.mainapp.service.data.User;
import com.mainapp.web.dto.TrainingDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/training")
public class TrainingController {

    private final TrainingService trainingService;

    @GetMapping
    public String getTraining(ModelMap modelMap) {
        return trainingService.getTraining(modelMap);
    }

    @GetMapping("{trainingId}")
    public String getTrainingToUpdate(@PathVariable Long trainingId,
                                      ModelMap modelMap) {
        return trainingService.getTrainingById(trainingId, modelMap);
    }

    @PostMapping("{trainingId}")
    public String updateTrainingById(@ModelAttribute TrainingDto trainingDto,
                                     @PathVariable Long trainingId, ModelMap modelMap) {
        return trainingService.updateTraining(trainingDto, trainingId, modelMap);
    }

    @DeleteMapping("/delete/{trainingId}")
    public @ResponseBody void deleteTraining(@PathVariable Long trainingId, ModelMap modelMap) {
        trainingService.deleteTrainingById(trainingId, modelMap);
    }

    @PostMapping
    public String createNewTraining(@AuthenticationPrincipal User user,
                                    @ModelAttribute TrainingDto trainingDto,
                                    ModelMap modelMap) {
        return trainingService.validateData(user, trainingDto, modelMap);
    }
}
