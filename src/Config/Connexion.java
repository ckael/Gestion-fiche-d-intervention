/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CKAEL
 */
public class Connexion {
    public static java.sql.Connection c =null;
    
    public static void conecter() throws SQLException{
        try {
            //Connection c =
            Class.forName("org.sqlite.JDBC");
            c= DriverManager.getConnection("jdbc:sqlite:src\\db\\gestionticket.db");
            if (c == null){
                System.out.println("connexion erreur");
            }
            else{
                System.out.println("connexion reussi ...");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
                Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
   
}
