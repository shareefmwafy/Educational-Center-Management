import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import oracle.jdbc.pool.OracleDataSource;
public class Manager_AddTeacher2Controller implements Initializable {

    @FXML
    public BorderPane addTeacherBorderPane2;
     Parent root ;
     Stage stage ;
     Scene scene;
     
    public String TeacherID;
    public String FirstName;
    public String MidName;
    public String LastName;
    public String birthdate;
    public String Password;
    public String Gender;
    @FXML
    private TextField PhoneNumberTextField;
    @FXML
    private TextField EmailTextField;
    @FXML
    private TextField CityTextField;
    @FXML
    private TextField StreetTextField;
    @FXML
    private TextField MonthlySalaryTextField;
    @FXML
    private Label label;
    private Label l1;
    private Label l2;
    
    
    
  

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void closeAddTeacher2(MouseEvent event) {
        Stage stage = (Stage) addTeacherBorderPane2.getScene().getWindow();
        stage.close();
    }
    String Salary="0";
    @FXML
    private void openAddTeacher3(MouseEvent event) throws SQLException, IOException {
        
        int x = 0;
        String PhoneNumber = PhoneNumberTextField.getText();
        String City = CityTextField.getText();
        String Street = StreetTextField.getText();
        String Email = EmailTextField.getText();
        try {
          x = Integer.parseInt(MonthlySalaryTextField.getText());
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Please Enter a Number!" , "Warning" , JOptionPane.WARNING_MESSAGE);
            MonthlySalaryTextField.setText("");
        }

         if (!CityTextField.getText().equals("") && !StreetTextField.getText().equals("") &&!EmailTextField.getText().equals("") && !PhoneNumberTextField.getText().equals("") && !MonthlySalaryTextField.getText().equals("") )
        {
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());   
        OracleDataSource orc = new OracleDataSource ();
        orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
        orc.setUser("testuser");
        orc.setPassword("123456");
        Connection conn = orc.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement("insert into teacher values ( ? , ? , ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                stmt.setString(1,TeacherID );
                stmt.setString(2,FirstName );
                stmt.setString(3,MidName );
                stmt.setString(4,LastName );
                stmt.setString(5,Gender );
                stmt.setString(6,PhoneNumber );
                stmt.setString(7,Password );
                stmt.setString(8, Email);
                stmt.setString(9, City);
                stmt.setString(10, Street);
                stmt.setString(11, String.valueOf(x));
                stmt.setString(12, currentDate);
                stmt.setNull(13, java.sql.Types.VARCHAR);
                stmt.setNull(14, java.sql.Types.VARCHAR);
                stmt.executeUpdate();
            }catch (Exception ex) 
            {
                JOptionPane.showMessageDialog(null, ex.toString());
            }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Manager_AddTeacher3.fxml"));
        root = loader.load();
        Manager_AddTeacher3Controller m1 = loader.getController();
        m1.TeacherID = new String (TeacherID);
        m1.FirstName = new String (FirstName);
        m1.MidName = new String (MidName);
        m1.LastName = new String (LastName);
        m1.Password = new String (Password);
        m1.Gender = new String (Gender);
        m1.Phone = new String (PhoneNumber);
        m1.Email= new String (Email);
        m1.City = new String (City);
        m1.Street = new String (Street);
        m1.StartDate = new String (currentDate);
        m1.Salary = new String (String.valueOf(x));
        new animatefx.animation.FadeInLeft(m1.addTeacherBorderPane3).play();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image ("D:\\Netbeans_WorkSpace\\JavaApplication1\\src\\icon.png"));
        stage.show();
        }
         else 
        {
           
             if (CityTextField.getText().equals("")) {
            CityTextField.setStyle("-fx-background-color: transparent; -fx-border-color: red ; -fx-border-width:  0px 0px 2px 0px;");
            }
            else {
                 CityTextField.setStyle("-fx-background-color: transparent; -fx-border-color: #0d0332 ; -fx-border-width:  0px 0px 2px 0px;");
            }
             if (StreetTextField.getText().equals("")) {
            StreetTextField.setStyle("-fx-background-color: transparent; -fx-border-color: red ; -fx-border-width:  0px 0px 2px 0px;");
            }
            else {
                 StreetTextField.setStyle("-fx-background-color: transparent; -fx-border-color: #0d0332 ; -fx-border-width:  0px 0px 2px 0px;");
            }
             
              if (EmailTextField.getText().equals("")) {
            EmailTextField.setStyle("-fx-background-color: transparent; -fx-border-color: red ; -fx-border-width:  0px 0px 2px 0px;");
            }
            else {
                 EmailTextField.setStyle("-fx-background-color: transparent; -fx-border-color: #0d0332 ; -fx-border-width:  0px 0px 2px 0px;");
            }
             if (PhoneNumberTextField.getText().equals("")) {
            PhoneNumberTextField.setStyle("-fx-background-color: transparent; -fx-border-color: red ; -fx-border-width:  0px 0px 2px 0px;");
            }
            else {
                 PhoneNumberTextField.setStyle("-fx-background-color: transparent; -fx-border-color: #0d0332 ; -fx-border-width:  0px 0px 2px 0px;");
            }
             
             if (MonthlySalaryTextField.getText().equals("")) {
            MonthlySalaryTextField.setStyle("-fx-background-color: transparent; -fx-border-color: red ; -fx-border-width:  0px 0px 2px 0px;");
            }
            else {
                 MonthlySalaryTextField.setStyle("-fx-background-color: transparent; -fx-border-color: #0d0332 ; -fx-border-width:  0px 0px 2px 0px;");
            } 
             label.setText("Please enter all Data!!");
            
        }
        
        
        
        
        
        
        
        
        
        
    }
    
    
    private void loadpage (String page)
    {
        Parent root = null ; 
        try {
           root = FXMLLoader.load(getClass().getResource(page+".fxml"));
            
        }catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
        addTeacherBorderPane2.setCenter(root);
    }

    private void ByGroupRatioButtonS(ActionEvent event) {
        l1.setText("The salary will be determined based on the");
        l2.setText("number of groups for each teacher");
        Salary="0";
        MonthlySalaryTextField.setDisable(true);
    }

    private void MonthlyS(ActionEvent event) {
        l1.setText("");
        l2.setText("");
        MonthlySalaryTextField.setDisable(false);
        
        
    }
    
    
}


