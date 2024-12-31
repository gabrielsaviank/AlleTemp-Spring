package com.allesys.demo.service;

import org.springframework.stereotype.Service;


import org.tensorflow.*;
import org.tensorflow.ndarray.Shape;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Placeholder;
import org.tensorflow.op.core.Variable;
import org.tensorflow.types.TFloat32;

@Service
public class TensorTemperatureModelService {
    public float predictNextDayAverage(float[] lastDayTemps) {
        if (lastDayTemps == null || lastDayTemps.length == 0) {
            throw new IllegalArgumentException("Temperature array cannot be null or empty");
        }

        try (Graph graph = new Graph();
             Session session = new Session(graph)) {
            Ops tf = Ops.create(graph);

            Placeholder<TFloat32> tempsInput = tf.placeholder(TFloat32.class, Placeholder.shape(Shape.of(lastDayTemps.length)));
            Variable<TFloat32> weights = tf.variable(tf.fill(tf.constant(new long[]{lastDayTemps.length}), tf.constant(1.0f)));
            Variable<TFloat32> bias = tf.variable(tf.constant(1.0f));

            Operand<TFloat32> weightedSum = tf.math.add(tf.math.mul(tempsInput, weights), bias);

            Operand<TFloat32> average = tf.math.mean(weightedSum, tf.constant(0));

            Operand<TFloat32> weightsInit = tf.fill(tf.constant(new long[]{lastDayTemps.length}), tf.constant(1.0f));
            Operand<TFloat32> biasInit = tf.constant(1.0f);
            session.runner()
                    .addTarget(tf.assign(weights, weightsInit))
                    .addTarget(tf.assign(bias, biasInit))
                    .run();

            final float[] temps = lastDayTemps;
            Tensor inputTensor = TFloat32.tensorOf(Shape.of(temps.length), data -> {
                for (int i = 0; i < temps.length; i++) {
                    data.setFloat(temps[i], i);
                }
            });

            Tensor resultTensor = session.runner()
                    .feed(tempsInput.asOutput(), inputTensor)
                    .fetch(average)
                    .run()
                    .get(0);

            float result = ((TFloat32) resultTensor).getFloat();

            resultTensor.close();
            inputTensor.close();

            return result;
        }

    }
}
