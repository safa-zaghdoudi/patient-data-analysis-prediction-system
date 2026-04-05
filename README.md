# 🏥 Patient Data Analysis and Prediction System

A Java-based Object-Oriented Programming project focused on **diabetes detection and analysis** using patient health records, machine learning, and data visualization.

> 📚 Academic Project — Tunis Business School, University of Tunis | 2024–2025  
> Supervised by: Professor Amani Azouz  
> Team: Ranim Sayahi, Ghaith Dkhili, Safa Zaghdoudi

---

## 📋 Table of Contents

- [About the Project](#about-the-project)
- [Tech Stack](#tech-stack)
- [Features](#features)
- [Project Structure](#project-structure)
- [Classes and Interfaces](#classes-and-interfaces)
- [OOP Concepts](#oop-concepts)
- [Getting Started](#getting-started)

---

## 📌 About the Project

This system analyzes patient health records to **predict diabetes risk** using a machine learning decision tree model. It handles the full data pipeline — from ingestion and cleaning to statistical analysis, visualization, and prediction.

Key medical indicators used: glucose levels, blood pressure, insulin, BMI, skin thickness, age, and diabetic status.

The system separates personal identity data from medical data using unique patient IDs to ensure **data privacy**.

---

## 🛠 Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Java |
| IDE | NetBeans |
| Database | PostgreSQL |
| Visualization | JFreeChart (`org.jfree`) |
| UI | Java Swing (`javax.swing`) |
| ML | Apache Spark (Decision Tree Classifier) |
| Libraries | `java.awt`, `java.util` |

---

## ✨ Features

- 📥 **Data Ingestion** — Import patient records from CSV files
- 🧹 **Data Cleaning** — Handle incorrect values, remove outliers, rename headers
- 🔒 **Data Privacy** — Personal and medical data separated by unique patient ID
- 📊 **Statistical Analysis** — Mean, median, frequency distribution, risk scoring
- 📈 **Data Visualization** — BMI distribution, age group charts, sickness distribution, patient comparison
- 🤖 **Machine Learning** — Decision tree trained on 80% of data, tested on 20%, predicts new patient diabetes risk
- 🗄 **Database Integration** — PostgreSQL connection via `DBConnect` class

---

## 🗂 Project Structure

```
patient-data-analysis-prediction-system/
├── src/
│   ├── Person.java              # Base class: personal info
│   ├── Patients.java            # Extends Person: medical data
│   ├── Display.java             # Interface: display contract
│   ├── Data_Ingestion.java      # Loads data from CSV
│   ├── Data_Cleaning.java       # Cleans and exports data
│   ├── Data_Analysis.java       # Statistical analysis & risk scoring
│   ├── Data_Visualization.java  # Charts and comparison tables
│   ├── Machine_Learning.java    # Decision tree model
│   ├── DBConnect.java           # PostgreSQL connection
│   └── Main.java                # Entry point / testing
├── data/
│   └── diabetes_dataset.csv     # Patient dataset
├── Project Report.pdf
└── README.md
```

---

## 🧩 Classes and Interfaces

### `Display` *(Interface)*
Defines the `void display()` contract implemented by `Data_Cleaning` and `Data_Ingestion`.

### `Person` *(Class)*
Stores personal information with private attributes and controlled access via getters/setters.
- Attributes: `PersonID`, `FirstName`, `LastName`, `Email`, `Gender`
- Methods: `getters/setters`, `toString()`, `isValidEmail()`

### `Patients` *(extends Person)*
Stores medical data, inheriting from `Person`.
- Attributes: `PatientID`, `Glucose`, `BloodPressure`, `SkinThickness`, `Insulin`, `BMI`, `DiabetesPedigreeFunction`, `Age`, `DiabetesStatus`

### `Data_Ingestion` *(implements Display)*
Fetches initial datasets from CSV files.
- Methods: `LoadData()`, `DisplayData()`

### `Data_Cleaning` *(extends Patients, implements Display)*
Cleans imported data and exports it back to CSV.
- Methods: `HandleIncorrectValues()`, `RemoveOutliers()`, `SetHeaderName()`, `SavetoCSV()`

### `DBConnect` *(Class)*
Manages the PostgreSQL ↔ NetBeans connection.
- Methods: `connect()`, `getAllPersonsAsObjects()`, `getAllPatientsAsObjects()`

### `Data_Analysis` *(Class)*
Performs statistical analysis and patient risk classification.
- Methods: `classifyBMI()`, `calculateRiskScore()`, `classifyRisk()`, `mean()`, `median()`, `frequencyDistribution()`

### `Data_Visualization` *(Class)*
Generates charts and comparison views.
- Methods: `createAgeGroupDistributionChart()`, `createBMIDistributionCharts()`, `ComparePatients()`, `createSicknessDistributionChart()`

### `Machine_Learning` *(Class)*
Trains a decision tree on 80% of the data and tests on 20%.
- Methods: `trainAndPredictDiabetesRisk()`, `convertPatientsListToDataFrame()`, `prepareDataForTraining()`

### `Main` *(Class)*
Entry point for testing all components.

---

## 🧠 OOP Concepts

| Concept | How it's used |
|---------|--------------|
| **Inheritance** | `Patients` extends `Person`; `Data_Cleaning` extends `Patients` |
| **Encapsulation** | All attributes are `private`, accessed via getters/setters |
| **Abstraction** | `Display` interface defines contracts without implementation |
| **Polymorphism** | `Data_Cleaning` and `Data_Ingestion` both implement `Display` differently |

---

## 🚀 Getting Started

### Prerequisites
- Java JDK 11+
- NetBeans IDE
- PostgreSQL
- Apache Spark (for ML module)

### Setup

```bash
# Clone the repository
git clone https://github.com/safa-zaghdoudi/patient-data-analysis-prediction-system.git
cd patient-data-analysis-prediction-system
```

1. Open the project in **NetBeans**
2. Set up your **PostgreSQL** database and update credentials in `DBConnect.java`:
```java
static final String URL = "jdbc:postgresql://localhost:5432/your_db";
static final String USER = "your_username";
static final String PASSWORD = "your_password";
```
3. Place your CSV dataset in the `data/` folder
4. Run `Main.java`

---

## 👩‍💻 Authors

**Safa Zaghdoudi** | **Ranim Sayahi** | **Ghaith Dkhili**  
Tunis Business School — University of Tunis | 2024–2025
