import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javax.swing.plaf.basic.BasicSliderUI;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import oracle.jdbc.pool.OracleDataSource;
public class Manager_StudentHomeController implements Initializable {
    String Selection ; 
    @FXML
    private JFXButton AddButton;////////////////////////////////////////////////////////////
    @FXML
    public TableColumn<Student, Integer> StudentID;
    @FXML
    public TableView<Student> StudentTable;
    @FXML
    private TableColumn<Student, String> FirstNameCo;
    @FXML
    private TableColumn<Student, String> MidNameCo;
    @FXML
    private TableColumn<Student, String> LNameCo;
    @FXML
    private TableColumn<Student, String> GenderCol;
    @FXML
    private TableColumn<Student, String> StartDateCol;
    @FXML
    private TableColumn<Student, String> CityCol;
    @FXML
    private TableColumn<Student, String> StreetCol;
    @FXML
    private TableColumn<Student, String> BirthdateCol;
    @FXML
    private TableColumn<Student, Integer> CountMateriaCol;
    @FXML
    public TextField FirstNameTextFeild;
    @FXML
    public TextField SecondNameTextFeild;
    @FXML
    private TextField LastNameTextFeild;
    @FXML
    private JFXRadioButton MaleRadioButton;
    @FXML
    private JFXRadioButton FemaleRadioButton;
    @FXML
    private TextField CityTextFeild;
    @FXML
    private TextField StreetTextFeild;
      @FXML
    private TextField IDTexField;
   
