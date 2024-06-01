import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import oracle.jdbc.pool.OracleDataSource;


public class ManagerAddStudentControllar3 implements Initializable {
//    ManagerAddStudentController m1 = 
    
    @FXML
    public Pane Pane3;
    public String x; 
    @FXML
    private JFXRadioButton LiteraryRadioButton;
    @FXML
    private JFXRadioButton ScientificRadioButton;
    @FXML
    private JFXRadioButton IndustrialRadilButton;
    @FXML
    private JFXRadioButton CommercialRadioButton;
    @FXML
    private JFXButton AddMaterilaButton;
    @FXML
    private Label label;
    // all values
    String StudentID;
    String FirstName;
    String MidName; 
    String LastName;
    String Gender;
    String Birthdate;
    String Passeord;
    String City;
    String Street;
    String Email;
    String Phone;
    String Stage;
    String Specilization;
    String NetAccount;
    String StrtDate;
    
   
    String q;
    @FXML
    private ToggleGroup ToggleSpe;
    @FXML
    private JFXComboBox<String> MaterialList;
    @FXML
    private TableView<StudentMaterialTable> Material;
    @FXML
    private TableColumn<StudentMaterialTable, String> TeacherName;
    @FXML
    private TableColumn<StudentMaterialTable, String> Period;
    @FXML
    private TableColumn<StudentMaterialTable, String> Time;
    @FXML
    private TableColumn<StudentMaterialTable, String> GroupID;
    @FXML
    public void Material(ActionEvent event) throws SQLException{
            OracleDataSource orc = new OracleDataSource ();
            orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
            orc.setUser("testuser");
            orc.setPassword("123456");
            Connection conn = orc.getConnection();
            Statement st = conn.createStatement();
            
        
        if(Stage.equals("10")){
            q="select * from MATERIAL where stage = '10' ";
            
            ResultSet rs = st.executeQuery(q);
            MaterialList.getItems().clear();
            while(rs.next()){
                String s = rs.getString(2);
           
                MaterialList.getItems().add(s); 
                Specilization = "None";
            }
        }
        else{
            if (Stage.equals("11"))
            {
                if (ScientificRadioButton.isSelected())
                {
                    q="select * from MATERIAL where stage = '11' and SPECILIZATION = 'Scientific'";
                    ResultSet rs = st.executeQuery(q);
                    MaterialList.getItems().clear();
                    Specilization = "Scientific";
                    while(rs.next()){
                        String s = rs.getString(2);
                        MaterialList.getItems().add(s); 
                    }
//                    ScientificRadioButton.setSelected(false);
                }
                else if (LiteraryRadioButton.isSelected()){
                    q="select * from MATERIAL where stage = '11' and SPECILIZATION = 'Literary'";
                    ResultSet rs = st.executeQuery(q);
                    MaterialList.getItems().clear();
                    Specilization = "Literary";
                    while(rs.next()){
                        String s = rs.getString(2);
                        MaterialList.getItems().add(s); 
                    }  
//                    LiteraryRadioButton.setSelected(false);
                }
                else if (IndustrialRadilButton.isSelected()){
                    q="select * from MATERIAL where stage = '11' and SPECILIZATION = 'Industrial'";
                    ResultSet rs = st.executeQuery(q);
                    MaterialList.getItems().clear();
                    Specilization = "Industrial";
                    while(rs.next()){
                        String s = rs.getString(2);
                        MaterialList.getItems().add(s); 
                    }  
//                    IndustrialRadilButton.setSelected(false);
                    
                }
                else if (CommercialRadioButton.isSelected()){
                    q="select * from MATERIAL where stage = '11' and SPECILIZATION = 'Commercial'";
                    ResultSet rs = st.executeQuery(q);
                    MaterialList.getItems().clear();
                    Specilization = "Commercial";
                    while(rs.next()){
                        String s = rs.getString(2);
                        MaterialList.getItems().add(s); 
                    } 
//                    CommercialRadioButton.setSelected(false);
                }
            }
            else if (Stage.equals("12"))
            {
                if (ScientificRadioButton.isSelected())
                {
                    q="select * from MATERIAL where stage = '12' and SPECILIZATION = 'Scientific'";
                    ResultSet rs = st.executeQuery(q);
                    MaterialList.getItems().clear();
                    Specilization = "Scientific";
                    while(rs.next()){
                        String s = rs.getString(2);
                        MaterialList.getItems().add(s); 
                    } 
//                    ScientificRadioButton.setSelected(false);
                }
                else if (CommercialRadioButton.isSelected()){
                    q="select * from MATERIAL where stage = '12' and SPECILIZATION = 'Commercial'";
                    ResultSet rs = st.executeQuery(q);
                    MaterialList.getItems().clear();
                    Specilization = "Commercial";
                    while(rs.next()){
                        String s = rs.getString(2);
                        MaterialList.getItems().add(s); 
                    }  
//                    CommercialRadioButton.setSelected(false);
                }
                else if (IndustrialRadilButton.isSelected()){
                    q="select * from MATERIAL where stage = '12' and SPECILIZATION = 'Industrial'";
                    ResultSet rs = st.executeQuery(q);
                    MaterialList.getItems().clear();
                    Specilization = "Industrial";
                    while(rs.next()){
                        String s = rs.getString(2);
                        MaterialList.getItems().add(s); 
                    }  
//                    IndustrialRadilButton.setSelected(false);
                }
                else if (LiteraryRadioButton.isSelected()){
                    q="select * from MATERIAL where stage = '12' and SPECILIZATION = 'Literary'";
                    ResultSet rs = st.executeQuery(q);
                    MaterialList.getItems().clear();
                    Specilization = "Literary";
                    while(rs.next()){
                        String s = rs.getString(2);
                        MaterialList.getItems().add(s); 
                    }  
//                    LiteraryRadioButton.setSelected(false);
                }
                
            }  
        }
        
           
        
            
       
    }   
    @FXML
    public void MaterialListMethod(ActionEvent event) throws Exception{
        ResultSet rs1;
        String MaterilaQuery ;
        String s = "";
        OracleDataSource orc = new OracleDataSource ();
        orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
        orc.setUser("testuser");
        orc.setPassword("123456");
        Connection conn = orc.getConnection();
        Statement st = conn.createStatement();
        String s1=MaterialList.getValue();
        String q1 = "select MaterialId from material where materialname = "+"'"+s1+"'"+ "and Stage ="+"'"+Stage+"'"+"and SPECILIZATION = "+"'"+Specilization+"'";
        rs1 = st.executeQuery(q1);
        while(rs1.next()){
             s = rs1.getString(1); // id of material
        }

       
        
        MaterilaQuery = "SELECT FirstName, G.GroupID , Period , StartTime From Teacher T"
                + " INNER JOIN GROUPS G "
                + "ON T.TEACHERID = G.TEACHERID "
                + "INNER JOIN MATERIAL M "
                + "ON G.MATERIALID = M.MATERIALID "
                + "INNER JOIN TEACHER_MATERIAL X "
                + "ON X.TEACHERID = T.TEACHERID "
                + "WHERE X.MATERIALID = G.MATERIALID AND"
                + " G.MaterialID = '" + s +"'";


        ResultSet rs2 ; 
        rs2 = st.executeQuery(MaterilaQuery);
        Material.getItems().clear(); 
        while (rs2.next())
        {
            StudentMaterialTable m = new StudentMaterialTable (rs2.getString(1) , rs2.getString(2) , rs2.getString(3) , rs2.getString(4));
            Material.getItems().add(m);
           
            
        }
         
        //        JOptionPane.showMessageDialog(null , s);
//       while(rs1.next()){
//             s = rs1.getString(1); // id of material
//        }
//        JOptionPane.showMessageDialog(null, s);
//       String s1=MaterialList.getValue();
//       String q1 = "select MaterialId from material where materialname = "+"'"+s1+"'"+ "and Stage ="+"'"+Stage+"'";
//       rs1 = st.executeQuery(q1);
//       if (Stage.equals("10"))
//       {
//             s1=MaterialList.getValue();
//             q1 = "select MaterialId from material where materialname = "+"'"+s1+"'"+ "and Stage ="+"'"+Stage+"'";
//             rs1 = st.executeQuery(q1);
//             while(rs1.next()){
//             s = rs1.getString(1); // id of material
//        }
//        JOptionPane.showMessageDialog(null,s);
//       }
//       
//       
//       
//       else if (Stage.equals("11") || Stage.equals("12"))
//       {
//            s1=MaterialList.getValue();
//            q1 = "select MaterialId from material where materialname = "+"'"+s1+"'"+ "and Stage ="+"'"+Stage+"'" + "and SPECILIZATION = " + "'" +Specilization+ "'" ;
//            rs1 = st.executeQuery(q1);
//            while(rs1.next()){
//             s = rs1.getString(1);
//        }
//        JOptionPane.showMessageDialog(null,s);
//       }
        
        //                MaterilaQuery = "SELECT FirstName, G.GroupID , Period , StartTime From Teacher T"
//                + " natural JOIN GROUPS G "
//                + "ON T.TEACHERID = G.TEACHERID "
//                + "natural JOIN MATERIAL M "
//                + "ON G.MATERIALID = M.MATERIALID "
//                + "where G.MaterialID = '" + s +"'";




//               MaterilaQuery = "Select * from Student S "
//                       + "Inner Join STUDENT_GROUPS T "
//                       + "on S.StudentID = T.StudentID "
//                       + "where S.FirstName = 'Ayham'" ;
                       

//        MaterilaQuery = "SELECT * FROM TEACHER";
        
        
        
       }  
    
