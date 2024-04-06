/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author CKAEL
 */
public class MenuGeneralController implements Initializable {
       @FXML
    private Pane Main;
    
    @FXML
    private BorderPane pMenu;

    @FXML
    private Pane bArchive;

    @FXML
    private FontAwesomeIconView bClose;

    @FXML
    private Pane bDemande;

    @FXML
    private Pane bHome;

    @FXML
    private Pane bHideMenu;

     @FXML
    private Pane bMenu;


    @FXML
    private Pane bPersonel;

    @FXML
    private Pane bPorte;

    @FXML
    private Pane bRecuperation;

    @FXML
    private Pane bReparateur;

    @FXML
    private Pane bService;
    
     @FXML
    private Pane pLeft;
     
       @FXML
    private Pane bListeArchive;
    
      @FXML
    private Pane bStatistique;
     
    @FXML
    void Statistique(MouseEvent event)
    {
           try {
               Miova("Chart");
           } catch (IOException ex) {
               Logger.getLogger(MenuGeneralController.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
    
     @FXML
    void ListeArchive(MouseEvent event)
    {
           try {
               Miova("ListeArchive");
           } catch (IOException ex) {
               Logger.getLogger(MenuGeneralController.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
    
    
    @FXML
    void HideMenu(MouseEvent event) {
          TranslateTransition slide = new TranslateTransition();
          slide.setDuration(Duration.seconds(0.5));
          slide.setNode(pLeft);
          slide.setToX(-176);
          slide.play();
          pLeft.setTranslateX(0);
          slide.setOnFinished((ActionEvent e)->{bMenu.setVisible(true);bHideMenu.setVisible(false);});  
    }
    
    @FXML
    void ShowMenu(MouseEvent event) {
          TranslateTransition slide = new TranslateTransition();
          slide.setDuration(Duration.seconds(0.5));
          slide.setNode(pLeft);
          slide.setToX(0);
          slide.play();
          pLeft.setTranslateX(-176);
          slide.setOnFinished((ActionEvent e)->{bHideMenu.setVisible(true);bMenu.setVisible(false);});
    }

    @FXML
    void Materiel(MouseEvent event) throws IOException {
        Miova("Materiel");
        bArchive.setDisable(true);
         bDemande.setDisable(false);
             bPersonel.setDisable(false);
            bService.setDisable(false);
             bReparateur.setDisable(false);
             bPorte.setDisable(false);
               bRecuperation.setDisable(false);
    }

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
    void Demande(MouseEvent event) {
        try {
            Miova("Depot");
            bDemande.setDisable(true);
             bPersonel.setDisable(false);
            bService.setDisable(false);
             bReparateur.setDisable(false);
             bPorte.setDisable(false);
               bRecuperation.setDisable(false);
                bArchive.setDisable(false);
                 bMenu.setDisable(false);
        } catch (IOException ex) {
            Logger.getLogger(MenuGeneralController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void Home(MouseEvent event) {
        pMenu.setCenter(Main);
         bService.setDisable(false);
            bPersonel.setDisable(false);
             bReparateur.setDisable(false);
             bPorte.setDisable(false);
               bRecuperation.setDisable(false);
                bDemande.setDisable(false);
                  bArchive.setDisable(false);
                   bMenu.setDisable(true);
    }

   

    @FXML
    void Personel(MouseEvent event) {
        try {
            Miova("FXMLDocument");
            bMenu.setDisable(false);
            bPersonel.setDisable(true);
            bService.setDisable(false);
             bReparateur.setDisable(false);
             bPorte.setDisable(false);
               bRecuperation.setDisable(false);
                bDemande.setDisable(false);
                  bArchive.setDisable(false);
        } catch (IOException ex) {
            Logger.getLogger(MenuGeneralController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void Porte(MouseEvent event) {
        try {
            Miova("Porte");
            bPorte.setDisable(true);
            bService.setDisable(false);
            bPersonel.setDisable(false);
             bReparateur.setDisable(false);
              bRecuperation.setDisable(false);
               bDemande.setDisable(false);
                 bArchive.setDisable(false);
                  bMenu.setDisable(false);
        } catch (IOException ex) {
            Logger.getLogger(MenuGeneralController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void Recuperation(MouseEvent event) {
        try {
            Miova("Recuperation");
            bRecuperation.setDisable(true);
            bService.setDisable(false);
            bPersonel.setDisable(false);
             bReparateur.setDisable(false);
             bPorte.setDisable(false);
              bDemande.setDisable(false);
                bArchive.setDisable(false);
                 bMenu.setDisable(false);
        } catch (IOException ex) {
            Logger.getLogger(MenuGeneralController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void Reparateur(MouseEvent event) {
        try {
            Miova("Reparateur");
            bReparateur.setDisable(true);
            bService.setDisable(false);
            bPersonel.setDisable(false);
            bPorte.setDisable(false);
              bRecuperation.setDisable(false);
               bDemande.setDisable(false);
                 bArchive.setDisable(false);
                  bMenu.setDisable(false);
        } catch (IOException ex) {
            Logger.getLogger(MenuGeneralController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void Service(MouseEvent event) {
        try {
            Miova("Service");
            bService.setDisable(true);
            bPersonel.setDisable(false);
             bReparateur.setDisable(false);
             bPorte.setDisable(false);
               bRecuperation.setDisable(false);
                bDemande.setDisable(false);
                  bArchive.setDisable(false);
                   bMenu.setDisable(false);
        } catch (IOException ex) {
            Logger.getLogger(MenuGeneralController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void Miova(String Page) throws IOException
    {
        Parent root = null;
         root = FXMLLoader.load(getClass().getResource("/View/"+Page+".fxml"));
         pMenu.setCenter(root);
    
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    
    }    
    
}
