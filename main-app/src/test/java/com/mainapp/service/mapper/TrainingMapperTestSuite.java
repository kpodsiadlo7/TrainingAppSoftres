package com.mainapp.service.mapper;

import com.mainapp.MainAppApplication;
import com.mainapp.service.data.Training;
import com.mainapp.web.dto.TrainingDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MainAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TrainingMapperTestSuite {

    @Autowired
    private TrainingMapper trainingMapper;

    @Test
    void mapToTrainingFromTrainingDto(){
        //given
        TrainingDto trainingDto = createTrainingDto();
        //when
        Training trainingAfterMapper = trainingMapper.mapToTrainingFromTrainingDto(trainingDto);
        //then
        Assertions.assertEquals(7,trainingAfterMapper.getId());
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

    private TrainingDto createTrainingDto() {
        return new TrainingDto(
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
