/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *s
 * @author Ayham Dw
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("UI1.fxml"));
    primaryStage.setTitle("Educational Center");
    primaryStage.setScene(new Scene (root, 700, 500));
    primaryStage.setResizable(false);
    primaryStage.getIcons().add(new Image ("D:\\Netbeans_WorkSpace\\JavaApplication1\\src\\icon.png"));
    primaryStage.show();
    
    
    
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
