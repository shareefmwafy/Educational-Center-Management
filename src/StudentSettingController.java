import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import oracle.jdbc.pool.OracleDataSource;
import java.sql.*;
import javafx.event.ActionEvent;

public class StudentSettingController implements Initializable {
   String Password ;
   String username;
    @FXML
    private TextField FirstNameTextField;
    @FXML
    private TextField MidNameTextField;
    @FXML
    private TextField LastNameTextField;
    @FXML
    private TextField CityTExtField;
    @FXML
    private TextField StreetTextField;
    @FXML
    private TextField EmailTextField;
    @FXML
    private TextField PhoneNumberTextField;
    @FXML
    private PasswordField CurrentPasswordTextField;
    @FXML
    private PasswordField NewPasswordTextField;
    @FXML
    private PasswordField ConfirmPasswordTextField;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
//        String Query = "Select FirstName , MIDNAME,LASTNAME,CITY,STREET, EMAIL, PHONENUMBER "
//                + "FROM STUDENT WHERE STUDENTID = '" + username+ "'";    
//        OracleDataSource orc = new OracleDataSource ();
//        orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
//        orc.setUser("testuser");
//        orc.setPassword("123456");
//        Connection conn = orc.getConnection();
//        Statement stm = conn.createStatement();
//        ResultSet rs = stm.executeQuery(Query);
//        while (rs.next()) {
//            FirstNameTextField.setText(rs.getString(1));
//            MidNameTextField.setText(rs.getString(2));
//            LastNameTextField.setText(rs.getString(3));
//            CityTExtField.setText(rs.getString(4));
//            StreetTextField.setText(rs.getString(5));
//            EmailTextField.setText(rs.getString(6));
//            PhoneNumberTextField.setText(rs.getString(7));
//           
//            
//        }
//        }catch (Exception Ex) {
//            JOptionPane.showMessageDialog(null, Ex.toString());
//        }
    }    

    @FXML
    private void SaveSetting(ActionEvent event) throws SQLException {
       // JOptionPane.showMessageDialog(null, username);
        String Query = "Select Password FROM STUDENT WHERE STUDENTID = '" + username + "'";
        try {
        OracleDataSource orc = new OracleDataSource ();
        orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
        orc.setUser("testuser");
        orc.setPassword("123456");
        Connection conn = orc.getConnection();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(Query);
        
              while (rs.next()){
                  Password = rs.getString(1);
              }
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
                
        if (CurrentPasswordTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(null , "Please Enter Current Password " , "Warnning" , JOptionPane.WARNING_MESSAGE);         
        }
        else {
            if (!CurrentPasswordTextField.getText().equals(Password)) {
                 JOptionPane.showMessageDialog(null , "Error in Password" , "Error" , JOptionPane.ERROR_MESSAGE);
            }
            else {
                if (NewPasswordTextField.getText().equals("") && ConfirmPasswordTextField.getText().equals("")) {
                     //JOptionPane.showMessageDialog(null , "Check new Password" , "Error" , JOptionPane.ERROR_MESSAGE);
                     String Update = "UPDATE Student "
                    + "SET FIRSTNAME = '" + FirstNameTextField.getText() + "' , "
                    + "MIDNAME = '" +MidNameTextField.getText() + "' , "
                    + "LASTNAME = '"+LastNameTextField.getText() + "' , "
                    + "EMAIL = '" + EmailTextField.getText() + "' , "
                    + "PHONENUMBER = '" + PhoneNumberTextField.getText() + "' , "
                    + "CITY = '" +  CityTExtField.getText() + "' , "
                    + "STREET = '" + StreetTextField.getText() + "' , "
                    + "PASSWORD = '" + Password + "' "
                    + "WHERE STUDENTID ='" + username + "'"; 
                      OracleDataSource orc = new OracleDataSource ();
                      orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
                      orc.setUser("testuser");
                      orc.setPassword("123456");
                      Connection conn = orc.getConnection();
                      Statement stm = conn.createStatement();
                      ResultSet rs = stm.executeQuery(Query);
                      stm.executeUpdate(Update);
                      Password = "0";
                      CurrentPasswordTextField.setText("");
                      NewPasswordTextField.setText("");
                      ConfirmPasswordTextField.setText("");
                      JOptionPane.showMessageDialog(null, "Done" , "Information" , JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    if (NewPasswordTextField.getText().equals(ConfirmPasswordTextField.getText())) {
                    String Update = "UPDATE Student "
                    + "SET FIRSTNAME = '" + FirstNameTextField.getText() + "' , "
                    + "MIDNAME = '" +MidNameTextField.getText() + "' , "
                    + "LASTNAME = '"+LastNameTextField.getText() + "' , "
                    + "EMAIL = '" + EmailTextField.getText() + "' , "
                    + "PHONENUMBER = '" + PhoneNumberTextField.getText() + "' , "
                    + "CITY = '" +  CityTExtField.getText() + "' , "
                    + "STREET = '" + StreetTextField.getText() + "' , "
                    + "PASSWORD = '" + NewPasswordTextField.getText() + "' "
                    + "WHERE STUDENTID ='" + username + "'";
                    OracleDataSource orc = new OracleDataSource ();
                    orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
                    orc.setUser("testuser");
                    orc.setPassword("123456");
                    Connection conn = orc.getConnection();
                    Statement stm = conn.createStatement();
                    ResultSet rs = stm.executeQuery(Query);
                    stm.executeUpdate(Update);
                    Password = "0";
                    CurrentPasswordTextField.setText("");
                    NewPasswordTextField.setText("");
                    ConfirmPasswordTextField.setText("");
                    JOptionPane.showMessageDialog(null, "Done" , "Information" , JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null , "Check new Password" , "Error" , JOptionPane.ERROR_MESSAGE);
                    }
                    
                }
            }
        }
        
    }
    
    public void Settings (String username1) {
        try {
        String Query = "Select FirstName , MIDNAME,LASTNAME,CITY,STREET, EMAIL, PHONENUMBER "
                + "FROM STUDENT WHERE STUDENTID = '" + username1+ "'";    
        OracleDataSource orc = new OracleDataSource ();
        orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
        orc.setUser("testuser");
        orc.setPassword("123456");
        Connection conn = orc.getConnection();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(Query);
        while (rs.next()) {
            FirstNameTextField.setText(rs.getString(1));
            MidNameTextField.setText(rs.getString(2));
            LastNameTextField.setText(rs.getString(3));
            CityTExtField.setText(rs.getString(4));
            StreetTextField.setText(rs.getString(5));
            EmailTextField.setText(rs.getString(6));
            PhoneNumberTextField.setText(rs.getString(7));
           
            
        }
        }catch (Exception Ex) {
            JOptionPane.showMessageDialog(null, Ex.toString());
        }
    }
    
   
    
}
