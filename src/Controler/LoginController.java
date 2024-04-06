/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import static Config.Connexion.c;
import static Config.Connexion.conecter;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author CKAEL
 */
public class LoginController implements Initializable {

    @FXML
    private FontAwesomeIconView bClose;

    @FXML
    private Button bValider;

    @FXML
    private Label lLoginIncorect;

    @FXML
    private Label lMdpIncorect;

    @FXML
    private TextField sLogin;

    @FXML
    private PasswordField sPsw;

    @FXML
    void Close(MouseEvent event) {
          Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Gestion de porte");
        alert.setContentText("Voulez vous vraiment quiter");
         alert.initStyle(StageStyle.UNDECORATED);
        alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
        
        alert.showAndWait();
        if(alert.getResult()==ButtonType.OK)
        {
            System.exit(0);
        }
        if(alert.getResult()==ButtonType.CANCEL)
        {
        alert.close();
        }
       
    }
    

    @FXML
    void Valider(ActionEvent event) throws IOException {
            String vNom = sLogin.getText();
            String Mdp = sPsw.getText();
            String Date = LocalDate.now().toString();
             System.out.println(Date);
             Statement st = null;
          
                
        try {
            conecter();
            st= c.createStatement();
            String Sql = "SELECT * FROM reparateur where NomReparateur ='"+vNom+"'";
            ResultSet rs = st.executeQuery(Sql);
             if(rs.next())
          {
                if(Mdp.equals(Date))
                {
                    changer();
                }
                else
                {
                    lMdpIncorect.setText("Mot de passe incorect");
                }
          }
          else
          {
          lLoginIncorect.setText("Nom d'utilisateur incorecte");
          }  
           
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally 
        {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
             
    }
    
    private void changer()
    {
                    
                    Scene scene;
                    Parent root;
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MenuGeneral.fxml"));
        try {
            root= loader.load();
            scene = new Scene(root);    
                    Stage stage1 = new Stage();
                     stage1.setScene(scene);
                    stage1.initStyle(StageStyle.UNDECORATED);
                    stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
