package com.allesys.demo.services;

import com.allesys.demo.service.TemperatureService;
import com.allesys.demo.service.TensorTemperatureModelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class TensorTemperatureModelServiceTest {
    @Test
    void testPredictNextDayAverage() {
        System.out.println(System.getProperty("java.library.path"));
        TensorTemperatureModelService service = new TensorTemperatureModelService();

        float[] lastDayTemps = {20.2f, 23.3f, 22.1f, 21.8f};

        float predictedAverage = service.predictNextDayAverage(lastDayTemps);

        System.out.println("Predicted Average Temperature: " + predictedAverage);

        assertTrue(predictedAverage > 0, "Predicted average should be greater than 0");
    }
}
