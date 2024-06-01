import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import oracle.jdbc.pool.OracleDataSource;
public class Manager_TeacherHomeController implements Initializable {
    @FXML
    private TextField TeacherIdTexField;
    @FXML
    private TableView<Teacher> TeacherTable;
    @FXML
    private TableColumn<Teacher, String> TeacherIDCol;
    @FXML
    private TableColumn<Teacher, String> FirstNameCol;
    @FXML
    private TableColumn<Teacher, String> LastNameCol;
    @FXML
    private TableColumn<Teacher, String> SalaryCol;
    @FXML
    private TableColumn<Teacher, String> StartDateCol;
    @FXML
    private TableColumn<Teacher, String> CityCol;
    @FXML
    private TableColumn<Teacher, String> StreetCol;
    @FXML
    private TableColumn<Teacher, String> GenderCol;
    @FXML
    private TableColumn<Teacher, String> PhoneNumberCol;
    @FXML
    private TextField FirstNameTextField;
    @FXML
    private TextField SecondNameTextField;
    @FXML
    private TextField LastNameTextField;
    @FXML
    private JFXRadioButton MaleRadioButton;
    @FXML
    private JFXRadioButton FemaleRadioButton;
    @FXML
    private ToggleGroup Gender;
    @FXML
    private TextField CityTexTfield;
    @FXML
    private TextField StreetTextField;
    @FXML
    private TextField PhoneNumberTextField;
    @FXML
    private Pane MTHome;

   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new animatefx.animation.ZoomIn(MTHome).play();
        TeacherIDCol.setCellValueFactory(new PropertyValueFactory<>("TeacherID"));
        FirstNameCol.setCellValueFactory(new PropertyValueFactory<>("FName"));
        LastNameCol.setCellValueFactory(new PropertyValueFactory<>("LName"));
        SalaryCol.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        StartDateCol.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
        CityCol.setCellValueFactory(new PropertyValueFactory<>("City"));
        StreetCol.setCellValueFactory(new PropertyValueFactory<>("Street"));
        GenderCol.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        PhoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber") );
        TeacherTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        
         OracleDataSource orc;
        try {
            orc = new OracleDataSource ();
            orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
            orc.setUser("testuser");
            orc.setPassword("123456");
            Connection conn = orc.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Teacher");
            TeacherTable.getItems().clear();
            while(rs.next())   
            { 
              Teacher t = new Teacher (rs.getString(1) ,rs.getString(2) , rs.getString(4) , rs.getString(11) , rs.getString(12) , rs.getString(9) , rs.getString(5) , rs.getString(10) , rs.getString(6) );
              TeacherTable.getItems().add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Manager_TeacherHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        
        
        

    }
    public void oracle (String s) throws Exception
      {
          
            OracleDataSource orc = new OracleDataSource ();
            orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
            orc.setUser("testuser");
            orc.setPassword("123456");
            Connection conn = orc.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(s);
            TeacherTable.getItems().clear();
            while(rs.next())   
            { 
              Teacher t = new Teacher (rs.getString(1) ,rs.getString(2) , rs.getString(4) , rs.getString(11) , rs.getString(12) , rs.getString(9) , rs.getString(10) , rs.getString(5) , rs.getString(6) );
              TeacherTable.getItems().add(t);
            }  
      }
    
    
    
    
    @FXML
    private void SearchTeacher(ActionEvent event) throws Exception{
        StringBuilder query = new StringBuilder();
//        StringBuilder query1 = new StringBuilder();
//         Selection = TeacherComboBox.getSelectionModel().getSelectedItem().toString();
        query.append("SELECT * FROM Teacher WHERE");
        int flag = 0;
        
        if(!TeacherIdTexField.getText().isEmpty() ){
            if (flag == 0) { 
             query.append(" TEACHERID = " + "'"+TeacherIdTexField.getText() + "'" );
             oracle(query.toString());
                flag = 1;
            }
            else 
            {
                 query.append(" and TEACHERID = " + "'"+TeacherIdTexField.getText() + "'" );
                 oracle(query.toString());
            }
        }
        if(!FirstNameTextField.getText().isEmpty() ){
            if (flag == 0) { 
             query.append(" FIRSTNAME = " + "'"+FirstNameTextField.getText() + "'" );
             oracle(query.toString());
                flag = 1;
            }
            else 
            {
                 query.append(" and FIRSTNAME = " + "'"+FirstNameTextField.getText() + "'" );
                 oracle(query.toString());
            }
        }
        if(!SecondNameTextField.getText().isEmpty() ){
            if (flag == 0) { 
             query.append(" MIDNAME = " + "'"+SecondNameTextField.getText() + "'" );
             oracle(query.toString());
                flag = 1;
            }
            else 
            {
                 query.append(" and MIDNAME = " + "'"+SecondNameTextField.getText() + "'" );
                 oracle(query.toString());
            }
        }
        if(!LastNameTextField.getText().isEmpty() ){
            if (flag == 0) { 
             query.append(" LASTNAME = " + "'"+LastNameTextField.getText() + "'" );
             oracle(query.toString());
                flag = 1;
            }
            else 
            {
                 query.append(" and LASTNAME = " + "'"+LastNameTextField.getText() + "'" );
                 oracle(query.toString());
            }
        }
        if (MaleRadioButton.isSelected())
           {
                if (flag == 0) { 
             query.append(" GENDER = 'Male'" );
             oracle(query.toString());
                flag = 1;
            }
            else 
            {
                 query.append(" and GENDER ='Male' "  );
                 oracle(query.toString());
            }
                 MaleRadioButton.setSelected(false);
           }
        else if (FemaleRadioButton.isSelected())
           {
                if (flag == 0) { 
             query.append(" GENDER = 'Female'" );
             oracle(query.toString());
                flag = 1;
            }
            else 
            {
                 query.append(" and GENDER ='Female' "  );
                 oracle(query.toString());
            }
                FemaleRadioButton.setSelected(false);
           }
        
        if (flag == 0) {
            OracleDataSource orc = new OracleDataSource ();
            orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
            orc.setUser("testuser");
            orc.setPassword("123456");
            Connection conn = orc.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Teacher");
            TeacherTable.getItems().clear();
            while(rs.next())   
            { 
              Teacher t = new Teacher (rs.getString(1) ,rs.getString(2) , rs.getString(4) , rs.getString(11) , rs.getString(12) , rs.getString(9) , rs.getString(5) , rs.getString(10) , rs.getString(6) );
              TeacherTable.getItems().add(t);
            }  
         }
        
        
        if(!CityTexTfield.getText().isEmpty() ){
            if (flag == 0) { 
             query.append(" CITY = " + "'"+CityTexTfield.getText() + "'" );
             oracle(query.toString());
                flag = 1;
            }
            else 
            {
                 query.append(" and CITY = " + "'"+CityTexTfield.getText() + "'" );
                 oracle(query.toString());
            }
        }
        
         if(!StreetTextField.getText().isEmpty() ){
            if (flag == 0) { 
             query.append(" Street = " + "'"+StreetTextField.getText() + "'" );
             oracle(query.toString());
                flag = 1;
            }
            else 
            {
                 query.append(" and Street = " + "'"+StreetTextField.getText() + "'" );
                 oracle(query.toString());
            }
        }
         
         
         if(!PhoneNumberTextField.getText().isEmpty() ){
            if (flag == 0) { 
             query.append(" PHONENUMBER = " + "'"+PhoneNumberTextField.getText() + "'" );
             oracle(query.toString());
                flag = 1;
            }
            else 
            {
                 query.append(" and PHONENUMBER = " + "'"+PhoneNumberTextField.getText() + "'" );
                 oracle(query.toString());
            }
        }
        
        
        
        
    }
   @FXML
    private void openAddTeacher1(MouseEvent event) {
        
          try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("Manager_AddTeacher.fxml"));
            Parent root = (Parent) fxml.load();
            Stage stage = new Stage ();
            stage.setTitle("Add Teacher");
            stage.setScene(new Scene (root));
            stage.getIcons().add(new Image ("D:\\Netbeans_WorkSpace\\JavaApplication1\\src\\icon.png"));
            stage.show();
            
        } catch (IOException ex) {
        JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    @FXML
    private void RemoveTeacher(ActionEvent event) {
        int selected; 
        try {
             
             ObservableList <Teacher> obs; 
             obs = TeacherTable.getSelectionModel().getSelectedItems();
           if (!obs.isEmpty()) {
                 OracleDataSource orc = new OracleDataSource();
                 orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
                 orc.setUser("testuser");
                 orc.setPassword("123456");
                 Connection con = orc.getConnection();
                 Statement stm = con.createStatement();
                 String x = obs.get(0).getTeacherID();
                 String Query = "DELETE FROM TEACHER WHERE TEACHERID = '" + x + "'";
                 stm.executeUpdate(Query);
                 selected = TeacherTable.getSelectionModel().getSelectedIndex();
                 //TeacherTable.getSelectionModel().getSelectedItems().remove(selected);
                  TeacherTable.getItems().remove(selected);
             }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        
        
        
    }

    @FXML
    private void TeacherReport(ActionEvent event) throws Exception {
                OracleDataSource orc = new OracleDataSource();
                orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
                orc.setUser("testuser");
                orc.setPassword("123456");
                Connection conn = orc.getConnection();
                InputStream in = new FileInputStream (new File ("Teacher.jrxml"));
                JasperDesign j = JRXmlLoader.load(in) ;
                JasperReport jr = JasperCompileManager.compileReport(j); 
                JasperPrint jp = JasperFillManager.fillReport(jr , null , conn) ;
                OutputStream out = new FileOutputStream (new File ("Teacher Report.pdf"));
                JasperExportManager.exportReportToPdfStream(jp, out);
                out.close();
                in.close();
                JOptionPane.showMessageDialog(null, "Done!" , "Information" , JOptionPane.INFORMATION_MESSAGE);
    }
    
}