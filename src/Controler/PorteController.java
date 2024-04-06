/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Config.Connexion;
import static Config.Connexion.c;
import static Config.Connexion.conecter;
import Model.Porte;
import Model.Service;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author CKAEL
 */
public class PorteController implements Initializable {
    
      @FXML
    private HBox bRestaurer;
      
    @FXML
    private Pane pAct;
    
    
     @FXML
    private TableColumn<Porte, String> CNumPorte;

    @FXML
    private ComboBox<String> CmbService;
    
      @FXML
    private HBox bAfficheActu;

    @FXML
    private HBox bAfficheArchive;

    
    @FXML
    private HBox bModifier;

    @FXML
    private HBox bSuprimer;

    @FXML
    private HBox bEnregistrer;

    @FXML
    private TableColumn<Porte, String> cCodeS;

    @FXML
    private TableColumn<Porte, String> cPoste;

    @FXML
    private TableColumn<Porte, String> cService;

    @FXML
    private TextField sCodeS;

    @FXML
    private TextField sNumP;

    @FXML
    private TextField sPoste;
    
    @FXML
    private TextField sSearch;

    @FXML
    private TableView<Porte> tPorte;
    public void alert(String texte)
{
   
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Valeur invalide");
            alert.setContentText(texte);
             alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();  
}
        @FXML
    void AfficheActuel(MouseEvent event) {
        AffichagePorte();
        bAfficheArchive.setVisible(true);
        bAfficheActu.setVisible(false);
        pAct.setVisible(true);
        bRestaurer.setVisible(false);
    }

    @FXML
    void AfficheArchive(MouseEvent event) {
        AffichagePorteArchiver();
        bAfficheArchive.setVisible(false);
        bAfficheActu.setVisible(true);
          pAct.setVisible(false);
          bRestaurer.setVisible(true);
         
    }

