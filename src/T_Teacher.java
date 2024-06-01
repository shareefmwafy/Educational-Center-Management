
import javafx.beans.property.SimpleStringProperty;


public class T_Teacher {
    SimpleStringProperty MaterialID;
    SimpleStringProperty MaterialName;
    SimpleStringProperty GroupID;
    SimpleStringProperty GroupName;
    SimpleStringProperty Period;
    SimpleStringProperty Time;
    SimpleStringProperty Classs;
    
    public T_Teacher(String MID,String MName,String GID,String GName,String Period,String time,String Class){
        this.MaterialID = new SimpleStringProperty(MID);
        this.MaterialName = new SimpleStringProperty(MName);
        this.GroupID = new SimpleStringProperty(GID); 
        this.GroupName = new SimpleStringProperty(GName);
        this.Period = new SimpleStringProperty(Period);
        this.Time = new SimpleStringProperty(time);
        this.Classs = new SimpleStringProperty(Class);          
    }

    public void setMaterialID(String MaterialID) {
        this.MaterialID = new SimpleStringProperty (MaterialID);
    }

    public void setMaterialName(String MaterialName) {
        this.MaterialName = new SimpleStringProperty (MaterialName);
    }

    public void setGroupID(String GroupID) {
        this.GroupID = new SimpleStringProperty(GroupID);
    }

    public void setGroupName(String GroupName) {
        this.GroupName =new SimpleStringProperty( GroupName);
    }

    public void setPeriod(String Period) {
        this.Period = new SimpleStringProperty( Period);
    }

    public void setTime(String Time) {
        this.Time = new SimpleStringProperty(  Time);
    }

    public void setClass(String Class) {
        this.Classs = new SimpleStringProperty(  Class);
    }

    public String getMaterialID() {
        return MaterialID.get();
    }

    public String getMaterialName() {
        return MaterialName.get();
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

    public String getTime() {
        return Time.get();
    }

    public String getClasss() {
        return Classs.get();
    }
    
}
