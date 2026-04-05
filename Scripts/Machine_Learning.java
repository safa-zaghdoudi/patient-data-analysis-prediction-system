/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package com.mycompany.mavenproject1;
package com.mycompany.mavenproject1;



import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.ml.classification.DecisionTreeClassifier;
import org.apache.spark.ml.classification.DecisionTreeClassificationModel;
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator;
import org.apache.spark.ml.feature.VectorAssembler;
import org.apache.spark.sql.functions;

import java.util.List;

public class Machine_Learning {

    public void trainAndPredictDiabetesRisk(SparkSession spark, List<Patients> patientsList) {
        // Convert List<Patients> to Spark DataFrame
        Dataset<Row> data = convertPatientsListToDataFrame(spark, patientsList);

        // Prepare data: Assemble features and create label column
        Dataset<Row> preparedData = prepareDataForTraining(data);

        // Split data into training and testing sets
        Dataset<Row>[] splits = preparedData.randomSplit(new double[]{0.8, 0.2}, 42);
        Dataset<Row> trainingData = splits[0];
        Dataset<Row> testData = splits[1];

        // Train a Decision Tree model
        DecisionTreeClassifier dt = new DecisionTreeClassifier()
                .setLabelCol("label")
                .setFeaturesCol("features");

        DecisionTreeClassificationModel model = dt.fit(trainingData);

        // Make predictions
        Dataset<Row> predictions = model.transform(testData);

        // Evaluate the model
        MulticlassClassificationEvaluator evaluator = new MulticlassClassificationEvaluator()
                .setLabelCol("label")
                .setPredictionCol("prediction")
                .setMetricName("accuracy");

        double accuracy = evaluator.evaluate(predictions);
        System.out.println("Model Accuracy: " + accuracy);

        // Show predictions
        predictions.groupBy("label", "prediction").count().show();
    }

    private Dataset<Row> convertPatientsListToDataFrame(SparkSession spark, List<Patients> patientsList) {
        return spark.createDataFrame(patientsList, Patients.class);
    }

    private Dataset<Row> prepareDataForTraining(Dataset<Row> data) {
        VectorAssembler assembler = new VectorAssembler()
                .setInputCols(new String[]{"Glucose", "BMI", "Age", "DiabetesPedigreeFunction"})
                .setOutputCol("features");

        Dataset<Row> assembledData = assembler.transform(data);

        // Convert the diabetes status to a numerical label
        return assembledData.withColumn("label", functions.col("Diabetes Status").cast("double"));
    }
}

 
    
    