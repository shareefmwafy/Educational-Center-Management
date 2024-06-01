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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import oracle.jdbc.pool.OracleDataSource;


public class Student_financialInformationController implements Initializable {
    String StudentID;
    @FXML
    private TableColumn<StudentFinincial, String> MaterialNameCol;
    @FXML
    private TableColumn<StudentFinincial, String> CostMaterialCol;
    @FXML
    private Label CountOfMaterialLabel;
    @FXML
    private Label AmountLabel;
    @FXML
    private Label DeservedAmountLabel;
    @FXML
    private Label NetAccountLabel;
    @FXML
    private TableView<StudentFinincial> Table;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MaterialNameCol.setCellValueFactory(new PropertyValueFactory<> ("MaterialName"));
        CostMaterialCol.setCellValueFactory(new PropertyValueFactory<> ("Cost"));
    }    

    @FXML
    private void SearchMaterial(ActionEvent event) throws SQLException {
        int x = 0;
            String Query = "SELECT MATERIALNAME FROM GROUPS G "
                    + "INNER JOIN MATERIAL M "
                    + "ON M.MATERIALID = G.MATERIALID "
                    + "INNER JOIN STUDENT_GROUPS S "
                    + "ON S.GROUPSID = G.GROUPID "
                    + "WHERE "
                + "STUDENTID =  '"+ StudentID+"'";
            OracleDataSource orc = new OracleDataSource();
            orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
            orc.setUser("testuser");
            orc.setPassword("123456");
            Connection conn = orc.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(Query);
            Table.getItems().clear();
            while (rs.next()){
                StudentFinincial s = new StudentFinincial (rs.getString(1) , "40");
                Table.getItems().add(s);
            }
            
           String Query2 = "Select count(StudentID) From STUDENT_GROUPS "
                   + "where StudentID = '" + StudentID+ "'";
           rs = st.executeQuery(Query2);
           while (rs.next()) {
               x = rs.getInt(1);
               CountOfMaterialLabel.setText(rs.getString(1));
           }
           
           AmountLabel.setText("40$");
           x = x * 40;
           DeservedAmountLabel.setText(String.valueOf(x));
           String Query3 = "SELECT NETACCOUNT FROM STUDENT WHERE STUDENTID = '" + StudentID+ "'";
           rs = st.executeQuery(Query3);
           while (rs.next()) {
               NetAccountLabel.setText(rs.getString(1) + "$");
           }
            
            
        
    }
    
}
