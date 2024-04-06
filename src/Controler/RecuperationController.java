/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import static Config.Connexion.c;
import static Config.Connexion.conecter;
import Model.Fiche;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author CKAEL
 */
public class RecuperationController implements Initializable {

  
      @FXML
    private Pane bEnregistrer;
      
     @FXML
    private ToggleGroup Materiel;

    @FXML
    private FontAwesomeIconView bRefresh;

    @FXML
    private FontAwesomeIconView bSearch;

    @FXML
    private TableColumn<Fiche, String> cDate;

    @FXML
    private TableColumn<Fiche, String> cDesignation;

    @FXML
    private TableColumn<Fiche, String> cDimension;

    @FXML
    private TableColumn<Fiche, String> cDur;

    @FXML
    private TableColumn<Fiche, String> cHeure;

    @FXML
    private TableColumn<Fiche, String> cIdMateriel;

    @FXML
    private TableColumn<Fiche, String> cMarque;

    @FXML
    private TableColumn<Fiche, String> cNom;

    @FXML
    private TableColumn<Fiche, String> cNumeroFiche;

    @FXML
    private TableColumn<Fiche, String> cPrenom;

    @FXML
    private TableColumn<Fiche, String> cProcesseur;

    @FXML
    private TableColumn<Fiche, String> cRam;

    @FXML
    private TableColumn<Fiche, String> cType;

    @FXML
    private CheckBox chkReparer;

    @FXML
    private ComboBox<String> cmbReparateur;

    @FXML
    private RadioButton rAutre;

    @FXML
    private RadioButton rEcran;

    @FXML
    private RadioButton rUniteCentrale;

    @FXML
    private DatePicker sDate;

    @FXML
    private TextField sNomReparateur;

    @FXML
    private TextField sNumFiche;

    @FXML
    private TextField sObservation;

    @FXML
    private TextField sPrenom;
    
    @FXML
    private TextField sNoFiche;
    
    @FXML
    private TextField sIdMateriel;

    @FXML
    private TextField sSolution;

    @FXML
    private TableView<Fiche> tDemande;

    @FXML
    void Actualiser(MouseEvent event) {
           if(Type=="E")
        {
        AffichageEc();
        }
        if(Type=="U")
        {
        AffichageUc();
        }
        if(Type=="A")
        {
        AffichageAutre();
        
        }
    }
    public String Type="U";
    
