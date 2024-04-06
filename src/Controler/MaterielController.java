/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import static Config.Connexion.c;
import static Config.Connexion.conecter;
import Model.Autre;
import Model.Ecran;
import Model.Reparateur;
import Model.UniteCentrale;
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
public class MaterielController implements Initializable {
    @FXML
    private Pane pAure;

      @FXML
    private HBox bArchiverAutre;

    @FXML
    private HBox bEnregistrerAutre;

    @FXML
    private HBox bModifierAutre;

    @FXML
    private TableColumn<Autre, String> cDesignationAutre;

    @FXML
    private TableColumn<Autre, String> cIdAutre;


    @FXML
    private TextField sSearchAutre;

    @FXML
    private TableView<Autre> tAutre;

    @FXML
    void ArchiverAutre(MouseEvent event) {
           String vId = sIdAutre.getText();
       
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Gestion de Materiel");
            alert.setContentText(" Voulez vous vraiment archiver la materiel\n"+vId);
             alert.initStyle(StageStyle.UNDECORATED);
            alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
            alert.showAndWait();
            
            if(alert.getResult()==ButtonType.OK)
            {
try {
        Autre.Archiver(vId);
        alert.close();
        sIdAutre.setText("");
        sDesignation.setText("");
        AffichageAutre();
} catch (SQLException ex) {
        Logger.getLogger(MaterielController.class.getName()).log(Level.SEVERE, null, ex);
}
            }
            if(alert.getResult()==ButtonType.CLOSE)
            {
            alert.close();
            }
            
           
        
    }

