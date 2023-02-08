package com.trainingmanager.service.mapper;

import com.trainingmanager.TrainingManagerApplication;
import com.trainingmanager.domain.TrainingEntity;
import com.trainingmanager.service.data.Training;
import com.trainingmanager.web.dto.TrainingDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TrainingManagerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TrainingMapperTestSuite {

    @Autowired
    private TrainingMapper trainingMapper;

    @Test
    void mapToTrainingDtoFromTraining(){
        //given
        Training training = createTraining();
        //when
        TrainingDto trainingAfterMapper = trainingMapper.mapToTrainingDtoFromTraining(training);
        //then
        Assertions.assertEquals(7L,trainingAfterMapper.getId());
        Assertions.assertEquals(8L,trainingAfterMapper.getUserId());
        Assertions.assertEquals(1L,trainingAfterMapper.getHours());
        Assertions.assertEquals(1L,trainingAfterMapper.getMinutes());
        Assertions.assertEquals(1L,trainingAfterMapper.getSeconds());
        Assertions.assertEquals(3L,trainingAfterMapper.getKcal());
        Assertions.assertEquals(12L,trainingAfterMapper.getKilometers());
        Assertions.assertEquals(5L,trainingAfterMapper.getAvgSpeed());
        Assertions.assertEquals("desc",trainingAfterMapper.getDescription());
        Assertions.assertEquals(LocalDate.now().minusDays(1),trainingAfterMapper.getTrainingDate());
    }

    @Test
    void mapToTrainingFromTrainingEntity(){
        //given
        TrainingEntity trainingEntity = createTrainingEntity();
        //when
        Training trainingAfterMapper = trainingMapper.mapToTrainingFromTrainingEntity(trainingEntity);
        //then
        Assertions.assertEquals(7L,trainingAfterMapper.getId());
        Assertions.assertEquals(8L,trainingAfterMapper.getUserId());
        Assertions.assertEquals(1L,trainingAfterMapper.getHours());
        Assertions.assertEquals(1L,trainingAfterMapper.getMinutes());
        Assertions.assertEquals(1L,trainingAfterMapper.getSeconds());
        Assertions.assertEquals(3L,trainingAfterMapper.getKcal());
        Assertions.assertEquals(12L,trainingAfterMapper.getKilometers());
        Assertions.assertEquals(5L,trainingAfterMapper.getAvgSpeed());
        Assertions.assertEquals("desc",trainingAfterMapper.getDescription());
        Assertions.assertEquals(LocalDate.now().minusDays(1),trainingAfterMapper.getTrainingDate());
    }


    @Test
    void mapToTrainingFromTrainingDto(){
        //given
        TrainingDto trainingDto = new TrainingDto(
                7L,
                8L,
                1,
                1,
                1,
                3,
                12,
                5,
                "desc",
                LocalDate.now().minusDays(1),
                null
        );
        //when
        Training trainingAfterMapper = trainingMapper.mapToTrainingFromTrainingDto(trainingDto);
        //then
        Assertions.assertEquals(7L,trainingAfterMapper.getId());
        Assertions.assertEquals(8L,trainingAfterMapper.getUserId());
        Assertions.assertEquals(1L,trainingAfterMapper.getHours());
        Assertions.assertEquals(1L,trainingAfterMapper.getMinutes());
        Assertions.assertEquals(1L,trainingAfterMapper.getSeconds());
        Assertions.assertEquals(3L,trainingAfterMapper.getKcal());
        Assertions.assertEquals(12L,trainingAfterMapper.getKilometers());
        Assertions.assertEquals(5L,trainingAfterMapper.getAvgSpeed());
        Assertions.assertEquals("desc",trainingAfterMapper.getDescription());
        Assertions.assertEquals(LocalDate.now().minusDays(1),trainingAfterMapper.getTrainingDate());
    }

    @Test
    void mapToTrainingEntityFromTraining(){
        //given
        Training training = createTraining();
        //when
        TrainingEntity trainingAfterMapper = trainingMapper.mapToTrainingEntityFromTraining(training);
        //then
        Assertions.assertNull(trainingAfterMapper.getId());
        Assertions.assertEquals(8L,trainingAfterMapper.getUserId());
        Assertions.assertEquals(1L,trainingAfterMapper.getHours());
        Assertions.assertEquals(1L,trainingAfterMapper.getMinutes());
        Assertions.assertEquals(1L,trainingAfterMapper.getSeconds());
        Assertions.assertEquals(3L,trainingAfterMapper.getKcal());
        Assertions.assertEquals(12L,trainingAfterMapper.getKilometers());
        Assertions.assertEquals(5L,trainingAfterMapper.getAvgSpeed());
        Assertions.assertEquals("desc",trainingAfterMapper.getDescription());
        Assertions.assertEquals(LocalDate.now().minusDays(1),trainingAfterMapper.getTrainingDate());
    }

    @Test
    void updateTrainingEntity(){
        //given
        Training training = createTraining();
        //when
        TrainingEntity trainingAfterMapper = trainingMapper.updateTrainingEntity(training);
        //then
        Assertions.assertEquals(1L,trainingAfterMapper.getHours());
        Assertions.assertEquals(1L,trainingAfterMapper.getMinutes());
        Assertions.assertEquals(1L,trainingAfterMapper.getSeconds());
        Assertions.assertEquals(3L,trainingAfterMapper.getKcal());
        Assertions.assertEquals(12L,trainingAfterMapper.getKilometers());
        Assertions.assertEquals("desc",trainingAfterMapper.getDescription());
    }

    @Test
    void mapToTrainingSetFromTrainingEntitySet(){
        //given
        Set<TrainingEntity> trainingEntitySet = new HashSet<>();
        trainingEntitySet.add(createTrainingEntity());
        //when
        Set<Training> trainingAfterMapperSet = trainingMapper.mapToTrainingSetFromTrainingEntitySet(trainingEntitySet);
        //then
        Assertions.assertTrue(trainingAfterMapperSet.stream()
                .anyMatch(t -> t.getId().equals(7L)));

        Assertions.assertTrue(trainingAfterMapperSet.stream()
                .anyMatch(t -> t.getUserId().equals(8L)));

        Assertions.assertTrue(trainingAfterMapperSet.stream()
                .anyMatch(t -> t.getHours() == 1));

        Assertions.assertTrue(trainingAfterMapperSet.stream()
                .anyMatch(t -> t.getMinutes() == 1));

        Assertions.assertTrue(trainingAfterMapperSet.stream()
                .anyMatch(t -> t.getSeconds() == 1));

        Assertions.assertTrue(trainingAfterMapperSet.stream()
                .anyMatch(t -> t.getKcal() == 3));

        Assertions.assertTrue(trainingAfterMapperSet.stream()
                .anyMatch(t -> t.getKilometers() == 12));

        Assertions.assertTrue(trainingAfterMapperSet.stream()
                .anyMatch(t -> t.getAvgSpeed() == 5));

        Assertions.assertTrue(trainingAfterMapperSet.stream()
                .anyMatch(t -> t.getDescription().equals("desc")));

        Assertions.assertTrue(trainingAfterMapperSet.stream()
                .anyMatch(t -> t.getTrainingDate().equals(LocalDate.now().minusDays(1))));
    }

    @Test
    void mapToTrainingDtoSetFromTrainingSet(){
        //given
        Set<Training> trainings = new HashSet<>();
        trainings.add(createTraining());
        //when
        Set<TrainingDto> trainingAfterMapperSet = trainingMapper.mapToTrainingDtoSetFromTrainingSet(trainings);
        //then
        Assertions.assertTrue(trainingAfterMapperSet.stream()
                .anyMatch(t -> t.getId().equals(7L)));

        Assertions.assertTrue(trainingAfterMapperSet.stream()
                .anyMatch(t -> t.getUserId().equals(8L)));

        Assertions.assertTrue(trainingAfterMapperSet.stream()
                .anyMatch(t -> t.getHours() == 1));

        Assertions.assertTrue(trainingAfterMapperSet.stream()
                .anyMatch(t -> t.getMinutes() == 1));

        Assertions.assertTrue(trainingAfterMapperSet.stream()
                .anyMatch(t -> t.getSeconds() == 1));

        Assertions.assertTrue(trainingAfterMapperSet.stream()
                .anyMatch(t -> t.getKcal() == 3));

        Assertions.assertTrue(trainingAfterMapperSet.stream()
                .anyMatch(t -> t.getKilometers() == 12));

        Assertions.assertTrue(trainingAfterMapperSet.stream()
                .anyMatch(t -> t.getAvgSpeed() == 5));

        Assertions.assertTrue(trainingAfterMapperSet.stream()
                .anyMatch(t -> t.getDescription().equals("desc")));

        Assertions.assertTrue(trainingAfterMapperSet.stream()
                .anyMatch(t -> t.getTrainingDate().equals(LocalDate.now().minusDays(1))));
    }

    private Training createTraining() {
        return new Training(
                7L,
                8L,
                1,
                1,
                1,
                3,
                12,
                5,
                "desc",
                LocalDate.now().minusDays(1),
                null
        );
    }
    private TrainingEntity createTrainingEntity() {
        return new TrainingEntity(
                7L,
                8L,
                1,
                1,
                1,
                3,
                12,
                5,
                "desc",
                LocalDate.now().minusDays(1),
                null
        );
    }
}
