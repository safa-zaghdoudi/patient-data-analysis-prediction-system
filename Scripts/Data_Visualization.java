package com.mycompany.mavenproject1;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.category.DefaultCategoryDataset;
       
import javax.swing.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
/**
 *
 * @author ASUS
 */
public class Data_Visualization {
    private final DBConnect dbConnection;
    private final Data_Analysis DataAnalysis;

    
    public Data_Visualization() {
        dbConnection = new DBConnect(); // Initialize the dbconnect instance
        DataAnalysis = new Data_Analysis();
    }
   
    

    public void createAgeGroupDistributionChart() {
    try {
        List<Patients> patientsList = dbConnection.getAllPatientsAsObjects();

        // Classify patients by age group
        List<String> ageGroupsList = patientsList.stream()
                .map(DataAnalysis::classifyAge)
                .collect(Collectors.toList());

        // Use frequency distribution to count age groups
        Map<String, Long> ageGroups = Data_Analysis.frequencyDistribution(ageGroupsList);

        // Prepare the dataset for the bar chart
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        ageGroups.forEach((ageGroup, count) -> dataset.addValue(count, "Count", ageGroup));

        // Calculate mean and median age
        List<Double> ages = patientsList.stream()
                .map(patient -> (double) patient.GetAge())
                .collect(Collectors.toList());
        double meanAge = Data_Analysis.mean(ages);
        double medianAge = Data_Analysis.median(ages);

        // Create the bar chart
        JFreeChart barChart = ChartFactory.createBarChart(
                "Age Group Distribution (Mean: " + meanAge + ", Median: " + medianAge + ")", // Chart title
                "Age Group",             // X-axis label
                "Count",                 // Y-axis label
                dataset                  // Dataset
        );

        // Display the chart in a JFrame
        ChartPanel chartPanel = new ChartPanel(barChart);
        JFrame frame = new JFrame("Bar Chart - Age Group Distribution");
        frame.setContentPane(chartPanel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    } catch (Exception e) {
        e.printStackTrace();
    }
}
         
// This is to create a pie chart to display the distribution of the sick and non sick people in our dataset

        public void createSicknessDistributionChart() {
        try {
            // Fetch all patients
            List<Patients> patientsList = dbConnection.getAllPatientsAsObjects();

            // Count healthy and sick people
            long sickCount = patientsList.stream()
                    .filter(patient -> patient.GetOutcome() == 1) // Outcome = 1 for sick
                    .count();
            long HealthyCount = patientsList.stream()
                   .filter(patient -> patient.GetOutcome() == 0) // Outcome = 0 for non-sick
                   .count();
            // Prepare the dataset for the pie chart
            DefaultPieDataset dataset = new DefaultPieDataset();
            dataset.setValue("Sick", sickCount);
            dataset.setValue("Healthy", HealthyCount);

            // Create the pie chart
           JFreeChart pieChart = ChartFactory.createPieChart(
                    "Distribution of Sick VS Healthy Patients", // Chart title
                    dataset,                          // Dataset
                    true,                             // Include legend
                   true,                             // Include tooltips
                    false                             // No URLs
            );

            // Display the chart in a JFrame
            ChartPanel chartPanel = new ChartPanel(pieChart);
            JFrame frame = new JFrame("Pie Chart - Sick VS healthy patients Distribution");
            frame.setContentPane(chartPanel);
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        
// Bar chat to compare two patients' vitals.
    public void ComparePatients(int patientId1, int patientId2) {
        try {
            // Fetch all patients
            List<Patients> patientsList = dbConnection.getAllPatientsAsObjects();

            // Find the two patients by ID
            Patients patient1 = patientsList.stream()
                    .filter(p -> p.GetPatientID() == patientId1)
                    .findFirst()
                    .orElse(null);

            Patients patient2 = patientsList.stream()
                    .filter(p -> p.GetPatientID() == patientId2)
                    .findFirst()
                    .orElse(null);

            if (patient1 == null || patient2 == null) {
                System.out.println("One or both patients not found.");
                return;
            }

            // Prepare the dataset
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            // Add patient 1's data
            dataset.addValue(patient1.GetGlucose(), patient1.GetID() + " Glucose", "Glucose");
            dataset.addValue(patient1.GetBMI(), patient1.GetID() + " BMI", "BMI");
            dataset.addValue(patient1.GetInsulin(), patient1.GetID() + " Insulin", "Insulin");
            dataset.addValue(patient1.GetDiabetesPedigreeFunction(), patient1.GetID() + " Diabetes Pedigree", "Diabetes Pedigree");
            dataset.addValue(patient1.GetSkinThickness(), patient1.GetID() + " Skin Thickness", "Skin Thickness");
            dataset.addValue(patient1.GetBloodpressure(), patient1.GetID() + " Blood Pressure", "Blood Pressure");
            dataset.addValue(patient1.GetAge(), patient1.GetID() + " Age", "Age");

            // Add patient 2's data
            dataset.addValue(patient2.GetGlucose(), patient2.GetID() + " Glucose", "Glucose");
            dataset.addValue(patient2.GetBMI(), patient2.GetID() + " BMI", "BMI");
            dataset.addValue(patient2.GetInsulin(), patient2.GetID() + " Insulin", "Insulin");
            dataset.addValue(patient2.GetDiabetesPedigreeFunction(), patient2.GetID() + " Diabetes Pedigree", "Diabetes Pedigree");
            dataset.addValue(patient2.GetSkinThickness(), patient2.GetID() + " Skin Thickness", "Skin Thickness");
            dataset.addValue(patient2.GetBloodpressure(), patient2.GetID() + " Blood Pressure", "Blood Pressure");
            dataset.addValue(patient2.GetAge(), patient2.GetID() + " Age", "Age");

            // Create the bar chart
            JFreeChart barChart = ChartFactory.createBarChart(
                    "Comparison between two Patients' Vitals", // Chart title
                    "Vitals",                    // X-axis label
                    "Value",                     // Y-axis label
                    dataset                      // Dataset
            );

            
            CategoryPlot plot = barChart.getCategoryPlot();
            CategoryAxis xAxis = plot.getDomainAxis();

            // Rotate category labels to fit longer names
            xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

            // Set custom font for clarity
            xAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 10)); // Adjust font size
            xAxis.setLabelFont(new Font("SansSerif", Font.BOLD, 12));     // Axis label font
            plot.getRangeAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 12));

            // Customize the bar renderer for better visibility
            BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setSeriesPaint(0, Color.BLUE);  // Patient 1 color
            renderer.setSeriesPaint(1, Color.RED);   // Patient 2 color
           
            // Display the chart in a JFrame
            ChartPanel chartPanel = new ChartPanel(barChart);
            JFrame frame = new JFrame("Bar Chart - Patient Comparison");
            frame.setContentPane(chartPanel);
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
   
//A table that showcases the differences between two patients of our choosing
    public void displayPatientComparisonTable(int patientId1, int patientId2) {
        try {
            // Fetch all patients
            List<Patients> patientsList = dbConnection.getAllPatientsAsObjects();

            // Find the two patients by ID
            Patients patient1 = patientsList.stream()
                    .filter(p -> p.GetPatientID() == patientId1)
                    .findFirst()
                    .orElse(null);

            Patients patient2 = patientsList.stream()
                    .filter(p -> p.GetPatientID() == patientId2)
                    .findFirst()
                    .orElse(null);

            if (patient1 == null || patient2 == null) {
                System.out.println("One or both patients not found.");
                return;
            }

            // Create the table data
            String[] columnNames = {"Attribute", "Patient1", "Patient 2"};

            String[][] data = {
                    {"PersonID", String.valueOf(patient1.GetPatientID()), String.valueOf(patient2.GetPatientID())},
                    {"Gender", patient1.GetGender() == 'M' ? "Male" : "Female",
                            patient2.GetGender() == 'M' ? "Male" : "Female"},
                    {"Age", patient1.GetAge() + " (" + DataAnalysis.classifyAge(patient1) + ")",
                            patient2.GetAge() + " (" + DataAnalysis.classifyAge(patient2) + ")"},
                    {"BMI", patient1.GetBMI() + " (" + DataAnalysis.classifyBMI(patient1) + ")",
                            patient2.GetBMI() + " (" + DataAnalysis.classifyBMI(patient2) + ")"},
                    {"Blood Pressure", patient1.GetBloodpressure() + " (" + DataAnalysis.classifyBloodPressure(patient1) + ")",
                            patient2.GetBloodpressure() + " (" + DataAnalysis.classifyBloodPressure(patient2) + ")"},
                    {"Skin Thickness", patient1.GetSkinThickness() + " (" + DataAnalysis.classifySkinThickness(patient1) + ")",
                            patient2.GetSkinThickness() + " (" + DataAnalysis.classifySkinThickness(patient2) + ")"},
                    {"Insulin Level", patient1.GetInsulin() + " (" + DataAnalysis.classifyInsulinLevel(patient1) + ")",
                            patient2.GetInsulin() + " (" + DataAnalysis.classifyInsulinLevel(patient2) + ")"},
                    {"Glucose Level", patient1.GetGlucose() + " (" + DataAnalysis.classifyGlucoseLevel(patient1) + ")",
                            patient2.GetGlucose() + " (" + DataAnalysis.classifyGlucoseLevel(patient2) + ")"},
                    {"Risk Level", DataAnalysis.classifyRisk(patient1),
                            DataAnalysis.classifyRisk(patient2)},
                    {"Health Score", String.valueOf(DataAnalysis.calculateHealthScore(patient1)),
                            String.valueOf(DataAnalysis.calculateHealthScore(patient2))}
            };

            // Create the JTable
            JTable table = new JTable(data, columnNames);
            table.setRowHeight(30);
            table.setFont(new Font("SansSerif", Font.PLAIN, 14));
            table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));

            // Wrap the table in a scroll pane
            JScrollPane scrollPane = new JScrollPane(table);

            // Create the JFrame to display the table
            JFrame frame = new JFrame("Patient Comparison");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.add(scrollPane, BorderLayout.CENTER);
            frame.setSize(1000, 500);
            frame.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Create BMI Distribution Chart (Bar & Pie Charts)
    public void createBMIDistributionCharts() {
    try {
        List<Patients> patientsList = dbConnection.getAllPatientsAsObjects();

        // Classify patients by BMI
        List<String> bmiCategoriesList = patientsList.stream()
                .map(DataAnalysis::classifyBMI)
                .collect(Collectors.toList());
        Map<String, Long> bmiCategories = Data_Analysis.frequencyDistribution(bmiCategoriesList);

        // Calculate mean and median BMI
        List<Double> bmis = patientsList.stream()
                .map(Patients::GetBMI)
                .collect(Collectors.toList());
        double meanBMI = Data_Analysis.mean(bmis);
        double medianBMI = Data_Analysis.median(bmis);

        // Create Bar Chart
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        bmiCategories.forEach((category, count) -> dataset.addValue(count, "Count", category));

        JFreeChart barChart = ChartFactory.createBarChart(
                "BMI Distribution (Mean: " + meanBMI + ", Median: " + medianBMI + ")", // Chart title
                "BMI Category",     // X-axis label
                "Count",            // Y-axis label
                dataset             // Dataset
        );

        // Create Pie Chart
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        bmiCategories.forEach(pieDataset::setValue);

        JFreeChart pieChart = ChartFactory.createPieChart(
                "BMI Distribution", // Chart title
                pieDataset          // Dataset
        );

        // Display Bar Chart
        ChartPanel barChartPanel = new ChartPanel(barChart);
        JFrame barFrame = new JFrame("Bar Chart - BMI Distribution");
        barFrame.setContentPane(barChartPanel);
        barFrame.setSize(800, 600);
        barFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        barFrame.setVisible(true);

        // Display Pie Chart
        ChartPanel pieChartPanel = new ChartPanel(pieChart);
        JFrame pieFrame = new JFrame("Pie Chart - BMI Distribution");
        pieFrame.setContentPane(pieChartPanel);
        pieFrame.setSize(800, 600);
        pieFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pieFrame.setVisible(true);

    } catch (Exception e) {
        e.printStackTrace();
    }
}
   
   
   
   
    public static void main(String[] args) {
        Data_Visualization visualization = new Data_Visualization();

        // Generate the gender distribution pie chart
        visualization.createAgeGroupDistributionChart();
        visualization.createSicknessDistributionChart();
        visualization.ComparePatients(20,15);
        visualization.displayPatientComparisonTable(20,15);
        visualization.createBMIDistributionCharts();
    }
    
}
    
    
   