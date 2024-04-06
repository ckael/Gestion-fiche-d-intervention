/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import static Config.Connexion.c;
import static Config.Connexion.conecter;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author CKAEL
 */
public class AutreController implements Initializable {

       @FXML
    private ComboBox<String> IdAUtre;

    @FXML
    private TextField sDesignation;

    @FXML
    void ChoixAutre(ActionEvent event) {
   String  vId = IdAUtre.getValue();
       
           try {
               conecter();
               Statement st = c.createStatement();
               String sql = "SELECT * FROM autremateriel where IdMateriel = '"+vId+"'";
               ResultSet rs = st.executeQuery(sql);
               if(rs.next())
               {
               sDesignation.setText(rs.getString(2));
               }
           } catch (SQLException ex) {
               Logger.getLogger(AutreController.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
    
        public void AffichageAutre()
    {
        ObservableList<String> Autres  = FXCollections.observableArrayList();
        try {
             conecter();
            Statement st = c.createStatement();
            String sql ="SELECT * FROM autremateriel";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
               Autres.add(rs.getString(1));
                    
            }        
             IdAUtre.setItems(Autres);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AffichageAutre();
    }    
    
}
