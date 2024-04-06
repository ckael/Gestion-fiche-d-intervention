/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;
import Config.Connexion;
import static Config.Connexion.c;
import static Config.Connexion.conecter;
import Model.Personel;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;


/**
 *
 * @author CKAEL
 */
public class FXMLDocumentController implements Initializable {
    
  
    @FXML
    private HBox bAjouter;

    @FXML
    private HBox bModifier;

    @FXML
    private HBox bSuprimer;
    @FXML
    private TableColumn<Personel, String> cMatricule;

    @FXML
    private TableColumn<Personel, String> cNom;

    @FXML
    private TableColumn<Personel, String> cPrenom;

    @FXML
    private TextField sNom;

    @FXML
    private TextField sPrenom;

    @FXML
    private TextField sTelephone;

    @FXML
    private TableView<Personel> tPersonel;
    @FXML
    private TableColumn<Personel, String> cTelephone;

    @FXML
    private TextField sMatricule;
    
    @FXML
    private TextField sSearch;

    @FXML
    void Actualiser(MouseEvent event) {
        Affichage();
    }
    
    @FXML
    void Chercher(MouseEvent event) {
        Recherche();
    }



    @FXML
    void Ajouter(MouseEvent event) {
        String vNom = sNom.getText();
        String vPrenom = sPrenom.getText();
        String vTelephone = sTelephone.getText();
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
                else
                {
                       if(vTelephone.isEmpty())
                            {
 alert("Veuiilez remplir la zone de texte Telephone ");
                            }
                       else
                       {
                            try {
            Personel.Inserer(vNom, vPrenom, vTelephone);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Gestion de personel");
            alert.setContentText(vNom+"\n Ajouter avec succes");
             alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            sNom.setText("");
            sPrenom.setText("");
            sTelephone.setText("");
            Affichage();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
                       }
                }
        }
       
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
    void Modifier(MouseEvent event) {
        String vNom = sNom.getText();
        String vPrenom = sPrenom.getText();
        String vTelephone = sTelephone.getText();
        String vMatricule = sMatricule.getText();
        try {           
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Gestion de personel");
            alert.setContentText(" Voulez vous vraiment modifier\n"+vNom);
             alert.initStyle(StageStyle.UNDECORATED);
            alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
            alert.showAndWait();
            
            if (alert.getResult() == ButtonType.OK) {
           Personel.Modifier(vMatricule, vNom, vPrenom, vTelephone);
            sNom.setText("");
            sPrenom.setText("");
            sTelephone.setText("");
            sMatricule.setText("");
             bAjouter.setDisable(false);
            bModifier.setDisable(true);
            bSuprimer.setDisable(true);
            Affichage();
            alert.close();
        } else if (alert.getResult() == ButtonType.CANCEL) {
            alert.close();
        } 
            
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void Suprimer(MouseEvent event) {
    String  vMatricule = sMatricule.getText();
        try {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Gestion de personnel");
         alert.initStyle(StageStyle.UNDECORATED);
        alert.setContentText("Voulez-vous vraiment supprimer"+sNom.getText());
        alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            Personel.Suprimer(vMatricule);
            sNom.setText("");
            sPrenom.setText("");
            sTelephone.setText("");
            sMatricule.setText("");
            bAjouter.setDisable(false);
            bModifier.setDisable(true);
            bSuprimer.setDisable(true);
            Affichage();
            alert.close();
        } else if (alert.getResult() == ButtonType.CANCEL) {
            alert.close();
        } 
              
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
        
        
    
    public void Affichage()
    {
        ObservableList<Personel> personels  = FXCollections.observableArrayList();
        try {
             conecter();
            Statement st = c.createStatement();
            String sql ="SELECT * from personel order by NomPersonel;";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
                Personel ps = new Personel();
                ps.setMatricule(rs.getString(1));
                ps.setNom(rs.getString(2));
                ps.setPrenom(rs.getString(3));
                ps.setTelephone(rs.getString(4));
                personels.add(ps);     
            }
            tPersonel.setItems(personels);
            cMatricule.setCellValueFactory(f ->f.getValue().MatriculeProperty());
            cNom.setCellValueFactory(f ->f.getValue().NomProperty());
            cPrenom.setCellValueFactory(f ->f.getValue().PrenomProperty());
            cTelephone.setCellValueFactory(f ->f.getValue().TelephoneProperty());
          
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tPersonel.setRowFactory(tv ->{ TableRow<Personel> row = new TableRow<>();
        row.setOnMouseClicked((MouseEvent event) ->{
            if(event.getClickCount()==1)
            
            {
                int index= tPersonel.getSelectionModel().getSelectedIndex();
                sMatricule.setText(tPersonel.getItems().get(index).getMatricule());
                sNom.setText(tPersonel.getItems().get(index).getNom());
                sPrenom.setText(tPersonel.getItems().get(index).getPrenom());
                sTelephone.setText(tPersonel.getItems().get(index).getTelephone());
                bAjouter.setDisable(true);
                bModifier.setDisable(false);
                bSuprimer.setDisable(false);
            
            
            } });
        return row;
        });
    }
            
    
    
    
    public void Recherche()
    {
        String vNom=sSearch.getText();
        ObservableList<Personel> personels  = FXCollections.observableArrayList();
        try {
             conecter();
            Statement st = c.createStatement();
            String sql ="SELECT * from personel where NomPersonel LIKE '%"+vNom+"%' order by NomPersonel;";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
                Personel ps = new Personel();
                ps.setMatricule(rs.getString(1));
                ps.setNom(rs.getString(2));
                ps.setPrenom(rs.getString(3));
                ps.setTelephone(rs.getString(4));
                personels.add(ps);     
            }
            tPersonel.setItems(personels);
            cMatricule.setCellValueFactory(f ->f.getValue().MatriculeProperty());
            cNom.setCellValueFactory(f ->f.getValue().NomProperty());
            cPrenom.setCellValueFactory(f ->f.getValue().PrenomProperty());
            cTelephone.setCellValueFactory(f ->f.getValue().TelephoneProperty());
          
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tPersonel.setRowFactory(tv ->{ TableRow<Personel> row = new TableRow<>();
        row.setOnMouseClicked((MouseEvent event) ->{
            if(event.getClickCount()==1)
            
            {
                int index= tPersonel.getSelectionModel().getSelectedIndex();
                sMatricule.setText(tPersonel.getItems().get(index).getMatricule());
                sNom.setText(tPersonel.getItems().get(index).getNom());
                sPrenom.setText(tPersonel.getItems().get(index).getPrenom());
                sTelephone.setText(tPersonel.getItems().get(index).getTelephone());
                 bAjouter.setDisable(true);
                bModifier.setDisable(false);
                bSuprimer.setDisable(false);
            
            
            } });
        return row;
        });
    }
    
        public void masque()
    {
         sTelephone.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("([\\d]|[\\s])*")) {
                sTelephone.setText(oldValue);
            }
        });
         
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
               
      sSearch.textProperty().addListener((observable, oldValue, newValue) -> {
    if (!newValue.matches("([a-zA-Z]|[\\s])*")) {
        sSearch.setText(oldValue);
    }   
});
    }
                
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            masque();
       
            Connexion.conecter();
            Affichage();
           
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