    @FXML
    void ArchivePorte(MouseEvent event) {
         String vNumP=sNumP.getText();
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Gestion de porte");
        alert.setContentText("Voulez vous vraiment archiver la Porte \t"+vNumP);
         alert.initStyle(StageStyle.UNDECORATED);
        alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
        
        alert.showAndWait();
        if(alert.getResult()==ButtonType.OK)
        {
            
            Statement st = null;
             try {
                 conecter();
                 st = c.createStatement();
                 String Sql ="UPDATE porte SET Archiver = 1 WHERE NumPorte='"+vNumP+"'";
                st.execute(Sql);           
               AffichagePorte();
                alert.close();
                
             } catch (SQLException ex) {
                 Logger.getLogger(PorteController.class.getName()).log(Level.SEVERE, null, ex);
             }
            finally
             {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PorteController.class.getName()).log(Level.SEVERE, null, ex);
                }
             }
            
        }
        if(alert.getResult()==ButtonType.CANCEL)
        {
        alert.close();
        }
    }

    @FXML
    void EditPorte(MouseEvent event)  {
         String vNumP=sNumP.getText();
         String vPoste =sPoste.getText();
         String vCodeS = sCodeS.getText();
         
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Gestion de porte");
        alert.setContentText("Voulez vous vraiment modifier la Porte \t"+vNumP);
         alert.initStyle(StageStyle.UNDECORATED);
        alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
        
        alert.showAndWait();
        Statement st=null;
        if(alert.getResult()==ButtonType.OK)
        {
             try {
                 conecter();
                 st = c.createStatement();
            String Sql ="UPDATE porte SET Poste = '"+vPoste+"',CodeService='"+vCodeS+"' WHERE NumPorte='"+vNumP+"'";
            st.execute(Sql);
             } catch (SQLException ex) {
                 Logger.getLogger(PorteController.class.getName()).log(Level.SEVERE, null, ex);
             }
               finally
             {
                 try {
                     st.close();
                 } catch (SQLException ex) {
                     Logger.getLogger(PorteController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
            
           
            alert.close();
            AffichagePorte();
            bModifier.setDisable(true);
            bSuprimer.setDisable(true);
            bEnregistrer.setDisable(false);
        }
        if(alert.getResult()==ButtonType.CANCEL)
        {
        alert.close();
        }
        
    }

    @FXML
    void Refresh(MouseEvent event) {
        AffichagePorte();
    }
@FXML
void Restaurer(MouseEvent event){
    String vNumP=sNumP.getText();
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Gestion de porte");
        alert.setContentText("Voulez vous vraiment Restaurer la Porte \t"+vNumP);
         alert.initStyle(StageStyle.UNDECORATED);
        alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
        
        alert.showAndWait();
        if(alert.getResult()==ButtonType.OK)
        {
            
            Statement st = null;
             try {
                 conecter();
                 st = c.createStatement();
                 String Sql ="UPDATE porte SET Archiver = 0 WHERE NumPorte='"+vNumP+"'";
                st.execute(Sql);           
               AffichagePorteArchiver();
                alert.close();
                
             } catch (SQLException ex) {
                 Logger.getLogger(PorteController.class.getName()).log(Level.SEVERE, null, ex);
             }
            finally
             {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PorteController.class.getName()).log(Level.SEVERE, null, ex);
                }
             }
            
        }
        if(alert.getResult()==ButtonType.CANCEL)
        {
        alert.close();
        }
}
    @FXML
    void SavePorte(MouseEvent event)  {
         String vNumP=sNumP.getText();
         String vPoste =sPoste.getText();
         String vCodeS = sCodeS.getText();
         Statement st=null;
         if(vNumP.isEmpty())
         {
         alert("Veuillez remplir la zone de texte numero porte");
         }else{
         if(vPoste.isEmpty())
         {
         alert("Veuillez remplir la zone de texte du poste ");
         }
         else
         {
         if(vCodeS.isEmpty())
         {
         alert("Veuillez choisir un service ");
         }
         else
         {
            try {
              conecter();
              st = c.createStatement();
             String sql = "INSERT INTO `porte` (`NumPorte`, `Poste`, `CodeService`) VALUES ('"+vNumP+"', '"+vPoste+"', '"+vCodeS+"')"; 
             st.execute(sql);
          } catch (SQLException ex) {
              Logger.getLogger(PorteController.class.getName()).log(Level.SEVERE, null, ex);
          }
              finally
             {
                 try {
                     st.close();
                 } catch (SQLException ex) {
                     Logger.getLogger(PorteController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
            
             
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Gestion de porte");
            alert.setContentText(" Porte \t"+vNumP+"\t Ajouter avec succes");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            
            AffichagePorte();
         }
         }
         }
         
       
            
         
    }

    @FXML
    void Search(MouseEvent event) {
        RecherchePorte();
    }

    @FXML
    void Service(ActionEvent event)  {
      String  vService = CmbService.getSelectionModel().getSelectedItem();
      Statement st=null;
          try {
         Connexion.conecter();
         st = c.createStatement();
        String sql ="Select * from service where Service ='"+vService+"'  ";
        ResultSet rs = st.executeQuery(sql);  
       if(rs.next())
       {
           sCodeS.setText(rs.getString(1));
       }
          } catch (SQLException ex) {
              Logger.getLogger(PorteController.class.getName()).log(Level.SEVERE, null, ex);
          }
            finally
             {
                 try {
                     st.close();
                 } catch (SQLException ex) {
                     Logger.getLogger(PorteController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
            
        
    }

    public void AffichageService()
    {
        ObservableList<String> services  = FXCollections.observableArrayList();
        Statement st=null;
        try {
             conecter();
            st = c.createStatement();
            String sql ="SELECT * FROM `service`";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
               services.add(rs.getString(2));
                    
            }        
             CmbService.setItems(services); 
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
          finally
             {
                 try {
                     st.close();
                 } catch (SQLException ex) {
                     Logger.getLogger(PorteController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
           
    }
    public void AffichagePorte()
            
    {
    ObservableList<Porte> Portes  = FXCollections.observableArrayList();
    Statement st =null;
         try {
             conecter();
              st=c.createStatement();
             String sql="SELECT*FROM viewporte where Archiver = 0";
             ResultSet rs=st.executeQuery(sql);
             while(rs.next())
             {
             Porte prt = new Porte();
             prt.setNumPorte(rs.getString(2));
             prt.setPoste(rs.getString(3));
             prt.setCodeService(rs.getString(1));
             prt.setService(rs.getString(5));
             Portes.add(prt);
            
             }
              
           tPorte.setItems(Portes);
           CNumPorte.setCellValueFactory(f ->f.getValue().NumPorteProperty());
           cCodeS.setCellValueFactory(f ->f.getValue().CodeServiceProperty());
           cPoste.setCellValueFactory(f ->f.getValue().PosteProperty());
           cService.setCellValueFactory(f ->f.getValue().ServiceProperty());
           
         } catch (SQLException ex) {
             Logger.getLogger(PorteController.class.getName()).log(Level.SEVERE, null, ex);
         }
          finally
             {
                 try {
                     st.close();
                 } catch (SQLException ex) {
                     Logger.getLogger(PorteController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
           
    
         
            tPorte.setRowFactory(tv ->{ TableRow<Porte> row = new TableRow<>();
        row.setOnMouseClicked(event ->{
            if(event.getClickCount()==1)
            
            {
                int index= tPorte.getSelectionModel().getSelectedIndex();
               sCodeS.setText(tPorte.getItems().get(index).getCodeService());
               sNumP.setText(tPorte.getItems().get(index).getNumPorte());
               sPoste.setText(tPorte.getItems().get(index).getPoste());
               String vService = tPorte.getItems().get(index).getService();
       CmbService.setValue(vService);
                bModifier.setDisable(false);
            bSuprimer.setDisable(false);
            bEnregistrer.setDisable(true);
                   
            } });
        return row;
        });
    }
    
      public void AffichagePorteArchiver()
            
    {
    ObservableList<Porte> Portes  = FXCollections.observableArrayList();
    Statement st = null;
         try {
             conecter();
              st=c.createStatement();
             String sql="SELECT*FROM viewporte where Archiver = 1";
             ResultSet rs=st.executeQuery(sql);
             while(rs.next())
             {
             Porte prt = new Porte();
             prt.setNumPorte(rs.getString(2));
             prt.setPoste(rs.getString(3));
             prt.setCodeService(rs.getString(1));
             prt.setService(rs.getString(5));
             Portes.add(prt);
            
             }
            
           tPorte.setItems(Portes);
           CNumPorte.setCellValueFactory(f ->f.getValue().NumPorteProperty());
           cCodeS.setCellValueFactory(f ->f.getValue().CodeServiceProperty());
           cPoste.setCellValueFactory(f ->f.getValue().PosteProperty());
           cService.setCellValueFactory(f ->f.getValue().ServiceProperty());
           
         } catch (SQLException ex) {
             Logger.getLogger(PorteController.class.getName()).log(Level.SEVERE, null, ex);
         }finally
         {
        try {
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(PorteController.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
    
         
            tPorte.setRowFactory(tv ->{ TableRow<Porte> row = new TableRow<>();
        row.setOnMouseClicked(event ->{
            if(event.getClickCount()==1)
            
            {
                  bRestaurer.setDisable(false);
                int index= tPorte.getSelectionModel().getSelectedIndex();
               sCodeS.setText(tPorte.getItems().get(index).getCodeService());
               sNumP.setText(tPorte.getItems().get(index).getNumPorte());
               sPoste.setText(tPorte.getItems().get(index).getPoste());
               String vService = tPorte.getItems().get(index).getService();
       CmbService.setValue(vService);
         
             
                   
            } });
        return row;
        });
    }
    
    
    public void RecherchePorte()
           
    {
         String vSearch = sSearch.getText();
    ObservableList<Porte> Portes  = FXCollections.observableArrayList();
    Statement st = null;
         try {
             conecter();
             st=c.createStatement();
             String sql="SELECT*FROM viewporte where NumPorte LIKE '%"+vSearch+"%' AND Archiver = 0";
             ResultSet rs=st.executeQuery(sql);
             while(rs.next())
             {
             Porte prt = new Porte();
             prt.setNumPorte(rs.getString(2));
             prt.setPoste(rs.getString(3));
             prt.setCodeService(rs.getString(1));
             prt.setService(rs.getString(5));
             Portes.add(prt);
            
             }
             
           tPorte.setItems(Portes);
           CNumPorte.setCellValueFactory(f ->f.getValue().NumPorteProperty());
           cCodeS.setCellValueFactory(f ->f.getValue().CodeServiceProperty());
           cPoste.setCellValueFactory(f ->f.getValue().PosteProperty());
           cService.setCellValueFactory(f ->f.getValue().ServiceProperty());
           
         } catch (SQLException ex) {
             Logger.getLogger(PorteController.class.getName()).log(Level.SEVERE, null, ex);
         }
         finally
         {
        try {
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(PorteController.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
    
    
         
              tPorte.setRowFactory(tv ->{ TableRow<Porte> row = new TableRow<>();
        row.setOnMouseClicked(event ->{
            if(event.getClickCount()==1)
            
            {
                int index= tPorte.getSelectionModel().getSelectedIndex();
               sCodeS.setText(tPorte.getItems().get(index).getCodeService());
               sNumP.setText(tPorte.getItems().get(index).getNumPorte());
               sPoste.setText(tPorte.getItems().get(index).getPoste());
               String vService = tPorte.getItems().get(index).getService();
       CmbService.setValue(vService);
             bModifier.setDisable(false);
            bSuprimer.setDisable(false);
            bEnregistrer.setDisable(true);   
                   
            } });
        return row;
        });
    }
    
        public void masque()
    {
         sNumP.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                sNumP.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
         
           sSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                sSearch.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
         sPoste.textProperty().addListener((observable, oldValue, newValue) -> {
    if (!newValue.matches("([a-zA-Z]|[\\s])*")) {
        sPoste.setText(oldValue);
    }   
});
   
               
  
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        masque();
        // TODO
        sNumP.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                sNumP.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        AffichageService();
        AffichagePorte();
    }    
    
}
