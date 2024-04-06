/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import static Config.Connexion.c;
import static Config.Connexion.conecter;
import Model.Reparateur;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;
/**
 * FXML Controller class
 *
 * @author CKAEL
 */
public class ReparateurController implements Initializable {

       @FXML
    private FontAwesomeIconView bActualiser;

    @FXML
    private HBox bEnregistrer;

    @FXML
    private Label bModifer;

    @FXML
    private FontAwesomeIconView bRecherche;

    @FXML
    private HBox bSuprimer;
    
    @FXML
    private HBox bModifier;

    @FXML
    private TableColumn<Reparateur, String> cMatricule;

    @FXML
    private TableColumn<Reparateur, String> cNom;

    @FXML
    private TableColumn<Reparateur, String> cPrenom;

    @FXML
    private Label lTitre;

    @FXML
    private TextField sNom;

    @FXML
    private TextField sPrenom;

    @FXML
    private TextField sRecherche;
    
    @FXML
    private TextField sMatricule;
     
    @FXML
    private TableView<Reparateur> tReparateur;

    @FXML
    void Actualiser(MouseEvent event) {
        Affichage();
    }
public void alert(String texte)
{
   
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Valeur invalide");
            alert.setContentText(texte);
             alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();  
}
    @FXML
    void Enregistrer(MouseEvent event) {
        String vNom=sNom.getText();
        String vPrenom = sPrenom.getText();
          if(vNom.isEmpty())
        {
            alert("Veuiilez remplir la zone de texte Nom");
        }
        else
        {
                if(vPrenom.isEmpty())
                        {
 alert("Veuiilez remplir la zone de texte Prenom");
                        }
                else{
           try {
               Reparateur.Inserer(vNom, vPrenom);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Gestion de Reparateur");
            alert.setContentText(vNom+"\n Ajouter avec succes");
           alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            sNom.setText("");
            sPrenom.setText("");
            sMatricule.setText("");
            Affichage();
           } catch (SQLException ex) {
               Logger.getLogger(ReparateurController.class.getName()).log(Level.SEVERE, null, ex);
           }
                }}
    }

