package com.allesys.demo.services;

import static org.junit.jupiter.api.Assertions.*;

import com.allesys.demo.service.TensorTemperatureModelService;
import org.junit.jupiter.api.Test;

public class TensorTemperatureModelServiceTest {
    @Test
    void testPredictNextDayAverage() {
        TensorTemperatureModelService service = new TensorTemperatureModelService();

        float[] lastDayTemps = {20.2f, 23.3f, 22.1f, 21.8f};
        float predictedAverage = service.predictNextDayAverage(lastDayTemps);

        System.out.println("Predicted Average Temperature: " + predictedAverage);
        assertTrue(predictedAverage > 0, "Predicted average should be greater than 0");
    }

    @Test
    void testEmptyInput() {
        TensorTemperatureModelService service = new TensorTemperatureModelService();
        assertThrows(IllegalArgumentException.class, () -> service.predictNextDayAverage(new float[]{}));
    }

    @Test
    void testNullInput() {
        TensorTemperatureModelService service = new TensorTemperatureModelService();
        assertThrows(IllegalArgumentException.class, () -> service.predictNextDayAverage(null));
    }
}
