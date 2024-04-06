/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Config.Connexion;
import static Config.Connexion.c;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author CKAEL
 */
public class Autre {
    private final StringProperty Identification;
    private final StringProperty Designation;
    
    public Autre()
    {
        Identification = new SimpleStringProperty(this,"Identification");
        Designation = new SimpleStringProperty(this,"Designation");        
        
    }
    public StringProperty IdentificationProperty() { return Identification ;}
    public String getIdentification() { return Identification.get(); }
    public void setIdentification(String vIdentification) { Identification.set(vIdentification); }
    
    public StringProperty DesignationProperty() {return Designation;}
    public String getDesignation() {return Designation.get(); }
    public void setDesignation(String vDesignation){Designation.set(vDesignation);}
    
    public static void Inserer(String Id,String Designation ) throws SQLException
    {
        Connexion.conecter();
        Statement st = c.createStatement();
        String sql1 ="INSERT INTO materiel (IdMateriel,Type) values ('"+Id+"','Autre')";
        String sql ="INSERT INTO autremateriel (IdMateriel,Designation,Archiver) values ('"+Id+"','"+Designation+"', 0)";
        st.execute(sql1);
        st.execute(sql);
    }
    
    public static void Modifier(String Id,String Designation) throws SQLException
    {
        Connexion.conecter();
        Statement st = c.createStatement();
        String sql="UPDATE autremateriel SET Designation='"+Designation+"' WHERE IdMateriel ='"+Id+"'";
        st.execute(sql);
    }
    
    public static void Archiver(String Id) throws SQLException
    {
    Connexion.conecter();
    Statement st = c.createStatement();
    String sql="UPDATE autremateriel SET Archiver = 1 WHERE IdMateriel ='"+Id+"' ";
    st.execute(sql);
     }
    
    public static void Suprimer(String Id) throws SQLException
    {
    Connexion.conecter();
    Statement st = c.createStatement();
    String sql="DELETE FROM autremateriel WHERE IdMateriel='"+Id+"'";
    st.execute(sql);
    }
}
