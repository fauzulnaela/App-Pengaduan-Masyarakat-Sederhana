/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cobacrud;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author naelafm
 */
public class MenuBarController implements Initializable {

    /**
     * Initializes the controller class.
     */

    
    @FXML
    void pindahKeLaporkan (ActionEvent event) throws IOException {
        System.out.println("Berhasil masuk ke laporkan");
        Parent Home  = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene sceneLaporkan = new Scene (Home);
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow() ;
        stage.setScene(sceneLaporkan);
        stage.setResizable(false);
        stage.show();
    
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
