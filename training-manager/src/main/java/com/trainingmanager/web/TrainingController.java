package com.trainingmanager.web;

import com.trainingmanager.service.TrainingService;
import com.trainingmanager.service.mapper.TrainingMapper;
import com.trainingmanager.web.dto.TrainingDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingService trainingService;
    private final TrainingMapper trainingMapper;

    @GetMapping
    public ResponseEntity<TrainingDto> getTrainingByTrainingId(@RequestParam Long trainingId) {
        return ResponseEntity.ok(trainingMapper.mapToTrainingDtoFromTraining
                (trainingService.getTrainingByTrainingId(trainingId)));
    }

    @GetMapping("/all-trainings")
    public ResponseEntity<Set<TrainingDto>> getAllTrainingByUserId(@RequestParam Long userId){
        return ResponseEntity.ok(trainingMapper.mapToTrainingDtoSetFromTrainingSet(
                (trainingService.getAllTrainingByUserId(userId))));
    }

    @PostMapping
    public ResponseEntity<TrainingDto> createNewTraining(@RequestBody TrainingDto trainingDto){
        log.info(trainingDto.getTrainingDate().toString());
        return ResponseEntity.ok(trainingMapper.mapToTrainingDtoFromTraining
                (trainingService.createNewTraining
                        (trainingMapper.mapToTrainingFromTrainingDto(trainingDto))));
    }

    @PostMapping("/update-training")
    public ResponseEntity<TrainingDto> updateTrainingById(@RequestBody TrainingDto trainingDto,
                                                          @RequestParam Long trainingId){
        return ResponseEntity.ok(trainingMapper.mapToTrainingDtoFromTraining
                (trainingService.updateTrainingById
                        (trainingMapper.mapToTrainingFromTrainingDto(trainingDto),trainingId)));
    }

    @DeleteMapping("/delete/{trainingId}")
    public ResponseEntity<Boolean> deleteTrainingById(@PathVariable Long trainingId){
        return ResponseEntity.ok(trainingService.deleteTrainingById(trainingId));
    }
}