    @FXML
    private JFXComboBox<String> combobox;
   
   
    @FXML
    private JFXRadioButton SciantaficRadioButton;
    @FXML
    private JFXRadioButton LiteraryRadioButton;
    @FXML
    private JFXRadioButton CommercialRadioButton;
    @FXML
    private JFXRadioButton IndustrialRadioButton;
    @FXML
    private ToggleGroup gender;
    @FXML
    private JFXButton RemoveStudentButton;
    @FXML
    private ToggleGroup Specilization;
   int selected ; 
    @FXML
    private Pane MSHome;
    
    
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        new animatefx.animation.ZoomIn(MSHome).play();
        StudentID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        FirstNameCo.setCellValueFactory((new PropertyValueFactory<> ("FName")));
        MidNameCo.setCellValueFactory(new PropertyValueFactory<> ("MName"));
        LNameCo.setCellValueFactory(new PropertyValueFactory<> ("LName"));
        GenderCol.setCellValueFactory(new PropertyValueFactory<> ("Gender") );
        StartDateCol.setCellValueFactory(new PropertyValueFactory<>("StartDate") );
        CityCol.setCellValueFactory(new PropertyValueFactory<> ("Birthdate") );
        StreetCol.setCellValueFactory(new PropertyValueFactory<> ("Street") );
        BirthdateCol.setCellValueFactory(new PropertyValueFactory <> ("Specilization") );
        CountMateriaCol.setCellValueFactory(new PropertyValueFactory<> ("Stage"));
        StudentTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        String [] items = {"Nothing" , "StudentID" , "10" , "11" , "12" };
        combobox.getItems().addAll(items);
        IDTexField.setEditable(false);
        combobox.getSelectionModel().selectFirst(); // first index of combobox to ignore null value 
        FirstNameCo.setCellFactory(TextFieldTableCell.forTableColumn()); // first name
        MidNameCo.setCellFactory(TextFieldTableCell.forTableColumn()); // mid name
        LNameCo.setCellFactory(TextFieldTableCell.forTableColumn()); // last name
        StreetCol.setCellFactory(TextFieldTableCell.forTableColumn()); // city
        //BirthdateCol.setCellFactory(TextFieldTableCell.forTableColumn()); // for sepecilization
        StartDateCol.setCellFactory(TextFieldTableCell.forTableColumn()); 
        CityCol.setCellFactory(TextFieldTableCell.forTableColumn()); // stage
        //FirstNameCo.setCellFactory(TextFieldTableCell.forTableColumn());
        
        
        OracleDataSource orc;
        try {
            orc = new OracleDataSource ();
            orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
            orc.setUser("testuser");
            orc.setPassword("123456");
            Connection conn = orc.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM STUDENT ORDER BY STUDENTID");
            StudentTable.getItems().clear();
            while(rs.next())   
            { 
               Student s1 = new Student(rs.getString(1) ,rs.getString(2) , rs.getString(3) , rs.getString(4) , rs.getString(5) , rs.getString(11) ,rs.getString(13) ,rs.getString(14) ,rs.getString(7) ,rs.getString(6) );
               StudentTable.getItems().add(s1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Manager_StudentHomeController.class.getName()).log(Level.SEVERE, null, ex);
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
            StudentTable.getItems().clear();
            while(rs.next())   
            { 
               Student s1 = new Student(rs.getString(1) ,rs.getString(2) , rs.getString(3) , rs.getString(4) , rs.getString(5) , rs.getString(11) ,rs.getString(13) ,rs.getString(14) ,rs.getString(7) ,rs.getString(6) );
               StudentTable.getItems().add(s1);
            }  
      }
//    ObservableList<Student> o2 = FXCollections.observableArrayList(new Student("12029160" , "Shareef", "Imad", "Mwafy" , "Male" , "Qalqilia" , "S1","9/7/2022" ,3));

    @FXML
    private void OpenAddStudent(ActionEvent event) {
        
          try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("Manager-Add-Student-1.fxml"));
            Parent root = (Parent) fxml.load();
            Stage stage = new Stage ();
            stage.setTitle("Add Student");
            stage.setScene(new Scene (root));
            stage.getIcons().add(new Image ("D:\\Netbeans_WorkSpace\\JavaApplication1\\src\\icon.png"));
            stage.show();
            
        } catch (IOException ex) {
        JOptionPane.showMessageDialog(null, ex.toString());
        }
          
    }

    @FXML
    private void OpenPayment(MouseEvent event) {
        
         try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("Manager_PaymentDusStudent.fxml"));
            Parent root = (Parent) fxml.load();
            Stage stage = new Stage ();
            stage.setTitle("Add Student");
            stage.setScene(new Scene (root));
            stage.getIcons().add(new Image ("D:\\Netbeans_WorkSpace\\JavaApplication1\\src\\icon.png"));
            stage.show();
            
        } catch (IOException ex) {
        JOptionPane.showMessageDialog(null, ex.toString());
        }
         
    }

    @FXML
    private void SearchStudent(ActionEvent event) throws Exception {
        StringBuilder query = new StringBuilder();
        StringBuilder query1 = new StringBuilder();
         Selection = combobox.getSelectionModel().getSelectedItem().toString();
        query.append("SELECT * FROM STUDENT WHERE");
        int flag = 0;
        
        if(!IDTexField.getText().isEmpty() ){
            if (flag == 0) { 
             query.append(" STUDENTID = " + "'"+IDTexField.getText() + "' ORDER BY STUDENTID " );
             oracle(query.toString());
                flag = 1;
            }
            else 
            {
                 query.append(" and STUDENTID = " + "'"+IDTexField.getText() + "' ORDER BY STUDENTID" );
                 oracle(query.toString());
            }
        }
        
         if(!FirstNameTextFeild.getText().isEmpty() ){
            if (flag == 0) { 
             query.append(" FIRSTNAME = " + "'"+FirstNameTextFeild.getText() + "' ORDER BY STUDENTID" );
             oracle(query.toString());
                flag = 1;
            }
            else 
            {
                 query.append(" and FIRSTNAME = " + "'"+FirstNameTextFeild.getText() + "' ORDER BY STUDENTID" );
                 oracle(query.toString());
            }
        }
         
         if(!SecondNameTextFeild.getText().isEmpty() ){
            if (flag == 0) { 
             query.append(" MIDNAME = " + "'"+SecondNameTextFeild.getText() + "' ORDER BY STUDENTID" );
             oracle(query.toString());
                flag = 1;
            }
            else 
            {
                 query.append(" and MIDNAME = " + "'"+SecondNameTextFeild.getText() + "' ORDER BY STUDENTID" );
                 oracle(query.toString());
            }
        }
          if(!LastNameTextFeild.getText().isEmpty() ){
            if (flag == 0) { 
             query.append(" LASTNAME = " + "'"+LastNameTextFeild.getText() + "' ORDER BY STUDENTID" );
             oracle(query.toString());
                flag = 1;
            }
            else 
            {
                 query.append(" and LASTNAME = " + "'"+LastNameTextFeild.getText() + "' ORDER BY STUDENTID" );
                 oracle(query.toString());
            }
        }
          
           if(!CityTextFeild.getText().isEmpty() ){
            if (flag == 0) { 
             query.append(" CITY = " + "'"+CityTextFeild.getText() + "' ORDER BY STUDENTID" );
             oracle(query.toString());
                flag = 1;
            }
            else 
            {
                 query.append(" and CITY = " + "'"+CityTextFeild.getText() + "' ORDER BY STUDENTID" );
                 oracle(query.toString());
            }
        }
           
           if(!StreetTextFeild.getText().isEmpty() ){
            if (flag == 0) { 
             query.append(" STREET = " + "'"+StreetTextFeild.getText() + "' ORDER BY STUDENTID" );
             oracle(query.toString());
                flag = 1;
            }
            else 
            {
                 query.append(" and STREET = " + "'"+StreetTextFeild.getText() + "' ORDER BY STUDENTID" );
                 oracle(query.toString());
            }
        }
           
           if (SciantaficRadioButton.isSelected())
           {
               if (flag == 0) { 
             query.append(" SPECILIZATION = 'Scientific' ORDER BY STUDENTID" );
             oracle(query.toString());
                flag = 1;
            }
            else 
            {
                 query.append(" and SPECILIZATION ='Scientific' ORDER BY STUDENTID "  );
                 oracle(query.toString());
            }
               SciantaficRadioButton.setSelected(false);
           }
           
          else if (LiteraryRadioButton.isSelected())
           {
               if (flag == 0) { 
             query.append(" SPECILIZATION = 'Literary' ORDER BY STUDENTID" );
             oracle(query.toString());
                flag = 1;
            }
            else 
            {
                 query.append(" and SPECILIZATION ='Literary' ORDER BY STUDENTID "  );
                 oracle(query.toString());
            }
               LiteraryRadioButton.setSelected(false);
           }
           
         else if (CommercialRadioButton.isSelected())
           {
               if (flag == 0) { 
             query.append(" SPECILIZATION = 'Commercial' ORDER BY STUDENTID" );
             oracle(query.toString());
                flag = 1;
            }
            else 
            {
                 query.append(" and SPECILIZATION ='Commercial' ORDER BY STUDENTID"  );
                 oracle(query.toString());
            }
               CommercialRadioButton.setSelected(false);
           }
           
           else if (IndustrialRadioButton.isSelected())
           {
               if (flag == 0) { 
             query.append(" SPECILIZATION = 'Industrial' ORDER BY STUDENTID" );
             oracle(query.toString());
                flag = 1;
            }
            else 
            {
                 query.append(" and SPECILIZATION ='Industrial' ORDER BY STUDENTID"  );
                 oracle(query.toString());
            }
               IndustrialRadioButton.setSelected(false);
           }
           
           
           if (MaleRadioButton.isSelected())
           {
                if (flag == 0) { 
             query.append(" GENDER = 'Male' ORDER BY STUDENTID" );
             oracle(query.toString());
                flag = 1;
            }
            else 
            {
                 query.append(" and GENDER ='Male'ORDER BY STUDENTID "  );
                 oracle(query.toString());
            }
                 MaleRadioButton.setSelected(false);
           }
           
          else if (FemaleRadioButton.isSelected())
           {
                if (flag == 0) { 
             query.append(" GENDER = 'Female' ORDER BY STUDENTID" );
             oracle(query.toString());
                flag = 1;
            }
            else 
            {
                 query.append(" and GENDER ='Female'ORDER BY STUDENTID "  );
                 oracle(query.toString());
            }
                FemaleRadioButton.setSelected(false);
           } 
            
           
          
         if (Selection.equals("StudentID")) {
             
             if (flag == 0) { 
             query.append(" STUDENTID = " + "'" + IDTexField.getText() + "' ORDER BY STUDENTID"  );
             oracle(query.toString());
                flag = 1;
            }
            else 
            {
                 query.append(" and STUDENTID =" + "'" + IDTexField.getText() + "' ORDER BY STUDENTID"   );
                 oracle(query.toString());
            }
             
         }
         
          
          if (Selection.equals("10")) {
             
              if (flag == 0) { 
             query.append(" STAGE = '10' ORDER BY STUDENTID" );
             oracle(query.toString());
                flag = 1;
            }
            else 
            {
                 query.append(" and STAGE = '10' ORDER BY STUDENTID" );
                 oracle(query.toString());
            }
             
         }
          
          if (Selection.equals("11")) {
             
              if (flag == 0) { 
             query.append(" STAGE = '11' ORDER BY STUDENTID " );
             oracle(query.toString());
                flag = 1;
            }
            else 
            {
                 query.append(" and STAGE = '11' ORDER BY STUDENTID" );
                 oracle(query.toString());
            }
             
         }
          if (Selection.equals("12")) {
             
              if (flag == 0) { 
             query.append(" STAGE = '12'ORDER BY STUDENTID " );
             oracle(query.toString());
                flag = 1;
            }
            else 
            {
                 query.append(" and STAGE = '12' ORDER BY STUDENTID" );
                 oracle(query.toString());
            }
             
         }
          
          
           
         
        
         if (flag == 0) {
            OracleDataSource orc = new OracleDataSource ();
            orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
            orc.setUser("testuser");
            orc.setPassword("123456");
            Connection conn = orc.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM STUDENT ORDER BY STUDENTID");
            StudentTable.getItems().clear();
            while(rs.next())   
            { 
               Student s1 = new Student(rs.getString(1) ,rs.getString(2) , rs.getString(3) , rs.getString(4) , rs.getString(5) , rs.getString(11) ,rs.getString(13) ,rs.getString(14) ,rs.getString(7) ,rs.getString(6) );
               StudentTable.getItems().add(s1);
            }  
         }
         
        
         
         
         
    }
    @FXML
    private void RemoveStudentButton(ActionEvent event) throws SQLException{
//        TablePosition tp = StudentTable.getSelectionModel().getSelectedCells().get(0);
//        int row = tp.getRow();
//        Student item =StudentTable.getItems().get(row);
//        TableColumn tc = tp.getTableColumn();
//        String data = (String) tc.getCellObservableValue(item).getValue();
//        StudentTable.getItems().removeAll(StudentTable.getSelectionModel().getSelectedItem());
//        
//        String query = "Delete FROM STUDENT Where STUDENTID =  " + "'"+data+"'";
//         OracleDataSource orc = new OracleDataSource ();  
//         orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
//         orc.setUser("testuser");
//         orc.setPassword("123456");
//         Connection conn = orc.getConnection();
//         Statement st = conn.createStatement();
//         st.executeUpdate(query);
//         st.close();
//          
      
       ObservableList <Student> obs ;
       obs = StudentTable.getSelectionModel().getSelectedItems();
       if (!obs.isEmpty()){
       String id = obs.get(0).getID();
       
       String query = "DELETE FROM STUDENT WHERE STUDENTID = " + "'" + id + "'";
       OracleDataSource orc = new OracleDataSource ();
       orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
       orc.setUser("testuser");
       orc.setPassword("123456");
       Connection conn = orc.getConnection();
       Statement st = conn.createStatement();
       st.executeUpdate(query);
     
      selected = StudentTable.getSelectionModel().getSelectedIndex();
       StudentTable.getItems().remove(selected);
       }else{
           JOptionPane.showMessageDialog(null, "Please Select Student", "Warning", JOptionPane.WARNING_MESSAGE);
       }
         
         
         
    }

    @FXML
    private void comboBoxItems(ActionEvent event) {
        
      
       if (combobox.getSelectionModel().getSelectedItem().toString().equals("StudentID")) {
                  IDTexField.setEditable(true);
                  IDTexField.setText("");
       }
       else {
             IDTexField.setEditable(false);
             IDTexField.setText("");
       }
        
    }
    private void FirstNameChanged(TableColumn.CellEditEvent<Student, String> event) {
       Student student = StudentTable.getSelectionModel().getSelectedItem();
       student.setFName(event.getNewValue());
       String x = student.getFName();
       ObservableList <Student> obs ;
       obs = StudentTable.getSelectionModel().getSelectedItems();
       String id = obs.get(0).getID();
       try {
           
       String query = "update Student set FIRSTNAME = '" + x + "' "
               + "where StudentID = '" + id + "'";
       OracleDataSource orc = new OracleDataSource ();
       orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
       orc.setUser("testuser");
       orc.setPassword("123456");
       Connection conn = orc.getConnection();
       Statement st = conn.createStatement();
       st.executeUpdate(query);
       }catch (Exception ex) 
       {
           JOptionPane.showMessageDialog(null, ex.toString());
       }
    }

    private void MidNameChanged(TableColumn.CellEditEvent<Student, String> event) {
     
        Student student = StudentTable.getSelectionModel().getSelectedItem();
       student.setMName(event.getNewValue());
       String x = student.getMName();
       ObservableList <Student> obs ;
       obs = StudentTable.getSelectionModel().getSelectedItems();
       String id = obs.get(0).getID();
       try {
           
       String query = "update Student set MIDNAME = '" + x + "' "
               + "where StudentID = '" + id + "'";
       OracleDataSource orc = new OracleDataSource ();
       orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
       orc.setUser("testuser");
       orc.setPassword("123456");
       Connection conn = orc.getConnection();
       Statement st = conn.createStatement();
       st.executeUpdate(query);
       }catch (Exception ex) 
       {
           JOptionPane.showMessageDialog(null, ex.toString());
       }
        
        
    }

    private void LastNameChanged(TableColumn.CellEditEvent<Student, String> event) {
        
        
        Student student = StudentTable.getSelectionModel().getSelectedItem();
        student.setLName(event.getNewValue());
       String x = student.getLName();
       ObservableList <Student> obs ;
       obs = StudentTable.getSelectionModel().getSelectedItems();
       String id = obs.get(0).getID();
       try {
           
       String query = "update Student set LASTNAME = '" + x + "' "
               + "where StudentID = '" + id + "'";
       OracleDataSource orc = new OracleDataSource ();
       orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
       orc.setUser("testuser");
       orc.setPassword("123456");
       Connection conn = orc.getConnection();
       Statement st = conn.createStatement();
       st.executeUpdate(query);
       }catch (Exception ex) 
       {
           JOptionPane.showMessageDialog(null, ex.toString());
       }
        
        
    }

    private void BirthdateChanged(TableColumn.CellEditEvent<Student, String> event) {
        
         Student student = StudentTable.getSelectionModel().getSelectedItem();
        student.setBirthdate(event.getNewValue());
       String x = student.getBirthdate();
       ObservableList <Student> obs ;
       obs = StudentTable.getSelectionModel().getSelectedItems();
       String id = obs.get(0).getID();
       try {
           
       String query = "update Student set BIRTHDATE = '" + x + "' "
               + "where StudentID = '" + id + "'";
       OracleDataSource orc = new OracleDataSource ();
       orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
       orc.setUser("testuser");
       orc.setPassword("123456");
       Connection conn = orc.getConnection();
       Statement st = conn.createStatement();
       st.executeUpdate(query);
       }catch (Exception ex) 
       {
           JOptionPane.showMessageDialog(null, ex.toString());
       }
        
    }

    private void StreetChanged(TableColumn.CellEditEvent<Student, String> event) {
        
         Student student = StudentTable.getSelectionModel().getSelectedItem();
        student.setStreet(event.getNewValue());
       String x = student.getStreet();
       ObservableList <Student> obs ;
       obs = StudentTable.getSelectionModel().getSelectedItems();
       String id = obs.get(0).getID();
       try {
           
       String query = "update Student set STREET = '" + x + "' "
               + "where StudentID = '" + id + "'";
       OracleDataSource orc = new OracleDataSource ();
       orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
       orc.setUser("testuser");
       orc.setPassword("123456");
       Connection conn = orc.getConnection();
       Statement st = conn.createStatement();
       st.executeUpdate(query);
       }catch (Exception ex) 
       {
           JOptionPane.showMessageDialog(null, ex.toString());
       }
        
    }
    @FXML
    private void StudentReport(ActionEvent event) throws Exception {
                 OracleDataSource orc = new OracleDataSource();
                 orc.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
                 orc.setUser("testuser");
                 orc.setPassword("123456");
                 Connection conn = orc.getConnection();
                InputStream in = new FileInputStream (new File ("Student.jrxml"));
                JasperDesign j = JRXmlLoader.load(in) ;
                JasperReport jr = JasperCompileManager.compileReport(j); 
                JasperPrint jp = JasperFillManager.fillReport(jr , null , conn) ;
                OutputStream out = new FileOutputStream (new File ("Stdudent Report.pdf"));
                JasperExportManager.exportReportToPdfStream(jp, out);
                out.close();
                in.close();
                JOptionPane.showMessageDialog(null, "Done!" , "Information" , JOptionPane.INFORMATION_MESSAGE);

    }

   
}