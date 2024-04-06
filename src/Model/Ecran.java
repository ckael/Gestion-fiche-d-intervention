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
public class Ecran {
    private final StringProperty Identification ;
    private final StringProperty Marque ;
    private final StringProperty Dimension;
  
 

    public Ecran() {
    Identification = new SimpleStringProperty(this,"Identification");
    Marque = new SimpleStringProperty(this,"Marque");
    Dimension = new SimpleStringProperty(this,"Dimension");
    
    }
    public StringProperty IdentificationProperty() { return Identification ;}
    public String getIdentification() { return Identification.get(); }
    public void setIdentification(String vIdentification) { Identification.set(vIdentification); } 
    
    public StringProperty MarqueProperty() { return Marque ;}
    public String getMarque() { return Marque.get(); }
    public void setMarque(String vMarque) { Marque.set(vMarque); } 
    
    public StringProperty DimensionProperty() { return Dimension ;}
    public String getDimension() { return Dimension.get(); }
    public void setDimension(String vDimension) { Dimension.set(vDimension); } 
    
  
            
   
     public static void Inserer(String Identification,String Marque ,String Dimension) throws SQLException
    {
    Connexion.conecter();
     Statement st = c.createStatement();
            String sql,sql1;
            sql1= "INSERT INTO materiel (IdMateriel,Type) values ('"+Identification+"','Ecran')";
            sql = "INSERT INTO `ecran` (`IdMateriel`, `Marque`, `Dimension`) VALUES ( '"+Identification+"', '"+Marque+"', '"+Dimension+"');";
            st.executeUpdate(sql1);
            st.executeUpdate(sql);
            
    }
     public static void Modifier(String Identification,String Marque ,String Dimension) throws SQLException
    {
    Connexion.conecter();
     Statement st = c.createStatement();
            String sql;
            sql = "UPDATE `ecran` SET `Marque` = '"+Marque+"', `Dimension` = '"+Dimension+"' WHERE `ecran`.`IdMateriel` = '"+Identification+"';";
            st.executeUpdate(sql);
         
    }
     
     public static void Suprimer(String Identification) throws SQLException 
     {
         Connexion.conecter();
         Statement st=c.createStatement();
         String Sql;
         Sql = "Delete from materiel where IdMateriel='"+Identification+"';";
         st.executeUpdate(Sql);
     }
     
      public static void Archiver(String Identification) throws SQLException 
     {
         Connexion.conecter();
         Statement st=c.createStatement();
         String Sql;
         Sql = "UPDATE ecran SET Archiver=1 where IdMateriel='"+Identification+"';";
         st.executeUpdate(Sql);
     }
   
     
}
     