   public void Enregistrement()
   {
    LocalDate vDate = LocalDate.now();
        LocalDateTime DateHeure = LocalDateTime.now();
        DateTimeFormatter fHeure = DateTimeFormatter.ofPattern("HH:mm:ss");
        String vHeure = DateHeure.format(fHeure);
        String vNumF = sNoFiche.getText();
       String vObservation = sObservation.getText();
       String vSolution = sSolution.getText();
       String vMatRep = cmbReparateur.getValue();
          String vRep= "";
          if(chkReparer.isSelected()) { vRep="Reparer"; } else { vRep ="Irréparable"; }
        Statement st=null;
          try {
          
              st = c.createStatement();
              String sql = "UPDATE Recuperer set observation='"+vObservation+"',solution='"+vSolution+"',MatriculeReparateur='"+vMatRep+"',Reparer = '"+vRep+"',heurerecuperation ='"+vHeure+"',daterecuperation='"+vDate+"' where numerofiche = '"+vNumF+"' ";
              st.executeUpdate(sql);
              
          } catch (SQLException ex) {
              Logger.getLogger(RecuperationController.class.getName()).log(Level.SEVERE, null, ex);
          }
          finally
          {
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(RecuperationController.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
   
   }
    
        @FXML
    void Enregistrer(MouseEvent event)  {
       
        
        if("E".equals(Type))
        {
            
            Enregistrement();
            afficheec();
            AffichageEc();
            sObservation.setText("");
            sSolution.setText("");
            sNomReparateur.setText("");
            sPrenom.setText("");
            cmbReparateur.setValue("");
           
        }
        if("U".equals(Type))
        {
            Enregistrement();
            afficheuc();
            AffichageUc();
             sObservation.setText("");
            sSolution.setText("");
            sNomReparateur.setText("");
            sPrenom.setText("");
            cmbReparateur.setValue("");
            
           
        }
        if("A".equals(Type))
        {
            Enregistrement();
                afficheau();
                AffichageAutre();
                 sObservation.setText("");
            sSolution.setText("");
            sNomReparateur.setText("");
            sPrenom.setText("");
            cmbReparateur.setValue("");
        }
        
      
    }

   
    
 
    @FXML
    void ChoixReparateur(ActionEvent event) {
        String vMatriculeReparateur=cmbReparateur.getValue();
         try {
             conecter();
             Statement st = c.createStatement();
             String  Sql="Select * from reparateur where MatriculeReparateur= '"+vMatriculeReparateur+"';";
             ResultSet rs = st.executeQuery(Sql);
             if(rs.next())
             {
                 sNomReparateur.setText(rs.getString(2));
                 sPrenom.setText(rs.getString(3));
             }
        
         } catch (SQLException ex) {
             Logger.getLogger(RecuperationController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    void ChoixType(ActionEvent event) {
        if(rEcran.isSelected())
        {
            Type="E";
            cDimension.setVisible(true);
            cMarque.setVisible(true);
            cRam.setVisible(false);
            cProcesseur.setVisible(false);
            cDur.setVisible(false);
            cDesignation.setVisible(false);
            AffichageEc();
        }
        if(rAutre.isSelected())
        {
            Type = "A";
            cDesignation.setVisible(true);
            cDimension.setVisible(false);
            cMarque.setVisible(false);
            cRam.setVisible(false);
            cProcesseur.setVisible(false);
            cDur.setVisible(false);
            AffichageAutre();
        }
        if(rUniteCentrale.isSelected())
        {
            Type="U";
            cRam.setVisible(true);
            cProcesseur.setVisible(true);
            cDur.setVisible(true);
            cDesignation.setVisible(false);
             cDimension.setVisible(false);
            cMarque.setVisible(false);
            AffichageUc();
            
        }
    }

    @FXML
    void rechercher(MouseEvent event) {
        if(Type=="E")
        {
          RecEc();
        
        }
        if(Type=="U")
        {
         RecUc();
        }
        if(Type=="A")
        {
       RecAutre();
        
        }

    }

        public void AffichageReparateur()
    {
        ObservableList<String> Reparateurs  = FXCollections.observableArrayList();
        try {
             conecter();
            Statement st = c.createStatement();
            String sql ="SELECT * FROM reparateur";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
               Reparateurs.add(rs.getString(1));
                    
            }        
             cmbReparateur.setItems(Reparateurs); 
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }
        
    
 public void AffichageEc()
    {
        ObservableList<Fiche> FicheEcrans  = FXCollections.observableArrayList();
        try {
             conecter();
            Statement st = c.createStatement();
            String sql ="SELECT * FROM `recuperer` JOIN ecran ON recuperer.IdMateriel=ecran.IdMateriel JOIN viewfiche ON recuperer.NumeroFiche=viewfiche.NumeroFiche WHERE `Observation`='Non';";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
               Fiche Fiches = new Fiche();
               Fiches.setNumeroFiche(rs.getString(8));
               Fiches.setNom(rs.getString("NomPersonel"));
               Fiches.setPrenom(rs.getString("PrenomPersonel"));
               Fiches.setDateDemande(rs.getString("DateDemande"));
               Fiches.setHeureDemande(rs.getString("HeureDemande"));
               Fiches.setIdentification(rs.getString(6));
               Fiches.setMarque(rs.getString("Marque"));
               Fiches.setDimension(rs.getString("Dimension"));
               Fiches.setRam("");
               Fiches.setProcesseur("");
               Fiches.setDisque("");
               Fiches.setDesignation("");
                FicheEcrans.add(Fiches);     
            }
            tDemande.setItems(FicheEcrans);
           
            cNumeroFiche.setCellValueFactory(f ->f.getValue().NumeroFicheProperty());
            cNom.setCellValueFactory(f ->f.getValue().NomProperty());
            cPrenom.setCellValueFactory(f ->f.getValue().PrenomProperty());
            cDate.setCellValueFactory(f ->f.getValue().DateDemandeProperty());
            cHeure.setCellValueFactory(f ->f.getValue().HeureDemandeProperty());
            cIdMateriel.setCellValueFactory(f ->f.getValue().IdentificationProperty());
            cMarque.setCellValueFactory(f ->f.getValue().MarqueProperty());
            cDimension.setCellValueFactory(f ->f.getValue().DimensionProperty());
            cRam.setCellValueFactory(f ->f.getValue().RamProperty());
            cProcesseur.setCellValueFactory(f ->f.getValue().ProcesseurProperty());
            cDur.setCellValueFactory(f ->f.getValue().DisqueProperty());
            cDesignation.setCellValueFactory(f ->f.getValue().DesignationProperty());
          
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tDemande.setRowFactory(tv ->{ TableRow<Fiche> row = new TableRow<>();
        row.setOnMouseClicked((MouseEvent event) ->{
            if(event.getClickCount()==1)
            
            {
                int index= tDemande.getSelectionModel().getSelectedIndex();
                sNoFiche.setText(tDemande.getItems().get(index).getNumeroFiche());
                sIdMateriel.setText(tDemande.getItems().get(index).getIdentification());
                 bEnregistrer.setVisible(true);
            
            
            } });
        return row;
        });
    }
 public void RecEc()
    {
        String vNumF=sNumFiche.getText();
        LocalDate vDate = sDate.getValue();
        ObservableList<Fiche> FicheEcrans  = FXCollections.observableArrayList();
        try {
             conecter();
            Statement st = c.createStatement();
            String sql ="SELECT * FROM `recuperer` JOIN ecran ON recuperer.IdMateriel=ecran.IdMateriel JOIN viewfiche ON recuperer.NumeroFiche=viewfiche.NumeroFiche WHERE `Observation`='Non' AND recuperer.NumeroFiche='"+vNumF+"' OR DateDemande='"+vDate+"' ;";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
               Fiche Fiches = new Fiche();
               Fiches.setNumeroFiche(rs.getString(8));
               Fiches.setNom(rs.getString("NomPersonel"));
               Fiches.setPrenom(rs.getString("PrenomPersonel"));
               Fiches.setDateDemande(rs.getString("DateDemande"));
               Fiches.setHeureDemande(rs.getString("HeureDemande"));
               Fiches.setIdentification(rs.getString(6));
               Fiches.setMarque(rs.getString("Marque"));
               Fiches.setDimension(rs.getString("Dimension"));
               Fiches.setRam("");
               Fiches.setProcesseur("");
               Fiches.setDisque("");
               Fiches.setDesignation("");
                FicheEcrans.add(Fiches);     
            }
            tDemande.setItems(FicheEcrans);
           
            cNumeroFiche.setCellValueFactory(f ->f.getValue().NumeroFicheProperty());
            cNom.setCellValueFactory(f ->f.getValue().NomProperty());
            cPrenom.setCellValueFactory(f ->f.getValue().PrenomProperty());
            cDate.setCellValueFactory(f ->f.getValue().DateDemandeProperty());
            cHeure.setCellValueFactory(f ->f.getValue().HeureDemandeProperty());
            cIdMateriel.setCellValueFactory(f ->f.getValue().IdentificationProperty());
            cMarque.setCellValueFactory(f ->f.getValue().MarqueProperty());
            cDimension.setCellValueFactory(f ->f.getValue().DimensionProperty());
            cRam.setCellValueFactory(f ->f.getValue().RamProperty());
            cProcesseur.setCellValueFactory(f ->f.getValue().ProcesseurProperty());
            cDur.setCellValueFactory(f ->f.getValue().DisqueProperty());
            cDesignation.setCellValueFactory(f ->f.getValue().DesignationProperty());
          
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tDemande.setRowFactory(tv ->{ TableRow<Fiche> row = new TableRow<>();
        row.setOnMouseClicked((MouseEvent event) ->{
            if(event.getClickCount()==1)
            
            {
                int index= tDemande.getSelectionModel().getSelectedIndex();
                sNoFiche.setText(tDemande.getItems().get(index).getNumeroFiche());
                sIdMateriel.setText(tDemande.getItems().get(index).getIdentification());
                 bEnregistrer.setVisible(true);
            
            
            } });
        return row;
        });
    }
  public void AffichageUc()
    {
        ObservableList<Fiche> FicheEcrans  = FXCollections.observableArrayList();
        try {
             conecter();
            Statement st = c.createStatement();
            String sql ="SELECT * FROM `recuperer` JOIN unitecentrale on (unitecentrale.IdMateriel=recuperer.IdMateriel) JOIN viewfiche on (viewfiche.NumeroFiche=recuperer.NumeroFiche) WHERE `Observation`='Non';";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
               Fiche Fiches = new Fiche();
               Fiches.setNumeroFiche(rs.getString(8));
               Fiches.setNom(rs.getString("NomPersonel"));
               Fiches.setPrenom(rs.getString("PrenomPersonel"));
               Fiches.setDateDemande(rs.getString("DateDemande"));
               Fiches.setHeureDemande(rs.getString("HeureDemande"));
               Fiches.setIdentification(rs.getString(6));
               Fiches.setMarque("");
               Fiches.setDimension("");
               Fiches.setRam(rs.getString("Ram"));
               Fiches.setProcesseur(rs.getString("Processeur"));
               Fiches.setDisque(rs.getString("DisqueDur"));
               Fiches.setDesignation("");
                FicheEcrans.add(Fiches);     
            }
            tDemande.setItems(FicheEcrans);
           
            cNumeroFiche.setCellValueFactory(f ->f.getValue().NumeroFicheProperty());
            cNom.setCellValueFactory(f ->f.getValue().NomProperty());
            cPrenom.setCellValueFactory(f ->f.getValue().PrenomProperty());
            cDate.setCellValueFactory(f ->f.getValue().DateDemandeProperty());
            cHeure.setCellValueFactory(f ->f.getValue().HeureDemandeProperty());
            cIdMateriel.setCellValueFactory(f ->f.getValue().IdentificationProperty());
            cMarque.setCellValueFactory(f ->f.getValue().MarqueProperty());
            cDimension.setCellValueFactory(f ->f.getValue().DimensionProperty());
            cRam.setCellValueFactory(f ->f.getValue().RamProperty());
            cProcesseur.setCellValueFactory(f ->f.getValue().ProcesseurProperty());
            cDur.setCellValueFactory(f ->f.getValue().DisqueProperty());
            cDesignation.setCellValueFactory(f ->f.getValue().DesignationProperty());
          
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tDemande.setRowFactory(tv ->{ TableRow<Fiche> row = new TableRow<>();
        row.setOnMouseClicked((MouseEvent event) ->{
            if(event.getClickCount()==1)
            
            {
                int index= tDemande.getSelectionModel().getSelectedIndex();
                sNoFiche.setText(tDemande.getItems().get(index).getNumeroFiche());
                sIdMateriel.setText(tDemande.getItems().get(index).getIdentification());
                 bEnregistrer.setVisible(true);
            
            
            } });
        return row;
        });
    }
        
   public void RecUc()
    {
          String vNumF=sNumFiche.getText();
        LocalDate vDate = sDate.getValue();
        ObservableList<Fiche> FicheEcrans  = FXCollections.observableArrayList();
        try {
             conecter();
            Statement st = c.createStatement();
            String sql ="SELECT * FROM `recuperer` JOIN unitecentrale on (unitecentrale.IdMateriel=recuperer.IdMateriel) JOIN viewfiche on (viewfiche.NumeroFiche=recuperer.NumeroFiche) WHERE `Observation`='Non'AND recuperer.NumeroFiche='"+vNumF+"' OR DateDemande='"+vDate+"';";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
               Fiche Fiches = new Fiche();
               Fiches.setNumeroFiche(rs.getString(8));
               Fiches.setNom(rs.getString("NomPersonel"));
               Fiches.setPrenom(rs.getString("PrenomPersonel"));
               Fiches.setDateDemande(rs.getString("DateDemande"));
               Fiches.setHeureDemande(rs.getString("HeureDemande"));
               Fiches.setIdentification(rs.getString(6));
               Fiches.setMarque("");
               Fiches.setDimension("");
               Fiches.setRam(rs.getString("Ram"));
               Fiches.setProcesseur(rs.getString("Processeur"));
               Fiches.setDisque(rs.getString("DisqueDur"));
               Fiches.setDesignation("");
                FicheEcrans.add(Fiches);     
            }
            tDemande.setItems(FicheEcrans);
           
            cNumeroFiche.setCellValueFactory(f ->f.getValue().NumeroFicheProperty());
            cNom.setCellValueFactory(f ->f.getValue().NomProperty());
            cPrenom.setCellValueFactory(f ->f.getValue().PrenomProperty());
            cDate.setCellValueFactory(f ->f.getValue().DateDemandeProperty());
            cHeure.setCellValueFactory(f ->f.getValue().HeureDemandeProperty());
            cIdMateriel.setCellValueFactory(f ->f.getValue().IdentificationProperty());
            cMarque.setCellValueFactory(f ->f.getValue().MarqueProperty());
            cDimension.setCellValueFactory(f ->f.getValue().DimensionProperty());
            cRam.setCellValueFactory(f ->f.getValue().RamProperty());
            cProcesseur.setCellValueFactory(f ->f.getValue().ProcesseurProperty());
            cDur.setCellValueFactory(f ->f.getValue().DisqueProperty());
            cDesignation.setCellValueFactory(f ->f.getValue().DesignationProperty());
          
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tDemande.setRowFactory(tv ->{ TableRow<Fiche> row = new TableRow<>();
        row.setOnMouseClicked((MouseEvent event) ->{
            if(event.getClickCount()==1)
            
            {
                int index= tDemande.getSelectionModel().getSelectedIndex();
                sNoFiche.setText(tDemande.getItems().get(index).getNumeroFiche());
                sIdMateriel.setText(tDemande.getItems().get(index).getIdentification());
                 bEnregistrer.setVisible(true);
            
            
            } });
        return row;
        });
    }
  public void AffichageAutre()
    {
        ObservableList<Fiche> FicheEcrans  = FXCollections.observableArrayList();
        try {
             conecter();
            Statement st = c.createStatement();
            String sql ="SELECT * FROM `recuperer` JOIN autremateriel on (autremateriel.IdMateriel=recuperer.IdMateriel) JOIN viewfiche on (viewfiche.NumeroFiche=recuperer.NumeroFiche) WHERE `Observation`='Non';";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
               Fiche Fiches = new Fiche();
               Fiches.setNumeroFiche(rs.getString(8));
               Fiches.setNom(rs.getString("NomPersonel"));
               Fiches.setPrenom(rs.getString("PrenomPersonel"));
               Fiches.setDateDemande(rs.getString("DateDemande"));
               Fiches.setHeureDemande(rs.getString("HeureDemande"));
               Fiches.setIdentification(rs.getString(6));
               Fiches.setMarque("");
               Fiches.setDimension("");
               Fiches.setRam("");
               Fiches.setProcesseur("");
               Fiches.setDisque("");
               Fiches.setDesignation(rs.getString("Designation"));
                FicheEcrans.add(Fiches);     
            }
            tDemande.setItems(FicheEcrans);
           
            cNumeroFiche.setCellValueFactory(f ->f.getValue().NumeroFicheProperty());
            cNom.setCellValueFactory(f ->f.getValue().NomProperty());
            cPrenom.setCellValueFactory(f ->f.getValue().PrenomProperty());
            cDate.setCellValueFactory(f ->f.getValue().DateDemandeProperty());
            cHeure.setCellValueFactory(f ->f.getValue().HeureDemandeProperty());
            cIdMateriel.setCellValueFactory(f ->f.getValue().IdentificationProperty());
            cMarque.setCellValueFactory(f ->f.getValue().MarqueProperty());
            cDimension.setCellValueFactory(f ->f.getValue().DimensionProperty());
            cRam.setCellValueFactory(f ->f.getValue().RamProperty());
            cProcesseur.setCellValueFactory(f ->f.getValue().ProcesseurProperty());
            cDur.setCellValueFactory(f ->f.getValue().DisqueProperty());
            cDesignation.setCellValueFactory(f ->f.getValue().DesignationProperty());
          
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tDemande.setRowFactory(tv ->{ TableRow<Fiche> row = new TableRow<>();
        row.setOnMouseClicked((MouseEvent event) ->{
            if(event.getClickCount()==1)
            
            {
                int index= tDemande.getSelectionModel().getSelectedIndex();
                sNoFiche.setText(tDemande.getItems().get(index).getNumeroFiche());
                sIdMateriel.setText(tDemande.getItems().get(index).getIdentification());
                 bEnregistrer.setVisible(true);
            
            
            } });
        return row;
        });
    }
   public void RecAutre()
    {
        String vNumF=sNumFiche.getText();
        LocalDate vDate = sDate.getValue();
        ObservableList<Fiche> FicheEcrans  = FXCollections.observableArrayList();
        try {
             conecter();
            Statement st = c.createStatement();
            String sql ="SELECT * FROM `recuperer` JOIN autremateriel on (autremateriel.IdMateriel=recuperer.IdMateriel) JOIN viewfiche on (viewfiche.NumeroFiche=recuperer.NumeroFiche) WHERE `Observation`='Non'AND recuperer.NumeroFiche='"+vNumF+"' OR DateDemande='"+vDate+"';";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
               Fiche Fiches = new Fiche();
               Fiches.setNumeroFiche(rs.getString(8));
               Fiches.setNom(rs.getString("NomPersonel"));
               Fiches.setPrenom(rs.getString("PrenomPersonel"));
               Fiches.setDateDemande(rs.getString("DateDemande"));
               Fiches.setHeureDemande(rs.getString("HeureDemande"));
               Fiches.setIdentification(rs.getString(6));
               Fiches.setMarque("");
               Fiches.setDimension("");
               Fiches.setRam("");
               Fiches.setProcesseur("");
               Fiches.setDisque("");
               Fiches.setDesignation(rs.getString("Designation"));
                FicheEcrans.add(Fiches);     
            }
            tDemande.setItems(FicheEcrans);
           
            cNumeroFiche.setCellValueFactory(f ->f.getValue().NumeroFicheProperty());
            cNom.setCellValueFactory(f ->f.getValue().NomProperty());
            cPrenom.setCellValueFactory(f ->f.getValue().PrenomProperty());
            cDate.setCellValueFactory(f ->f.getValue().DateDemandeProperty());
            cHeure.setCellValueFactory(f ->f.getValue().HeureDemandeProperty());
            cIdMateriel.setCellValueFactory(f ->f.getValue().IdentificationProperty());
            cMarque.setCellValueFactory(f ->f.getValue().MarqueProperty());
            cDimension.setCellValueFactory(f ->f.getValue().DimensionProperty());
            cRam.setCellValueFactory(f ->f.getValue().RamProperty());
            cProcesseur.setCellValueFactory(f ->f.getValue().ProcesseurProperty());
            cDur.setCellValueFactory(f ->f.getValue().DisqueProperty());
            cDesignation.setCellValueFactory(f ->f.getValue().DesignationProperty());
          
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tDemande.setRowFactory(tv ->{ TableRow<Fiche> row = new TableRow<>();
        row.setOnMouseClicked((MouseEvent event) ->{
            if(event.getClickCount()==1)
            
            {
                int index= tDemande.getSelectionModel().getSelectedIndex();
                sNoFiche.setText(tDemande.getItems().get(index).getNumeroFiche());
                sIdMateriel.setText(tDemande.getItems().get(index).getIdentification());
                 bEnregistrer.setVisible(true);
            
            
            } });
        return row;
        });
    }
  
       public void masque()
    {
         sNumFiche.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                sNumFiche.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
         sNoFiche.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                sNoFiche.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
         
    }
       
       public void  afficheec()
       {
        Statement st = null;
        String vNumF = sNoFiche.getText();
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("p1", vNumF);
        try {
                conecter();
                st = c.createStatement();
                String sql = "SELECT * FROM recuperer join viewfiche using(NumeroFiche) join ecran using(IdMateriel) join reparateur using(MatriculeReparateur) where NumeroFiche='"+vNumF+"'";
                ResultSet rs = st.executeQuery(sql);
                while(rs.next())
                {
                    String vNom=rs.getString("NomPersonel")+"\t"+rs.getString("PrenomPersonel");
                    String vNomRep=rs.getString("NomReparateur")+"\t"+rs.getString("PrenomReparateur");
                    String vDimension = rs.getString("Dimension");
                    String vMarque = rs.getString("Marque");
                    String vObservation = rs.getString(1); 
                    
                    parameters.put("DateDepot",rs.getString("DateDemande"));
                    parameters.put("HeureDepot",rs.getString("HeureDemande"));
                    parameters.put("Service",rs.getString("Service"));
                    parameters.put("Nom",vNom);
                    parameters.put("Porte",rs.getString("NumPorte"));
                    parameters.put("Telephone",rs.getString("Telephone"));
                    parameters.put("Dimension",vDimension);
                    parameters.put("Marque",vMarque);
                    parameters.put("Observation",vObservation);
                    parameters.put("Solution",rs.getString("Solution"));
                    parameters.put("NomRep",vNomRep);
                    parameters.put("DateRecu",rs.getString("DateRecuperation"));
                    parameters.put("HeureRecu",rs.getString("HeureRecuperation"));
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(RecuperationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally
            {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RecuperationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
              try {
               
              JasperDesign jd =JRXmlLoader.load("C:\\Users\\CKAEL\\Documents\\NetBeansProjects\\GMaintenance\\src\\Etat\\EtatEcran.jrxml");
              JasperReport ireport = JasperCompileManager.compileReport(jd);
              JasperPrint jasperPrint = JasperFillManager.fillReport(ireport,parameters,new JREmptyDataSource());         
              JasperViewer.viewReport(jasperPrint,false);
          } catch (JRException ex) {
              
              Logger.getLogger(RecuperationController.class.getName()).log(Level.SEVERE, null, ex);
          }
       
       
       }
       public void afficheuc()
       {
           Statement st = null;
        String vNumF = sNoFiche.getText();
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("p1", vNumF);
        try {
                conecter();
                st = c.createStatement();
                String sql = "SELECT * FROM recuperer join viewfiche using(NumeroFiche) join unitecentrale using(IdMateriel) join reparateur using(MatriculeReparateur) where NumeroFiche='"+vNumF+"'";
                ResultSet rs = st.executeQuery(sql);
                while(rs.next())
                {
                    String vNom=rs.getString("NomPersonel")+"\t"+rs.getString("PrenomPersonel");
                    String vNomRep=rs.getString("NomReparateur")+"\t"+rs.getString("PrenomReparateur");
                    
                    parameters.put("DateDepot",rs.getString("DateDemande"));
                    parameters.put("HeureDepot",rs.getString("HeureDemande"));
                    parameters.put("Service",rs.getString("Service"));
                    parameters.put("Nom",vNom);
                    parameters.put("Porte",rs.getString("NumPorte"));
                    parameters.put("Telephone",rs.getString("Telephone"));
                    parameters.put("Ram",rs.getString("Ram"));
                    parameters.put("DisqueDur",rs.getString("DisqueDur"));
                    parameters.put("Cpu",rs.getString("Processeur"));
                    parameters.put("Observation",rs.getString("Observation"));
                    parameters.put("Solution",rs.getString("Solution"));
                    parameters.put("NomRep",vNomRep);
                    parameters.put("DateRecu",rs.getString("DateRecuperation"));
                    parameters.put("HeureRecu",rs.getString("HeureRecuperation"));
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(RecuperationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally
            {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RecuperationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
              try {
               
              JasperDesign jd =JRXmlLoader.load("C:\\Users\\CKAEL\\Documents\\NetBeansProjects\\GMaintenance\\src\\Etat\\EtatUc.jrxml");
              JasperReport ireport = JasperCompileManager.compileReport(jd);
              JasperPrint jasperPrint = JasperFillManager.fillReport(ireport,parameters,new JREmptyDataSource());         
              JasperViewer.viewReport(jasperPrint,false);
          } catch (JRException ex) {
              
              Logger.getLogger(RecuperationController.class.getName()).log(Level.SEVERE, null, ex);
          }
       
       }
       public void afficheau()
       {
               Statement st = null;
        String vNumF = sNoFiche.getText();
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("p1", vNumF);
       
         try {
                conecter();
                st = c.createStatement();
                String sql = "SELECT * FROM recuperer join viewfiche using(NumeroFiche) join autremateriel using(IdMateriel) join reparateur using(MatriculeReparateur) where NumeroFiche='"+vNumF+"'";
                ResultSet rs = st.executeQuery(sql);
                while(rs.next())
                {
                    String vNom=rs.getString("NomPersonel")+"\t"+rs.getString("PrenomPersonel");
                    String vNomRep=rs.getString("NomReparateur")+"\t"+rs.getString("PrenomReparateur");
                    
                    parameters.put("DateDepot",rs.getString("DateDemande"));
                    parameters.put("HeureDepot",rs.getString("HeureDemande"));
                    parameters.put("Service",rs.getString("Service"));
                    parameters.put("Nom",vNom);
                    parameters.put("Porte",rs.getString("NumPorte"));
                    parameters.put("Telephone",rs.getString("Telephone"));
                    parameters.put("Designation",rs.getString("Designation"));
                    parameters.put("Observation",rs.getString("Observation"));
                    parameters.put("Solution",rs.getString("Solution"));
                    parameters.put("NomRep",vNomRep);
                    parameters.put("DateRecu",rs.getString("DateRecuperation"));
                    parameters.put("HeureRecu",rs.getString("HeureRecuperation"));
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(RecuperationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally
            {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RecuperationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
              try {
               
              JasperDesign jd =JRXmlLoader.load("C:\\Users\\CKAEL\\Documents\\NetBeansProjects\\GMaintenance\\src\\Etat\\EtatAutre.jrxml");
              JasperReport ireport = JasperCompileManager.compileReport(jd);
              JasperPrint jasperPrint = JasperFillManager.fillReport(ireport,parameters,new JREmptyDataSource());         
              JasperViewer.viewReport(jasperPrint,false);
          } catch (JRException ex) {
              
              Logger.getLogger(RecuperationController.class.getName()).log(Level.SEVERE, null, ex);
          }
        
       }
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AffichageReparateur();
        AffichageUc();
        masque();
        // TODO
    }    
    
}
