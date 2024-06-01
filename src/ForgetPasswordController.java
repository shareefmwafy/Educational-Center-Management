/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;
import oracle.jdbc.pool.OracleDataSource;

/**
 * FXML Controller class
 *
 * @author Ayham Dw
 */
public class ForgetPasswordController implements Initializable {

    @FXML
    private TextField UserNameTextField;
    @FXML
    private TextField EmailTextField;
    @FXML
    private TextField CodeTextField;
    @FXML
    private Button SendCodeButton;
    @FXML
    private Button SaveButton;
    @FXML
    private Label codesent; 
    @FXML
    private Pane pane;
    @FXML
    private PasswordField NewPassTextField;
    @FXML
    private PasswordField ConfirmTextField;
    int num=0;
    int flag = 0;
    Session newSession;
    MimeMessage mimeMessage;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       CodeTextField.setDisable(true);
       NewPassTextField.setDisable(true);
       ConfirmTextField.setDisable(true);
      // SendCodeButton.setDisable(true);
       SaveButton.setDisable(true);
       
    }    
    
    @FXML
    private void SavePassword(ActionEvent event) throws SQLException {
        
        if (CodeTextField.getText().equals(String.valueOf(num))){
            if ((NewPassTextField.getText().equals(ConfirmTextField.getText())) &&(!NewPassTextField.getText().equals("") &&!ConfirmTextField.getText().equals("") ) ){
                if (flag == 1) {
                    String Query="UPDATE STUDENT SET PASSWORD = '" + NewPassTextField.getText() + "' "
                            + "WHERE STUDENTID = '" + UserNameTextField.getText() + "'";
                    OracleDataSource orc = new OracleDataSource();     
                    orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
                    orc.setUser("testuser");
                    orc.setPassword("123456");
                    Connection conn = orc.getConnection();
                    Statement stm = conn.createStatement();
                    stm.executeUpdate(Query);
                    JOptionPane.showMessageDialog(null, "Done!" , "Information" , JOptionPane.INFORMATION_MESSAGE);
                    Stage stage = (Stage) pane.getScene().getWindow();
                    stage.close();

                }
                else if (flag == 2){
                    String Query="UPDATE Teacher SET PASSWORD = '" + NewPassTextField.getText() + "' "
                            + "WHERE TEACHERID = '" + UserNameTextField.getText() + "'";
                    OracleDataSource orc = new OracleDataSource();     
                    orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
                    orc.setUser("testuser");
                    orc.setPassword("123456");
                    Connection conn = orc.getConnection();
                    Statement stm = conn.createStatement();
                    stm.executeUpdate(Query);
                    JOptionPane.showMessageDialog(null, "Done!" , "Information" , JOptionPane.INFORMATION_MESSAGE);
                    Stage stage = (Stage) pane.getScene().getWindow();
                    stage.close();
                }
            }
            
            else if (NewPassTextField.getText().equals("") && ConfirmTextField.getText().equals("") )
            {
                 new animatefx.animation.Shake(NewPassTextField).play();
                 new animatefx.animation.Shake(ConfirmTextField).play();
            }
           
            else if (NewPassTextField.getText().equals("")){
                new animatefx.animation.Shake(NewPassTextField).play();
            }
            else if (ConfirmTextField.getText().equals("")){
                 new animatefx.animation.Shake(ConfirmTextField).play();
            }
            else {
                 new animatefx.animation.Shake(NewPassTextField).play();
                 new animatefx.animation.Shake(ConfirmTextField).play();
            }
        }
        else {
            new animatefx.animation.Shake(CodeTextField).play();
        }
        
    }

    @FXML
    private void SendCode(ActionEvent event) throws SQLException, MessagingException {
        
       String Query = "SELECT EMAIL FROM STUDENT WHERE STUDENTID = '" + UserNameTextField.getText() + "'" ;
       
        Random random = new Random();
        num = random.nextInt(999999);
        OracleDataSource orc = new OracleDataSource();     
        orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
        orc.setUser("testuser");
        orc.setPassword("123456");
        Connection conn = orc.getConnection();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(Query);
        while (rs.next()){
            if (rs.getString(1).equals(EmailTextField.getText()))
                flag = 1;
        }
        if (flag != 1) {
            String Query1 = "SELECT EMAIL FROM TEACHER WHERE TEACHERID = '" + UserNameTextField.getText() + "'" ;
            rs = stm.executeQuery(Query1);
            while (rs.next()){
            if (rs.getString(1).equals(EmailTextField.getText()))
                flag = 2;
        }
        }
         
       if (flag!=2 && flag!=1) {
           //JOptionPane.showMessageDialog(null, "Error in Password or Email" , "Error" , JOptionPane.ERROR_MESSAGE);
           new animatefx.animation.Shake(EmailTextField).play();
           new animatefx.animation.Shake(UserNameTextField).play();
           codesent.setText("Error in username or password");
       }
       
       else {
           ForgetPasswordController ref = new ForgetPasswordController();
           ref.setup();
           ref.draft(EmailTextField.getText() , String.format("%06d", num));
           ref.sendemail();
           CodeTextField.setDisable(false);
           NewPassTextField.setDisable(false);
           ConfirmTextField.setDisable(false);
           SaveButton.setDisable(false);
           codesent.setText("The code sent successfully");
           
       }
       
       
        
    }
     private void setup() {   
        
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
                Properties properties = System.getProperties();
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
                newSession = Session.getDefaultInstance(properties,null);
        
    }
     
     private void sendemail() throws NoSuchProviderException, MessagingException {
       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                String fromUser = "educational.center2022@gmail.com";  //Enter sender email id
		String fromUserPassword = "nwvclgiebmysaepi";  //Enter sender gmail password , this will be authenticated by gmail smtp server
		String emailHost = "smtp.gmail.com";
		Transport transport = newSession.getTransport("smtp");
		transport.connect(emailHost, fromUser, fromUserPassword);
		transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
		transport.close();
		//System.out.println("Email successfully sent!!!");
       
    }
     
      private MimeMessage draft( String email , String code) throws AddressException, MessagingException {
       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                String[] emailReceipients = {email};  //Enter list of email recepients
		String emailSubject = "Reset Password";
		String emailBody = "Your Reset Code is: " + code;
                 mimeMessage = new MimeMessage(newSession);
		
		for (int i =0 ;i<emailReceipients.length;i++)
		{
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailReceipients[i]));
		}
		mimeMessage.setSubject(emailSubject);
                
                
                 MimeBodyPart bodyPart = new MimeBodyPart();
		 bodyPart.setContent(emailBody,"text/html;charset=utf-8");
		 MimeMultipart multiPart = new MimeMultipart();
		 multiPart.addBodyPart(bodyPart);
		 mimeMessage.setContent(multiPart);
		 return mimeMessage;
       
    }
      
}
