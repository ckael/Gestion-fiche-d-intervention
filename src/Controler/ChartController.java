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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.jfree.chart.axis.DateAxis;

/**
 * FXML Controller class
 *
 * @author CKAEL
 */
public class ChartController implements Initializable {

    
       @FXML
    private HBox BarChart;

    @FXML
    private HBox LineChart;

    @FXML
    private PieChart PieChart;

    @FXML
    private HBox PieChartB;

    @FXML
    private Label bModifer11;

    @FXML
    private Label bModifer12;

    @FXML
    private Pane pPie;
    
        @FXML
    private BorderPane pMenu;

    @FXML
    void ChachgeBarChart(MouseEvent event) {

  CategoryAxis X = new CategoryAxis();
   NumberAxis Y = new NumberAxis();
   X.setLabel("Service");
   Y.setLabel("Nombre de materiel");
   
   BarChart barchart = new BarChart(X,Y);
   
   XYChart.Series data = new XYChart.Series();
   
   data.setName("Autre");
   
    XYChart.Series data1 = new XYChart.Series();
   
   data1.setName("Ecran");
   
    XYChart.Series data2 = new XYChart.Series();
   
   data2.setName("Unité centrale");
   Statement st = null;
   
           try {
               conecter();
               st = c.createStatement();
               String sql = "Select COUNT(NumeroFiche),service from viewall  where IdMateriel LIKE 'A%' GROUP BY service ;";
               ResultSet rs = st.executeQuery(sql);
               while(rs.next())
               {
               data.getData().add(new XYChart.Data(rs.getString(2), rs.getInt(1)));
                            
               }
           } catch (SQLException ex) {
               Logger.getLogger(ChartController.class.getName()).log(Level.SEVERE, null, ex);
           }
           finally
           {
      try {
          st.close();
      } catch (SQLException ex) {
          Logger.getLogger(ChartController.class.getName()).log(Level.SEVERE, null, ex);
      }
           }
           
           
           try {
               conecter();
               st = c.createStatement();
               String sql = "Select COUNT(NumeroFiche),service from viewall  where IdMateriel LIKE 'U%' GROUP BY service ;";
               ResultSet rs = st.executeQuery(sql);
               while(rs.next())
               {
               data2.getData().add(new XYChart.Data(rs.getString(2), rs.getInt(1)));
                            
               }
           } catch (SQLException ex) {
               Logger.getLogger(ChartController.class.getName()).log(Level.SEVERE, null, ex);
           }
           finally
           {
      try {
          st.close();
      } catch (SQLException ex) {
          Logger.getLogger(ChartController.class.getName()).log(Level.SEVERE, null, ex);
      }
           }
           
           
      
           try {
               conecter();
               st = c.createStatement();
               String sql = "Select COUNT(NumeroFiche),service from viewall  where IdMateriel LIKE 'E%' GROUP BY service ;";
               ResultSet rs = st.executeQuery(sql);
               while(rs.next())
               {
               data1.getData().add(new XYChart.Data(rs.getString(2), rs.getInt(1)));
                            
               }
           } catch (SQLException ex) {
               Logger.getLogger(ChartController.class.getName()).log(Level.SEVERE, null, ex);
           }
           finally
           {
      try {
          st.close();
      } catch (SQLException ex) {
          Logger.getLogger(ChartController.class.getName()).log(Level.SEVERE, null, ex);
      }
           }
           
      barchart.getData().add(data1);
      barchart.getData().add(data2);
      barchart.getData().add(data);
      barchart.setStyle("-fx-background-color : silver;");
       barchart.setTitle("Graphe de la répartition des matériels par diréction");
      pMenu.setCenter(barchart);
    
    }

    @FXML
    void ChachgeLineChart(MouseEvent event) {
       Statement st = null;
      CategoryAxis XAxis = new CategoryAxis();
      XAxis.setLabel("Mois");
      NumberAxis YAxis = new NumberAxis();
      YAxis.setLabel("Nombre de materiel");
      LineChart linechart = new LineChart(XAxis,YAxis);
       XYChart.Series data1 = new XYChart.Series(); 
       XYChart.Series data2 = new XYChart.Series(); 
       data2.setName("Réparer");
          data1.setName("Irréparable");
       
           try {
               conecter();
               st = c.createStatement();
               String sql = "SELECT COUNT(numerofiche) ,strftime('%m',daterecuperation) from recuperer where reparer = 'Reparer' group by  strftime('%m',daterecuperation) ;";
               ResultSet rs = st.executeQuery(sql);
               while(rs.next())
               {
                      data2.getData().add(new XYChart.Data(rs.getString(2), rs.getInt(1)));
               }
           } catch (SQLException ex) {
               Logger.getLogger(ChartController.class.getName()).log(Level.SEVERE, null, ex);
           }
           finally
           {
           try {
               st.close();
           } catch (SQLException ex) {
               Logger.getLogger(ChartController.class.getName()).log(Level.SEVERE, null, ex);
           }
           }
           
            try {
               conecter();
               st = c.createStatement();
               String sql = "SELECT COUNT(numerofiche) ,strftime('%m',daterecuperation) from recuperer where reparer = 'Irréparable' group by  strftime('%m',daterecuperation) ;";
               ResultSet rs = st.executeQuery(sql);
               while(rs.next())
               {
                      data1.getData().add(new XYChart.Data(rs.getString(2), rs.getInt(1)));
               }
           } catch (SQLException ex) {
               Logger.getLogger(ChartController.class.getName()).log(Level.SEVERE, null, ex);
           }
           finally
           {
           try {
               st.close();
           } catch (SQLException ex) {
               Logger.getLogger(ChartController.class.getName()).log(Level.SEVERE, null, ex);
           }
           }
            
       linechart.getData().add(data1);
       linechart.getData().add(data2);
       linechart.setStyle("-fx-background-color : silver;");
        linechart.setTitle("Graphe de la répartition des matériels réparés et declaré irréparables ");
       pMenu.setCenter(linechart);
    }

  
    @FXML
    void ChachgePiechart(MouseEvent event) {
        Statement st = null;
         ObservableList<PieChart.Data> PieData = FXCollections.observableArrayList( );
   
        
           try {
               conecter();
                 st = c.createStatement();
             String  Sql="Select COUNT(NumeroFiche),type from recuperer join materiel  WHERE recuperer.IdMateriel = materiel.IdMateriel GROUP BY type; ";
              
             ResultSet rs = st.executeQuery(Sql);
           
             while(rs.next())
                     {
                    PieData.add(new PieChart.Data(rs.getString(2)+": "+rs.getString(1),rs.getInt(1))) ;
                     
                     }
             
            
           } catch (SQLException ex) {
               Logger.getLogger(ChartController.class.getName()).log(Level.SEVERE, null, ex);
           }
           finally
           {
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(ChartController.class.getName()).log(Level.SEVERE, null, ex);
            }
           }
           
           
            
        
          
      
        PieChart.setData(PieData);
       
        PieChart.setTitle("Graphe de la répartition des matériels qui ont passer dans l'atelier");
        PieChart.setClockwise(true);
        PieChart.setLabelLineLength(10);
        PieChart.setLabelsVisible(true);
        PieChart.setStartAngle(180);
        PieChart.setTitleSide(Side.TOP);
     
        PieChart.setStyle("-fx-background-color : silver;");
        pMenu.setCenter(PieChart);
        
    }

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
