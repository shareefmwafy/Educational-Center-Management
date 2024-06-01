
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import oracle.jdbc.pool.OracleDataSource;
public class Manager_AddTeacher3Controller implements Initializable {
    public String TeacherID;
    public String FirstName;
    public String MidName;
    public String LastName;
    public String Gender;
    public String Phone;
    public String Password;
    public String Email;
    public String City;
    public String Street;
    public String Salary;
    public String StartDate;
    public String Stage;
    public String Specilization;

    @FXML
    public Pane addTeacherBorderPane3;
    @FXML
    private JFXRadioButton Stage10RadioButton;
    @FXML
    private ToggleGroup StageToggle;
    @FXML
    private JFXRadioButton Stage12RadioButton;
    @FXML
    private JFXRadioButton CountOneRadioButton;
    @FXML
    private ToggleGroup CountToggle;
    @FXML
    private JFXRadioButton CountTowRadioButton;
    @FXML
    private JFXRadioButton ScientificRadioButton;
    @FXML
    private ToggleGroup SpecilizationToggle;
    @FXML
    private JFXRadioButton LiteraryRadioButton;
    @FXML
    private JFXRadioButton CommercialRadioButton;
    @FXML
    private JFXRadioButton IndustrialRadioButton;
    @FXML
    private JFXComboBox<String> FirstMaterialComboBox;
    @FXML
    private JFXComboBox<String> SecondMaterialComboBox;
    @FXML
    private JFXRadioButton Stage11RadioButton;


   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if( (!Stage10RadioButton.isSelected() && !Stage11RadioButton.isSelected() && !Stage12RadioButton.isSelected()) ){
            ScientificRadioButton.setDisable(true);
            CommercialRadioButton.setDisable(true);
            LiteraryRadioButton.setDisable(true);
            IndustrialRadioButton.setDisable(true);
        }
        SecondMaterialComboBox.setDisable(true);
        FirstMaterialComboBox.setDisable(true);

    }
