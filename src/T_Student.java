
import javafx.beans.property.SimpleStringProperty;


public class T_Student {
    SimpleStringProperty SID;
    SimpleStringProperty FName;
    SimpleStringProperty LName;
    SimpleStringProperty Phone;
    SimpleStringProperty MName;
    SimpleStringProperty MID;
    SimpleStringProperty Email;
    public T_Student(String sid, String fname,String lname,String phone,String mname,String mid,String email){
        this.SID = new SimpleStringProperty(sid);
        this.FName = new SimpleStringProperty (fname);
        this.LName = new SimpleStringProperty(lname);
        this.Phone = new SimpleStringProperty(phone);
        this.MName = new SimpleStringProperty(mname);
        this.MID = new SimpleStringProperty(mid);
        this.Email = new SimpleStringProperty(email);
        
    }
    
    
    public void setSID(String sid){
        this.SID = new SimpleStringProperty(sid);
    }
    public void setFName(String fnaem){
        this.FName = new SimpleStringProperty(fnaem);
    }
    public void setLName(String sid){
        this.LName = new SimpleStringProperty(sid);
    }
    public void setPhone(String sid){
        this.Phone = new SimpleStringProperty(sid);
    }
    public void setMName(String sid){
        this.MName = new SimpleStringProperty(sid);
    }
    public void setMID(String sid){
        this.MID = new SimpleStringProperty(sid);
    }
    public void setEmail(String sid){
        this.Email = new SimpleStringProperty(sid);
    }
    
    public String getSID(){
        return this.SID.get();
    }
    public String getFName(){
        return this.FName.get();
    }
    public String getLName(){
        return this.LName.get();
    }
    public String getPhone(){
        return this.Phone.get();
    }
    public String getMName(){
        return this.MName.get();
    }
    public String getMID(){
        return this.MID.get();
    }
    public String getEmail(){
        return this.Email.get();
    }
     
    
}
