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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;
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
public class DepotController implements Initializable {
    
        @FXML
    private Label lPtes;
  
     @FXML
    private ToggleGroup Mat;

    @FXML
    private Label lType;

   

    @FXML
    private RadioButton rAutre;

    @FXML
    private RadioButton rEcran;

    @FXML
    private RadioButton rUniteCentrale;
    
        @FXML
    void IDENTIFICATION(ActionEvent event) {
           System.out.println("Mety");
          String vID=CmbIdentification.getValue();
          
       Statement st = null;
       if(vID.startsWith("E"))
       {
        String vMarque ="";
        String vDimension="";
        
            try {
                
                conecter();
                st = c.createStatement();
                String sql = "SELECT * FROM ecran where IdMateriel= '"+vID+"'";
                ResultSet rs =st.executeQuery(sql);
                if(rs.next())
                {
        
                   vDimension = rs.getString(3);
                    vMarque = rs.getString(2);
                 
                    st.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(EcranController.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally
            {
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(DepotController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
      
              lPtes.setText("Identification:\t"+vID+"\n\n Marque:\t"+vMarque+"\n\n Dimension:\t"+vDimension);
         System.out.println("Ecran");
         
       }
       if(vID.startsWith("U"))
       {
           String Cpu="",Ram="",Dur ="";
           try {
               conecter();
              st = c.createStatement();
               String sql = "SELECT * FROM unitecentrale where IdMateriel = '"+vID+"'";
               ResultSet rs = st.executeQuery(sql);
               if(rs.next())
               
               {
                   Cpu=rs.getString(3);
                   Ram=rs.getString(2);
                   Dur=rs.getString(4);
                   st.close();
               }
           } catch (SQLException ex) {
               Logger.getLogger(UniteCentraleController.class.getName()).log(Level.SEVERE, null, ex);
           }
           finally
           {
               try {
                   st.close();
               } catch (SQLException ex) {
                   Logger.getLogger(DepotController.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
              lPtes.setText("Identification:\t"+vID+"\n\n Processeur:\t"+Cpu+"\n\n RAM:\t"+Ram+"\n\n DisqueDur:\t"+Dur);
       }
       if(vID.startsWith("A"))
       {
           String Designation ="";
              try {
               conecter();
               st = c.createStatement();
               String sql = "SELECT * FROM autremateriel where IdMateriel = '"+vID+"'";
               ResultSet rs = st.executeQuery(sql);
               if(rs.next())
               {
               Designation=rs.getString(2);
               st.close();
               }
           } catch (SQLException ex) {
               Logger.getLogger(AutreController.class.getName()).log(Level.SEVERE, null, ex);
           }
              finally
              {
               try {
                   st.close();
               } catch (SQLException ex) {
                   Logger.getLogger(DepotController.class.getName()).log(Level.SEVERE, null, ex);
               }
              }
       
                     lPtes.setText("Identification:\t"+vID+"\n\n\n Processeur:\t"+Designation);

       }

    }

    
      

    @FXML
    void ChoixType(ActionEvent event) throws IOException {

        if(rAutre.isSelected())
        {       
        lType.setText("Autre");
        AffichageAutre();
          lPtes.setText("Identification:\t\n\n\n Processeur:\t");
        }
        if(rEcran.isSelected())
        {
            lType.setText("Ecran");
            AffichageEcran();
             lPtes.setText("Identification:\t\n\n Marque:\t\n\n Dimension:\t");
            
        }
        if(rUniteCentrale.isSelected())
        {      
            lType.setText("Unité centrale");
            AffichageUc();
             lPtes.setText("Identification:\t\n\n Processeur:\t\n\n RAM:\t\n\n DisqueDur:\t");
        }
    }
    @FXML
    private Pane bValider;
    
    public void alert(String texte)
{
   
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Valeur invalide");
            alert.setContentText(texte);
             alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();  
}
      @FXML
    void Valider(MouseEvent event) {
        String vNumP = sPorte.getValue();
        String vMatricule = sMatricule.getValue();
        String vIdMateriel = CmbIdentification.getValue();
        String vNumF="";
        LocalDate vDate = LocalDate.now();
        LocalDateTime DateHeure = LocalDateTime.now();
        DateTimeFormatter fHeure = DateTimeFormatter.ofPattern("HH:mm:ss");
        String vHeure = DateHeure.format(fHeure);
        Statement st=null;
        if(vNumP.isEmpty())
        {
            alert("Veuillez choisir la numéro du porte");
        }
        else
        {
            if(vMatricule.isEmpty())
            {
             alert("Veuillez choisir le demandeur ");
            }
            else
            {
            if(vIdMateriel.isEmpty())
            {
            alert("Veuillez choisir la matériel");
            }
            else{
              Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Enregistrement depot");
            alert.setContentText(" Voulez vous vraiment enregistrer la depot\t");
             alert.initStyle(StageStyle.UNDECORATED);
            alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
            alert.showAndWait();
            
            if(alert.getResult()==ButtonType.OK)
            {
            try {
                try {
                    conecter();
                    st = c.createStatement();
                    String sql = "INSERT INTO `fichedemande` (`NumeroFiche`, `DateDemande`, `HeureDemande`, `NumPorte`, `MatriculePersonel`) VALUES (NULL, '"+vDate+"', '"+vHeure+"', '"+vNumP+"', '"+vMatricule+"');";
                    st.executeUpdate(sql);
                    st.close();
                    String sql2 ="SELECT COUNT(`NumeroFiche`) FROM fichedemande;";
                    ResultSet rs = st.executeQuery(sql2);
                    if(rs.next()){
                        vNumF=rs.getString(1);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DepotController.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally
                {
                st.close();
                }
                   
                String sql1 = "INSERT INTO `recuperer` (`Observation`, `Solution`, `DateRecuperation`, `HeureRecuperation`, `Reparer`, `IdMateriel`, `MatriculeReparateur`, `NumeroFiche`) VALUES ('Non', NULL, NULL, NULL, NULL, '"+vIdMateriel+"', NULL, '"+vNumF+"');";
                st.executeUpdate(sql1);
               
               
                lPtes.setText("Les proprietes de la materiele choisi s'affichent ici");
                alert.close();             
                
            } catch (SQLException ex) {
                Logger.getLogger(DepotController.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally
            {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DepotController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
              affiche(vNumF);
               sNom.setText("");
                sPrenom.setText("");
                sService.setText("");
                CmbIdentification.setValue("");
                sPorte.setValue("");
                sMatricule.setValue("");
            }
            if(alert.getResult()==ButtonType.CLOSE)
            {
            alert.close();
            }
            }
            }
        }
          
            
           
    }   
    
    
       @FXML
    private ComboBox<String> sMatricule;

    @FXML
    private TextField sNom;

    @FXML
    private ComboBox<String> sPorte;
    
    
    @FXML
    private ComboBox<String> CmbIdentification;

    @FXML
    private TextField sPrenom;

    @FXML
    private TextField sService;

     public void affiche(String vNumF)
       {
           String IdMateriel = CmbIdentification.getValue();
           Statement st = null;
      
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("IdMateriel", IdMateriel);
        parameters.put("Nom", sNom.getText());
        parameters.put("Prenom", sPrenom.getText());
        parameters.put("Porte", sPorte.getValue());
        parameters.put("Direction", sService.getText());
        parameters.put("Numerofiche", vNumF);
        try {
                conecter();
                st = c.createStatement();
                String sql = "SELECT * FROM materiel where IdMateriel = '"+IdMateriel+"'";
                ResultSet rs = st.executeQuery(sql);
                while(rs.next())
                {
                   
                    
                    parameters.put("NumSerie",rs.getString("NumSerie"));
                    parameters.put("Type",rs.getString("Type"));
                   
                    
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
               
              JasperDesign jd =JRXmlLoader.load("C:\\Users\\CKAEL\\Documents\\NetBeansProjects\\GMaintenance\\src\\Etat\\Recu.jrxml");
              JasperReport ireport = JasperCompileManager.compileReport(jd);
              JasperPrint jasperPrint = JasperFillManager.fillReport(ireport,parameters,new JREmptyDataSource());         
              JasperViewer.viewReport(jasperPrint,false);
          } catch (JRException ex) {
              
              Logger.getLogger(RecuperationController.class.getName()).log(Level.SEVERE, null, ex);
          }
       
       }
     
     
    @FXML
    void ChoixPersonel(ActionEvent event)  {
        String vMatricule = sMatricule.getValue();
        Statement st=null;
            try {
                conecter();
                  st = c.createStatement();
        String sql ="SELECT * FROM personel where MatriculePersonel = '"+vMatricule+"'";
        ResultSet rs = st.executeQuery(sql);
       if(rs.next())
       {
           sNom.setText(rs.getString(2));
           sPrenom.setText(rs.getString(3));
        
       }
            } catch (SQLException ex) {
                Logger.getLogger(DepotController.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally
            {
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(DepotController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
       
    }

    @FXML
    void ChoixPorte(ActionEvent event)  {
    String    VNumP = sPorte.getValue();
    Statement st=null;
            try {
                conecter();
                  st = c.createStatement();
                String sql="SELECT * FROM viewporte where NumPorte='"+VNumP+"'";
                ResultSet rs =st.executeQuery(sql);
                if(rs.next())
                {
                    sService.setText(rs.getString(5));
                }
            } catch (SQLException ex) {
                Logger.getLogger(DepotController.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally
            {
        try {
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DepotController.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
      
    }

         public void AffichagePorte()
    {
         Statement st=null;
        ObservableList<String> Portes  = FXCollections.observableArrayList();
        try {
             conecter();
            st = c.createStatement();
            String sql ="SELECT * FROM porte where archiver =0";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
               Portes.add(rs.getString(1));
                    
            }        
             sPorte.setItems(Portes); 
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
             try {
                 st.close();
             } catch (SQLException ex) {
                 Logger.getLogger(DepotController.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
           
    }
        
                public void AffichagePersonel()
    {
        Statement st = null;
        ObservableList<String> Personels  = FXCollections.observableArrayList();
        try {
             conecter();
             st = c.createStatement();
            String sql ="SELECT * FROM personel";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
               Personels.add(rs.getString(1));
                    
            }        
             sMatricule.setItems(Personels); 
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(DepotController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
           
    }
                
      public void AffichageEcran()
    {
        Statement st = null;
        ObservableList<String> Ecrans  = FXCollections.observableArrayList();
        try {
             conecter();
            st = c.createStatement();
            String sql ="SELECT * FROM ecran where archiver=0";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
               Ecrans.add(rs.getString(1));
                    
            }        
             CmbIdentification.setItems(Ecrans); 
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(DepotController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
           
    }
      
         public void AffichageAutre()
    {
        Statement st = null;
        ObservableList<String> Autres  = FXCollections.observableArrayList();
        try {
             conecter();
            st = c.createStatement();
            String sql ="SELECT * FROM autremateriel WHERE Archiver =  '0'";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
               Autres.add(rs.getString(1));
                    
            }        
             CmbIdentification.setItems(Autres);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(DepotController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
           
    }
         
            public void AffichageUc()
    {
        Statement st=null;
        ObservableList<String> UCS  = FXCollections.observableArrayList();
        try {
             conecter();
            st = c.createStatement();
            String sql ="SELECT * FROM unitecentrale where archiver=0";
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
               UCS.add(rs.getString(1));
                    
            }        
             CmbIdentification.setItems(UCS);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(DepotController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
           
    }
    
    
 
   
   
  
   
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AffichagePorte();
        AffichagePersonel();
   
    }    
    
}
