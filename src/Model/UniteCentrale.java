/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Config.Connexion;
import static Config.Connexion.c;
import static Config.Connexion.conecter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author CKAEL
 */
public class UniteCentrale {
    private final StringProperty IdUc ;
    private final StringProperty Ram ;
    private final StringProperty Processeur ;
    private final StringProperty Disque ;
 

    public UniteCentrale() {
    IdUc = new SimpleStringProperty(this,"IdUc");
    Ram = new SimpleStringProperty(this,"Ram");
    Processeur = new SimpleStringProperty(this,"Processeur");
    Disque = new SimpleStringProperty(this,"Disque" );
    }
    public StringProperty IdUcProperty() { return IdUc ;}
    public String getIdUc() { return IdUc.get(); }
    public void setIdUc(String vIdUc) { IdUc.set(vIdUc); } 
    
    public StringProperty RamProperty() { return Ram ;}
    public String getRam() { return Ram.get(); }
    public void setRam(String vRam) { Ram.set(vRam); } 
    
    public StringProperty ProcesseurProperty() { return Processeur ;}
    public String getProcesseur() { return Processeur.get(); }
    public void setProcesseur(String vProcesseur) { Processeur.set(vProcesseur); } 
    
    public StringProperty DisqueProperty() { return Disque ;}
    public String getDisque() { return Disque.get(); }
    public void setDisque(String vDisque) { Disque.set(vDisque); } 
            

     public static void Inserer(String IdMateriel,String Ram ,String Processeur,String Disque) 
    {
           Statement st =null;
        try {
            Connexion.conecter();
               st = c.createStatement();
            String sql,sql1;
            sql1= "INSERT INTO materiel (IdMateriel,Type) values ('"+IdMateriel+"','Unité Centrale')";
            sql = "INSERT INTO `unitecentrale` (`IdMateriel`, `Ram`, `Processeur`, `DisqueDur`, `Archiver`) VALUES ('"+IdMateriel+"', '"+Ram+"', '"+Processeur+"', '"+Disque+"', '0');";
            st.executeUpdate(sql1);
            st.executeUpdate(sql);
                 
  
        } catch (SQLException ex) {
            Logger.getLogger(UniteCentrale.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
        
               try {
                   st.close();
               } catch (SQLException ex) {
                   Logger.getLogger(UniteCentrale.class.getName()).log(Level.SEVERE, null, ex);
               }
        }
         
    }
     public static void Modifier(String IdMateriel,String Ram ,String Processeur,String Disque) 
    {
        Statement st= null;
        try {
            Connexion.conecter();
              st = c.createStatement();
            String sql;
            sql = "UPDATE `unitecentrale` SET `Ram` = '"+Ram+" ', `Processeur` = '"+Processeur+"', `DisqueDur` = '"+Disque+"' WHERE `unitecentrale`.`IdMateriel` = '"+IdMateriel+"';";
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UniteCentrale.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(UniteCentrale.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
         
    }
     
     public static void Suprimer(String Code) 
     {
         Statement st= null;
        try {
            Connexion.conecter();
               st=c.createStatement();
         String Sql;
         Sql = "Delete from materiel where IdMateriel='"+Code+"';";
         st.executeUpdate(Sql);
        } catch (SQLException ex) {
            Logger.getLogger(UniteCentrale.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
             try {
                 st.close();
             } catch (SQLException ex) {
                 Logger.getLogger(UniteCentrale.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
      
     }
   
         public static void Archiver(String Identification) 
     {
         Statement st= null;
        try {
            Connexion.conecter();
                st=c.createStatement();
         String Sql;
         Sql = "UPDATE `unitecentrale` SET Archiver=1 where IdMateriel='"+Identification+"';";
         st.executeUpdate(Sql);
        } catch (SQLException ex) {
            Logger.getLogger(UniteCentrale.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
             try {
                 st.close();
             } catch (SQLException ex) {
                 Logger.getLogger(UniteCentrale.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
      
     }
     
}
     
