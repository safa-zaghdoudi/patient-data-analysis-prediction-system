package com.mycompany.mavenproject1;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Data_Ingestion implements Display {

    // Method to load data from a CSV file
    public List<String[]> loadData(String filePath) {
        List<String[]> dataList = new ArrayList<>();
        String line;
        String csvSplitBy = ";"; // Assuming the CSV is comma-separated

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                // Use comma as separator
                String[] data = line.split(csvSplitBy);
                dataList.add(data); // Store the data in the list
            }
        } catch (IOException e) {
        }

        return dataList; // Return the list of data
    }
    
    // Method to display the ingested data
    @Override
    public void displayData(List<String[]> dataList) {
        System.out.println("Data Ingested:");
        for (String[] row : dataList) {
            for (String value : row) {
                System.out.print(value + " | ");
            }
            System.out.println(); // New line after each row
        }
    }

}
