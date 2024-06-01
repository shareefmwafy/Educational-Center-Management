
import javafx.beans.property.SimpleStringProperty;



public class Teacher {
   SimpleStringProperty TeacherID; 
   SimpleStringProperty FName ; 
   SimpleStringProperty LName; 
   SimpleStringProperty Salary; 
   SimpleStringProperty StartDate; 
   SimpleStringProperty City;
   SimpleStringProperty Gender; 
   SimpleStringProperty Street; 
   SimpleStringProperty PhoneNumber; 

    public Teacher(String TeacherID, String FName, String LName, String Salary, String StartDate, String City, String Gender, String Street, String PhoneNumber) {
        this.TeacherID = new SimpleStringProperty (TeacherID);
        this.FName =new SimpleStringProperty ( FName);
        //this.MidName = new SimpleStringProperty ( MidName);
        this.LName = new SimpleStringProperty ( LName);
        this.Salary = new SimpleStringProperty ( Salary);
        this.StartDate = new SimpleStringProperty ( StartDate);
        this.City = new SimpleStringProperty ( City);
        this.Gender = new SimpleStringProperty ( Gender);
        this.Street = new SimpleStringProperty ( Street);
        this.PhoneNumber = new SimpleStringProperty ( PhoneNumber);
    }

    public void setTeacherID(String TeacherID) {
        this.TeacherID = new SimpleStringProperty ( TeacherID);
    }

    public void setFName(String FName) {
        this.FName = new SimpleStringProperty ( FName);
    }

   

    public void setLName(String LName) {
        this.LName = new SimpleStringProperty (LName);
    }

    public void setSalary(String Salary) {
        this.Salary = new SimpleStringProperty ( Salary);
    }

    public void setStartDate(String StartDate) {
        this.StartDate = new SimpleStringProperty ( StartDate);
    }

    public void setCity(String City) {
        this.City = new SimpleStringProperty ( City);
    }

    public void setGender(String Gender) {
        this.Gender =new SimpleStringProperty ( Gender);
    }

    public void setStreet(String Street) {
        this.Street = new SimpleStringProperty ( Street);
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = new SimpleStringProperty ( PhoneNumber);
    }

    public String getTeacherID() {
        return TeacherID.get();
    }

    public String getFName() {
        return FName.get();
    }

   

    public String getLName() {
        return LName.get();
    }

    public String getSalary() {
        return Salary.get();
    }

    public String getStartDate() {
        return StartDate.get();
    }

    public String getCity() {
        return City.get();
    }

    public String getGender() {
        return Gender.get();
    }

    public String getStreet() {
        return Street.get();
    }

    public String getPhoneNumber() {
        return PhoneNumber.get();
    }
           
    
}
