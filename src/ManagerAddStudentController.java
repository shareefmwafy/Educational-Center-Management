import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import oracle.jdbc.pool.OracleDataSource;
public class ManagerAddStudentController implements Initializable {

    @FXML
    private BorderPane borderPane1;
    @FXML
    public TextField FirstNameTextField;
    @FXML
    private TextField MidNameTextField;
    @FXML
    private TextField LastNameTextField;
    @FXML
    public TextField StudentIDTextField;
    @FXML
    private DatePicker DatapickerBirthdate;
    @FXML
    public JFXRadioButton MaleRadioButton;
    @FXML
    public ToggleGroup gender;
    @FXML
    public  JFXRadioButton FemaleRadioButton;
    @FXML
    private PasswordField PasswordTextField;
    Parent root1;
    Stage stage1;
    Scene scene1;
    @FXML
    private Label label;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int x = 0;
        try {
            String Query = "SELECT SEQUENCE1.NEXTVAL FROM DUAL";
            OracleDataSource orc;
            orc = new OracleDataSource ();
            orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
            orc.setUser("testuser");
            orc.setPassword("123456");
            Connection conn = orc.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(Query);
            while (rs.next()) {
                x = rs.getInt(1);
            }
            StudentIDTextField.setText("S" + x);  
            StudentIDTextField.setDisable(true);
            
            
            
        }catch (Exception ex) {
            
        }
    }

    @FXML
    private void closePane1(ActionEvent event) {
        Stage stage = (Stage) borderPane1.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void openPane2(ActionEvent event) throws IOException {
        
        String Formate = null ;
        //loadpage("Manager-Add-student-2");
        String userid= StudentIDTextField.getText();
        String FirstName = FirstNameTextField.getText();
        String SecondName = MidNameTextField.getText();
        String LastName = LastNameTextField.getText();
        String Password = PasswordTextField.getText();
        String gender; 
        if (FemaleRadioButton.isSelected())
            gender = "Female";
           else 
            gender = "Male";
   
        
        LocalDate BirthDate = DatapickerBirthdate.getValue();
         try{
             Formate = BirthDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")); // change format of date
             DatapickerBirthdate.setStyle("-fx-background-color:#0d0332");
            }catch(Exception ex)
            {
                DatapickerBirthdate.setStyle("-fx-background-color:red");
            }
           

        
         
        if (!FirstName.equals("") &&!SecondName.equals("")  && !LastName.equals("") && !Password.equals("") && !BirthDate.toString().equals("") && (FemaleRadioButton.isSelected() ||MaleRadioButton.isSelected())&&!Formate.equals(""))
        {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Manager-Add-student-2.fxml"));
        root1 = loader.load();
        ManagerAddStudentConroller2 m1 = loader.getController();
        m1.FirstName = new String (FirstName);
        m1.SecondName = new String (SecondName);
        m1.LastName = new String (LastName);
        m1.birthdate = new String (Formate);
        m1.Password = new String (Password);
        m1.Gender = new String (gender);
        m1.StudentID = new String (userid);
        new animatefx.animation.FadeInLeft(m1.borderPane2).play();
        stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene1 = new Scene(root1);
        stage1.setScene(scene1);
        stage1.getIcons().add(new Image ("D:\\Netbeans_WorkSpace\\JavaApplication1\\src\\icon.png"));
        stage1.show();
        }
        else {
            if (FirstName.equals("")) {
            FirstNameTextField.setStyle("-fx-background-color: transparent; -fx-border-color: red ; -fx-border-width:  0px 0px 2px 0px;");
            }
            else {
                 FirstNameTextField.setStyle("-fx-background-color: transparent; -fx-border-color: #0d0332 ; -fx-border-width:  0px 0px 2px 0px;");
            }
            if (SecondName.equals("")) {
            MidNameTextField.setStyle("-fx-background-color: transparent; -fx-border-color: red ; -fx-border-width:  0px 0px 2px 0px;");
            }
            else {
                 MidNameTextField.setStyle("-fx-background-color: transparent; -fx-border-color: #0d0332 ; -fx-border-width:  0px 0px 2px 0px;");
            }
            
            if (LastName.equals(""))
            {
                 LastNameTextField.setStyle("-fx-background-color: transparent; -fx-border-color: red ; -fx-border-width:  0px 0px 2px 0px;");
            }
            else {
                LastNameTextField.setStyle("-fx-background-color: transparent; -fx-border-color: #0d0332 ; -fx-border-width:  0px 0px 2px 0px;");
            }
            if (PasswordTextField.getText().equals(""))
            {
                 PasswordTextField.setStyle("-fx-background-color: transparent; -fx-border-color: red ; -fx-border-width:  0px 0px 2px 0px;");
            }
            else {
                PasswordTextField.setStyle("-fx-background-color: transparent; -fx-border-color: #0d0332 ; -fx-border-width:  0px 0px 2px 0px;");
            }
            if (!FemaleRadioButton.isSelected() &&!MaleRadioButton.isSelected() )
            {
                label.setText("Please enter all Data!!");
            }
            else {
                label.setText("");
            }
            
           
        }
        
        
        
        
        
        
        // go to next page
        
         
    }
    
    /*
     private void loadpage (String page)
    {
        Parent root = null ; 
        try {
           root = FXMLLoader.load(getClass().getResource(page+".fxml"));
            
        }catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
        borderPane1.setCenter(root);
    }
     */
     
     
    
    
     
     
     
     
     
     
    
}