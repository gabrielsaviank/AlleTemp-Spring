package com.allesys.demo.service;

import org.springframework.stereotype.Service;
import org.tensorflow.*;
import org.tensorflow.ndarray.NdArrays;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Placeholder;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.TFloat32;

import java.util.List;

@Service
public class TensorTemperatureModelService {
    TemperatureService temperatureService;

    public static final int EPOCHS = 100;
    public static final float LEARNING_RATE = 0.01f;

    public float predictAverageTemperature() {
        List<Double> tempsList = temperatureService.getLastDayTemperatures();
        int numSamples = tempsList.size();
        int numFeatures = 1;

        float[] tempsArray = new float[numSamples];
        for (int i = 0; i < numSamples; i++) {
            tempsArray[i] = tempsList.get(i).floatValue();
        }

        try (Tensor xsTensor = TFloat32.tensorOf(Shape.of(numSamples, numFeatures), data -> {
            for (int i = 0; i < numSamples; i++) {
                data.setFloat(tempsArray[i], i, 0);
            }
        });
             Tensor ysTensor = TFloat32.tensorOf(Shape.of(numSamples), data -> {
                 for (int i = 0; i < numSamples; i++) {
                     data.setFloat(tempsArray[i], i);
                 }
             });
            Graph graph = new Graph();
            Session session = new Session(graph)) {
            Ops tf = Ops.create(graph);

            Placeholder<TFloat32> xs = tf.placeholder(
                    TFloat32.class, Placeholder.shape(Shape.of(-1, numFeatures))
            );
            Placeholder<TFloat32> ys = tf.placeholder(
                    TFloat32.class, Placeholder.shape(Shape.of(-1))
            );

            // Intake Layer
            Variable<TFloat32> W1 = tf.variable(tf.random.randomUniform(tf.constant(
                    new long [] { numFeatures, 100 }),
                    TFloat32.class
            ));
            Variable<TFloat32> b1 = tf.variable(
                    tf.zeros(
                            tf.constant(new long[] { 100 }),
                            TFloat32.class
                    ));
            Operand<TFloat32> layer1 = tf.nn.relu(tf.math.add(tf.linalg.matMul(xs, W1), b1));

           // Exhaust Layer
            Variable<TFloat32> W2 = tf.variable(
                    tf.random.randomUniform(tf.constant(
                            new long[] { numFeatures, 100, 1 }),
                            TFloat32.class
                    )
            );
            Variable<TFloat32> b2 = tf.variable(
                    tf.zeros(
                            tf.constant(new long[]{1}),
                            TFloat32.class
                    ));
            Operand<TFloat32> predictions = tf.math.add(tf.linalg.matMul(layer1, W2), b2);



            // Optimiser
            Operand<TFloat32> loss = tf.math.mean(
                    tf.math.square(tf.math.sub(predictions, ys)),
                    tf.constant(0)
            );

            return 0;
        }
    }
}

