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
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author CKAEL
 */
public class Reparateur {
    private final StringProperty Matricule ;
    private final StringProperty Nom ;
    private final StringProperty Prenom ;
    
 

    public Reparateur() {
    Matricule = new SimpleStringProperty(this,"Matricule");
    Nom = new SimpleStringProperty(this,"Nom");
    Prenom = new SimpleStringProperty(this,"Prenom");
  
    }
    public StringProperty MatriculeProperty() { return Matricule ;}
    public String getMatricule() { return Matricule.get(); }
    public void setMatricule(String vMatricule) { Matricule.set(vMatricule); } 
    
    public StringProperty NomProperty() { return Nom ;}
    public String getNom() { return Nom.get(); }
    public void setNom(String vNom) { Nom.set(vNom); } 
    
    public StringProperty PrenomProperty() { return Prenom ;}
    public String getPrenom() { return Prenom.get(); }
    public void setPrenom(String vPrenom) { Prenom.set(vPrenom); } 
    

            
   
     public static void Inserer(String Nom,String Prenom ) throws SQLException
    {
    Connexion.conecter();
     Statement st = c.createStatement();
            String sql;
            sql = "INSERT INTO `reparateur` (`MatriculeReparateur`, `NomReparateur`, `PrenomReparateur`) VALUES (NULL, '"+Nom+"', '"+Prenom+"');";
            st.executeUpdate(sql);
         
    }
     public static void Modifier(String Code,String Nom,String Prenom ) throws SQLException
    {
    Connexion.conecter();
     Statement st = c.createStatement();
            String sql;
            sql = "UPDATE `reparateur` SET `NomReparateur` = '"+Nom+"', `PrenomReparateur` = '"+Prenom+"'  WHERE `reparateur`.`MatriculeReparateur` = '"+Code+"';";
            st.executeUpdate(sql);
         
    }
     
     public static void Suprimer(String Code) throws SQLException 
     {
         Connexion.conecter();
         Statement st=c.createStatement();
         String Sql;
         Sql = "Delete from reparateur where MatriculeReparateur='"+Code+"';";
         st.executeUpdate(Sql);
     }
   
     
}
     