    // to turn of the Specilization RadioButtons
    public void turnoff() {
        if (Stage.equals("10")){
        ScientificRadioButton.setDisable(true);
        LiteraryRadioButton.setDisable(true);
        IndustrialRadilButton.setDisable(true);
        CommercialRadioButton.setDisable(true);
            }
            else {
        ScientificRadioButton.setDisable(false);
        LiteraryRadioButton.setDisable(false);
        IndustrialRadilButton.setDisable(false);
        CommercialRadioButton.setDisable(false);
            }
        
    }
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TeacherName.setCellValueFactory(new PropertyValueFactory<>("TeacherName") );
        Period.setCellValueFactory( new PropertyValueFactory<>("Period") );
        Time.setCellValueFactory(new PropertyValueFactory<>("StartTime"));
        GroupID.setCellValueFactory(new PropertyValueFactory<>("GroupsID"));
        Material.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            
    }
    
  
   
    @FXML
    private void EndAddStudent(ActionEvent event) throws SQLException, IOException {
        
        //String query="insert into STUDENT values("+"'"+StudentID+"'"+","+"'"+FirstName+"'"+","+"'"+MidName+"'"+","+"'"+LastName+"'"+","+"'"+Gender+"'"+","+"'"+Stage+"'"+","+"'"+Specilization+"'"+","+"'"+Email+"'"+","+"'"+Passeord+"'"+","+"'"+Phone+"'"+","+"'"+currentDate+"'"+","+0+","+"'"+Birthdate+"'"+","+"'"+Street+"'"+","+"'"+City+"')" ;
        int count = 0;
        String query = "select count(*) from STUDENT_GROUPS where STUDENTID = '" + StudentID + "'";
       OracleDataSource orc = new OracleDataSource ();
       orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
       orc.setUser("testuser");
       orc.setPassword("123456");
       Connection conn = orc.getConnection();
        try (Statement st = conn.createStatement()) {
            ResultSet rs =  st.executeQuery(query);
           if (rs.next()) {
                //JOptionPane.showMessageDialog(null, "Inside");
                count = rs.getInt(1);
            }
            
            //JOptionPane.showMessageDialog(null, "Hello" + count);
            int money = 40 * count * -1;
            String query1 = "Update STUDENT "
                    + "set NETACCOUNT = " +"'" +money+"' ,"
                    + "SPECILIZATION = '" + Specilization + "'"
                    + " where STUDENTID ='" +  StudentID + "'";  
            st.executeUpdate(query1);
        }
        
        
        
       JOptionPane.showMessageDialog(null, "Added Succesfuly");
      
        Stage stage = (Stage) Pane3.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void ClosePage3(ActionEvent event) {
          Stage stage = (Stage) Pane3.getScene().getWindow();
          stage.close();
    }
    
    
    public void AddMaterilaButton () throws Exception {
       
         ObservableList <StudentMaterialTable> obs ;
         obs = Material.getSelectionModel().getSelectedItems();
         if (!obs.isEmpty())
         {
             String GroupId = obs.get(0).getGroupsID();
             String query = "INSERT INTO STUDENT_GROUPS VALUES ('" + StudentID + "','" + GroupId + "')";  
             OracleDataSource orc = new OracleDataSource ();
             orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
             orc.setUser("testuser");
             orc.setPassword("123456");
             Connection conn = orc.getConnection();
             Statement st = conn.createStatement();
             st.executeUpdate(query);
             st.close();
             int selected = Material.getSelectionModel().getSelectedIndex();
             Material.getItems().remove(selected);
             
         }
         
    }
    
   
}