    @FXML
    void Modifier(MouseEvent event) {
        String vNom=sNom.getText();
        String vPrenom = sPrenom.getText();
        String vCode=sMatricule.getText();
           try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Gestion de reparateur");
            alert.setContentText(" Voulez vous vraiment modifier\n"+vNom);
             alert.initStyle(StageStyle.UNDECORATED);
            alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
            alert.showAndWait();
            
            if (alert.getResult() == ButtonType.OK) {
           Reparateur.Modifier(vCode, vNom, vPrenom);
            sNom.setText("");
            sPrenom.setText("");
            sMatricule.setText("");
             bModifier.setDisable(true);
            bSuprimer.setDisable(true);
            bEnregistrer.setDisable(false);
            Affichage();
            alert.close();
        } else if (alert.getResult() == ButtonType.CANCEL) {
            alert.close();
        } 
                          
           } catch (SQLException ex) {
               Logger.getLogger(ReparateurController.class.getName()).log(Level.SEVERE, null, ex);
           }
    }

    @FXML
    void Rechercher(MouseEvent event) {
       Rechercher();
    }

    @FXML
    void Suprimer(MouseEvent event) {
     String vCode=sMatricule.getText();
      try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Gestion de reparateur");
            alert.setContentText(" Voulez vous vraiment Suprimer\n"+sNom.getText());
            alert.initStyle(StageStyle.UNDECORATED);
            alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
            alert.showAndWait();
            
            if (alert.getResult() == ButtonType.OK) {
           Reparateur.Suprimer(vCode);
            sNom.setText("");
            sPrenom.setText("");
            sMatricule.setText("");
             bModifier.setDisable(true);
            bSuprimer.setDisable(true);
            bEnregistrer.setDisable(false);
            Affichage();
            alert.close();
        } else if (alert.getResult() == ButtonType.CANCEL) {
            alert.close();
        } 
                          
           } catch (SQLException ex) {
               Logger.getLogger(ReparateurController.class.getName()).log(Level.SEVERE, null, ex);
           }
    }

    
    
    
     public void Affichage()
    {
        ObservableList<Reparateur> Reparateurs  = FXCollections.observableArrayList();
        try {
             conecter();
            Statement st = c.createStatement();
            String sql ="SELECT * from reparateur order by NomReparateur;";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
                Reparateur rep = new Reparateur();
                rep.setMatricule(rs.getString(1));
                rep.setNom(rs.getString(2));
                rep.setPrenom(rs.getString(3));
                
                Reparateurs.add(rep);     
            }
            tReparateur.setItems(Reparateurs);
            cMatricule.setCellValueFactory(f ->f.getValue().MatriculeProperty());
            cNom.setCellValueFactory(f ->f.getValue().NomProperty());
            cPrenom.setCellValueFactory(f ->f.getValue().PrenomProperty());
       
          
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tReparateur.setRowFactory(tv ->{ TableRow<Reparateur> row = new TableRow<>();
        row.setOnMouseClicked(event ->{
            if(event.getClickCount()==1)
            
            {
                int index= tReparateur.getSelectionModel().getSelectedIndex();
                sMatricule.setText(tReparateur.getItems().get(index).getMatricule());
                sNom.setText(tReparateur.getItems().get(index).getNom());
                sPrenom.setText(tReparateur.getItems().get(index).getPrenom());

             bModifier.setDisable(false);
            bSuprimer.setDisable(false);
            bEnregistrer.setDisable(false);
            
            } });
        return row;
        });
    }
     
     
         public void Rechercher()
    {
        ObservableList<Reparateur> Reparateurs  = FXCollections.observableArrayList();
        String vNom=sRecherche.getText();
        try {
             conecter();
            Statement st = c.createStatement();
            String sql ="SELECT * from reparateur where NomReparateur LIKE '%"+vNom+"%' order by NomReparateur;";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
                Reparateur rep = new Reparateur();
                rep.setMatricule(rs.getString(1));
                rep.setNom(rs.getString(2));
                rep.setPrenom(rs.getString(3));
                
                Reparateurs.add(rep);     
            }
            tReparateur.setItems(Reparateurs);
            cMatricule.setCellValueFactory(f ->f.getValue().MatriculeProperty());
            cNom.setCellValueFactory(f ->f.getValue().NomProperty());
            cPrenom.setCellValueFactory(f ->f.getValue().PrenomProperty());
       
          sRecherche.setText("");
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tReparateur.setRowFactory(tv ->{ TableRow<Reparateur> row = new TableRow<>();
        row.setOnMouseClicked(event ->{
            if(event.getClickCount()==1)
            
            {
                int index= tReparateur.getSelectionModel().getSelectedIndex();
                sMatricule.setText(tReparateur.getItems().get(index).getMatricule());
                sNom.setText(tReparateur.getItems().get(index).getNom());
                sPrenom.setText(tReparateur.getItems().get(index).getPrenom());

            bModifier.setDisable(false);
            bSuprimer.setDisable(false);
            bEnregistrer.setDisable(true);
            
            } });
        return row;
        });
    }
         
             public void masque()
    {
       
         
             sNom.textProperty().addListener((observable, oldValue, newValue) -> {
    if (!newValue.matches("([a-zA-Z]|[\\s])*")) {
        sNom.setText(oldValue);
    }   
});
                sPrenom.textProperty().addListener((observable, oldValue, newValue) -> {
    if (!newValue.matches("([a-zA-Z]|[\\s])*")) {
        sPrenom.setText(oldValue);
    }   
});
               
            sRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
    if (!newValue.matches("([a-zA-Z]|[\\s])*")) {
        sRecherche.setText(oldValue);
    }   
});
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Affichage();
        masque();
    }    
    
}
