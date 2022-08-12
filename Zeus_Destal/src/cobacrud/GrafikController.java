/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cobacrud;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author naelafm
 */
public class GrafikController implements Initializable {
    
    int a=0, 
        b=0, 
        c=0, 
        d=0, 
        e=0, 
        f=0,
        g=0,
        h=0, 
        i=0, 
        j=0, 
        k=0,
        l=0,
        m=0,
        n=0;

    
    File tempf;
    boolean exf;
    XStream xstream;
    FileInputStream finp = null;
    FileOutputStream fout = null;
    
    ObservableList data = observableArrayList();
    ArrayList<dataLaporan> array = new ArrayList<>();

    @FXML
    private BarChart<String, Integer> chart;
    @FXML
    private NumberAxis numberChart;
    @FXML
    private CategoryAxis axisChart;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        tempf = new File("data.xml");
        exf = tempf.exists();
        xstream = new XStream(new StaxDriver());
        if(exf){
            try {
                loadData();
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        for (Iterator it = data.iterator(); it.hasNext();) {
            dataLaporan x = (dataLaporan) it.next();
            if (x.getKategori().equals("BANSOS")) {
                a++;
            }else if (x.getKategori().equals("BENCANA ALAM")) {
                b++;
            }else if (x.getKategori().equals("JALAN & JEMBATAN")) {
                c++;
            }else if (x.getKategori().equals("KEPEGAWAIAN")) {
                d++;
            }else if (x.getKategori().equals("KEPENDUDUKAN")) {
                e++;
            }else if (x.getKategori().equals("KESEHATAN")) {
                f++;
            }else if (x.getKategori().equals("KETENTRAMAN & KETERTIBAN")) {
                g++;
            }else if (x.getKategori().equals("PERTANIAN")) {
                h++;
            }else if (x.getKategori().equals("PEMBANGUNAN DESA")) {
                i++;
            }else if (x.getKategori().equals("LAYANAN DESA")) {
                j++;
            }else if (x.getKategori().equals("PENDIDIKAN")) {
                k++;
            }else if (x.getKategori().equals("PUNGLI")) {
                l++;
            }else if (x.getKategori().equals("RASKIN")) {
                m++;
            }else if (x.getKategori().equals("SAMPAH, LIMBAH DAN TAMAN")) {
                n++;

            }
        }
        
        numberChart.setLabel("Jumlah Pengaduan");
        axisChart.setLabel("Kategori");
        XYChart.Series<String, Integer> series = new XYChart.Series();
        series.getData().add(new XYChart.Data<>("BANSOS", a));
        series.getData().add(new XYChart.Data<>("BENCANA ALAM", b));
        series.getData().add(new XYChart.Data<>("JALAN & JEMBATAN", c));
        series.getData().add(new XYChart.Data<>("KEPEGAWAIAN", d));
        series.getData().add(new XYChart.Data<>("KEPENDUDUKAN", e));
        series.getData().add(new XYChart.Data<>("KESEHATAN", f));
        series.getData().add(new XYChart.Data<>("KETENTRAMAN & KETERTIBAN", g));
        series.getData().add(new XYChart.Data<>("PERTANIAN", h));
        series.getData().add(new XYChart.Data<>("PEMBANGUNAN DESA", i));
        series.getData().add(new XYChart.Data<>("LAYANAN DESA", j));
        series.getData().add(new XYChart.Data<>("PENDIDIKAN", k));
        series.getData().add(new XYChart.Data<>("PUNGLI", l));
        series.getData().add(new XYChart.Data<>("RASKIN", m));
        series.getData().add(new XYChart.Data<>("SAMPAH, LIMBAH DAN TAMAN", n));

        
        chart.getData().add(series);
    }    
    
    @FXML
    void pindahKePengaduan (ActionEvent event) throws IOException {
        System.out.println("Berhasil masuk ke laporkan");
        Parent Home  = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene sceneLaporkan = new Scene (Home);
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow() ;
        stage.setScene(sceneLaporkan);
        stage.setResizable(false);
        stage.show();
    
    }
    
    @FXML
    private void pindahKeHome (ActionEvent event) throws IOException{
        System.out.println("Berhasil masuk ke home");
        Parent Home  = FXMLLoader.load(getClass().getResource("MenuBar.fxml"));
        Scene sceneLaporkan = new Scene (Home);
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow() ;
        stage.setScene(sceneLaporkan);
        stage.setResizable(false);
        stage.show();
        
    }
    
    @FXML
    private void pindahKeKontak (ActionEvent event) throws IOException{
        System.out.println("Berhasil masuk ke kontak");
        Parent Home  = FXMLLoader.load(getClass().getResource("Kontak.fxml"));
        Scene sceneLaporkan = new Scene (Home);
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow() ;
        stage.setScene(sceneLaporkan);
        stage.setResizable(false);
        stage.show();
        
    }
    
    public void loadData() throws FileNotFoundException, IOException{
        finp = new FileInputStream("data.xml");
        try{
            int isi;
            char c;
            String sxml = "";
            while((isi = finp.read()) != -1){
                c = (char) isi;
                sxml = sxml + c;
            }
            data = (ObservableList) xstream.fromXML(sxml);
        }
        catch(Exception e){
            
        }
    }
}
