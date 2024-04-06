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
public class Service {
    private final StringProperty CodeService ;
    private final StringProperty Service ;
    
    public  Service() {
    CodeService = new SimpleStringProperty(this,"CodeService");
    Service = new SimpleStringProperty(this,"Service");
    }
    public StringProperty CodeServiceProperty() { return CodeService ;}
    public String getCodeService() { return CodeService.get(); }
    public void setCodeService(String vCodeService) { CodeService.set(vCodeService); } 
    public StringProperty ServiceProperty() { return Service ;}
    public String getService() { return Service.get(); }
    public void setService(String vService) { Service.set(vService); } 
  
    
    
   public static void Inserer(String Code,String Service) throws SQLException
    {
    Connexion.conecter();
     Statement st = c.createStatement();
            String sql;
            sql = "INSERT INTO `service` (`CodeService`, `Service`) VALUES ('"+Code+"', '"+Service+"');";
            st.executeUpdate(sql);
         
    }
     public static void Modifier(String Code,String Service) throws SQLException
    {
    Connexion.conecter();
     Statement st = c.createStatement();
            String sql;
            sql = "UPDATE `service` SET `Service` = '"+Service+"' WHERE `service`.`CodeService` = '"+Code+"';";
            st.executeUpdate(sql);
         
    }
     
     public static void Suprimer(String Code) throws SQLException 
     {
         Connexion.conecter();
         Statement st=c.createStatement();
         String Sql;
         Sql = "Delete from service where CodeService='"+Code+"'";
         st.executeUpdate(Sql);
     }
    
}

