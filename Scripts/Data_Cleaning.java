package com.mycompany.mavenproject1;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.*;
import java.util.*;


public class Data_Cleaning  extends Patients implements Display{    



    
    public  void HandleUncorrectValues(List<String[]> dataset) {
        for (String[] row : dataset) {
            try {
                //Handling missing or invalid values
                row[2] = row[2].isEmpty() ? "0" : row[2]; // Glucose: Replace empty with 0
                row[3] = row[3].isEmpty() ? "0" : row[3]; // BloodPressure: Replace empty with 0
                row[4] = row[4].isEmpty() ? "0" : row[4]; // SkinThinkness : Replace empty with 0 
                row[5] = row[5].isEmpty() ? "0" : row[5]; // Insulin : Replace empty with 0 
                row[6] = row[6].isEmpty() ? "0.0" : row[6]; // BMI: Replace empty with 0.0
                row[7] = row[7].isEmpty() ? "0.0" : row[7];  // DiabetesPedigreeFunction : Replace empty with 0.0 
                row[9] = row[9].isEmpty() ? "0" : row[9]; // Diabetes Statues : Replace emplty with 0 
                
                //Parse values to ensure validity

                int Glucose = Integer.parseInt(row[2]);
                int Bloodpressure = Integer.parseInt(row[3]);
                int SkinThinkness =Integer.parseInt(row[4]);
                int Insulin = Integer.parseInt(row[5]);
                double BMI = Double.parseDouble(row[6]);
                double DiabetesPedigreeFunction = Double.parseDouble(row[7]);
                
                
                //Clean incorrect or unrealistic values
                if (Glucose < 0) row[2] = "0"; // Set negative glucose values to 0
                if (Bloodpressure < 0) row[3] = "0"; // Set negative blood pressure values to 0
                if (SkinThinkness < 20) row[4] = "20"; 
                if (Insulin< 0 ) row[5] = "0"; // Set negative insulin values to 0 
                if (BMI < 0 || BMI > 100) row[6] = "25.0"; // Replace extreme BMI with default
                if (DiabetesPedigreeFunction < 0 || DiabetesPedigreeFunction > 2.5 ) row[7] = "0.5"; // Replace Extreme DiabetesPedigreeFunction with default 
                
              // Handle invalid rows 
            } catch (NumberFormatException e) {
               // System.err.println("Error parsing row: " + e.getMessage());
  
            }
        }
    }

        
    
    public void RemoveOutliers(List<String[]> dataset, String feature, double lowerThreshold, double upperThreshold) {
               
            int featureIndex = switch (feature.trim().toLowerCase()) {
                case "glucose" -> 2 ; 
                case "bmi" -> 6;
                case "age" -> 8 ;
                default -> -1 ;
            };
            if (featureIndex == -1){
                System.err.println("Invalid Sytax : " + feature );
        }
            List<String[]> dataWithoutHeaders = dataset.subList(1, dataset.size());
            dataWithoutHeaders.removeIf(row ->{
                        try {
                                double value = Double.parseDouble(row[featureIndex]);
                                return value < lowerThreshold || value > upperThreshold;
                                }catch (NumberFormatException e) {
            // Handle rows with invalid or missing data by removing them
            return true;
        }
        });
}
        //change header name from outcome to Diabeties Status
        
        
    public void SetHeaderName(List<String[]>data){

        String[] header = data.get(0);
        for (int i = 0 ; i < header.length; i++) {
            if(header[i].equalsIgnoreCase("Outcome")){
            header[i] = "Diabetes Status" ;
            break;
            }
        }
        
        }

    public static void saveToCSV(List<String[]> data, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] row : data) {
                String line = String.join(",", row); // Convert array to comma-separated string
                writer.write(line);
                writer.newLine(); // Add a new line for the next row
            }
        }
    
    }
        
    @Override
    public void displayData(List<String[]> data) {
        if (data == null || data.isEmpty()) {
            System.out.println("The dataset is empty or null.");
            return;
        }
        for (String[] row : data) {
            System.out.println(String.join(" | ", row)); // Custom format
        }
    }
}
        
        
        
    
