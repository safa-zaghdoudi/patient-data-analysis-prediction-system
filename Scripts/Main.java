
package com.mycompany.mavenproject1;

import org.apache.spark.sql.SparkSession;
import java.util.*;
import java.io.*;


public class Main {

    public static void main(String[] args) throws IOException {  
// I/ Initial Data loading (data cleaning)
       // Path to the CSV file on your desktop
        String filePath ="C:\\Users\\ASUS\\OneDrive\\Desktop\\OOp\\oop project\\Patients.csv"; 
        String outputFilePath = "C:\\Users\\ASUS\\OneDrive\\Desktop\\OOp\\oop project\\PatientCleaned.csv" ;

        Data_Ingestion dataIngestion = new Data_Ingestion();
        
        Data_Cleaning cleaneddata = new Data_Cleaning();
        
        //Loading the data : Call the loadData method
        List<String[]> ingestedData = dataIngestion.loadData(filePath);
        
        dataIngestion.displayData(ingestedData);
        
        
        
        //Data Cleaning : Setting header name, Removing outliers ,and handling uncorrect values
        cleaneddata.SetHeaderName(ingestedData);
        cleaneddata.RemoveOutliers(ingestedData, "age ", 15, 80);
        cleaneddata.HandleUncorrectValues(ingestedData);
        cleaneddata.displayData(ingestedData);
        Data_Cleaning.saveToCSV(ingestedData, outputFilePath);
        //System.out.println("Cleaned data exported successfully to: " + outputFilePath);
        
        
    

//  I/ fetching the data
//        DBConnect dbConnect = new DBConnect();
//        
//        //This helps us get the data of patients in a tabular format
//        List<Patients> patientsList = dbConnect.getAllPatientsAsObjects();
//
//        System.out.printf("%-10s %-10s %-10s %-15s %-15s %-10s %-15s %-10s %-10s %-10s%n",
//                "PatientID", "PersonID", "Glucose", "BloodPressure", "SkinThickness",
//                "Insulin", "BMI", "DiabetesPedigree", "Age", "Outcome");
//        System.out.println("--------------------------------------------------------------------------------------------------------");
//
//        for (Patients patient : patientsList) {
//            System.out.printf("%-10d %-10d %-10d %-15d %-15d %-10d %-15.2f %-10.2f %-10d %-10d%n",
//                    patient.GetPatientID(),
//                    patient.GetID(),
//                    patient.GetGlucose(),
//                    patient.GetBloodpressure(),
//                    patient.GetSkinThickness(),
//                    patient.GetInsulin(),
//                    patient.GetBMI(),
//                    patient.GetDiabetesPedigreeFunction(),
//                    patient.GetAge(),
//                    patient.GetOutcome());
//        SparkSession spark = SparkSession.builder()
//                .appName("Diabetes Prediction")
//                .master("local[*]") // Adjust for your environment
//                .getOrCreate();
//        }
        DBConnect dbConnect = new DBConnect();

// Get the data of patients in a tabular format
        List<Patients> patientsList = dbConnect.getAllPatientsAsObjects();

        System.out.printf("%-10s %-10s %-10s %-15s %-15s %-10s %-15s %-10s %-10s %-15s%n",
            "PatientID", "PersonID", "Glucose", "Bloodpressure", "SkinThickness",
            "Insulin", "BMI", "DiabetesPedigree", "Age", "Diabetes Status");
        System.out.println("-------------------------------------------------------------------------------------------------------------");

        for (Patients patient : patientsList) {
            System.out.printf("%-10d %-10d %-10d %-15d %-15d %-10d %-15.2f %-10.2f %-10d %-15d%n",
                patient.GetPatientID(),
                patient.GetID(),
                patient.GetGlucose(),
                patient.GetBloodpressure(),
                patient.GetSkinThickness(),
                patient.GetInsulin(),
                patient.GetBMI(),
                patient.GetDiabetesPedigreeFunction(),
                patient.GetAge(),
                patient.GetOutcome());
            }

      
        //This helps us get the data of person in a tabular format
        List<Person> personsList = dbConnect.getAllPersonsAsObjects();

        System.out.printf("%-10s %-15s %-15s %-25s %-10s%n", "PersonID", "FirstName", "LastName", "Email", "Gender");
        System.out.println("-------------------------------------------------------------------------------");

        for (Person person : personsList) {
            System.out.printf("%-10d %-15s %-15s %-25s %-10c%n",
                    person.GetID(),
                    person.GetFirstName(),
                    person.GetLastName(),
                    person.GetEmail(),
                    person.GetGender());
        }
    
        
// II/ Data Analysis         
        Data_Analysis dataAnalysis = new Data_Analysis();
    // Prompt the user to enter a PersonID
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the PersonID to analyze: ");
        int personIDToAnalyze = scanner.nextInt();

        // Check if the PersonID exists
        Person selectedPerson = null;
        for (Person person : personsList) {
            if (person.GetID() == personIDToAnalyze) {
                selectedPerson = person;
                break;
            }
        }

        if (selectedPerson == null) {
            System.out.println("Person with ID " + personIDToAnalyze + " not found.");
            return;
        }

        // Display Person Details
        System.out.println("Selected Person Details:");
        System.out.printf("%-10s %-15s %-15s %-25s %-10s%n", "PersonID", "FirstName", "LastName", "Email", "Gender");
        System.out.printf("%-10d %-15s %-15s %-25s %-10c%n",
                selectedPerson.GetID(),
                selectedPerson.GetFirstName(),
                selectedPerson.GetLastName(),
                selectedPerson.GetEmail(),
                selectedPerson.GetGender());

        // Find the corresponding patient data
        Patients selectedPatient = null;
        for (Patients patient : patientsList) {
            if (patient.GetID() == personIDToAnalyze) {
                selectedPatient = patient;
                break;
            }
        }

        if (selectedPatient == null) {
            System.out.println("No patient data found for PersonID " + personIDToAnalyze);
            return;
        }

        // Perform Data Analysis for the selected patient
        System.out.println("\nData Analysis for the Selected Patient:");
        System.out.printf("%-15s %-15s %-15s %-15s %-20s %-15s %-15s%n",
                "BMI Category", "Risk Level", "Health Score", "Age Category",
                "Blood Pressure", "Skin Thickness", "Insulin Level");

        String bmiCategory = dataAnalysis.classifyBMI(selectedPatient);
        String riskCategory = dataAnalysis.classifyRisk(selectedPatient);
        double healthScore = dataAnalysis.calculateHealthScore(selectedPatient);
        String ageCategory = dataAnalysis.classifyAge(selectedPatient);
        String bloodPressureCategory = dataAnalysis.classifyBloodPressure(selectedPatient);
        String skinThicknessCategory = dataAnalysis.classifySkinThickness(selectedPatient);
        String insulinLevelCategory = dataAnalysis.classifyInsulinLevel(selectedPatient);

        System.out.printf("%-15s %-15s %-15.2f %-15s %-20s %-15s %-15s%n",
                bmiCategory,
                riskCategory,
                healthScore,
                ageCategory,
                bloodPressureCategory,
                skinThicknessCategory,
                insulinLevelCategory);
        
        
        
//        Machine_Learning machineLearning = new Machine_Learning();
//        machineLearning.trainAndPredictDiabetesRisk(spark, patientsList);
//
//        // Step 6: Stop the Spark session
//        spark.stop();
    }

        
    }
    
    


        
        
        
       
    
 
    
    
    

