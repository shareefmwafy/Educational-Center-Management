
import javafx.beans.property.SimpleStringProperty;


public class Groups {
    
    SimpleStringProperty GroupID;
    SimpleStringProperty GroupName;
    SimpleStringProperty Period;
    SimpleStringProperty Material;
    SimpleStringProperty StartTime;
    SimpleStringProperty Teacher;
    SimpleStringProperty Classes;    

    public Groups(String GroupID, String GroupName, String Period, String Material, String StartTime, String Class, String Teacher) {
        this.GroupID = new SimpleStringProperty (GroupID);
        this.GroupName =  new SimpleStringProperty ( GroupName);
        this.Period =  new SimpleStringProperty (Period);
        this.Material =  new SimpleStringProperty ( Material);
        this.StartTime =  new SimpleStringProperty ( StartTime);
        this.Teacher =  new SimpleStringProperty ( Teacher);
        this.Classes =  new SimpleStringProperty ( Class);
    }

    public void setGroupID(String GroupID) {
        this.GroupID = new SimpleStringProperty ( GroupID);
    }

    public void setGroupName(String GroupName) {
        this.GroupName =new SimpleStringProperty ( GroupName);
    }

    public void setPeriod(String Period) {
        this.Period =new SimpleStringProperty ( Period);
    }

    public void setMaterial(String Material) {
        this.Material = new SimpleStringProperty ( Material);
    }

    public void setStartTime(String StartTime) {
        this.StartTime = new SimpleStringProperty ( StartTime);
    }

    public void setTeacher(String Teacher) {
        this.Teacher =new SimpleStringProperty ( Teacher);
    }

    public void setClasses(String Class) {
        this.Classes = new SimpleStringProperty ( Class);
    }

    public String getGroupID() {
        return GroupID.get();
    }

    public String getGroupName() {
        return GroupName.get();
    }

    public String getPeriod() {
        return Period.get();
    }

    public String getMaterial() {
        return Material.get();
    }

    public String getStartTime() {
        return StartTime.get();
    }

    public String getTeacher() {
        return Teacher.get();
    }

    public String getClasses() {
        return Classes.get();
    }

}
