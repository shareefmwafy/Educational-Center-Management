import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Student {
    
  SimpleStringProperty ID, FName , MName , LName , gender , city,street ;
  SimpleStringProperty  Birthdate ,StartDate ;
  SimpleIntegerProperty  countMatarial;
  SimpleStringProperty Specilization , Stage;

  public Student(String ID, String FName, String MName, String LName, String gender, String StartDate,  String Birthdate,String street,String Specilization,  String Stage) {
        this.ID =  new SimpleStringProperty (ID);
        this.FName =new SimpleStringProperty( FName);
        this.MName = new SimpleStringProperty( MName);
        this.LName = new SimpleStringProperty (LName);
        this.gender =  new SimpleStringProperty(gender);
        this.street = new SimpleStringProperty (street);
        this.Birthdate = new SimpleStringProperty (Birthdate);
        this.StartDate = new SimpleStringProperty (StartDate);
        this.Stage = new SimpleStringProperty (Stage);
        this.Specilization = new SimpleStringProperty (Specilization);
    }
  
    public String getSpecilization() {
        return Specilization.get();
    }

    public String getStage() {
        return Stage.get();
    }

    public void setSpecilization(String Specilization) {
        this.Specilization = new SimpleStringProperty (Specilization);
    }

    public void setStage(String Stage) {
        this.Stage = new SimpleStringProperty( Stage);
    }

    public String getID() {
        return ID.get();
    }

    public void setID(String ID) {
        this.ID = new SimpleStringProperty (ID);
    }

    public String getFName() {
        return FName.get();
    }

    public void setFName(String FName) {
        this.FName = new SimpleStringProperty (FName);
    }

    public String getMName() {
        return MName.get();
    }

    public void setMName(String MName) {
        this.MName = new SimpleStringProperty (MName);
    }

    public String getLName() {
        return LName.get();
    }

    public void setLName(String LName) {
        this.LName = new SimpleStringProperty (LName);
    }

    public String getGender() {
        return gender.get();
    }

    public void setGender(String gender) {
        this.gender = new SimpleStringProperty (gender);
    }


    public String getStreet() {
        return street.get();
    }

    public void setStreet(String street) {
        this.street = new SimpleStringProperty(street);
    }

    public String getBirthdate() {
        return Birthdate.get();
    }

    public void setBirthdate(String Birthdate) {
        this.Birthdate = new SimpleStringProperty(Birthdate);
    }

    public String getStartDate() {
       return StartDate.get();
    }

    public void setStartDate(String startDate) {
        this.StartDate = new SimpleStringProperty(startDate);
    }

    
          
    
}
