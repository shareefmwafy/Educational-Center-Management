import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import javafx.scene.control.DatePicker;
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
public class Manager_AddTeacherController implements Initializable {

    @FXML
    private BorderPane addTeacherBorderPane1;
    @FXML
    private TextField FirstNameTextField;
    @FXML
    private TextField MidNameTextField;
    @FXML
    private TextField LastNameTextField;
    @FXML
    private TextField TeacherIDTextField;
    @FXML
    private ToggleGroup GenderToggle;
    @FXML
    private TextField PasswordTextField;
    @FXML
    private JFXRadioButton MaleRadioButton;
    @FXML
    private JFXRadioButton FemaleRadioButton;
    @FXML
    private DatePicker DatapickerBirthdate;
    
    Parent root1;
    Stage stage1;
    Scene scene1;
    @FXML
    private Label label;

    ArrayList<Integer> TID = new ArrayList();
    int min = 0;
    int id = 0;
    String ID = "99T";
    int id1 = 0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        String Query = "SELECT SEQUENCE2.NEXTVAL FROM DUAL";
            OracleDataSource orc;
        try {
            orc = new OracleDataSource ();
            orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
            orc.setUser("testuser");
            orc.setPassword("123456");
            Connection conn = orc.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(Query);
            int x=0;
            while (rs.next()) {
            x = rs.getInt(1);
            }
            TeacherIDTextField.setText("T" + x);
            TeacherIDTextField.setDisable(true);
        } catch (SQLException ex) {
            Logger.getLogger(Manager_AddTeacherController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
            
//        id++;
//        String s =String.valueOf(id);  
//        TeacherIDTextField.setText(s+ "T");
//        TeacherIDTextField.setDisable(true);

    }

    @FXML
    private void openAddTeacher2(MouseEvent event) throws IOException {
        String Formate =null;
        String TID = TeacherIDTextField.getText();
        String FName = FirstNameTextField.getText();
        String MName = MidNameTextField.getText();
        String LName = LastNameTextField.getText();
        String Pass = PasswordTextField.getText();
        String gender;
        
        if (FemaleRadioButton.isSelected())
        {
            gender = "Female";
        }
        else {
            gender = "Male";
        }
        LocalDate BirthDate = DatapickerBirthdate.getValue();
         try{
             Formate = BirthDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")); // change format of date
             DatapickerBirthdate.setStyle("-fx-background-color:#0d0332");
            }catch(Exception ex)
            {
                DatapickerBirthdate.setStyle("-fx-background-color:red");
            }
        if (!FName.equals("") &&!MName.equals("")  && !LName.equals("") && !Pass.equals("") && !BirthDate.toString().equals("") && (FemaleRadioButton.isSelected() ||MaleRadioButton.isSelected())&&!Formate.equals(""))
        {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Manager_AddTeacher2.fxml"));
        root1 = loader.load();
        Manager_AddTeacher2Controller m1 = loader.getController();
        m1.FirstName = new String (FName);
        m1.MidName = new String (MName);
        m1.LastName = new String (LName);
//        m1.birthdate = new String (Formate);
        m1.Password = new String (Pass);
        m1.Gender = new String (gender);
        m1.TeacherID = new String (TID);
        new animatefx.animation.FadeInLeft(m1.addTeacherBorderPane2).play();
        
        stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene1 = new Scene(root1);
        stage1.setScene(scene1);
        stage1.getIcons().add(new Image ("D:\\Netbeans_WorkSpace\\JavaApplication1\\src\\icon.png"));
        stage1.show();
        }
        else {
            if (FName.equals("")) {
            FirstNameTextField.setStyle("-fx-background-color: transparent; -fx-border-color: red ; -fx-border-width:  0px 0px 2px 0px;");
            }
            else {
                 FirstNameTextField.setStyle("-fx-background-color: transparent; -fx-border-color: #0d0332 ; -fx-border-width:  0px 0px 2px 0px;");
            }
            if (MName.equals("")) {
            MidNameTextField.setStyle("-fx-background-color: transparent; -fx-border-color: red ; -fx-border-width:  0px 0px 2px 0px;");
            }
            else {
                 MidNameTextField.setStyle("-fx-background-color: transparent; -fx-border-color: #0d0332 ; -fx-border-width:  0px 0px 2px 0px;");
            }
            
            if (LName.equals(""))
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
        
        
        
        
    }
    
    @FXML
    private void closeAddTeacher1(MouseEvent event) {
        Stage stage = (Stage) addTeacherBorderPane1.getScene().getWindow();
        stage.close();
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
        addTeacherBorderPane1.setCenter(root);
    }
    
}