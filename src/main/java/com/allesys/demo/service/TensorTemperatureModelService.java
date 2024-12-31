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
            lastDayTemps = new float[]{20.2f, 23.3f, 22.1f, 21.8f};
        }


        try (Graph graph = new Graph();
             Session session = new Session(graph)) {
            return 1;
//            Ops tf = Ops.create(graph);
//
//            Placeholder<TFloat32> tempsInput = tf.placeholder(TFloat32.class, Placeholder.shape(Shape.of(lastDayTemps.length)));
//            Variable<TFloat32> weights = tf.variable(tf.fill(tf.constant(new long[]{lastDayTemps.length}), tf.constant(1.0f)));
//            Variable<TFloat32> bias = tf.variable(tf.constant(1.0f));
//
//            Operand<TFloat32> weightedSum = tf.math.add(tf.math.mul(tempsInput, weights), bias);
//
//            Operand<TFloat32> average = tf.math.mean(weightedSum, tf.constant(0));
//
//            Operand<TFloat32> weightsInit = tf.fill(tf.constant(new long[]{lastDayTemps.length}), tf.constant(1.0f));
//            Operand<TFloat32> biasInit = tf.constant(1.0f);
//            session.runner()
//                    .addTarget(tf.assign(weights, weightsInit))
//                    .addTarget(tf.assign(bias, biasInit))
//                    .run();
//
//            final float[] temps = lastDayTemps;
//            Tensor inputTensor = TFloat32.tensorOf(Shape.of(temps.length), data -> {
//                for (int i = 0; i < temps.length; i++) {
//                    data.setFloat(temps[i], i);
//                }
//            });
//
//
//            Tensor resultTensor = session.runner()
//                    .feed(tempsInput.asOutput(), inputTensor)
//                    .fetch(average)
//                    .run()
//                    .get(0);
//
//
//            float result = ((TFloat32) resultTensor).getFloat();
//
//            resultTensor.close();
//            inputTensor.close();
//
//            return result;
        }
    }
}



//@Service
//public class TensorTemperatureModelService {
//    public TemperatureService temperatureService;
//
//    public static final int EPOCHS = 100;
//    public static final float LEARNING_RATE = 0.01f;
//
//    public float predictAverageTemperature() {
//        List<Double> tempsList = temperatureService.getLastDayTemperatures();
//        int numSamples = tempsList.size();
//        int numFeatures = 1;
//
//        float[] tempsArray = new float[numSamples];
//        for (int i = 0; i < numSamples; i++) {
//            tempsArray[i] = tempsList.get(i).floatValue();
//        }
//
//        try (Tensor xsTensor = TFloat32.tensorOf(Shape.of(numSamples, numFeatures), data -> {
//            for (int i = 0; i < numSamples; i++) {
//                data.setFloat(tempsArray[i], i, 0);
//            }
//        });
//             Tensor ysTensor = TFloat32.tensorOf(Shape.of(numSamples), data -> {
//                 for (int i = 0; i < numSamples; i++) {
//                     data.setFloat(tempsArray[i], i);
//                 }
//             });
//            Graph graph = new Graph();
//            Session session = new Session(graph)) {
//            Ops tf = Ops.create(graph);
//
//            Placeholder<TFloat32> xs = tf.placeholder(
//                    TFloat32.class, Placeholder.shape(Shape.of(-1, numFeatures))
//            );
//            Placeholder<TFloat32> ys = tf.placeholder(
//                    TFloat32.class, Placeholder.shape(Shape.of(-1))
//            );
//
//            // Intake Layer
//            Variable<TFloat32> W1 = tf.variable(tf.random.randomUniform(tf.constant(
//                    new long [] { numFeatures, 100 }),
//                    TFloat32.class
//            ));
//            Variable<TFloat32> b1 = tf.variable(
//                    tf.zeros(
//                            tf.constant(new long[] { 100 }),
//                            TFloat32.class
//                    ));
//            Operand<TFloat32> layer1 = tf.nn.relu(tf.math.add(tf.linalg.matMul(xs, W1), b1));
//
//           // Exhaust Layer
//            Variable<TFloat32> W2 = tf.variable(
//                    tf.random.randomUniform(tf.constant(
//                            new long[] { 100, 1 }),
//                            TFloat32.class
//                    )
//            );
//            Variable<TFloat32> b2 = tf.variable(
//                    tf.zeros(
//                            tf.constant(new long[]{1}),
//                            TFloat32.class
//                    ));
//
//            Operand<TFloat32> predictions = tf.math.add(tf.linalg.matMul(layer1, W2), b2);
//
//            // Loss and Optimiser
//            Operand<TFloat32> loss = tf.math.mean(
//                    tf.math.square(tf.math.sub(predictions, ys)),
//                    tf.constant(0)
//            );
//
//            GradientDescent gradientDescent = new GradientDescent(graph, LEARNING_RATE);
//
//            Op trainOp = gradientDescent.minimize(loss);
//
//
//            Operand<TFloat32> W1Init = tf.random.randomUniform(tf.constant(new long[]{numFeatures, 100}), TFloat32.class);
//            Operand<TFloat32> b1Init = tf.zeros(tf.constant(new long[]{100}), TFloat32.class);
//            Operand<TFloat32> W2Init = tf.random.randomUniform(tf.constant(new long[]{100, 1}), TFloat32.class);
//            Operand<TFloat32> b2Init = tf.zeros(tf.constant(new long[]{1}), TFloat32.class);
//
//            Op W1InitOp = tf.assign(W1, W1Init);
//            Op b1InitOp = tf.assign(b1, b1Init);
//            Op W2InitOp = tf.assign(W2, W2Init);
//            Op b2InitOp = tf.assign(b2, b2Init);
//
//            session.runner()
//                    .addTarget(W1InitOp)
//                    .addTarget(b1InitOp)
//                    .addTarget(W2InitOp)
//                    .addTarget(b2InitOp)
//                    .run();
//
//            for(int epoch = 0; epoch < EPOCHS; epoch++) {
//                session.runner()
//                        .feed(xs.asOutput(), xsTensor)
//                        .feed(ys.asOutput(), ysTensor)
//                        .addTarget(trainOp)
//                        .run();
//
//                Tensor lossValue = session.runner()
//                        .feed(xs.asOutput(), xsTensor)
//                        .feed(ys.asOutput(), ysTensor)
//                        .fetch(loss)
//                        .run().get(0);
//
//                float lossVal = ((TFloat32) lossValue).getFloat();
//
//                System.out.println("Epoch " + epoch + ", Loss: " + lossVal);
//                lossValue.close();
//            }
//
//            Tensor outputTensor = session.runner()
//                    .feed(xs.asOutput(), xsTensor)
//                    .fetch(predictions)
//                    .run().get(0);
//
//            float[] predictedValues = new float[numSamples];
//            for (int i = 0; i < numSamples; i++) {
//                predictedValues[i] = ((TFloat32) outputTensor).getFloat(i, 0);
//            }
//            outputTensor.close();
//
//            float sum = 0;
//            for (float val : predictedValues) {
//                sum += val;
//            }
//            float predictedAverage = sum / predictedValues.length;
//
//            System.out.println("Predicted Average Temperature: " + predictedAverage);
//
//            return predictedAverage;
//        }
//    }
//}

