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
public class UniteCentraleController implements Initializable {
    
       public void AffichageUc()
    {
        ObservableList<String> UCS  = FXCollections.observableArrayList();
        try {
             conecter();
            Statement st = c.createStatement();
            String sql ="SELECT * FROM unitecentrale";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
               UCS.add(rs.getString(1));
                    
            }        
             cmbIDUc.setItems(UCS);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }
       
        @FXML
    private ComboBox<String> cmbIDUc;

    @FXML
    private TextField sCpu;

    @FXML
    private TextField sDur;

    @FXML
    private TextField sRam;

    @FXML
    void ChoixUc(ActionEvent event) {
        String vId=cmbIDUc.getValue();
           try {
               conecter();
               Statement st = c.createStatement();
               String sql = "SELECT * FROM unitecentrale where IdMateriel = '"+vId+"'";
               ResultSet rs = st.executeQuery(sql);
               if(rs.next())
               
               {
                   sCpu.setText(rs.getString(3));
                   sRam.setText(rs.getString(2));
                   sDur.setText(rs.getString(4));
               }
           } catch (SQLException ex) {
               Logger.getLogger(UniteCentraleController.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     AffichageUc();
        // TODO
    
    }    
    
}