//    private void DisableSP(ActionEvent event){
//        
//    }
    String Tid="";
    String MMid="",MMid2="";
    String Mid="";
    String Mid1="";
    
    @FXML
    private void closeAddTeacher3(ActionEvent event) throws SQLException {
        
        
        
        String Stage="",Specilization="";
        if(Stage10RadioButton.isSelected()){
            Stage = "10th";  
        }
        
        else if(Stage11RadioButton.isSelected()){
            Stage = "11th";
        }
        else if(Stage12RadioButton.isSelected()){
            Stage = "12th";
        }
        if(!Stage.equals("10th")){
            if(ScientificRadioButton.isSelected()){
                Specilization = "Scientific";
            }
            else if(CommercialRadioButton.isSelected()){
                Specilization = "Commercial";
            }
            else if(LiteraryRadioButton.isSelected()){
                Specilization = "Literary";
            }
            else if(IndustrialRadioButton.isSelected()){
                Specilization = "Industrial";
            }
            
        }
        else{
            Specilization="Non";
        }

        
        
        String q1="";
        if((Stage11RadioButton.isSelected() || Stage12RadioButton.isSelected() ) && (ScientificRadioButton.isSelected() || CommercialRadioButton.isSelected() || LiteraryRadioButton.isSelected() || IndustrialRadioButton.isSelected())){

        if(Stage11RadioButton.isSelected() && ScientificRadioButton.isSelected()){
            q1="select materialid from material where MATERIALNAME = '"+FirstMaterialComboBox.getValue()
            +"'"+"And SPECILIZATION='Scientific' And STAGE = '11'";
        }
        else if(Stage11RadioButton.isSelected() && CommercialRadioButton.isSelected()){
            q1="select materialid from material where MATERIALNAME = '"+FirstMaterialComboBox.getValue()
            +"'"+"And SPECILIZATION='Commercial' And STAGE = '11'";
        }
        else if(Stage11RadioButton.isSelected() && LiteraryRadioButton.isSelected()){
            q1="select materialid from material where MATERIALNAME = '"+FirstMaterialComboBox.getValue()
            +"'"+"And SPECILIZATION='Literary' And STAGE = '11'";
        }
        else if(Stage11RadioButton.isSelected() && IndustrialRadioButton.isSelected()){
            q1="select materialid from material where MATERIALNAME = '"+FirstMaterialComboBox.getValue()
            +"'"+"And SPECILIZATION='Industrial' And STAGE = '11'";
        }
        else if(Stage12RadioButton.isSelected() && ScientificRadioButton.isSelected()){
            q1="select materialid from material where MATERIALNAME = '"+FirstMaterialComboBox.getValue()
            +"'"+"And SPECILIZATION='Scientific' And STAGE = '12'";
        }
        else if(Stage12RadioButton.isSelected() && CommercialRadioButton.isSelected()){
            q1="select materialid from material where MATERIALNAME = '"+FirstMaterialComboBox.getValue()
            +"'"+"And SPECILIZATION='Commercial' And STAGE = '12'";
        }
        else if(Stage12RadioButton.isSelected() && LiteraryRadioButton.isSelected()){
            q1="select materialid from material where MATERIALNAME = '"+FirstMaterialComboBox.getValue()
            +"'"+"And SPECILIZATION='Literary' And STAGE = '12'";
        }
        else if(Stage12RadioButton.isSelected() && IndustrialRadioButton.isSelected()){
            q1="select materialid from material where MATERIALNAME = '"+FirstMaterialComboBox.getValue()
            +"'"+"And SPECILIZATION='Industrial' And STAGE = '12'";
        }
        //Chose Second Material
         String q4="";

        if(Stage11RadioButton.isSelected() && ScientificRadioButton.isSelected()){
            q4="select materialid from material where MATERIALNAME = '"+SecondMaterialComboBox.getValue()
            +"'"+"And SPECILIZATION='Scientific' And STAGE = '11'";
        }
        else if(Stage11RadioButton.isSelected() && CommercialRadioButton.isSelected()){
            q4="select materialid from material where MATERIALNAME = '"+SecondMaterialComboBox.getValue()
            +"'"+"And SPECILIZATION='Commercial' And STAGE = '11'";
        }
        else if(Stage11RadioButton.isSelected() && LiteraryRadioButton.isSelected()){
            q4="select materialid from material where MATERIALNAME = '"+SecondMaterialComboBox.getValue()
            +"'"+"And SPECILIZATION='Literary' And STAGE = '11'";
        }
        else if(Stage11RadioButton.isSelected() && IndustrialRadioButton.isSelected()){
            q4="select materialid from material where MATERIALNAME = '"+SecondMaterialComboBox.getValue()
            +"'"+"And SPECILIZATION='Industrial' And STAGE = '11'";
        }
        else if(Stage12RadioButton.isSelected() && ScientificRadioButton.isSelected()){
            q4="select materialid from material where MATERIALNAME = '"+SecondMaterialComboBox.getValue()
            +"'"+"And SPECILIZATION='Scientific' And STAGE = '12'";
        }
        else if(Stage12RadioButton.isSelected() && CommercialRadioButton.isSelected()){
            q4="select materialid from material where MATERIALNAME = '"+SecondMaterialComboBox.getValue()
            +"'"+"And SPECILIZATION='Commercial' And STAGE = '12'";
        }
        else if(Stage12RadioButton.isSelected() && LiteraryRadioButton.isSelected()){
            q4="select materialid from material where MATERIALNAME = '"+SecondMaterialComboBox.getValue()
            +"'"+"And SPECILIZATION='Literary' And STAGE = '12'";
        }
        else if(Stage12RadioButton.isSelected() && IndustrialRadioButton.isSelected()){
            q4="select materialid from material where MATERIALNAME = '"+SecondMaterialComboBox.getValue()
            +"'"+"And SPECILIZATION='Industrial' And STAGE = '12'";
        }
//        if(Stage10RadioButton.isSelected()){
//            q4="select materialid from material where MATERIALNAME = '"+SecondMaterialComboBox.getValue()
//            +"'"+"And SPECILIZATION='Non' And STAGE = '10'";
//        }
        if(Stage10RadioButton.isSelected()){
            q1="select materialid from material where MATERIALNAME = '"+FirstMaterialComboBox.getValue()
            +"'"+"And SPECILIZATION='Non' And STAGE = '10'";
        }
        
       
        
        
        
        OracleDataSource orc4 = new OracleDataSource ();
        orc4.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
        orc4.setUser("testuser");
        orc4.setPassword("123456");
        try (Connection conn4 = orc4.getConnection()) {
        Statement st4 = conn4.createStatement();
        ResultSet rs4 =  st4.executeQuery(q4);
        
        while(rs4.next()){
            Mid1 = rs4.getString(1);
        }
        MMid2=Mid1;
        }

        OracleDataSource orc = new OracleDataSource ();
        orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
        orc.setUser("testuser");
        orc.setPassword("123456");
        Connection conn = orc.getConnection(); 
        Statement st = conn.createStatement();
        ResultSet rs =  st.executeQuery(q1);
        
        while(rs.next()){
            Mid = rs.getString(1);
        }
        MMid=Mid;
        SecondMaterialComboBox.getItems().remove(MMid);
        
        String q2;
        q2="SELECT TeacherId FROM Teacher  order by TeacherId asc";
        OracleDataSource orc1 = new OracleDataSource ();
        orc1.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
        orc1.setUser("testuser");
        orc1.setPassword("123456");
       Connection conn1 = orc1.getConnection();
            Statement st1 = conn1.createStatement();
            ResultSet rs1 =  st1.executeQuery(q2);

            while(rs1.next()){
                Tid = rs1.getString(1);
            } 
        }
//        JOptionPane.showMessageDialog(null, Tid);

        
        
//      while(MMid.equals(MMid2)){
        if(!MMid.equals(MMid2)){
             String q3;
                q3 = "insert into teacher_material values('"+Tid+"','"+MMid+"')";
                OracleDataSource orc2 = new OracleDataSource ();
                orc2.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
                orc2.setUser("testuser");
                orc2.setPassword("123456");
                Connection conn2 = orc2.getConnection();
                Statement st2 = conn2.createStatement();
                st2.executeUpdate(q3);  
                conn2.close();

            if(!SecondMaterialComboBox.isDisable()){
                 String q5;
                 q5 = "insert into teacher_material values('"+Tid+"','"+MMid2+"')";
                 OracleDataSource orc5 = new OracleDataSource ();
                 orc5.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
                 orc5.setUser("testuser");
                 orc5.setPassword("123456");
                 Connection conn5 = orc5.getConnection();
                 Statement st5 = conn5.createStatement();
                 st5.executeUpdate(q5);
                 conn5.close();
            }
            JOptionPane.showMessageDialog(null, "Tecaher Added Succesfuly");
            Stage stage = (Stage) addTeacherBorderPane3.getScene().getWindow();
            stage.close();
            

        }
        else{
//            if(!SecondMaterialComboBox.isDisable()){
             JOptionPane.showMessageDialog(null, "The first Material is the same as the second Material","Error",JOptionPane.ERROR_MESSAGE);
            
        }
      

        try {
       String query = "update teacher set STAGE = '"+Stage+"',SPECILIZATION = '"+Specilization+"' where TeacherId = '"+Tid+"'";
        OracleDataSource orc = new OracleDataSource ();
        orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
        orc.setUser("testuser");
        orc.setPassword("123456");
        try (Connection conn = orc.getConnection()) {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        }
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        
    }

    private void finishAddTeacher3(MouseEvent event) {
        Stage stage = (Stage) addTeacherBorderPane3.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void Radio10Selected(ActionEvent event) throws SQLException {
        if(Stage10RadioButton.isSelected()){
            ScientificRadioButton.setDisable(true);
            CommercialRadioButton.setDisable(true);
            LiteraryRadioButton.setDisable(true);
            IndustrialRadioButton.setDisable(true);
        }
        String query="";
            query="select * from  material where stage = '10'";
             OracleDataSource orc = new OracleDataSource ();
            orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
            orc.setUser("testuser");
            orc.setPassword("123456");
            Connection conn = orc.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            FirstMaterialComboBox.getItems().clear();
            SecondMaterialComboBox.getItems().clear();
            while(rs.next()){
                String s = rs.getString(2);
                FirstMaterialComboBox.getItems().add(s);
                SecondMaterialComboBox.getItems().add(s);
                
            }
            CountTowRadioButton.setDisable(true);
            SecondMaterialComboBox.setDisable(true);
        
    }
    

    @FXML
    private void Radio11Selected(ActionEvent event) throws SQLException {
            CountTowRadioButton.setDisable(false);
            //SecondMaterialComboBox.setDisable(false);
        
            ScientificRadioButton.setDisable(false);
            CommercialRadioButton.setDisable(false);
            LiteraryRadioButton.setDisable(false);
            IndustrialRadioButton.setDisable(false);
            String query="";
            if(ScientificRadioButton.isSelected()){
                query="select * from  material where stage = '11' and SPECILIZATION='Scientific'";
            }
            if(CommercialRadioButton.isSelected()){
                query="select * from  material where stage = '11' and SPECILIZATION='Scientific'";
            }
            if(LiteraryRadioButton.isSelected()){
                query="select * from  material where stage = '11' and SPECILIZATION='Scientific'";
            }
            if(IndustrialRadioButton.isSelected()){
                query="select * from  material where stage = '11' and SPECILIZATION='Scientific'";
            }
            
            if(ScientificRadioButton.isSelected() || CommercialRadioButton.isSelected() || LiteraryRadioButton.isSelected() || IndustrialRadioButton.isSelected()){
                OracleDataSource orc = new OracleDataSource ();
                orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
                orc.setUser("testuser");
                orc.setPassword("123456");
                Connection conn = orc.getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
                FirstMaterialComboBox.getItems().clear();
                SecondMaterialComboBox.getItems().clear();
                while(rs.next()){
                    String s = rs.getString(2);
                    FirstMaterialComboBox.getItems().add(s);
                    SecondMaterialComboBox.getItems().add(s);

                }
            }

    }
    @FXML
    private void Radio12Selected(ActionEvent event) throws SQLException {
            CountTowRadioButton.setDisable(false);
            //SecondMaterialComboBox.setDisable(false);
            ScientificRadioButton.setDisable(false);
            CommercialRadioButton.setDisable(false);
            LiteraryRadioButton.setDisable(false);
            IndustrialRadioButton.setDisable(false);
            String query="";
            if(ScientificRadioButton.isSelected()){
                query="select * from  material where stage = '12' and SPECILIZATION='Scientific'";
            }
            if(CommercialRadioButton.isSelected()){
                query="select * from  material where stage = '12' and SPECILIZATION='Scientific'";
            }
            if(LiteraryRadioButton.isSelected()){
                query="select * from  material where stage = '12' and SPECILIZATION='Scientific'";
            }
            if(IndustrialRadioButton.isSelected()){
                query="select * from  material where stage = '12' and SPECILIZATION='Scientific'";
            }
            
            if(ScientificRadioButton.isSelected() || CommercialRadioButton.isSelected() || LiteraryRadioButton.isSelected() || IndustrialRadioButton.isSelected()){
                OracleDataSource orc = new OracleDataSource ();
                orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
                orc.setUser("testuser");
                orc.setPassword("123456");
                Connection conn = orc.getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query);
                FirstMaterialComboBox.getItems().clear();
                SecondMaterialComboBox.getItems().clear();
                while(rs.next()){
                    String s = rs.getString(2);
                    FirstMaterialComboBox.getItems().add(s);
                    SecondMaterialComboBox.getItems().add(s);

                }
            }
            

        
    }
    
    @FXML
    private void ScientificSelected(ActionEvent event) throws SQLException{
        String query="";
        if(Stage11RadioButton.isSelected()){
            query="select * from  material where stage = '11' and SPECILIZATION='Scientific'";
        }
        if(Stage12RadioButton.isSelected()){
            query="select * from  material where stage = '12' and SPECILIZATION='Scientific'";
        }
        OracleDataSource orc = new OracleDataSource ();
            orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
            orc.setUser("testuser");
            orc.setPassword("123456");
            Connection conn = orc.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            FirstMaterialComboBox.getItems().clear();
            SecondMaterialComboBox.getItems().clear();
            while(rs.next()){
                String s = rs.getString(2);
                FirstMaterialComboBox.getItems().add(s);
                SecondMaterialComboBox.getItems().add(s);
                
            }
        
    }
    @FXML
    private void CommercialSelected(ActionEvent event) throws SQLException{
        String query="";
        if(Stage11RadioButton.isSelected()){
            query="select * from  material where stage = '11' and SPECILIZATION='Commercial'";
        }
        if(Stage12RadioButton.isSelected()){
            query="select * from  material where stage = '12' and SPECILIZATION='Commercial'";
        }
        OracleDataSource orc = new OracleDataSource ();
            orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
            orc.setUser("testuser");
            orc.setPassword("123456");
            Connection conn = orc.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            FirstMaterialComboBox.getItems().clear();
            SecondMaterialComboBox.getItems().clear();
            while(rs.next()){
                String s = rs.getString(2);
                FirstMaterialComboBox.getItems().add(s);
                SecondMaterialComboBox.getItems().add(s);
                
            }
    }
    
    @FXML
    private void LiterarySelected(ActionEvent event) throws SQLException{
        String query="";
        if(Stage11RadioButton.isSelected()){
            query="select * from  material where stage = '11' and SPECILIZATION='Literary'";
        }
        if(Stage12RadioButton.isSelected()){
            query="select * from  material where stage = '12' and SPECILIZATION='Literary'";
        }
        OracleDataSource orc = new OracleDataSource ();
            orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
            orc.setUser("testuser");
            orc.setPassword("123456");
            Connection conn = orc.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            FirstMaterialComboBox.getItems().clear();
            SecondMaterialComboBox.getItems().clear();
            while(rs.next()){
                String s = rs.getString(2);
                FirstMaterialComboBox.getItems().add(s);
                SecondMaterialComboBox.getItems().add(s);
                
            }
        
    }
    @FXML
    private void IndustrialSelected(ActionEvent event) throws SQLException{
        String query="";
        if(Stage11RadioButton.isSelected()){
            query="select * from  material where stage = '11' and SPECILIZATION='Industrial'";
        }
        if(Stage12RadioButton.isSelected()){
            query="select * from  material where stage = '12' and SPECILIZATION='Industrial'";
        }
        OracleDataSource orc = new OracleDataSource ();
            orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
            orc.setUser("testuser");
            orc.setPassword("123456");
            Connection conn = orc.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            FirstMaterialComboBox.getItems().clear();
            SecondMaterialComboBox.getItems().clear();
            while(rs.next()){
                String s = rs.getString(2);
                FirstMaterialComboBox.getItems().add(s);
                SecondMaterialComboBox.getItems().add(s);
                
            }
        
    }
    
    
    
    
    @FXML
    private void enableMat(ActionEvent event){
        if(CountOneRadioButton.isSelected()){
            SecondMaterialComboBox.setDisable(true);
            FirstMaterialComboBox.setDisable(false);

        }
        else if(CountTowRadioButton.isSelected()){
            SecondMaterialComboBox.setDisable(false);
            FirstMaterialComboBox.setDisable(false);
        }
        
    }

    @FXML
    private void closeAddTeacher3(MouseEvent event) {
    }
}

