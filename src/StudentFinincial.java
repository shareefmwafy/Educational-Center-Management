
import javafx.beans.property.SimpleStringProperty;


public class StudentFinincial {
    SimpleStringProperty MaterialName;
    SimpleStringProperty Cost; 

    public StudentFinincial(String MaterialName, String Cost) {
        this.MaterialName = new SimpleStringProperty (MaterialName);
        this.Cost = new SimpleStringProperty (Cost);
    }

    public void setMaterialName(String MaterialName) {
        this.MaterialName = new SimpleStringProperty (MaterialName);
    }

    public void setCost(String Cost) {
        this.Cost = new SimpleStringProperty (Cost);
    }

    public String getMaterialName() {
        return MaterialName.get();
    }

    public String getCost() {
        return Cost.get();
    }
    
}
