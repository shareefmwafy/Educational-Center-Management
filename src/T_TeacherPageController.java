

import com.jfoenix.controls.JFXButton;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import oracle.jdbc.pool.OracleDataSource;

/**
 * FXML Controller class
 *
 * @author share
 */
public class T_TeacherPageController implements Initializable {
    
    @FXML
    private BorderPane borderpane;
    @FXML
    private Pane HomePage;
    @FXML
    private JFXButton ShowTeacherTableButton;
    @FXML
    private TableView<T_Teacher> T_TeacherTable;
    
    private Text MLable;
    private String teacherName;
    @FXML
    private TableColumn<T_Teacher, String> MIDTable;
    @FXML
    private TableColumn<T_Teacher, String> MNTable;
    @FXML
    private TableColumn<T_Teacher, String> GIDTable;
    @FXML
    private TableColumn<T_Teacher, String> GNTable;
    @FXML
    private TableColumn<T_Teacher, String> PTable;
    @FXML
    private TableColumn<T_Teacher, String> Ttable;
    @FXML
    private TableColumn<T_Teacher, String> CTble;
    public String username;
    @FXML
    private Label WelcomeLabel;
    @FXML
    private Text TimeLabel;
    @FXML
    private Text DateLabel;
    @FXML
    private Text TodayLabel;
    @FXML
    private Text StartDate;
    @FXML
    private Text NextSalary;
    @FXML
    private Text TodayLabel1;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MIDTable.setCellValueFactory(new PropertyValueFactory<>("MaterialID"));
        MNTable.setCellValueFactory(new PropertyValueFactory<>("MaterialName"));
        GNTable.setCellValueFactory(new PropertyValueFactory<>("GroupName"));
        GIDTable.setCellValueFactory(new PropertyValueFactory<>("GroupID"));
        PTable.setCellValueFactory(new PropertyValueFactory<>("Period"));
        Ttable.setCellValueFactory(new PropertyValueFactory<>("Time"));
        CTble.setCellValueFactory(new PropertyValueFactory<>("Classs"));
//        JOptionPane.showMessageDialog(null, username);
//         String query ="Select FIRSTNAME from teacher where teacherid = '"+username+"'";
//        OracleDataSource orc;
//        try {
//            orc = new OracleDataSource ();
//            orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
//            orc.setUser("testuser");
//            orc.setPassword("123456");
//            Connection conn = orc.getConnection();
//            Statement st = conn.createStatement();
//            ResultSet rs = st.executeQuery(query);
//            while(rs.next()){
//                teacherName=rs.getString(1);
//            }
//            MLable.setText("Hi "+teacherName);
////            JOptionPane.showMessageDialog(null, username);
//        } catch (SQLException ex) {
//            Logger.getLogger(T_TeacherPageController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
    }   
    
    @FXML
    private void showTable(ActionEvent event) throws SQLException{
        String query ="Select tm.MATERIALID,m.MATERIALNAME,g.GROUPID,g.GROUPNAME,g.PERIOD,g.STARTTIME,g.classid "
            + "from teacher_Material tm inner join material m "
            + "on tm.MATERIALID = m.MATERIALID "
            + "inner join groups g "
            + "on tm.TEACHERID = g.TEACHERID "
            + "inner join class C "
            + "on C.CLASSID = g.CLASSID "
            + "where tm.TEACHERID = '" + username + "' "
                + "and g.materialid = tm.materialid";
        OracleDataSource orc = new OracleDataSource ();
        orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
        orc.setUser("testuser");
        orc.setPassword("123456");
        Connection conn = orc.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        T_TeacherTable.getItems().clear();
        while(rs.next())   
           { 
             T_Teacher t = new T_Teacher (rs.getString(1) ,rs.getString(2) , rs.getString(3) , rs.getString(4) , rs.getString(5) , rs.getString(6) , rs.getString(7));
             
             T_TeacherTable.getItems().add(t);
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
        borderpane.setCenter(root);
    }

    @FXML
    private void LogOutPage(ActionEvent event) throws IOException {
        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to logout?");
        if(alert.showAndWait().get() == ButtonType.OK )
        {
            Stage stage = (Stage) borderpane.getScene().getWindow();
            stage.close();
             Parent root = FXMLLoader.load(getClass().getResource("UI1.fxml"));
             stage.setTitle("Educational Center");
             stage.setScene(new Scene (root, 700, 500));
             stage.show();
            
            
        }
    }

    @FXML
    private void StudentPage(ActionEvent event) throws IOException {
        loadpage("Teacher_YourStudent");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Teacher_YourStudent.fxml"));
        Parent root = loader.load();
        Teacher_YourStudentController ref = loader.getController();
        ref.username = username;
        Stage stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene1 = new Scene(root);
        borderpane.setCenter(root);

    }

    @FXML
    private void HomePage(ActionEvent event) {
        borderpane.setCenter(HomePage);
    }

    @FXML
    private void SettingPage(ActionEvent event) throws IOException {
        loadpage("TeacherSetting");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TeacherSetting.fxml"));
        Parent root = loader.load();
        TeacherSettingController ref = loader.getController();
        ref.username = username;
        ref.Settings(username);
        Stage stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene1 = new Scene(root);
        borderpane.setCenter(root);
    }
    
    
    public void test () {
        MLable.setText("Hi "+teacherName);
        
        
    }
    
    
         public void welcome () throws Exception {
        String Query = "Select FirstName from Teacher where Teacherid = '" + username +  "'" ;
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
        else if (x >=13 && x<= 17)
             WelcomeLabel.setText("Good Afternoon, " + rs.getString(1));
        else 
            WelcomeLabel.setText("Good Evening, " + rs.getString(1));
        }
         }
     private void ayham(ActionEvent event) throws URISyntaxException, IOException {
       try {
        Desktop.getDesktop().browse(new URI ("https://www.facebook.com/ayh00am"));
    }catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex.toString());
    }
     }
     
     public void initi () throws SQLException { 
         
         String test = null ;
         Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        DateLabel.setText(formatter.format(date)); 
        formatter = new SimpleDateFormat("HH : mm");
        TimeLabel.setText(formatter.format(date));
        String day = LocalDate.now().getDayOfWeek().name();
        TodayLabel.setText(day);
        String Query = "Select STARTDATE FROM Teacher WHERE TeacherID = '" +username+"'";
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
            StartDate.setText(rs.getString(1));
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
        NextSalary.setText(day1 + "-" + String.valueOf(AddMonth) + "-" + String.valueOf(AddYear));

     }catch (Exception ex) {}
}
    

}