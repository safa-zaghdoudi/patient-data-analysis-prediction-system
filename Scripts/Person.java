package com.mycompany.mavenproject1;

import java.util.List;

/**
 *
 * @author ASUS
 */
public class Person {
        private int ID;
        private String FirstName;
        private String LastName;
        private String Email;
        private char Gender;
        private List<Patients> Patients;

    // Parameterized constructor
    public Person(int ID, String FirstName, String LastName, String Email, char Gender){
        this.ID = ID ;
        this.FirstName = FirstName;
        this.LastName = LastName ;
        this.Email = Email ;
        this.Gender = Gender ;
    }
    
    //Default constructor
     public Person(){
        this.FirstName = "Null";
        this.LastName = "Null";
        this.Email = "Null";
        setGender(Gender);
     }
    
    //Getters and Setters
    public int GetID(){
       return ID;
   }
    
    public String GetFirstName(){
       return FirstName;
   }
    
    public void setFirstName(String FirstName){
       this.FirstName= FirstName;
       
   }
    
    public String GetLastName(){
       return LastName;
   }
    
    public void setLastName(String LastName){
       this.LastName= LastName;
       
   }
   
    public String GetEmail(){
       return Email;
   }
    
    public void setEmail(String Email) {
        if (isValidEmail(Email)) {
            this.Email = Email;
        } else {
            System.out.println("Invalid email format. Please provide a valid email.");
        }
    }
    
    public char GetGender(){
       return Gender;
    }
    
   //Gender can only be M or F or m or f
    public void setGender(char Gender) {
    if (Gender == 'f' || Gender == 'm' || Gender == 'F' || Gender == 'M') {
        this.Gender = Character.toLowerCase(Gender); // Normalize to lowercase
        System.out.println("Gender successfully set to: " + this.Gender);
    } else {
        System.out.println("Gender is invalid. It must be 'm' or 'f'");
    }
   }
   
   //to string function
    @Override
    public String toString() {
        return "Person[" + ", ID=" + ID + ",FirstName=" + FirstName + ",LastName=" + LastName +  ",Email=" + Email + ",Gender=" + Gender+ "]";    
    }
    
   //Get full name
    public String GetFullName(){
        return FirstName + " "+ LastName;
    }
    
   //Verify format of email
    private boolean isValidEmail(String Email) {
        if (Email == null) {
            return false; // Null check
        }
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        return Email.matches(emailRegex);
    }
    
    public List<Patients> getPatients(){
        return Patients;
    }
    
    
 
    
}