/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import oracle.jdbc.pool.OracleDataSource;

public class Teacher_YourStudentController implements Initializable {
    String username;
    @FXML
    private TextField IDTextField;
    @FXML
    private TextField NameTextField;
    @FXML
    private TableColumn<T_Student, String> SIDColumn;
    @FXML
    private TableColumn<T_Student, String> FNmaeColumn;
    @FXML
    private TableColumn<T_Student, String> LNameColumn;
    @FXML
    private TableColumn<T_Student, String> PhoneColumn;
    @FXML
    private TableColumn<T_Student, String> MNameColumn;
    @FXML
    private TableColumn<T_Student, String> MIDColumn;
    @FXML
    private TableColumn<T_Student, String> EmailColumn;
    @FXML
    private TableView<T_Student> TStudentTable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SIDColumn.setCellValueFactory(new PropertyValueFactory<>("SID"));
        FNmaeColumn.setCellValueFactory(new PropertyValueFactory<>("FName"));
        LNameColumn.setCellValueFactory(new PropertyValueFactory<>("LName"));
        PhoneColumn.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        MNameColumn.setCellValueFactory(new PropertyValueFactory<>("MName"));
        MIDColumn.setCellValueFactory(new PropertyValueFactory<>("MID"));
        EmailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));

    }
    public void oracle (String s) throws Exception
      {
          
            OracleDataSource orc = new OracleDataSource ();
            orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
            orc.setUser("testuser");
            orc.setPassword("123456");
            Connection conn = orc.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(s);
            TStudentTable.getItems().clear();
            while(rs.next())   
           { 
             T_Student ts = new T_Student (rs.getString(1) ,rs.getString(2) , rs.getString(3) , rs.getString(4) , rs.getString(5) , rs.getString(6) , rs.getString(7));
             
             TStudentTable.getItems().add(ts);
           }
      }

    @FXML
    private void SearchStudent(ActionEvent event) throws SQLException, Exception {
        StringBuilder query1 = new StringBuilder();
        StringBuilder query2 = new StringBuilder();
        query2.append("select S.studentid, S.FIRSTNAME, S.LASTNAME, S.PHONENUMBER, M.MATERIALNAME, M.MATERIALID, S.email "
                + "From Groups G "
                + "Inner Join Student_Groups S"
                + "on G.GroupID = S.GroupID "
                + "Inner Join Material M "
                + "on M.MaterialID = G.MaterialID "
                + "Inner Join Teacher_Material Y "
                + "on Y.MaterialID = M.MaterialID "
                + "Where TeacherID = '" + username + "' and ");
        int flag =0;
        if(!IDTextField.getText().isEmpty()){
            if(flag == 0 ){
                query2.append("S.studentid= '"+IDTextField.getText()+"'");
                flag=1;
                oracle(query2.toString());
            }
            else{
                query2.append("and S.studentid= '"+IDTextField.getText()+"'");
                flag=1;
                oracle(query2.toString());
                
            }
        }
        if(! NameTextField.getText().isEmpty()){
            if(flag==0){
                query2.append("S.firstname= '"+NameTextField.getText()+"'");
                flag=1;
                oracle(query2.toString());
            }
            else{
                query2.append("and S.firstname= '"+NameTextField.getText()+"'");
                flag=1;
                oracle(query2.toString());
            }  
        }
        
        
        
        
        
        
        
        
        if(flag==0){
        String query ="select ST.studentid, ST.FIRSTNAME, ST.LASTNAME, ST.PHONENUMBER, M.MATERIALNAME, M.MATERIALID, ST.email "
                + "From Groups G "
                + "Inner Join Student_Groups S "
                + "on G.GroupID = S.GROUPSID "
                + "Inner Join Material M "
                + "on M.MaterialID = G.MaterialID "
                + "Inner Join Teacher_Material Y "
                + "on Y.MaterialID = M.MaterialID "
                + "Inner Join Student ST "
                + "on ST.StudentId = S.StudentID "
                + "Where Y.TeacherID = '" + username + "'" ;
                    
                     
        
        OracleDataSource orc = new OracleDataSource ();
        orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
        orc.setUser("testuser");
        orc.setPassword("123456");
        Connection conn = orc.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        TStudentTable.getItems().clear();
        while(rs.next())   
           { 
             T_Student ts = new T_Student (rs.getString(1) ,rs.getString(2) , rs.getString(3) , rs.getString(4) , rs.getString(5) , rs.getString(6) , rs.getString(7));
             
             TStudentTable.getItems().add(ts);
           }
        }
        
        
    }
    
}
