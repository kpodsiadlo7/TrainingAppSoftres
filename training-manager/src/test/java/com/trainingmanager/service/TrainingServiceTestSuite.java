package com.trainingmanager.service;

import com.trainingmanager.TrainingManagerApplication;
import com.trainingmanager.domain.TrainingEntity;
import com.trainingmanager.repository.adapter.AdapterTrainingRepository;
import com.trainingmanager.service.data.Training;
import com.trainingmanager.service.mapper.TrainingMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TrainingManagerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TrainingServiceTestSuite {

    @Autowired
    private TrainingMapper trainingMapper;

    @Test
    @DisplayName("when we pass data with no time, and some kilometers, should return 0 speed")
    void calculateAvgSpeedWithoutTime() {
        //given
        Training training = new Training();
        training.setKilometers(77);
        //system under test
        var toTest = new TrainingService(null, null);
        //when
        double shouldBe0 = toTest.calculateAvgSpeed(training);
        //then
        Assertions.assertEquals(0, shouldBe0);
    }

    @Test
    @DisplayName("should correct calculate average speed when we pass correct data")
    void calculateAvgSpeed() {
        //given
        Training training = new Training();
        training.setHours(1);
        training.setMinutes(1);
        training.setSeconds(7);
        training.setKilometers(5);
        //system under test
        var toTest = new TrainingService(null, null);
        //when
        double shouldBe5 = toTest.calculateAvgSpeed(training);
        //then
        Assertions.assertEquals(4.91, shouldBe5);
    }

    @Test
    void updateTrainingById() {
        //given
        TrainingEntity trainingEntity = makeFullDataTraining();
        //and
        var mockRepo = mock(AdapterTrainingRepository.class);
        when(mockRepo.findById(anyLong())).thenReturn(trainingEntity);
        //and
        //system under test
        var toTest = new TrainingService(mockRepo, trainingMapper);
        //when
        Training trainingAfterEdit = toTest.updateTraining(trainingUpdated(), 0L);
        //then
        Assertions.assertEquals(1, trainingAfterEdit.getHours());
        Assertions.assertEquals(1, trainingAfterEdit.getMinutes());
        Assertions.assertEquals(1, trainingAfterEdit.getSeconds());
        Assertions.assertEquals(1, trainingAfterEdit.getKcal());
        Assertions.assertEquals(1, trainingAfterEdit.getKilometers());
        Assertions.assertEquals(0.98, trainingAfterEdit.getAvgSpeed());
        Assertions.assertEquals("destructive", trainingAfterEdit.getDescription());
    }

    private TrainingEntity makeFullDataTraining() {
        return new TrainingEntity(
                null,
                null,
                13,
                13,
                13,
                13,
                13,
                13,
                "description",
                null,
                null
        );
    }

    private Training trainingUpdated() {
        return new Training(
                null,
                null,
                1,
                1,
                1,
                1,
                1,
                1,
                "destructive",
                null,
                null
        );
    }

}
