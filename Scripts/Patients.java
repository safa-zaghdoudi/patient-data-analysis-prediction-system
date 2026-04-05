package com.mycompany.mavenproject1;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.*;
/**
 *
 * @author ASUS
 */
// extends because it inherits from person
public class Patients extends Person {

    
    private int PatientID;
    private int Glucose;
    private int Bloodpressure;
    private int SkinThickness;
    private int Insulin;
    private double BMI;
    private double DiabetesPedigreeFunction;
    private int Age;
    private int Outcome;
    private List<Patients> patients;
    
    
    public Patients(){}

    //Parameterized constructor
    public Patients(int PatientID,int ID, String FirstName, String LastName, String Email, char Gender, int Glucose, int Bloodpressure, int SkinThickness, int Insulin, double BMI, double DiabetesPedigreeFunction,int Age,int Outcome){
       super(ID,FirstName,LastName,Email,Gender);     
       this.Glucose=Glucose;
       this.Bloodpressure = Bloodpressure;
       this.SkinThickness = SkinThickness;
       this.Insulin = Insulin;
       this.BMI=BMI;
       this.DiabetesPedigreeFunction = DiabetesPedigreeFunction;
       this.Age = Age;
       this.Outcome = Outcome; 
       this.PatientID = PatientID;
    }
    
    
    //Setters and getters for all variables except set for outcome 
    public int GetPatientID(){
       return PatientID;
   }
    
    public int GetGlucose(){
       return Glucose;
   }
    
    public void setGlucose(int Glucose){
       this.Glucose= Glucose;
   }
   
    public int GetBloodpressure(){
       return Bloodpressure;
   }
    
    public void setBloodpressure(int Bloodpressure){
       this.Bloodpressure= Bloodpressure;
   }
    
    public int GetSkinThickness(){
       return SkinThickness;
   }
    
    public void setSkinThickness(int SkinThickness){
       this.SkinThickness= SkinThickness;
   }
   
    public int GetInsulin(){
       return Insulin;
   }
    
    public void setInsulin(int Insulin){
       this.Insulin= Insulin;
   }
    
   public double GetBMI(){
       return BMI;
   }
    
    public void setBMI(double BMI){
       this.BMI= BMI;
   }
   
   public double GetDiabetesPedigreeFunction(){
       return DiabetesPedigreeFunction;
   }
    
    public void setDiabetesPedigreeFunction(double DiabetesPedigreeFunction){
       this.DiabetesPedigreeFunction= DiabetesPedigreeFunction;
    }
    
    public int GetAge(){
       return Age;
    }
    
    public void setAge(int Age){
        if (Age >= 18 && Age <= 90) {
            this.Age = Age;
        } else {
            System.out.println("Age must be between 18 and 90.");
        }
    }
    
    public int GetOutcome(){
       return Outcome;
   }
    
   //to string function
@Override
    public String toString() {
        return "Patients["+ "PatientID= "+ PatientID + super.toString() + ",Glucose=" + Glucose + ",BloodPressure=" + Bloodpressure 
        +",SkinThickness=" + SkinThickness+",Insulin=" + Insulin +",BMI=" + BMI +",DiabetesPedigreeFunction="+ DiabetesPedigreeFunction 
        +",Age=" + Age+ ",Outcome=" + Outcome +"]";
    }    
    
       
   
}
