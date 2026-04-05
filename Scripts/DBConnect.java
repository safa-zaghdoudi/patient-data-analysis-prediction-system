package com.mycompany.mavenproject1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ASUS
 */

// this part is to ensure that the connection from postgresql is done successfully
public class DBConnect {
    private static final String URL = "jdbc:postgresql://localhost:5432/OOP_project";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Ran*m123";

    public static Connection connect() {
        Connection conn = null;
        try {
            // Load PostgreSQL JDBC Driver
            Class.forName("org.postgresql.Driver");

            // Connect to database
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection failed.");
            e.printStackTrace();
        }
        return conn;
    }
    
    public static void main(String[] args) {
        Connection testConnection = connect();
        if (testConnection != null) {
            System.out.println("Connected to the database!");
        } else {
            System.out.println("Connection failed!");
        }
    }
    
     // This part is to display the database created in postgresql
     // Fetch all records from Person table
    public List<Person> getAllPersonsAsObjects() {
        List<Person> personsList = new ArrayList<>();
         String query = "SELECT * FROM \"Person\"";

        try (Connection conn = DBConnect.connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            // Iterate through the results and fetch columns
            while (rs.next()) {
                int ID = rs.getInt("ID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String email = rs.getString("Email");
                String genderStr = rs.getString("Gender");
                char gender = genderStr != null && !genderStr.isEmpty() ? genderStr.charAt(0) : ' ';

                // Format the output string
                Person person  = new Person( ID, firstName, lastName, email, gender);
                personsList.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personsList;
    }
    
    
         // Fetch all records from patients table

    public List<Patients> getAllPatientsAsObjects() {
    List<Patients> patientsList = new ArrayList<>();
    String query = "SELECT * FROM \"patientcleaned\"";

    try (Connection conn = DBConnect.connect();
         PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        // Iterate through the result set and fetch data
        while (rs.next()) {
            int patientID = rs.getInt("PatientID");
            int ID = rs.getInt("ID");
            int glucose = rs.getInt("Glucose");
            int bloodPressure = rs.getInt("Bloodpressure");
            int skinThickness = rs.getInt("SkinThickness");
            int insulin = rs.getInt("Insulin");
            double bmi = rs.getDouble("BMI");
            double diabetesPedigree = rs.getDouble("DiabetesPedigreeFunction");
            int age = rs.getInt("Age");
            int outcome = rs.getInt("Diabetes Status"); // Updated column name

            // Create a patient object
            Patients patient = new Patients(
                patientID,
                ID,
                "FirstName",       // Placeholder
                "LastName",        // Placeholder
                "Email",           // Placeholder
                'm',               // Placeholder gender
                glucose,
                bloodPressure,
                skinThickness,
                insulin,
                bmi,
                diabetesPedigree,
                age,
                outcome
            );

            patientsList.add(patient);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return patientsList;
}

    
}

