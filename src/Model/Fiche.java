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
public class Fiche {
    private final StringProperty Identification ;
    private final StringProperty Marque ;
    private final StringProperty Dimension;
    private final StringProperty Ram ;
    private final StringProperty Processeur ;
    private final StringProperty Disque ;
    private final StringProperty Designation;
    private final StringProperty Nom ;
    private final StringProperty Prenom ;
    private final StringProperty NumeroFiche ;
    private final StringProperty DateDemande ;
    private final StringProperty HeureDemande ;
    
  public Fiche()
  {
   Identification = new SimpleStringProperty(this,"Identification");
    Marque = new SimpleStringProperty(this,"Marque");
    Dimension = new SimpleStringProperty(this,"Dimension");
    Ram = new SimpleStringProperty(this,"Ram");
    Processeur = new SimpleStringProperty(this,"Processeur");
    Disque = new SimpleStringProperty(this,"Disque" );
    Designation = new SimpleStringProperty(this,"Designation"); 
    Nom = new SimpleStringProperty(this,"Nom");
    Prenom = new SimpleStringProperty(this,"Prenom");
    NumeroFiche = new SimpleStringProperty(this,"NumeroFiche");
    DateDemande = new SimpleStringProperty(this,"DateDemande");
    HeureDemande = new SimpleStringProperty(this,"HeureDemande");
    
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
 
   public StringProperty RamProperty() { return Ram ;}
    public String getRam() { return Ram.get(); }
    public void setRam(String vRam) { Ram.set(vRam); } 
    
    public StringProperty ProcesseurProperty() { return Processeur ;}
    public String getProcesseur() { return Processeur.get(); }
    public void setProcesseur(String vProcesseur) { Processeur.set(vProcesseur); } 
    
    public StringProperty DisqueProperty() { return Disque ;}
    public String getDisque() { return Disque.get(); }
    public void setDisque(String vDisque) { Disque.set(vDisque); } 
    
    public StringProperty DesignationProperty() {return Designation;}
    public String getDesignation() {return Designation.get(); }
    public void setDesignation(String vDesignation){Designation.set(vDesignation);}
    
      public StringProperty NomProperty() { return Nom ;}
    public String getNom() { return Nom.get(); }
    public void setNom(String vNom) { Nom.set(vNom); } 
    
    public StringProperty PrenomProperty() { return Prenom ;}
    public String getPrenom() { return Prenom.get(); }
    public void setPrenom(String vPrenom) { Prenom.set(vPrenom); } 
    
    public StringProperty NumeroFicheProperty() { return NumeroFiche ;}
    public String getNumeroFiche() { return NumeroFiche.get(); }
    public void setNumeroFiche(String vNumeroFiche) { NumeroFiche.set(vNumeroFiche); } 
    
    public StringProperty DateDemandeProperty() { return DateDemande ;}
    public String getDateDemande() { return DateDemande.get(); }
    public void setDateDemande(String vDateDemande) { DateDemande.set(vDateDemande); } 
    
    public StringProperty HeureDemandeProperty() { return HeureDemande ;}
    public String getHeureDemande() { return HeureDemande.get(); }
    public void setHeureDemande(String vHeureDemande) { HeureDemande.set(vHeureDemande); } 
    
    
    
    
}
