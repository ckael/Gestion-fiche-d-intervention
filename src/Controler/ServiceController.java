/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Config.Connexion;
import static Config.Connexion.c;
import static Config.Connexion.conecter;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Model.Service;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;

public class ServiceController implements Initializable  {

     @FXML
    private Button BEnregistrer;

    @FXML
    private Button BModifier;

    @FXML
    private Button BSuprimer;

    @FXML
    private Label LTitre;

    @FXML
    private TableView<Service> TService;

    @FXML
    private TableColumn<Service, String> cService;
    
    @FXML
    private TableColumn<Service, String> cCode;
    
    @FXML
    private TextField sCodeService;

    @FXML
    private TextField sService;
    
      @FXML
    private FontAwesomeIconView bRefresh;

    @FXML
    private FontAwesomeIconView bSearch;
    
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
    void Enregistrer(ActionEvent event) {
        String vCodeService = sCodeService.getText();
        String vService = sService.getText();
        if(vCodeService.isEmpty()){alert("Veuillez remplir la zone de texte code service");}
        else{ 
            if(vService.isEmpty()){alert("Veuillez remplir la zone de texte service");}        
        else{
         try {
            Service.Inserer(vCodeService, vService);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Gestion de service");
            alert.setContentText(vService+"\n Ajouter avec succes");
             alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            
            Affichage();
            sCodeService.setText("");
            sService.setText("");
         } catch (SQLException ex) {
             Logger.getLogger(ServiceController.class.getName()).log(Level.SEVERE, null, ex);
         }
                }}
    }

    @FXML
    void Modifier(ActionEvent event) {
        String vCodeService = sCodeService.getText();
        String vService = sService.getText();
         try {
             Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Gestion de service");
            alert.setContentText(" Voulez vous vraiment modifier\n"+vService);
            
            alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
             alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
       
            
            if (alert.getResult() == ButtonType.OK) {
           Service.Modifier(vCodeService, vService);
            sCodeService.setText("");
            sService.setText("");
            Affichage();
            alert.close();
        } else if (alert.getResult() == ButtonType.CANCEL) {
            alert.close();
        } 
         } catch (SQLException ex) {
             Logger.getLogger(ServiceController.class.getName()).log(Level.SEVERE, null, ex);
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
    void Suprimer(ActionEvent event) {
         String vCodeService = sCodeService.getText();
         
              try {
             Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Gestion de service");
            alert.setContentText(" Voulez vous vraiment suprimer\n"+sService.getText());
           
            alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            
            if (alert.getResult() == ButtonType.OK) {
           Service.Suprimer(vCodeService);
            sCodeService.setText("");
            sService.setText("");
            Affichage();
            alert.close();
        } else if (alert.getResult() == ButtonType.CANCEL) {
            alert.close();
        } 
         
         } catch (SQLException ex) {
             Logger.getLogger(ServiceController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    

public void Affichage()
    {
        ObservableList<Service> services  = FXCollections.observableArrayList();
        try {
             conecter();
            Statement st = c.createStatement();
            String sql ="SELECT * FROM `service`";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
                Service sc = new Service();
                sc.setCodeService(rs.getString(1));
                sc.setService(rs.getString(2));
                services.add(sc); 
               
            }
            TService.setItems(services);
            cService.setCellValueFactory(f ->f.getValue().ServiceProperty());
            cCode.setCellValueFactory(f ->f.getValue().CodeServiceProperty());
            
          
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
             TService.setRowFactory(tv ->{ TableRow<Service> row = new TableRow<>();
        row.setOnMouseClicked(event ->{
            if(event.getClickCount()==1)
            
            {
                int index= TService.getSelectionModel().getSelectedIndex();
                sCodeService.setText(TService.getItems().get(index).getCodeService());
                sService.setText(TService.getItems().get(index).getService());
                
               
            
            
            } });
        return row;
        });
   
    }



public void Recherche()
    {
        String vService= sSearch.getText();
        ObservableList<Service> services  = FXCollections.observableArrayList();
        try {
             conecter();
            Statement st = c.createStatement();
            String sql ="SELECT * FROM `service` where Service LIKE '%"+vService+"%'";
            ResultSet rs=st.executeQuery(sql);
            if(rs.next())
            {
                Service sc = new Service();
                sc.setCodeService(rs.getString(1));
                sc.setService(rs.getString(2));
                services.add(sc); 
               
            }
            TService.setItems(services);
            cService.setCellValueFactory(f ->f.getValue().ServiceProperty());
            cCode.setCellValueFactory(f ->f.getValue().CodeServiceProperty());
            
          
            sSearch.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
             TService.setRowFactory(tv ->{ TableRow<Service> row = new TableRow<>();
        row.setOnMouseClicked(event ->{
            if(event.getClickCount()==1)
            
            {
                int index= TService.getSelectionModel().getSelectedIndex();
                sCodeService.setText(TService.getItems().get(index).getCodeService());
                sService.setText(TService.getItems().get(index).getService());
                
               
            
            
            } });
        return row;
        });
   
    }

    public void masque()
    {
        
         
             sService.textProperty().addListener((observable, oldValue, newValue) -> {
    if (!newValue.matches("([a-zA-Z]|[\\s])*")) {
        sService.setText(oldValue);
    }   
});
               sCodeService.textProperty().addListener((observable, oldValue, newValue) -> {
    if (!newValue.matches("[a-zA-Z]*")) {
        sCodeService.setText(newValue.replaceAll("[^a-zA-Z]", ""));
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
        masque();
         try {
             Connexion.conecter();
             Affichage();
         } catch (SQLException ex) {
             Logger.getLogger(ServiceController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }       
}
