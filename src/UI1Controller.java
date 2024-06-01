/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import com.jfoenix.controls.JFXButton;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import oracle.jdbc.pool.OracleDataSource;

/**
 * FXML Controller class
 *
 * @author Ayham Dw
 */
public class UI1Controller implements Initializable {

    @FXML
    private TextField UserNameTextField;
    @FXML
    private JFXButton button;
    @FXML
    private PasswordField PasswordTextField;
    @FXML
    private ImageView FacebookButton;
    @FXML
    private ImageView YoutupeButton;
    @FXML
    private ImageView InstageramButton;
    @FXML
    private ImageView TwitterButton;
    @FXML
    private Label label;
    @FXML
    private AnchorPane Anchor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//       new animatefx.animation.ZoomIn(Anchor).play();
       new animatefx.animation.BounceInDown(UserNameTextField).play();
       new animatefx.animation.BounceInDown(PasswordTextField).play();
       new animatefx.animation.BounceInLeft(button).play();
       
    }    

    @FXML
    private void Loging(ActionEvent event) throws Exception {
        int flag = 0;
      int Managerusername = 0;
        OracleDataSource orc = new OracleDataSource();
        orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
        orc.setUser("testuser");
        orc.setPassword("123456");
        Connection conn = orc.getConnection();
        Statement stm = conn.createStatement();
        
        String username = UserNameTextField.getText();
        String password = PasswordTextField.getText();
        if ((username.equals("") && password.equals("")) ) {
             UserNameTextField.setStyle("-fx-background-color: #D1E0E4 ; -fx-border-width:0 0 2 0 ; -fx-border-color : red");
             PasswordTextField.setStyle("-fx-background-color: #D1E0E4 ; -fx-border-width:0 0 2 0 ; -fx-border-color : red");
             new animatefx.animation.Shake(UserNameTextField).play();
             new animatefx.animation.Shake(PasswordTextField).play();
             label.setText("Empty UserName and Password!!");
        }
        else if (!username.equals("") && password.equals("")){
            UserNameTextField.setStyle("-fx-background-color: #D1E0E4 ; -fx-border-width:0 0 2 0 ; -fx-border-color : #0D0332");
             PasswordTextField.setStyle("-fx-background-color: #D1E0E4 ; -fx-border-width:0 0 2 0 ; -fx-border-color : red");
             new animatefx.animation.Shake(PasswordTextField).play();
             label.setText("Empty Password!!");
        }
        else if (username.equals("") && !password.equals("")){
            UserNameTextField.setStyle("-fx-background-color: #D1E0E4 ; -fx-border-width:0 0 2 0 ; -fx-border-color : red");
             PasswordTextField.setStyle("-fx-background-color: #D1E0E4 ; -fx-border-width:0 0 2 0 ; -fx-border-color : #0D0332");
             new animatefx.animation.Shake(UserNameTextField).play();
             label.setText("Empty UserName!!");
        }
        else {
             label.setText("");
            UserNameTextField.setStyle("-fx-background-color: #D1E0E4 ; -fx-border-width:0 0 2 0 ; -fx-border-color :  #0D0332");
            PasswordTextField.setStyle("-fx-background-color: #D1E0E4 ; -fx-border-width:0 0 2 0 ; -fx-border-color :  #0D0332");
            try {
            Managerusername = Integer.parseInt(UserNameTextField.getText());
            }catch (Exception ex) {}
            String Query1 = "SELECT MANAGERID , PASSWORD FROM MANAGER WHERE MANAGERID = " + Managerusername + "";
            String Query2 = "SELECT TEACHERID , PASSWORD FROM TEACHER WHERE TEACHERID = '" + UserNameTextField.getText() + "'";
            String Query3 = "SELECT STUDENTID , PASSWORD FROM STUDENT WHERE STUDENTID = '" + UserNameTextField.getText() + "'";
            
            ResultSet rs = stm.executeQuery(Query1);
            
            while (rs.next()) {
                if (UserNameTextField.getText().equals(rs.getString(1)) &&  PasswordTextField.getText().equals(rs.getString(2))) {
                    flag = 1; // for manager
                    Managerusername = rs.getInt(1);
                    password = rs.getString(2);
                    //JOptionPane.showMessageDialog(null, "Truee");
                }
            }
            
            rs = stm.executeQuery(Query2); 
            while (rs.next()) {
                if (UserNameTextField.getText().equals(rs.getString(1)) &&  PasswordTextField.getText().equals(rs.getString(2))) {
                    flag = 2; // for manager
                    username = rs.getString(1);
                    password = rs.getString(2);
                   
                }
            }
            
            rs = stm.executeQuery(Query3);
            while (rs.next()) {
                if (UserNameTextField.getText().equals(rs.getString(1)) &&  PasswordTextField.getText().equals(rs.getString(2))) {
                    flag = 3; // for manager
                    username = rs.getString(1);
                    password = rs.getString(2);
                }
            }
            if (flag == 1) {
             //JOptionPane.showMessageDialog(null, "Truee1");
             Stage stage = (Stage) Anchor.getScene().getWindow();
             stage.close();
             FXMLLoader loader = new FXMLLoader(getClass().getResource("Manager_HomaPage.fxml"));
             Parent root = loader.load();
             FXMLController ref = loader.getController();
             ref.managerid = Managerusername;
             ref.Date();
             Stage stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
             Scene scene1 = new Scene(root);
             stage1.setScene(scene1);
             stage1.setResizable(false);
             stage1.show();
             
            }
            else if (flag ==2) {
             Stage stage = (Stage) Anchor.getScene().getWindow();
             stage.close();
             FXMLLoader loader = new FXMLLoader(getClass().getResource("T_TeacherPage.fxml"));
             Parent root = loader.load();
             T_TeacherPageController ref = loader.getController();
             ref.username = username;
             ref.welcome();
             ref.initi();
             Stage stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
             Scene scene1 = new Scene(root);
             stage1.setScene(scene1);
             stage1.setResizable(false);
             stage1.show();
             
                
            }
            else if (flag == 3) {
             Stage stage = (Stage) Anchor.getScene().getWindow();
             stage.close();
             FXMLLoader loader = new FXMLLoader(getClass().getResource("Student_HomePage.fxml"));
             Parent root = loader.load();
             Student_HomePageController ref = loader.getController();
             ref.userid = username;
             ref.Initilize(username);
             ref.welcome();
             Stage stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
             Scene scene1 = new Scene(root);
             stage1.setScene(scene1);
             stage1.setResizable(false);
             stage1.show();
            }
            else {
//                
//                InputStream in = new FileInputStream (new File ("Test.jrxml"));
//                JasperDesign j = JRXmlLoader.load(in) ;
//                JasperReport jr = JasperCompileManager.compileReport(j); 
//                JasperPrint jp = JasperFillManager.fillReport(jr , null , conn) ;
//                JRDesignQuery query = new JRDesignQuery();
//                query.setText("""
//                              SELECT "STUDENT"."STUDENTID",
//                              \t"STUDENT"."FIRSTNAME"
//                              FROM "STUDENT"
//                              WHERE 
//                              \t "STUDENT"."STUDENTID" = '9S'""");
//                j.setQuery(query);
//                OutputStream out = new FileOutputStream (new File ("Output11141.pdf"));
//                JasperExportManager.exportReportToPdfStream(jp, out);
//                out.close();
//                in.close();
                 label.setText("Wrong Password Or User Name!!");
                 new animatefx.animation.Flash(UserNameTextField).play();
                 new animatefx.animation.Flash(PasswordTextField).play();
            }   
    }

    }

    @FXML
    private void ForgetPassword(ActionEvent event) {
        try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("ForgetPassword.fxml"));
            Parent root = (Parent) fxml.load();
            Stage stage = new Stage ();
            stage.setTitle("Reset Password");
            stage.setScene(new Scene (root));
            stage.getIcons().add(new Image ("D:\\Netbeans_WorkSpace\\JavaApplication1\\src\\icon.png"));
            stage.show();
            
        } catch (IOException ex) {
        JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    @FXML
    private void facebook(ActionEvent event) {
        try {
        Desktop.getDesktop().browse(new URI ("https://www.facebook.com/profile.php?id=100009684487237"));
    }catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex.toString());
    }
    }

    @FXML
    private void youtube(ActionEvent event) {
        try {
        Desktop.getDesktop().browse(new URI ("https://www.youtube.com/channel/UCPXwKxpuo1KNYrrYUV8AiHg"));
    }catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex.toString());
    }
    }

    @FXML
    private void instagram(ActionEvent event) {
        try {
        Desktop.getDesktop().browse(new URI ("https://www.instagram.com/ayham.dw/"));
    }catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex.toString());
    }
    }

    @FXML
    private void twitter(ActionEvent event) {
         try {
        Desktop.getDesktop().browse(new URI ("https://mobile.twitter.com/ShareefMwafy"));
    }catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex.toString());
    }
}
    
}