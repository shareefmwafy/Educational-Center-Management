/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

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
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import oracle.jdbc.pool.OracleDataSource;

/**
 * FXML Controller class
 *
 * @author Ayham Dw
 */
public class FXMLController implements Initializable {
    int managerid ;
    @FXML
    private BorderPane borderpane;
    @FXML
    private Pane HomePane;
    @FXML
    private Label WelcomLabel;
    @FXML
    private Label ScientificStudentCount;
    @FXML
    private Label LiteraryStudentCount;
    @FXML
    private Label CommercialStudentCount;
    @FXML
    private Label IndustrialStudentCount;
    @FXML
    private Label ScientificTeacherCount;
    @FXML
    private Label LiteraryTeacherCount;
    @FXML
    private Label CommercialTeacherCount;
    @FXML
    private Label IndustrialTeacherCount;
    @FXML
    private BarChart<?, ?> StudentChart;
    @FXML
    private NumberAxis x;
    @FXML
    private CategoryAxis y;
    @FXML
    private BarChart<?, ?> TeacherChart;
    @FXML
    private NumberAxis x1;
    @FXML
    private CategoryAxis y1;
    
    /**
     * Initializes the controller class.
     */
    int SS =0,SC=0,SL=0,SI=0;
    int TS =0,TC=0,TL=0,TI=0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for(Node n:StudentChart.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: red;");
        }
        for(Node n:StudentChart.lookupAll(".default-color1.chart-bar")) {
            n.setStyle("-fx-bar-fill: green;");
        }
        
