
import javafx.beans.property.SimpleStringProperty;

public class StudentMaterialTable {
   SimpleStringProperty TeacherName; 
    SimpleStringProperty GroupsID; 
    SimpleStringProperty Period; 
    SimpleStringProperty StartTime; 

    public StudentMaterialTable(String TeacherName, String GroupsID, String Period, String FirstTime) {
        this.TeacherName = new SimpleStringProperty (TeacherName);
        this.GroupsID = new SimpleStringProperty (GroupsID);
        this.Period = new SimpleStringProperty (Period);
        this.StartTime = new SimpleStringProperty (FirstTime);
        
    }

    public void setTeacherName(String TeacherName) {
        this.TeacherName = new SimpleStringProperty (TeacherName);
    }

    public void setGroupsID(String GroupsID) {
        this.GroupsID =  new SimpleStringProperty (GroupsID);
    }

    public void setPeriod(String Period) {
        this.Period = new SimpleStringProperty(Period);
    }

    public void setStartTime(String FirstTime) {
        this.StartTime =  new SimpleStringProperty (FirstTime);
    }

   

    public String getTeacherName() {
        return TeacherName.get();
    }

    public String getGroupsID() {
        return GroupsID.get();
    }

    public String getPeriod() {
        return Period.get();
    }

    public String getStartTime() {
        return StartTime.get();
    }

   
    
   
    
}
