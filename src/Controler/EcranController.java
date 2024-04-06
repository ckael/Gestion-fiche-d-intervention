/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import static Config.Connexion.c;
import static Config.Connexion.conecter;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author CKAEL
 */
public class EcranController implements Initializable {


        @FXML
    private ComboBox<String> IdEcran;

    @FXML
    private TextField sDimension;

    @FXML
    private TextField sMarque;

    @FXML
    void ChoixEcran(ActionEvent event) throws IOException {
        String vId = IdEcran.getValue();
        
            try {
                
                conecter();
                Statement st = c.createStatement();
                String sql = "SELECT * FROM ecran where IdMateriel= '"+vId+"'";
                ResultSet rs =st.executeQuery(sql);
                if(rs.next())
                {
        
                    sDimension.setText(rs.getString(3));
                    sMarque.setText(rs.getString(2));
                }
            } catch (SQLException ex) {
                Logger.getLogger(EcranController.class.getName()).log(Level.SEVERE, null, ex);
            }
      
    }
    
      public void AffichageEcran()
    {
        ObservableList<String> Ecrans  = FXCollections.observableArrayList();
        try {
             conecter();
            Statement st = c.createStatement();
            String sql ="SELECT * FROM ecran";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
               Ecrans.add(rs.getString(1));
                    
            }        
             IdEcran.setItems(Ecrans); 
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }
    
     public void SetTextfield(String vDimension,String vMarque){
    
   sDimension.setText(vDimension);
   sMarque.setText(vMarque);
  
 }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AffichageEcran();
    }    
    
}
