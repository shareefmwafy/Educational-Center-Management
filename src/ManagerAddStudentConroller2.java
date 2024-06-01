import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import oracle.jdbc.pool.OracleDataSource;
public class ManagerAddStudentConroller2 implements Initializable {

    @FXML
    public BorderPane borderPane2;
    @FXML
    private Pane pane;
    @FXML
    private JFXButton button;
    @FXML
    private Label label;
    
    // all Values of AddStudent(1)
    public String StudentID;
    public String FirstName; 
    public String SecondName; 
    public String LastName; 
    public String Gender; 
    public String birthdate; 
    public String Password;
    
    Parent root ; 
    Stage stage ; 
    Scene scene;
    @FXML
    private TextField CityTextField;
    @FXML
    private JFXRadioButton RadioButton12;
    @FXML
    private JFXRadioButton RadioButton11;
    @FXML
    private JFXRadioButton RadioButton10;
    @FXML
    private TextField StreetTextField;
    @FXML
    private TextField EmailTextField;
    @FXML
    private TextField PhoneTextField;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
        
    }
    
    

    @FXML
    private void CanclePage2(ActionEvent event) {
        Stage stage = (Stage) borderPane2.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void OpenPage3(ActionEvent event) throws IOException, SQLException {
//        loadpage("Manager-Add-Student-3");
//        JOptionPane.showMessageDialog(null, birthdate);
        
        String City =CityTextField.getText() ;
        String Street=StreetTextField.getText();
        String Email =EmailTextField.getText() ;
        String Phone = PhoneTextField.getText();
        String Stage;
        if (RadioButton10.isSelected())
            Stage = "10";
        else if (RadioButton11.isSelected())
            Stage = "11";
        else 
            Stage = "12";

        if (!CityTextField.getText().equals("") && !StreetTextField.getText().equals("") &&!EmailTextField.getText().equals("") && !PhoneTextField.getText().equals("") && (RadioButton10.isSelected() ||RadioButton11.isSelected() || RadioButton12.isSelected() ) )
        {
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());   
        OracleDataSource orc = new OracleDataSource ();
        orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
        orc.setUser("testuser");
        orc.setPassword("123456");
        Connection conn = orc.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement("insert into student values ( ? , ? , ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                stmt.setString(1,StudentID );
                stmt.setString(2,FirstName );
                stmt.setString(3,SecondName );
                stmt.setString(4,LastName );
                stmt.setString(5,Gender );
                stmt.setString(6,Stage );
                stmt.setNull(7,java.sql.Types.VARCHAR );
                stmt.setString(8, Email);
                stmt.setString(9, Password);
                stmt.setString(10, Phone);
                stmt.setString(11, currentDate );
                stmt.setInt(12, 0);
                stmt.setString(13, birthdate);
                stmt.setString(14, Street);
                stmt.setString(15, City);
                stmt.executeUpdate();
            }catch (Exception ex) 
            {
                JOptionPane.showMessageDialog(null, ex.toString());
            }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Manager-Add-Student-3.fxml"));
        root = loader.load();
        ManagerAddStudentControllar3 m1 = loader.getController();
        m1.StudentID = new String (StudentID);
        m1.FirstName = new String (FirstName);
        m1.MidName = new String (SecondName);
        m1.LastName = new String (LastName);
        m1.Gender = new String (Gender);
        m1.Birthdate = new String (birthdate);
        m1.Passeord = new String (Password);
        m1.City = new String (City);
        m1.Street = new String (Street);
        m1.Email= new String (Email);
        m1.Phone = new String (Phone);
        m1.Stage = new String (Stage);
        new animatefx.animation.FadeInLeft(m1.Pane3).play();
        
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
             if (PhoneTextField.getText().equals("")) {
            PhoneTextField.setStyle("-fx-background-color: transparent; -fx-border-color: red ; -fx-border-width:  0px 0px 2px 0px;");
            }
            else {
                 PhoneTextField.setStyle("-fx-background-color: transparent; -fx-border-color: #0d0332 ; -fx-border-width:  0px 0px 2px 0px;");
            }
             
              if (!RadioButton10.isSelected() &&!RadioButton11.isSelected() && !RadioButton12.isSelected() )
            {
                label.setText("Select Stage!!");
            }
            else {
                label.setText("");
            }
        }
            


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
        borderPane2.setCenter(root);
    }
    */
}