         try { 
        String QuerySS = "select count(StudentId) from student where SPECILIZATION='Scientific'";
        String QuerySC = "select count(StudentId) from student where SPECILIZATION='Commercial'";
        String QuerySL = "select count(StudentId) from student where SPECILIZATION='Literary'";
        String QuerySI = "select count(StudentId) from student where SPECILIZATION='Industrial'";
        
        String QueryTI = "select count(TeacherId) from Teacher where SPECILIZATION='Industrial'";
        String QueryTS = "select count(TeacherId) from Teacher where SPECILIZATION='Scientific'";
        String QueryTC = "select count(TeacherId) from Teacher where SPECILIZATION='Commercial'";
        String QueryTL = "select count(TeacherId) from Teacher where SPECILIZATION='Literary'";
        
        OracleDataSource orc = new OracleDataSource();     
        orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
        orc.setUser("testuser");
        orc.setPassword("123456");
        Connection conn = orc.getConnection();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(QuerySS);
        while (rs.next()) {
            SS= rs.getInt(1);
        }
        rs = stm.executeQuery(QuerySC);
        while (rs.next()) {
            SC= rs.getInt(1);
        }
        rs = stm.executeQuery(QuerySL);
        while (rs.next()) {
            SL= rs.getInt(1);
        }
        rs = stm.executeQuery(QuerySI);
        while (rs.next()) {
            SI= rs.getInt(1);
        }
        rs = stm.executeQuery(QueryTI);
        while (rs.next()) {
            TI= rs.getInt(1);
        }
        rs = stm.executeQuery(QueryTS);
        while (rs.next()) {
            TS= rs.getInt(1);
        }
        rs = stm.executeQuery(QueryTC);
        while (rs.next()) {
            TC= rs.getInt(1);
        }
        rs = stm.executeQuery(QueryTL);
        while (rs.next()) {
            TL= rs.getInt(1);
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ScientificStudentCount.setText(SS+"");
        CommercialStudentCount.setText(SC+"");
        LiteraryStudentCount.setText(SL+"");
        IndustrialStudentCount.setText(SI+"");
        
        IndustrialTeacherCount.setText(TI+"");
        ScientificTeacherCount.setText(TS+"");
        CommercialTeacherCount.setText(TC+"");
        LiteraryTeacherCount.setText(TL+"");
        
        XYChart.Series set1 = new XYChart.Series<>();
        set1.getData().add(new XYChart.Data("Scientific", SS));
        set1.getData().add(new XYChart.Data("Literary", SL));
        set1.getData().add(new XYChart.Data("Commercial", SC));
        set1.getData().add(new XYChart.Data("Industrial", SI));        
        StudentChart.getData().addAll(set1);
        
        
        XYChart.Series set2 = new XYChart.Series<>();
        set2.getData().add(new XYChart.Data("Scientific", TS));
        set2.getData().add(new XYChart.Data("Literary", TL));
        set2.getData().add(new XYChart.Data("Commercial", TC));
        set2.getData().add(new XYChart.Data("Industrial", TI));        
        TeacherChart.getData().addAll(set2);
        

    }    

    @FXML
    private void TeacherPage(MouseEvent event) throws Exception {
        loadpage("Manager_TeacherHome");
    }

    @FXML
    private void Home(MouseEvent event) throws Exception {
        new animatefx.animation.ZoomIn(HomePane).play();
        borderpane.setCenter(HomePane);
//         for(Node n:StudentChart.lookupAll(".default-color0.chart-bar")) {
//            n.setStyle("-fx-bar-fill: red;");
//        }
//        for(Node n:StudentChart.lookupAll(".default-color1.chart-bar")) {
//            n.setStyle("-fx-bar-fill: green;");
//        }
                
        StudentChart.getData().clear();       
        TeacherChart.getData().clear();
        
         try { 
        String QuerySS = "select count(StudentId) from student where SPECILIZATION='Scientific'";
        String QuerySC = "select count(StudentId) from student where SPECILIZATION='Commercial'";
        String QuerySL = "select count(StudentId) from student where SPECILIZATION='Literary'";
        String QuerySI = "select count(StudentId) from student where SPECILIZATION='Industrial'";
        
        String QueryTI = "select count(TeacherId) from Teacher where SPECILIZATION='Industrial'";
        String QueryTS = "select count(TeacherId) from Teacher where SPECILIZATION='Scientific'";
        String QueryTC = "select count(TeacherId) from Teacher where SPECILIZATION='Commercial'";
        String QueryTL = "select count(TeacherId) from Teacher where SPECILIZATION='Literary'";
        
        OracleDataSource orc = new OracleDataSource();     
        orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
        orc.setUser("testuser");
        orc.setPassword("123456");
        Connection conn = orc.getConnection();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(QuerySS);
        while (rs.next()) {
            SS= rs.getInt(1);
        }
        rs = stm.executeQuery(QuerySC);
        while (rs.next()) {
            SC= rs.getInt(1);
        }
        rs = stm.executeQuery(QuerySL);
        while (rs.next()) {
            SL= rs.getInt(1);
        }
        rs = stm.executeQuery(QuerySI);
        while (rs.next()) {
            SI= rs.getInt(1);
        }
        rs = stm.executeQuery(QueryTI);
        while (rs.next()) {
            TI= rs.getInt(1);
        }
        rs = stm.executeQuery(QueryTS);
        while (rs.next()) {
            TS= rs.getInt(1);
        }
        rs = stm.executeQuery(QueryTC);
        while (rs.next()) {
            TC= rs.getInt(1);
        }
        rs = stm.executeQuery(QueryTL);
        while (rs.next()) {
            TL= rs.getInt(1);
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ScientificStudentCount.setText(SS+"");
        CommercialStudentCount.setText(SC+"");
        LiteraryStudentCount.setText(SL+"");
        IndustrialStudentCount.setText(SI+"");
        
        IndustrialTeacherCount.setText(TI+"");
        ScientificTeacherCount.setText(TS+"");
        CommercialTeacherCount.setText(TC+"");
        LiteraryTeacherCount.setText(TL+"");
        
        XYChart.Series set1 = new XYChart.Series<>();
        set1.getData().add(new XYChart.Data("Scientific", SS));
        set1.getData().add(new XYChart.Data("Literary", SL));
        set1.getData().add(new XYChart.Data("Commercial", SC));
        set1.getData().add(new XYChart.Data("Industrial", SI));        
        StudentChart.getData().addAll(set1);
        
        
        XYChart.Series set2 = new XYChart.Series<>();
        set2.getData().add(new XYChart.Data("Scientific", TS));
        set2.getData().add(new XYChart.Data("Literary", TL));
        set2.getData().add(new XYChart.Data("Commercial", TC));
        set2.getData().add(new XYChart.Data("Industrial", TI));        
        TeacherChart.getData().addAll(set2);
        
        
        
    }

    @FXML
    private void StudentPage(MouseEvent event) throws Exception {
        loadpage("Manegar_StudentHome");
    }

    @FXML
    private void GroupsPage(MouseEvent event) throws Exception {
        loadpage("Manager_AddGroups");
    }


    @FXML
    private void SettingPage(MouseEvent event) throws Exception {
        //JOptionPane.showMessageDialog(null, managerid);
             loadpage("ManagerSetting");
             FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerSetting.fxml"));
             Parent root = loader.load();
             ManagerSettingControllar ref = loader.getController();
             ref.username1 = managerid;
             ref.Fill(managerid);
             Stage stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
             Scene scene1 = new Scene(root);
             borderpane.setCenter(root);  
//             stage1.setScene(scene1);
//             stage1.show();

//                ManagerSettingControllar ref = new ManagerSettingControllar ();
//                ref.username = managerid;
         
         
    }
    
    private void loadpage (String page) throws Exception
    {
        Parent root = null ; 
        
           root = FXMLLoader.load(getClass().getResource(page+".fxml"));
 
        borderpane.setCenter(root);
    }

    private void ReportsPage(MouseEvent event) throws Exception {
        loadpage("Reports");
    }

    @FXML
    private void LogoutPage(MouseEvent event) throws IOException {
        Alert alert = new Alert (AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to logout?");
        if(alert.showAndWait().get() == ButtonType.OK )
        {
             Stage stage = (Stage) borderpane.getScene().getWindow();
             stage.close();
             Parent root = FXMLLoader.load(getClass().getResource("UI1.fxml"));
             stage.setTitle("Hello");
             stage.setScene(new Scene (root, 700, 500));
             stage.show();
            
            
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
    
    public void Date () {
        String name =""; 
        try { 
        String Query = "Select FirstName From Manager where Managerid = " + managerid ;
        OracleDataSource orc = new OracleDataSource();     
        orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
        orc.setUser("testuser");
        orc.setPassword("123456");
        Connection conn = orc.getConnection();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(Query);
        while (rs.next()) {
            name = rs.getString(1);
        }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH");
        int x = Integer.parseInt(formatter.format(date));
        if (x >=0 && x<= 12) 
            WelcomLabel.setText("Good Morning, " + name);
        else if (x >=13 && x<= 17)
             WelcomLabel.setText("Good Afternoon, " + name);
        else 
            WelcomLabel.setText("Good Evening, " + name);
        
    }
     
}
