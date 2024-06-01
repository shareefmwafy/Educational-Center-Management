
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CostMaterial {
    
    SimpleStringProperty MaterialName, MaterialID;
    SimpleIntegerProperty cost; 

    public CostMaterial(String MaterialID, String MaterialName, int cost) {
        this.MaterialName = new SimpleStringProperty (MaterialName);
        this.MaterialID = new SimpleStringProperty (MaterialID);
        this.cost = new SimpleIntegerProperty (cost);
    }

    public void setMaterialName(String MaterialName) {
        this.MaterialName = new SimpleStringProperty(MaterialName);
    }

    public void setMaterialID(String MaterialID) {
        this.MaterialID =new SimpleStringProperty( MaterialID);
    }

    public void setCost(int cost) {
        this.cost =new SimpleIntegerProperty (cost);
    }

    public String getMaterialName() {
        return MaterialName.get();
    }

    public String getMaterialID() {
        return MaterialID.get();
    }

    public int getCost() {
        return cost.get();
    }
    
}
