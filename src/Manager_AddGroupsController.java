

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import oracle.jdbc.pool.OracleDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.ObservableList;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Manager_AddGroupsController implements Initializable {

    @FXML
    private TextField GroupIDTextField;
    @FXML
    private TextField GroupNameTextField;
    @FXML
    private TextField StartTimeTextField;
    @FXML
    private JFXRadioButton STTRadioButton;
    @FXML
    private JFXRadioButton SMWRadioButton;
    @FXML
    private JFXComboBox<String> SpecilizationComboBox;
    @FXML
    private JFXComboBox<String> TeacherComboBox;
    @FXML
    private JFXComboBox<String> MaterialComboBox;
    @FXML
    private JFXComboBox<String> ClassComboBox;
    @FXML
    private TableView<Groups> GroupsTable;
    @FXML
    private TableColumn<Groups, String> GroupIDCol;
    @FXML
    private TableColumn<Groups, String> GroupNameCol;
    @FXML
    private TableColumn<Groups, String> PeriodCol;
    @FXML
    private TableColumn<Groups, String> MaterialCol;
    @FXML
    private TableColumn<Groups, String> StartTimeCol;
    @FXML
    private TableColumn<Groups,String> ClassCol;
    @FXML
    private TableColumn<Groups, String> TeacherCol;
    @FXML
    private JFXComboBox<String> StageCombobox;
    @FXML
    private ToggleGroup Toggle;
    String Stage ;
    String TeacherID;
    //String [] materialid = {"dasd"};
    @FXML
    private Pane MGHome;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        new animatefx.animation.ZoomIn(MGHome).play();
        GroupIDCol.setCellValueFactory(new PropertyValueFactory<> ("GroupID"));
        GroupNameCol.setCellValueFactory(new PropertyValueFactory<> ("GroupName"));
        PeriodCol.setCellValueFactory(new PropertyValueFactory<> ("Period"));
        MaterialCol.setCellValueFactory(new PropertyValueFactory<> ("Material"));
        StartTimeCol.setCellValueFactory(new PropertyValueFactory<> ("StartTime"));
        ClassCol.setCellValueFactory(new PropertyValueFactory<> ("Classes"));
        TeacherCol.setCellValueFactory(new PropertyValueFactory<> ("Teacher"));
        String test = "Nothing";
        String [] Specilization = {"Nothing", "Scientific" , "Literary" , "Industrial"  ,"Commercial"};
        String [] Stage = {"Nothing" , "10" , "11" , "12" };
        StageCombobox.getItems().addAll(Stage);
        StageCombobox.getSelectionModel().selectFirst();
        //SpecilizationComboBox.getItems().add(test);
        SpecilizationComboBox.getItems().addAll(Specilization);
        SpecilizationComboBox.getSelectionModel().selectFirst();
        TeacherComboBox.getItems().add(test);
        MaterialComboBox.getItems().add(test);
        
        TeacherComboBox.getSelectionModel().selectFirst();
        MaterialComboBox.getSelectionModel().select("Nothing");
        
        SpecilizationComboBox.setDisable(true);
        String Query4 = "Select ClassID From Class" ; //for class
        try {
        OracleDataSource orc = new OracleDataSource();
        orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
        orc.setUser("testuser");
        orc.setPassword("123456");
        Connection conn = orc.getConnection();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(Query4);
        ClassComboBox.getItems().clear();
        ClassComboBox.getItems().add(test);
        ClassComboBox.getSelectionModel().select("Nothing");
        while (rs.next()) {
            String s = rs.getString(1);
           ClassComboBox.getItems().add(s);
        }
        
        conn.close();
        
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex.toString());
        }
        
        
        String Query = "SELECT GROUPID , GROUPNAME , PERIOD , MATERIALNAME , STARTTIME , C.CLASSID , FIRSTNAME "
                    + "FROM GROUPS G "
                    + "INNER JOIN MATERIAL M "
                    + "ON G.MATERIALID = M.MATERIALID "
                    + "INNER JOIN TEACHER T "
                    + "ON G.TEACHERID = T.TEACHERID "
                    + "INNER JOIN CLASS C "
                    + "ON G.CLASSID = C.CLASSID "
                    + "ORDER BY GROUPID " ;
                    oracle (Query);
        
        
    }    
    
    public void oracle (String s) {
        try {
        OracleDataSource orc = new OracleDataSource();
        orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
        orc.setUser("testuser");
        orc.setPassword("123456");
        Connection conn = orc.getConnection();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(s);
        GroupsTable.getItems().clear();
        while (rs.next()) {
            Groups g = new Groups (rs.getString(1) , rs.getString(2) , rs.getString(3) , rs.getString(4) , rs.getString(5) , rs.getString(6) , rs.getString(7));
            GroupsTable.getItems().add(g);
        }
        
        
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex.toString());
        }
    }

    @FXML
    private void SearchGroups(ActionEvent event) {
        int flag = 0; 
        StringBuilder query = new StringBuilder ();
        query.append("SELECT GROUPID , GROUPNAME , PERIOD , MATERIALNAME , STARTTIME , C.CLASSID , FIRSTNAME "
                    + "FROM GROUPS G "
                    + "INNER JOIN MATERIAL M "
                    + "ON G.MATERIALID = M.MATERIALID "
                    + "INNER JOIN TEACHER T "
                    + "ON G.TEACHERID = T.TEACHERID "
                    + "INNER JOIN CLASS C "
                    + "ON G.CLASSID = C.CLASSID where "
                    );
       // JOptionPane.showMessageDialog(null, "out");
        if (GroupIDTextField.getText().equals("") && GroupNameTextField.getText().equals("") && StartTimeTextField.getText().equals("") && (!STTRadioButton.isSelected() && !SMWRadioButton.isSelected()) &&  
            (SpecilizationComboBox.isDisable()==true) &&StageCombobox.getValue().equals("Nothing")) {
            String Query = "SELECT GROUPID , GROUPNAME , PERIOD , MATERIALNAME , STARTTIME , C.CLASSID , FIRSTNAME "
                    + "FROM GROUPS G "
                    + "INNER JOIN MATERIAL M "
                    + "ON G.MATERIALID = M.MATERIALID "
                    + "INNER JOIN TEACHER T "
                    + "ON G.TEACHERID = T.TEACHERID "
                    + "INNER JOIN CLASS C "
                    + "ON G.CLASSID = C.CLASSID "
                    + "ORDER BY GROUPID " ;
                    oracle (Query);
                   
        }
        else {
            if (!GroupIDTextField.getText().equals("")) {
                String ID = GroupIDTextField.getText();
                if (flag == 0) {
                query.append("GROUPID = '" + ID + "'");
                oracle(query.toString());
                    flag = 1;  
                }
                else {
                    query.append("and GROUPID = '" + ID + "' ");
                }
            }
            
             if (!GroupNameTextField.getText().equals("")) {
                if (flag == 0) {
                query.append("GROUPNAME = '" + GroupNameTextField.getText() + "' ");
                oracle(query.toString());
                    flag = 1;  
                }
                else {
                    query.append("and GROUPNAME = '" + GroupNameTextField.getText() + "'");
                }
            }
             
              if (!StartTimeTextField.getText().equals("")) {
                if (flag == 0) {
                query.append("STARTTIME = '" + StartTimeTextField.getText() + "' ");
                oracle(query.toString());
                    flag = 1;  
                }
                else {
                    query.append("and STARTTIME = '" + StartTimeTextField.getText() + "' ");
                }
            }
          if (!StageCombobox.getValue().equals("Nothing")) {
            if (flag == 0) {
                query.append("T.STAGE = '" + StageCombobox.getValue() + "' ");
                oracle(query.toString());
                    flag = 1;  
                }
                else {
                    query.append("and T.STAGE = '" + StageCombobox.getValue() + "' ");
                }
            
        if (!SpecilizationComboBox.getValue().equals("Nothing") && SpecilizationComboBox.isDisable() == false  ) {
            
            if (flag == 0) {
                query.append("M.SPECILIZATION = '" + SpecilizationComboBox.getValue() + "' ");
                oracle(query.toString());
                    flag = 1;  
                }
                else {
                    query.append("and M.SPECILIZATION = '" + SpecilizationComboBox.getValue() + "' ");
                }
            
        }
        
        
    }
        
      }        
    }
    @FXML
    private void AddGroups(ActionEvent event) throws SQLException {
        String Period ;
        if (GroupIDTextField.getText().equals("") || GroupNameTextField.getText().equals("") || StartTimeTextField.getText().equals("") ||
           (!STTRadioButton.isSelected() &&!SMWRadioButton.isSelected()) || SpecilizationComboBox.isDisable() == true 
                || TeacherComboBox.getValue().equals("Nothing") || MaterialComboBox.getValue().equals("Nothing") || ClassComboBox.getValue().equals("Nothing") || 
               StageCombobox.getValue().equals("Nothing") ) {
            JOptionPane.showMessageDialog(null, "Please Enter All Values" , "Warnning" , JOptionPane.WARNING_MESSAGE);
        }
        else {
            if (STTRadioButton.isSelected()) {
                Period = "S-T-T";
            }else Period= "S-M-W";
            String [] TeacherId = TeacherComboBox.getValue().split(":");
            String Teacherid = TeacherId[0];
            String [] MaterialID = MaterialComboBox.getValue().split(":");  
            String MaterialiD = MaterialID[0];
            String ID = GroupIDTextField.getText();
            String GroupName = GroupNameTextField.getText();
            String StartTime = StartTimeTextField.getText();
            String Specilization = SpecilizationComboBox.getValue();
            
            String Class = ClassComboBox.getValue();
            String stage = StageCombobox.getValue();
            //JOptionPane.showMessageDialog(null,Teacherid );
             String Query = "INSERT INTO GROUPS VALUES ('" + ID+"' , '" +  GroupName + "' , '" + Class + "' , ' "
            + StartTime + "' , '" + Period + "' , '" + MaterialiD+"' , '" + Teacherid +"' )"; 
            OracleDataSource orc = new OracleDataSource();
            orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
            orc.setUser("testuser");
            orc.setPassword("123456");
            Connection conn = orc.getConnection();
            Statement stm = conn.createStatement();
            stm.executeUpdate(Query);
            JOptionPane.showMessageDialog(null,   "Done!!" , "Information" , JOptionPane.INFORMATION_MESSAGE);
            GroupIDTextField.setText("");
            GroupNameTextField.setText("");
            StartTimeTextField.setText("");
            SpecilizationComboBox.getSelectionModel().select("Nothing");
            ClassComboBox.getSelectionModel().select("Nothing");
            StageCombobox.getSelectionModel().select("Nothing");
            TeacherComboBox.getSelectionModel().select("Nothing");
            STTRadioButton.setSelected(false);
            SMWRadioButton.setSelected(false);
            
            
        }
        
        
        
    }

    @FXML
    private void RemoveGroups(ActionEvent event) {
        ObservableList <Groups> obs;
        obs = GroupsTable.getSelectionModel().getSelectedItems();
        try {
            int selected ;
            if (!obs.isEmpty()) {
            OracleDataSource orc = new OracleDataSource ();
            orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
            orc.setUser("testuser");
            orc.setPassword("123456");
            Connection conn = orc.getConnection();
            Statement stm = conn.createStatement();
            String ID = obs.get(0).getGroupID();
            String Query = "Delete From Groups where GroupID = '" + ID + "'";
            stm.executeUpdate(Query);
            selected = GroupsTable.getSelectionModel().getSelectedIndex();
            GroupsTable.getItems().remove(selected);
            
            }
        }catch (Exception EX ) {
            JOptionPane.showMessageDialog(null, EX.toString());
        }
    }

    @FXML
    private void SpecilizationCombobox(ActionEvent event) throws SQLException { 
        MaterialComboBox.getItems().add("Nothing");
        MaterialComboBox.getSelectionModel().select("Nothing");
        String Specilization = SpecilizationComboBox.getValue();
        String Query1 = "SELECT MATERIALID , MATERIALNAME FROM MATERIAL WHERE STAGE = '" + Stage + "' AND "
                + "SPECILIZATION = '" +  Specilization + "'"; // for material
        String Query2 ="SELECT MATERIALID , MATERIALNAME FROM MATERIAL WHERE STAGE = '" + Stage + "'";
        String Query3 = "SELECT TEACHERID , FIRSTNAME , LASTNAME FROM TEACHER WHERE "
                + "STAGE = ' " + Stage+ "' AND SPECILIZATION = ' " + Specilization +"'" ; // for teacher
       
        OracleDataSource orc = new OracleDataSource();
        orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
        orc.setUser("testuser");
        orc.setPassword("123456");
        Connection conn = orc.getConnection();
        Statement stm = conn.createStatement();
        
        if (!Stage.equals("10")) {   
                 //MaterialComboBox.getItems().clear();
                 
                 ResultSet rs = stm.executeQuery(Query1);  
                 MaterialComboBox.getItems().clear(); 
                 MaterialComboBox.getItems().add("Nothing");
                 MaterialComboBox.getSelectionModel().select("Nothing");
                 while (rs.next()) {
                 String material = rs.getString(1) + ":" + rs.getString(2);
                 MaterialComboBox.getItems().add(material);
                 
            }
                 rs = stm.executeQuery(Query3);
                 TeacherComboBox.getItems().clear();
                 while (rs.next()) {
                 String material = rs.getString(1) + ":" + rs.getString(2) + " " + rs.getString(3);
                 TeacherComboBox.getItems().add(material);
        
        }
        
                 
        
    }
                 
    }

    @FXML
    private void StageComboBox(ActionEvent event) throws SQLException {
        if (StageCombobox.getValue().equals("Nothing")) {
            SpecilizationComboBox.setDisable(true);
            MaterialComboBox.getItems().clear();
           
        }
        else {   
        if (StageCombobox.getValue().equals("10")) {
            
            SpecilizationComboBox.setDisable(true);
            SpecilizationComboBox.getSelectionModel().select("Nothing");
            String Query2 ="SELECT MATERIALID , MATERIALNAME FROM MATERIAL WHERE STAGE = '" + StageCombobox.getValue() + "'";
            String Query3 = "SELECT TEACHERID , FIRSTNAME ,LASTNAME FROM TEACHER WHERE STAGE = '10' ";
            OracleDataSource orc = new OracleDataSource();
            orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
            orc.setUser("testuser");
             orc.setPassword("123456");
            Connection conn = orc.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(Query2);
            MaterialComboBox.getItems().clear();
            MaterialComboBox.getItems().add("Nothing");
            MaterialComboBox.getSelectionModel().select("Nothing");
            while (rs.next()) {
                String material = rs.getString(1) + ":"+ rs.getString(2);
                MaterialComboBox.getItems().add(material);
            }
            
            rs = stm.executeQuery(Query3) ;
            TeacherComboBox.getItems().clear();
            while (rs.next()) 
            {
               String material = rs.getString(1) + ":" +rs.getString(2) + " " + rs.getString(3);
               TeacherComboBox.getItems().add(material);
            }
            
            
        }
        
        else {
            SpecilizationComboBox.setDisable(false);
            
        }
    }
    Stage = StageCombobox.getValue();
}

    @FXML
    private void MaterialCombobox(ActionEvent event) throws Exception {
        String x = MaterialComboBox.getValue();
        MaterialComboBox.getSelectionModel().select(x);
        String [] materialid = MaterialComboBox.getValue().split(":");
        TeacherID = materialid[0];
        String query = "SELECT X.TEACHERID , FIRSTNAME , LASTNAME "
                + "FROM TEACHER T "
                + "INNER JOIN TEACHER_MATERIAL X "
                + "ON X.TEACHERID = T.TEACHERID "
                + "INNER JOIN MATERIAL M "
                + "ON X.MATERIALID = M.MATERIALID "
                + "WHERE X.MATERIALID = '" +TeacherID + "'" ;
        OracleDataSource orc = new OracleDataSource();
        orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
        orc.setUser("testuser");
        orc.setPassword("123456");
        Connection con = orc.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(query);
        TeacherComboBox.getItems().clear();
        while (rs.next()) {
            String teacher = rs.getString(1) + ":" + rs.getString(2) + " " + rs.getString(3);
            TeacherComboBox.getItems().add(teacher);
        }
       }
}

   

