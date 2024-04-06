/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author CKAEL
 */
public class Porte {
   private final StringProperty NumPorte ;
   private final StringProperty Poste;
   private final StringProperty CodeService ;
   private final StringProperty Service ;
   
  public Porte()
  {
    NumPorte = new SimpleStringProperty(this,"NumPorte");
    Poste = new SimpleStringProperty(this,"Poste");
    CodeService = new SimpleStringProperty(this,"CodeService");
    Service = new SimpleStringProperty(this,"Service");
  }
    public StringProperty NumPorteProperty(){return NumPorte;}
    public String getNumPorte(){return NumPorte.get();}
    public void setNumPorte(String vNumPorte) {NumPorte.set(vNumPorte); }
    
    public StringProperty PosteProperty(){return Poste;}
    public String getPoste(){return Poste.get();}
    public void setPoste(String vPoste) {Poste.set(vPoste); }
    
    public StringProperty CodeServiceProperty() { return CodeService ;}
    public String getCodeService() { return CodeService.get(); }
    public void setCodeService(String vCodeService) { CodeService.set(vCodeService); } 
    
    public StringProperty ServiceProperty() { return Service ;}
    public String getService() { return Service.get(); }
    public void setService(String vService) { Service.set(vService); } 
  
  }