    @FXML
    void EnregistrerAutre(MouseEvent event) {
        String vId = sIdAutre.getText();
        String vDesignation = sDesignation.getText();
        if(vId.isEmpty())
        {
            alert("Veuillez remplir l'identification du materiel ");
        }
        if(vDesignation.isEmpty())
        {
         alert("Veuillez remplir la designation du materiel ");
        }
        else{
        try {
            Autre.Inserer(vId, vDesignation);
            sIdAutre.setText("");
            sDesignation.setText("");
            AffichageAutre();
            
        } catch (SQLException ex) {
            Logger.getLogger(MaterielController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Gestion de Materiel");
        alert.setContentText(" Materiel\t"+vId+" \t enregistrer avec succes");
        alert.initStyle(StageStyle.UNDECORATED);
        alert.showAndWait();
        }
        
    }

    @FXML
    void ModifierAutre(MouseEvent event) {
        String vId = sIdAutre.getText();
        String vDesignation = sDesignation.getText();
        
         Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Gestion de Materiel");
            alert.setContentText(" Voulez vous vraiment modifier la materiel\n"+vId);
             alert.initStyle(StageStyle.UNDECORATED);
            alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
            alert.showAndWait();
            
            if(alert.getResult()==ButtonType.OK)
            {
try {
        Autre.Modifier(vId, vDesignation);
        alert.close();
        sIdAutre.setText("");
        sDesignation.setText("");
        AffichageAutre();
} catch (SQLException ex) {
        Logger.getLogger(MaterielController.class.getName()).log(Level.SEVERE, null, ex);
}
            }
            if(alert.getResult()==ButtonType.CLOSE)
            {
            alert.close();
            }
            
           
    }

    @FXML
    void RefreshAutre(MouseEvent event) {
        AffichageAutre();
    }

    @FXML
    void SearchAutre(MouseEvent event) {
        RechercheAutre();
    }

 private final ObservableList<String> options = FXCollections.observableArrayList(
            "Ecran", "Unité Centrale", "Autre");
    @FXML
    private HBox bArchiverUc;

    @FXML
    private HBox bEnregisterUc;
    
     @FXML
    private HBox bModifierUc;
     
       @FXML
    private TableColumn<UniteCentrale, String> cCpu;

    @FXML
    private TableColumn<UniteCentrale, String> cDisqueDur;
    
    @FXML
    private TableColumn<UniteCentrale, String> cIdUc;
      
    @FXML
    private TableColumn<UniteCentrale, String> cRAM;
    
    @FXML
    private TextField sSearchUc;
    
     @FXML
    private TableView<UniteCentrale> tUC;
     
        @FXML
    private TextField SDur;

    

    @FXML
    private TextField SRam;
    
    @FXML
    private TextField sCpu;

    
   
 
@FXML
private Pane pEcran;

 @FXML
private ComboBox <String> cType;
 
 @FXML
 void ChoixType(ActionEvent event) {

     String vType = cType.getSelectionModel().getSelectedItem();
     
     if(vType=="Ecran")
     {
         pEcran.setVisible(true);
         pUnitéCentrale.setVisible(false);
         pAure.setVisible(false);
     }
     if(vType=="Unité Centrale")
     {
         pUnitéCentrale.setVisible(true);
         pEcran.setVisible(false);
         pAure.setVisible(false);
     }
     if(vType=="Autre")
     {
         pAure.setVisible(true);
         pEcran.setVisible(false);
         pUnitéCentrale.setVisible(false);
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
    private HBox bEnregistrer;
   
    @FXML
    private Pane pUnitéCentrale;

    @FXML
    private HBox bModifier;

    @FXML
    private HBox bSuprimer;

    @FXML
    private TableColumn<Ecran, String> cDimension;

    @FXML
    private TableColumn<Ecran, String> cId;

    @FXML
    private TableColumn<Ecran, String> cMarque;

    @FXML
    private TextField sDimension;

    @FXML
    private TextField sIdEcran;
    
    @FXML
    private TextField sIdUc;
 
    @FXML
    private TextField sMarque;

    @FXML
    private TextField sSearch;

    @FXML
    private TableView<Ecran> tEcran;
    
    @FXML
    private TextField sIdAutre;
    
    @FXML
    private TextField sDesignation;

    @FXML
    void ActualiserEcran(MouseEvent event) {
        Affichage();
    }
    
      @FXML
    void EnregistrerEcran(MouseEvent event) {
           String vIdEcran = sIdEcran.getText();
           String vMarque = sMarque.getText();
           String vDimension = sDimension.getText();
       if(vIdEcran.isEmpty())
       {
       alert("Veuillez remplir l'identification de l'ecran");
       }
       if(vMarque.isEmpty())
       {
        alert("Veuillez indiquer la marque de l'ecran");
       }
       if(vDimension.isEmpty())
       {
        alert("Veuillez remplir la dimension de l'ecran");
       }
       else{
     try {
         Ecran.Inserer(vIdEcran, vMarque, vDimension);
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Gestion de Materiel");
            alert.setContentText(" Ecran \t"+vIdEcran+"\t Ajouter avec succes");
             alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            sIdEcran.setText("");
            sMarque.setText("");
            sDimension.setText("");
            Affichage();
     } catch (SQLException ex) {
         Logger.getLogger(MaterielController.class.getName()).log(Level.SEVERE, null, ex);
     }
       }
    }

    @FXML
    void ModifierEcran(MouseEvent event) {
         String vIdEcran = sIdEcran.getText();
         String vMarque = sMarque.getText();
         String vDimension = sDimension.getText();
         
          Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Gestion de Materiel");
            alert.setContentText(" Voulez vous vraiment modifier l'ecran\n"+vIdEcran);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
            alert.showAndWait();
            
            if (alert.getResult() == ButtonType.OK) {
             try {
                 Ecran.Modifier(vIdEcran, vMarque, vDimension);
                 sIdEcran.setText("");
                sMarque.setText("");
                sDimension.setText("");
                alert.close();
                bModifier.setDisable(true);
                bSuprimer.setDisable(true);
                bEnregistrer.setDisable(false);
                Affichage();
             } catch (SQLException ex) {
                 Logger.getLogger(MaterielController.class.getName()).log(Level.SEVERE, null, ex);
             }          
        } else if (alert.getResult() == ButtonType.CANCEL) {
            alert.close();
        } 

    }

    @FXML
    void RechercheEcran(MouseEvent event) {
        Rechercher();
    }

    @FXML
    void SuprimerEcran(MouseEvent event) {
        String vIdEcran = sIdEcran.getText();
          
          Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Gestion de Materiel");
            alert.setContentText(" Voulez vous vraiment modifier l'ecran\n"+vIdEcran);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
            alert.showAndWait();
            
            if (alert.getResult() == ButtonType.OK) {
             try {
                Ecran.Archiver(vIdEcran);
                sIdEcran.setText("");
                sMarque.setText("");
                sDimension.setText("");
                alert.close();
                Affichage();
                 bModifier.setDisable(true);
                 bSuprimer.setDisable(true);
                 bEnregistrer.setDisable(false);
                 
             } catch (SQLException ex) {
                 Logger.getLogger(MaterielController.class.getName()).log(Level.SEVERE, null, ex);
             }          
        } else if (alert.getResult() == ButtonType.CANCEL) {
            alert.close();
        } 
    }

    
     @FXML
    void ArchiverUc(MouseEvent event) {
             String vId = sIdUc.getText();
             
              Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Gestion de Materiel");
        alert.setContentText(" Voulez vous vraiment modifier l'unité centrale\n"+vId);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) 
        {
            UniteCentrale.Archiver(vId);
            alert.close();
            sIdUc.setText("");
            SRam.setText("");
            sCpu.setText("");
            SDur.setText("");
            AffichageUc();
        }
        
        if(alert.getResult()==ButtonType.CANCEL)
        {
            alert.close();
        }
             
    }
    
      @FXML
    void EnregistrerUc(MouseEvent event) {
        String vId = sIdUc.getText();
         String vRam = SRam.getText();
         String vCpu = sCpu.getText();
         String vDur = SDur.getText();
        if(vId.isEmpty())
        {
            alert("Veuillez remplir l'identification de l'unité centrale");
        }
         if(vRam.isEmpty())
        {
         alert("Veuillez remplir la zone de texte du RAM de l'unité centrale");
        } 
         if(vCpu.isEmpty())
        {
         alert("Veuillez remplir le processeur de l'unité centrale");
        } 
          if(vDur.isEmpty())
        {
         alert("Veuillez remplir la capacité de la disque dur de l'unité centrale");
        }
          else{
              UniteCentrale.Inserer(vId, vRam, vCpu, vDur);
     
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Gestion de Materiel");
            alert.setContentText(" Unité Centrale \t"+vId+"\t Ajouter avec succes");
             alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            AffichageUc();
          }
    }

      @FXML
    void ModifierUc(MouseEvent event) {
         String vId = sIdUc.getText();
         String vRam = SRam.getText();
         String vCpu = sCpu.getText();
         String vDur = SDur.getText();
         
      Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Gestion de Materiel");
        alert.setContentText(" Voulez vous vraiment modifier l'unité centrale\n"+vId);
         alert.initStyle(StageStyle.UNDECORATED);
        alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) 
        {
            UniteCentrale.Modifier(vId, vRam, vCpu, vDur);
            alert.close();
            sIdUc.setText("");
            SRam.setText("");
            sCpu.setText("");
            SDur.setText("");
            AffichageUc();
        }
        
        if(alert.getResult()==ButtonType.CANCEL)
        {
            alert.close();
        }
    }
    
       @FXML
    void ActualiserUc(MouseEvent event) {
        AffichageUc();
    }
    
      @FXML
    void RechercheUc(MouseEvent event) {
        RechercheUc();
    }
    
      public void Affichage()
    {
        ObservableList<Ecran> Ecrans  = FXCollections.observableArrayList();
        try {
             conecter();
            Statement st = c.createStatement();
            String sql ="SELECT * FROM `ecran` WHERE `Archiver` = 0 order by Marque";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
                Ecran Ec = new Ecran();
                Ec.setIdentification(rs.getString(1));
                Ec.setMarque(rs.getString(2));
                Ec.setDimension(rs.getString(3));
               
                
                Ecrans.add(Ec);     
            }
            tEcran.setItems(Ecrans);
            cDimension.setCellValueFactory(f ->f.getValue().DimensionProperty());
            cMarque.setCellValueFactory(f ->f.getValue().MarqueProperty());
            cId.setCellValueFactory(f ->f.getValue().IdentificationProperty());
       
          bModifier.setDisable(true);
            bSuprimer.setDisable(true);
            bEnregistrer.setDisable(false);
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tEcran.setRowFactory(tv ->{ TableRow<Ecran> row = new TableRow<>();
        row.setOnMouseClicked(event ->{
            if(event.getClickCount()==1)
            
            {
                int index= tEcran.getSelectionModel().getSelectedIndex();
                sIdEcran.setText(tEcran.getItems().get(index).getIdentification());
                sMarque.setText(tEcran.getItems().get(index).getMarque());
                sDimension.setText(tEcran.getItems().get(index).getDimension());

            bModifier.setDisable(false);
            bSuprimer.setDisable(false);
            bEnregistrer.setDisable(true);
            
            } });
        return row;
        });
    }
     
     
         public void Rechercher()
    {
        ObservableList<Ecran> Ecrans  = FXCollections.observableArrayList();
        String vSearch=sSearch.getText();
        try {
             conecter();
            Statement st = c.createStatement();
            String sql ="SELECT * from ecran  where Archiver=0 and IdMateriel LIKE '%"+vSearch+"%';";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
                Ecran Ec = new Ecran();
                Ec.setIdentification(rs.getString(1));
                Ec.setMarque(rs.getString(2));
                Ec.setDimension(rs.getString(3));
               
                
                Ecrans.add(Ec);     
            }
            tEcran.setItems(Ecrans);
            cDimension.setCellValueFactory(f ->f.getValue().DimensionProperty());
            cMarque.setCellValueFactory(f ->f.getValue().MarqueProperty());
            cId.setCellValueFactory(f ->f.getValue().IdentificationProperty());
       
          
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tEcran.setRowFactory(tv ->{ TableRow<Ecran> row = new TableRow<>();
        row.setOnMouseClicked(event ->{
            if(event.getClickCount()==1)
            
            {
                int index= tEcran.getSelectionModel().getSelectedIndex();
                sIdEcran.setText(tEcran.getItems().get(index).getIdentification());
                sMarque.setText(tEcran.getItems().get(index).getMarque());
                sDimension.setText(tEcran.getItems().get(index).getDimension());

            bModifier.setDisable(false);
            bSuprimer.setDisable(false);
            bEnregistrer.setDisable(true);
            
            } });
        return row;
        });
    }
         
     public void AffichageUc()
    {
        ObservableList<UniteCentrale> Ucs = FXCollections.observableArrayList();
        try {
             conecter();
            Statement st = c.createStatement();
            String sql ="SELECT * FROM `unitecentrale` WHERE `Archiver` = 0 ";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
                UniteCentrale Uc = new UniteCentrale();
                Uc.setIdUc(rs.getString(1));
                Uc.setRam(rs.getString(2));
                Uc.setProcesseur(rs.getString(3));
                Uc.setDisque(rs.getString(4));
               
                
                Ucs.add(Uc);     
            }
            tUC.setItems(Ucs);
            cIdUc.setCellValueFactory(f ->f.getValue().IdUcProperty());
            cRAM.setCellValueFactory(f ->f.getValue().RamProperty());
            cCpu.setCellValueFactory(f ->f.getValue().ProcesseurProperty());
            cDisqueDur.setCellValueFactory(f ->f.getValue().DisqueProperty());
            
           bEnregisterUc.setDisable(false);
           bModifierUc.setDisable(true);
           bArchiverUc.setDisable(true);
       
          
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tUC.setRowFactory(tv ->{ TableRow<UniteCentrale> row = new TableRow<>();
        row.setOnMouseClicked(event ->{
            if(event.getClickCount()==1)
            
            {
                int index= tUC.getSelectionModel().getSelectedIndex();
                sIdUc.setText(tUC.getItems().get(index).getIdUc());
                SRam.setText(tUC.getItems().get(index).getRam());
                sCpu.setText(tUC.getItems().get(index).getProcesseur());
                SDur.setText(tUC.getItems().get(index).getDisque());
               

           bEnregisterUc.setDisable(true);
           bModifierUc.setDisable(false);
           bArchiverUc.setDisable(false);
            
            } });
        return row;
        });
    }
       public void RechercheUc()
    {
        String vIdUc= sSearchUc.getText();
        ObservableList<UniteCentrale> Ucs = FXCollections.observableArrayList();
        
        try {
             conecter();
            Statement st = c.createStatement();
            String sql ="SELECT * FROM `unitecentrale` WHERE `Archiver` = 0 AND IdMateriel LIKE '%"+vIdUc+"%' ";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
                UniteCentrale Uc = new UniteCentrale();
                Uc.setIdUc(rs.getString(1));
                Uc.setRam(rs.getString(2));
                Uc.setProcesseur(rs.getString(3));
                Uc.setDisque(rs.getString(4));
               
                
                Ucs.add(Uc);     
            }
            tUC.setItems(Ucs);
            cIdUc.setCellValueFactory(f ->f.getValue().IdUcProperty());
            cRAM.setCellValueFactory(f ->f.getValue().RamProperty());
            cCpu.setCellValueFactory(f ->f.getValue().ProcesseurProperty());
            cDisqueDur.setCellValueFactory(f ->f.getValue().DisqueProperty());
       
          
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tUC.setRowFactory(tv ->{ TableRow<UniteCentrale> row = new TableRow<>();
        row.setOnMouseClicked(event ->{
            if(event.getClickCount()==1)
            
            {
                int index= tUC.getSelectionModel().getSelectedIndex();
                sIdUc.setText(tUC.getItems().get(index).getIdUc());
                SRam.setText(tUC.getItems().get(index).getRam());
                sCpu.setText(tUC.getItems().get(index).getProcesseur());
                SDur.setText(tUC.getItems().get(index).getDisque());
               

           bEnregisterUc.setDisable(true);
           bModifierUc.setDisable(false);
           bArchiverUc.setDisable(false);
            
            } });
        return row;
        });
    }
       
         public void AffichageAutre()
    {
        ObservableList<Autre> Autres  = FXCollections.observableArrayList();
        try {
             conecter();
            Statement st = c.createStatement();
            String sql ="SELECT * FROM `autremateriel` WHERE `Archiver` = 0 ";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
                Autre Au = new Autre();
                Au.setIdentification(rs.getString(1));
                Au.setDesignation(rs.getString(2));

                Autres.add(Au);     
            }
            tAutre.setItems(Autres);
            cIdAutre.setCellValueFactory(f ->f.getValue().IdentificationProperty());
            cDesignationAutre.setCellValueFactory(f ->f.getValue().DesignationProperty());
           ;
       
          bModifierAutre.setDisable(true);
            bArchiverAutre.setDisable(true);
            bEnregistrerAutre.setDisable(false);
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tAutre.setRowFactory(tv ->{ TableRow<Autre> row = new TableRow<>();
        row.setOnMouseClicked(event ->{
            if(event.getClickCount()==1)
            
            {
                int index= tAutre.getSelectionModel().getSelectedIndex();
                sIdAutre.setText(tAutre.getItems().get(index).getIdentification());
                sDesignation.setText(tAutre.getItems().get(index).getDesignation());
                

            bModifierAutre.setDisable(false);
            bArchiverAutre.setDisable(false);
            bEnregistrerAutre.setDisable(true);
            
            } });
        return row;
        });
    }
     
         
        public void RechercheAutre()
    {
        String vSearch = sSearchAutre.getText();
        ObservableList<Autre> Autres  = FXCollections.observableArrayList();
        try {
             conecter();
            Statement st = c.createStatement();
            String sql ="SELECT * FROM `autremateriel` WHERE `Archiver` = 0 AND IdMateriel LIKE '%"+vSearch+"%' ";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
                Autre Au = new Autre();
                Au.setIdentification(rs.getString(1));
                Au.setDesignation(rs.getString(2));

                Autres.add(Au);     
            }
            tAutre.setItems(Autres);
            cIdAutre.setCellValueFactory(f ->f.getValue().IdentificationProperty());
            cDesignationAutre.setCellValueFactory(f ->f.getValue().DesignationProperty());
           ;
       
          bModifierAutre.setDisable(true);
            bArchiverAutre.setDisable(true);
            bEnregistrerAutre.setDisable(false);
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tAutre.setRowFactory(tv ->{ TableRow<Autre> row = new TableRow<>();
        row.setOnMouseClicked(event ->{
            if(event.getClickCount()==1)
            
            {
                int index= tAutre.getSelectionModel().getSelectedIndex();
                sIdAutre.setText(tAutre.getItems().get(index).getIdentification());
                sDesignation.setText(tAutre.getItems().get(index).getDesignation());
                

            bModifierAutre.setDisable(false);
            bArchiverAutre.setDisable(false);
            bEnregistrerAutre.setDisable(true);
            
            } });
        return row;
        });
    }
     
     public void masque()
    {
        
         
             sIdAutre.textProperty().addListener((observable, oldValue, newValue) -> {
    if (!newValue.matches("([a-zA-Z]|[\\d])*")) {
        sIdAutre.setText(oldValue);
    }   
});
               
           sIdEcran.textProperty().addListener((observable, oldValue, newValue) -> {
    if (!newValue.matches("([a-zA-Z]|[\\d])*")) {
        sIdEcran.setText(oldValue);
    }   
});
           sIdUc.textProperty().addListener((observable, oldValue, newValue) -> {
    if (!newValue.matches("([a-zA-Z]|[\\d])*")) {
        sIdUc.setText(oldValue);
    }   
});
       
           
           
    }
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cType.setItems(options);
        Affichage();
        AffichageUc();
        AffichageAutre();
        pEcran.setVisible(true);
    }    
    
}
