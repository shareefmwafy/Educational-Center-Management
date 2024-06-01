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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import oracle.jdbc.pool.OracleDataSource;

/**
 * FXML Controller class
 *
 * @author Ayham Dw
 */
public class Manager_PaymentDusStudentController implements Initializable {
    int payment= 0 ; 
    int total = 0;
    int rest = 0;
    String ID = ""; 
    @FXML
    private TableView<CostMaterial> TableofMaterial;
    @FXML
    private TableColumn<CostMaterial, String> MaterialNumberCol;
    @FXML
    private TableColumn<CostMaterial, String> MaterialNameCol;
    @FXML
    private TableColumn<CostMaterial, Integer> CostCol;
    @FXML
    private Label CountOfMaterialLabel;
    @FXML 
    private TextField IDTextField;
    @FXML
    private TextField AmountTextField;
    @FXML
    private TextField StudentPaymentID;
    @FXML
    private TextField RestID;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        MaterialNumberCol.setCellValueFactory(new PropertyValueFactory<> ("MaterialID"));
        MaterialNameCol.setCellValueFactory(new PropertyValueFactory<> ("MaterialName") );
        CostCol.setCellValueFactory(new PropertyValueFactory<>("Cost"));
    }    

    @FXML
    private void SearchMaterialButton(ActionEvent event) {
             ID = IDTextField.getText();
             String Query = "Select count(STUDENTID) from STUDENT_GROUPS"
             + " where STUDENTID = '" + ID + "'";
            int x = 0;
         try {
            OracleDataSource orc = new OracleDataSource ();
            orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
            orc.setUser("testuser");
            orc.setPassword("123456");
            Connection conn = orc.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(Query); 
            
            while (rs.next())
            {
                 x = rs.getInt(1);
            }
         }catch (Exception Ex)
         {
             JOptionPane.showMessageDialog(null, Ex.toString());
         }
         //int all = x  * 40;
         CountOfMaterialLabel.setText(" " +x);
         
         String query = "Select M.MaterialID , MaterialName "
                 + "from Groups G "
                 + "Inner Join STUDENT_GROUPS S "
                 + "on S.GROUPSID = G.GROUPID "
                 + "Inner Join Material M "
                 + "on M.MaterialID = G.MaterialID "
                 + "where StudentID = '" +ID + "'" ;
                 
         try {
             
            OracleDataSource orc = new OracleDataSource ();
            orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
            orc.setUser("testuser");
            orc.setPassword("123456");
            Connection conn = orc.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query); 
            TableofMaterial.getItems().clear();
            while (rs.next()) {
                CostMaterial ref = new CostMaterial (rs.getString(1) , rs.getString(2) , 40);
                TableofMaterial.getItems().add(ref);
            }
             
         }catch (Exception ex) 
         {
            System.err.print(ex);
         }
         int size = TableofMaterial.getItems().size();
         total = size * 40; 
        AmountTextField.setText("-" + total);
        StudentPaymentID.setText(""+0);
        
    }

    @FXML
    private void Pay(ActionEvent event) {    
        payment = Integer.valueOf(StudentPaymentID.getText());
         rest = payment - total ; 
        RestID.setText( ""+rest);
        
    }

    @FXML
    private void SaveMaterialButton(ActionEvent event)  {
        String update = "Update Student set NETACCOUNT = '" +rest + "' "
                + "where StudentID = '" + ID + "'"; 
            OracleDataSource orc;
        try {
            orc = new OracleDataSource ();
            orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
            orc.setUser("testuser");
            orc.setPassword("123456");
            Connection conn = orc.getConnection();
            Statement st = conn.createStatement();
            st.executeUpdate(update);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
            
        JOptionPane.showMessageDialog(null, "Net Acoount Saved successfully", "Notification", JOptionPane.INFORMATION_MESSAGE);
    }
    
}
