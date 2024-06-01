import javafx.beans.property.SimpleStringProperty;
public class StudentHomePage {
    SimpleStringProperty MaterialNumber; 
    SimpleStringProperty MaterialName; 
    SimpleStringProperty GroupNumber; 
    SimpleStringProperty GroupName;
    SimpleStringProperty Period; 
    SimpleStringProperty Classes;
    SimpleStringProperty Time; 

    public StudentHomePage(String MaterialNumber, String MaterialName, String GroupNumber, String GroupName, String Period, String Class, String Time) {
        this.MaterialNumber = new SimpleStringProperty (MaterialNumber);
        this.MaterialName =new SimpleStringProperty ( MaterialName);
        this.GroupNumber =new SimpleStringProperty ( GroupNumber);
        this.GroupName =new SimpleStringProperty ( GroupName);
        this.Period =new SimpleStringProperty ( Period);
        this.Classes =new SimpleStringProperty ( Class);
        this.Time =new SimpleStringProperty ( Time);
    }

    public void setMaterialNumber(String MaterialNumber) {
        this.MaterialNumber = new SimpleStringProperty(MaterialNumber);
    }

    public void setMaterialName(String MaterialName) {
        this.MaterialName =new SimpleStringProperty( MaterialName);
    }

    public void setGroupNumber(String GroupNumber) {
        this.GroupNumber = new SimpleStringProperty(GroupNumber);
    }

    public void setGroupName(String GroupName) {
        this.GroupName = new SimpleStringProperty(GroupName);
    }

    public void setPeriod(String Period) {
        this.Period =new SimpleStringProperty( Period);
    }

    public void setClasses(String Classes) {
        this.Classes = new SimpleStringProperty(Classes);
    }

    public void setTime(String Time) {
        this.Time = new SimpleStringProperty(Time);
    }

    public String getMaterialNumber() {
        return MaterialNumber.get();
    }

    public String getMaterialName() {
        return MaterialName.get();
    }

    public String getGroupNumber() {
        return GroupNumber.get();
    }

    public String getGroupName() {
        return GroupName.get();
    }

    public String getPeriod() {
        return Period.get();
    }

    public String getClasses() {
        return Classes.get();
    }

    public String getTime() {
        return Time.get();
    }
    
    
          
}
