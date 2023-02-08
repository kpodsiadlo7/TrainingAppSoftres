package com.mainapp.web.feign;

import com.mainapp.web.dto.TrainingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@FeignClient(value = "training-manager", url = "http://training-manager:8090")
public interface FeignTrainingManager {

    @GetMapping("/all-trainings")
    Set<TrainingDto> getAllTrainingByUserId(@RequestParam Long userId);

    @PostMapping("/update-training")
    TrainingDto updateTrainingById(@RequestBody TrainingDto trainingDto,
                                   @RequestParam Long trainingId);

    @PostMapping
    void createNewTraining(@RequestBody TrainingDto trainingDto);

    @GetMapping
    TrainingDto getTrainingById(@RequestParam Long trainingId);

    @DeleteMapping("/delete/{trainingId}")
    boolean deleteTrainingById(@PathVariable Long trainingId);
}
