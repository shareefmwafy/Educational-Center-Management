import com.jfoenix.controls.JFXButton;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import oracle.jdbc.pool.OracleDataSource;
import java.sql.*;
import javafx.scene.Node;
import javafx.scene.text.Text;
public class Student_HomePageController implements Initializable {
    String userid;
    @FXML
    private BorderPane studentHomePane;
    @FXML
    private Pane ChomePage;
    @FXML
    private JFXButton Logout;
    @FXML
    private TableView<StudentHomePage> TableStudent;
    @FXML
    private TableColumn<StudentHomePage, String> MatereialNumberCol;
    @FXML
    private TableColumn<StudentHomePage, String> MaterialNameCol;
    @FXML
    private TableColumn<StudentHomePage, String> GroupNumberCol;
    @FXML
    private TableColumn<StudentHomePage, String> GroupNameCol;
    @FXML
    private TableColumn<StudentHomePage, String> PeriodCol;
    @FXML
    private TableColumn<StudentHomePage, String> TimeCol;
    @FXML
    private TableColumn<StudentHomePage, String> ClassCol;
    @FXML
    private Label TodayLabel;
    @FXML
    private Label TimeLabel;
    @FXML
    private Label DateLabel;
    @FXML
    private Label LastPayLabel;
    @FXML
    private Label NetAccountLabel;
    @FXML
    private Label NextPayLabel;
    @FXML
    private Text WelcomeLabel;
    

   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // for get month of Start date
        MatereialNumberCol.setCellValueFactory(new PropertyValueFactory<>("MaterialNumber"));
        MaterialNameCol.setCellValueFactory(new PropertyValueFactory<>("MaterialName"));
        GroupNumberCol.setCellValueFactory(new PropertyValueFactory<>("GroupNumber"));
        GroupNameCol.setCellValueFactory(new PropertyValueFactory<>("GroupName"));
        PeriodCol.setCellValueFactory(new PropertyValueFactory<>("Period"));
        ClassCol.setCellValueFactory(new PropertyValueFactory<>("Classes"));
        TimeCol.setCellValueFactory(new PropertyValueFactory<>("Time"));

        
    }

    @FXML
    private void openHomePage(MouseEvent event) {
        studentHomePane.setCenter(ChomePage);
        
    }

    @FXML
    private void settingPage(MouseEvent event) throws Exception {
             loadpage("StudentSetting");
             FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentSetting.fxml"));
             Parent root = loader.load();
             StudentSettingController ref = loader.getController();
             ref.username = userid;
             ref.Settings(userid);
             //ref.Fill(managerid);
             Stage stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
             Scene scene1 = new Scene(root);
             studentHomePane.setCenter(root);
        
    }

    @FXML
    private void openFinancialPage(MouseEvent event) throws Exception {
             loadpage("Student_financialinformation");
             FXMLLoader loader = new FXMLLoader(getClass().getResource("Student_financialinformation.fxml"));
             Parent root = loader.load();
             Student_financialInformationController ref = loader.getController();
             ref.StudentID = userid;
             //ref.Fill(managerid);
             Stage stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
             Scene scene1 = new Scene(root);
             studentHomePane.setCenter(root);  
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
        studentHomePane.setCenter(root);
    }

    @FXML
    private void Logout(ActionEvent event) throws IOException {
         Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to logout?");
        
        if(alert.showAndWait().get() == ButtonType.OK )
        {
            Stage stage = (Stage) studentHomePane.getScene().getWindow();
            stage.close();
            
             Parent root = FXMLLoader.load(getClass().getResource("UI1.fxml"));
             stage.setTitle("Educational Center");
             stage.setScene(new Scene (root, 700, 500));
             stage.show();
    
    
            
            
        }
    }

    @FXML
    private void ShowTables(ActionEvent event) throws SQLException {
        String Query = "SELECT G.MATERIALID , MATERIALNAME, G.GROUPID,GROUPNAME,PERIOD,STARTTIME,G.CLASSID " +
        "FROM MATERIAL M INNER JOIN GROUPS G " +
        "ON M.MATERIALID = G.MATERIALID " +
        "INNER JOIN CLASS C " +
        "ON C.CLASSID = G.CLASSID " +
        "INNER JOIN STUDENT_GROUPS X " +
        "ON X.GROUPSID = G.GROUPID " +
        "WHERE STUDENTID = '" + userid + "'";
        OracleDataSource orc = new OracleDataSource();
        orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
        orc.setUser("testuser");
        orc.setPassword("123456");
        Connection con = orc.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(Query);
        TableStudent.getItems().clear();
        while (rs.next()) {
            StudentHomePage s = new StudentHomePage (rs.getString(1) , rs.getString(2) , rs.getString(3) , rs.getString(4) , rs.getString(5) , rs.getString(7) ,rs.getString(6));
            TableStudent.getItems().add(s);
        }
        //JOptionPane.showMessageDialog(null, "tst");
        
               
    }
    public void Initilize (String id) {
         String test = "";
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        DateLabel.setText(formatter.format(date));
        formatter = new SimpleDateFormat("HH : mm");
        TimeLabel.setText(formatter.format(date));
        String day = LocalDate.now().getDayOfWeek().name();
        TodayLabel.setText(day);
        String Query = "Select STARTDATE FROM STUDENT WHERE STUDENTID = '" +id+"'";
        String Query2 = "Select NETACCOUNT FROM STUDENT WHERE STUDENTID = '" + id +"'";
       try {
        OracleDataSource orc = new OracleDataSource();
        orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
        orc.setUser("testuser");
        orc.setPassword("123456");
        Connection con = orc.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(Query);
        while (rs.next()) {
            test = rs.getString(1);
            LastPayLabel.setText(rs.getString(1));
        }
        
        rs = stm.executeQuery(Query2);
         while (rs.next()) {
            NetAccountLabel.setText(rs.getString(1));
        }
        String [] month =  test.split("-");
        String day1 = month [0];
        String year = month [2];
        int AddMonth = Integer.parseInt(month[1]);
        int AddYear = Integer.parseInt(month[2]);
        if (AddMonth == 12 && AddYear == 2022 ){
            AddMonth = 1;
            AddYear+=1;
        }else {
            AddMonth+=1;
        }
        NextPayLabel.setText(day1 + "-" + String.valueOf(AddMonth) + "-" + String.valueOf(AddYear));
         
        
       }catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex.toString());
       }
       
        
        
    }
    
    public void welcome () throws Exception {
        String Query = "Select FirstName from Student where Studentid = '" + userid +  "'" ;
        OracleDataSource orc = new OracleDataSource();
        orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
        orc.setUser("testuser");
        orc.setPassword("123456");
        Connection con = orc.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery(Query);
        while (rs.next()) {
         Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH");
        int x = Integer.parseInt(formatter.format(date));
        if (x >=0 && x<= 12) 
            WelcomeLabel.setText("Good Morning, " + rs.getString(1));
        else if (x >=13 && x<= 18)
             WelcomeLabel.setText("Good Afternoon, " + rs.getString(1));
        else 
            WelcomeLabel.setText("Good Evening, " + rs.getString(1));
        }
        
    }
    @FXML
     private void ayham(ActionEvent event) throws URISyntaxException, IOException {
       try {
        Desktop.getDesktop().browse(new URI ("https://www.facebook.com/ayh00am"));
    }catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex.toString());
    }
    }

    @FXML
    private void shareef(ActionEvent event) throws URISyntaxException, IOException {
        try {
        Desktop.getDesktop().browse(new URI ("https://www.facebook.com/profile.php?id=100009684487237"));
    }catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex.toString());
    }
    }
}