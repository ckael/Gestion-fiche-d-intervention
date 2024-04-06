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
public class Personel {
    private final StringProperty Matricule ;
    private final StringProperty Nom ;
    private final StringProperty Prenom ;
    private final StringProperty Telephone ;
 

    public Personel() {
    Matricule = new SimpleStringProperty(this,"Matricule");
    Nom = new SimpleStringProperty(this,"Nom");
    Prenom = new SimpleStringProperty(this,"Prenom");
    Telephone = new SimpleStringProperty(this,"Telephone" );
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
    
    public StringProperty TelephoneProperty() { return Telephone ;}
    public String getTelephone() { return Telephone.get(); }
    public void setTelephone(String vTelephone) { Telephone.set(vTelephone); } 
            
   
     public static void Inserer(String Nom,String Prenom ,String Telephone) throws SQLException
    {
    Connexion.conecter();
     Statement st = c.createStatement();
            String sql;
            sql = "INSERT INTO `personel` (`MatriculePersonel`, `NomPersonel`, `PrenomPersonel`, `Telephone`) VALUES (NULL, '"+Nom+"', '"+Prenom+"', '"+Telephone+"');";
            st.executeUpdate(sql);
         
    }
     public static void Modifier(String Code,String Nom,String Prenom ,String Telephone) throws SQLException
    {
    Connexion.conecter();
     Statement st = c.createStatement();
            String sql;
            sql = "UPDATE `personel` SET `NomPersonel` = '"+Nom+"', `PrenomPersonel` = '"+Prenom+"', `Telephone` = '"+Telephone+"' WHERE `personel`.`MatriculePersonel` = '"+Code+"';";
            st.executeUpdate(sql);
         
    }
     
     public static void Suprimer(String Code) throws SQLException 
     {
         Connexion.conecter();
         Statement st=c.createStatement();
         String Sql;
         Sql = "Delete from personel where MatriculePersonel='"+Code+"';";
         st.executeUpdate(Sql);
     }
   
     
}
     
