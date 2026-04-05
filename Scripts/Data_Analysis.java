package com.mycompany.mavenproject1;



import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class Data_Analysis {
    
    //classify BMI into 4 categories
    public String classifyBMI(Patients patient) {
        double BMI = patient.GetBMI();
        if (BMI < 18.5) {
            return "Underweight"; // BMI < 18.5
        } else if (BMI >= 18.5 && BMI < 24.9) {
            return "Normal weight"; // 18.5 <= BMI < 24.9
        } else if (BMI >= 25 && BMI < 29.9) {
            return "Overweight"; // 25 <= BMI < 29.9
       } else {
            return "Obese"; // BMI >= 30
        }
    }
    
    // we know that glucose, bmi, age and diabetes pedigree function contribute the most to risk of diabetes while skin thickness, blood pressure
    // and insulin not that much so we wont includee them in our risk score function
    //calculate and classify risk score
    public double calculateRiskScore(Patients patient) {
        int Glucose = patient.GetGlucose();
        double BMI = patient.GetBMI();
        int Age = patient.GetAge();
        double DiabetesPedigreeFunction = patient.GetDiabetesPedigreeFunction();
        return (Glucose * 0.5) + (BMI * 0.2) + (Age * 0.1) + (DiabetesPedigreeFunction * 0.2);
            }

    public String classifyRisk(Patients patient) {
        double riskScore = calculateRiskScore(patient);
                if (riskScore < 100) {
           return "Low Risk";
       } else if (riskScore < 200) {
           return "Medium Risk";
        } else {
            return "High Risk";
        }
    }
    
    //calculate health score( the weights are assigned based on what we saw as important feature)
    public double calculateHealthScore(Patients patient) {
        int Glucose = patient.GetGlucose();
        double BMI = patient.GetBMI();
        double DiabetesPedigreeFunction = patient.GetDiabetesPedigreeFunction();
        int Bloodpressure = patient.GetBloodpressure();
        int SkinThickness = patient.GetSkinThickness();
        int Insulin = patient.GetInsulin();
        return (0.3 * Glucose) + 
           (0.2 * BMI) +
           (0.1 * Bloodpressure) +
           (0.1 * SkinThickness) +
           (0.2 * DiabetesPedigreeFunction) +
          (0.1 * Insulin);
    }
    
    //classify age into 3 categories
    public String classifyAge(Patients patient) {
        int Age = patient.GetAge();
        if (Age >= 18 && Age <= 35) {
            return "Young Adult";
        } else if (Age >= 36 && Age <= 60) {
            return "Middle-Aged";
        } else if (Age >= 61 && Age <= 90) {
            return "Senior Citizen";
        } else {
            return "Invalid Age"; 
        }
    }
    
   //classify blood pressure into 4 categories
   public String classifyBloodPressure(Patients patient) {
        int Bloodpressure = patient.GetBloodpressure();
        if (Bloodpressure < 80) {
            return "Low Blood Pressure";
        } else if (Bloodpressure >= 80 && Bloodpressure <= 120) {
            return "Normal Blood Pressure";
        } else if (Bloodpressure > 120 && Bloodpressure <= 140) {
            return "Elevated Blood Pressure";
        } else {
            return "Hypertension";
        }
    }
   
   //classify skin thickness into 3 categories
   public String classifySkinThickness(Patients patient) {
        int SkinThickness = patient.GetSkinThickness();
        if (SkinThickness < 20) {
            return "Low Fat Thickness";
        } else if (SkinThickness >= 20 && SkinThickness < 35) {
            return "Normal Fat Thickness";
        } else {
            return "High Fat Thickness";
        }
    }
  
   //classify insulin level into 3 categories
   public String classifyInsulinLevel(Patients patient) {
        int Insulin = patient.GetInsulin();
        if (Insulin < 2) {
            return "Hypoinsulinemia";
        } else if (Insulin >= 2 && Insulin <= 25) {
            return "Normal Insulin";
        } else {
           return "Hyperinsulinemia";
       }
    }
   
   //Classify glucose levels into 4 categories
   public String classifyGlucoseLevel(Patients patient) {
        int Glucose = patient.GetGlucose();
        if (Glucose < 70) {
            return "Hypoglycemia";
        } else if (Glucose >= 70 && Glucose <= 99) {
            return "Normal Glucose";
       } else if (Glucose >= 100 && Glucose <= 125) {
            return "Prediabetes";        
       } else {         
            return "Diabetes";
        }
   }
   
    // Calculate Mean
    public static double mean(List<Double> data) {
        return data.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    // Calculate Median
    public static double median(List<Double> data) {
        List<Double> sorted = data.stream().sorted().collect(Collectors.toList());
        int n = sorted.size();
        if (n == 0) return 0.0;
        if (n % 2 == 0) {
            return (sorted.get(n / 2 - 1) + sorted.get(n / 2)) / 2.0;
        } else {
            return sorted.get(n / 2);
        }
    }

    // Frequency Distribution (for categories)
    public static Map<String, Long> frequencyDistribution(List<String> data) {
        return data.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
    }

   
}
