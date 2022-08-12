/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package cobacrud;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author naelafm
 */
public class FXMLDocumentController implements Initializable {
    
    File tempf;
    boolean exf;
    XStream xstream;
    FileInputStream finp = null;
    FileOutputStream fout = null;
    Alert a = new Alert(AlertType.NONE);
    Alert b = new Alert(AlertType.NONE);
    Alert c = new Alert(AlertType.NONE);
    Alert d = new Alert(AlertType.NONE);
    ObservableList data = observableArrayList();
    
    @FXML private TableView<dataLaporan> tvLaporan;
    @FXML private TableColumn<dataLaporan, String> tcTanggal,tcNIK, tcNama, tcNoHP,tcKategori,tcLokasi,tcJudul,tcLaporan; 
    @FXML private ChoiceBox cbKategori;
    @FXML private TextField tfNIK,tfNama,tfNoHP,tfLokasi,tfJudul, tfLaporan;
    @FXML private DatePicker dpTanggal;
    @FXML private Button btnDelete, btnUpdate, button;
    
    @FXML
    private void Kirim(ActionEvent event) throws IOException {
        System.out.println("Berhasil menambah data...");
        if(validate()){
            if(!exf){
                data.add(new dataLaporan(dpTanggal.getValue(),tfNIK.getText(),
                    tfNama.getText(),tfNoHP.getText(),cbKategori.getValue().toString(),
                    tfLokasi.getText(),tfJudul.getText(), tfLaporan.getText()));
                saveData();
                d.setAlertType(AlertType.INFORMATION);
                d.setTitle("Pesan");
                d.setHeaderText("Data berhasil dikirim");
                d.setContentText("Silahkan menunggu data untuk ditanggapi");
                d.show();
                
                dpTanggal.setValue(null);
                tfNIK.setText("");
                tfNama.setText("");
                tfNoHP.setText("");
                cbKategori.setValue(null);
                tfLokasi.setText("");
                tfJudul.setText("");
                tfLaporan.setText("");
            }else{
               
                loadData();
                data.add(new dataLaporan(dpTanggal.getValue(),tfNIK.getText(),
                    tfNama.getText(),tfNoHP.getText(),cbKategori.getValue().toString(),
                    tfLokasi.getText(),tfJudul.getText(), tfLaporan.getText()));
                saveData();
                d.setAlertType(AlertType.INFORMATION);
                d.setTitle("Pesan");
                d.setHeaderText("Data berhasil dikirim");
                d.setContentText("Silahkan menunggu data ditanggapi");
                d.show();
                
                dpTanggal.setValue(null);
                tfNIK.setText("");
                tfNama.setText("");
                tfNoHP.setText("");
                cbKategori.setValue(null);
                tfLokasi.setText("");
                tfJudul.setText("");
                tfLaporan.setText("");
            }
        }
        
        tvLaporan.setItems(data);        
    }
    
    //method untuk klik setiap baris agar bisa update / delete
    @FXML
    void rowClicked(MouseEvent event) throws ParseException {
        SimpleDateFormat format =new SimpleDateFormat("dd-MM-yyyy");
        
        dataLaporan clickedDataLaporan = (dataLaporan) tvLaporan.getSelectionModel().getSelectedItem();
        tfNama.setText(String.valueOf(clickedDataLaporan.getNama()));
        tfNIK.setText(String.valueOf(clickedDataLaporan.getNIK()));
        cbKategori.setValue(String.valueOf(clickedDataLaporan.getKategori()));
        tfNoHP.setText(String.valueOf(clickedDataLaporan.getNoHP()));
        Date tanggalMasuk = format.parse(clickedDataLaporan.getTanggal().toString());
        LocalDate date = tanggalMasuk.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        dpTanggal.setValue(clickedDataLaporan.getTanggal());
        tfLokasi.setText(String.valueOf(clickedDataLaporan.getLokasi()));
        tfJudul.setText(String.valueOf(clickedDataLaporan.getJudul()));
        tfLaporan.setText(String.valueOf(clickedDataLaporan.getLaporan()));
    }
    
    @FXML
    void update(ActionEvent event) throws IOException {
        @SuppressWarnings("unchecked")
        ObservableList<dataLaporan> clickedData = tvLaporan.getSelectionModel().getSelectedItems();
        int selectedIndex = tvLaporan.getSelectionModel().getSelectedIndex();
        if (selectedIndex <= 0) {
            c.setAlertType(AlertType.WARNING);
            c.setTitle("Perhatian!");
            c.setHeaderText("Belum memilih data.");
            c.setContentText("Silahkan pilih data yang ingin diedit");
            c.show();
        }
        dataLaporan clicked = clickedData.get(0);
        
        for (Iterator it = data.iterator(); it.hasNext();) {
            dataLaporan n = (dataLaporan) it.next();
            
            if(validate()){
                if (n.getNama().equals(clicked.getNama())) {

                    n.setNama(tfNama.getText());
                    n.setKategori(cbKategori.getValue().toString());
                    n.setNIK(tfNIK.getText());
                    n.setTanggal(dpTanggal.getValue());
                    n.setNoHP(tfNoHP.getText());
                    n.setLokasi(tfLokasi.getText());
                    n.setJudul(tfJudul.getText());
                    n.setLaporan(tfLaporan.getText());

                    loadData();
                    data.set(selectedIndex, n);
                    saveData();
                    
                    dpTanggal.setValue(null);
                    tfNIK.setText("");
                    tfNama.setText("");
                    tfNoHP.setText("");
                    cbKategori.setValue(null);
                    tfLokasi.setText("");
                    tfJudul.setText("");
                    tfLaporan.setText("");    

                    tvLaporan.refresh();
                    break;
                }
            }
        } 
        tvLaporan.setItems(data);
    }
    
    @FXML
    private void Hapus(){
        dpTanggal.setValue(null);
        tfNIK.setText(" ");
        tfNama.setText(" ");
        tfNoHP.setText(" ");
        cbKategori.setValue(null);
        tfLokasi.setText(" ");
        tfJudul.setText(" ");
        tfLaporan.setText(" ");
        
        int selectedIndex = tvLaporan.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Alert e = new Alert(AlertType.CONFIRMATION);
            e.setTitle("Hapus Laporan");
            e.setContentText("Apakah anda yakin ingin menghapus laporan ini?");
            Optional<ButtonType> result = e.showAndWait();
            if(result.get() == ButtonType.OK){
                data.remove(selectedIndex);
                saveData();
                tvLaporan.getItems().remove(selectedIndex);
            }
        }else{
            b.setAlertType(AlertType.WARNING);
            b.setTitle("Perhatian!");
            b.setHeaderText("Belum memilih data.");
            b.setContentText("Silahkan pilih data yang ingin dihapus");
            b.show();
        }
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
    private void pindahKeGrafik (ActionEvent event) throws IOException{
        System.out.println("Berhasil masuk ke statistik");
        Parent Home  = FXMLLoader.load(getClass().getResource("Grafik.fxml"));
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tcTanggal.setCellValueFactory(new PropertyValueFactory<dataLaporan, String>("Tanggal"));
        tcNIK.setCellValueFactory(new PropertyValueFactory<dataLaporan, String>("NIK"));
        tcNama.setCellValueFactory(new PropertyValueFactory<dataLaporan, String>("Nama"));
        tcNoHP.setCellValueFactory(new PropertyValueFactory<dataLaporan, String>("NoHP"));
        tcKategori.setCellValueFactory(new PropertyValueFactory<dataLaporan, String>("Kategori"));
        tcLokasi.setCellValueFactory(new PropertyValueFactory<dataLaporan, String>("Lokasi"));
        tcJudul.setCellValueFactory(new PropertyValueFactory<dataLaporan, String>("Judul"));
        tcLaporan.setCellValueFactory(new PropertyValueFactory<dataLaporan, String>("Laporan"));
        
        cbKategori.getItems().addAll("BANSOS", "BENCANA ALAM", "JALAN & JEMBATAN", "KEPEGAWAIAN", 
                "KEPENDUDUKAN", "KESEHATAN", "KETENTRAMAN & KETERTIBAN", "PERTANIAN", "PEMBANGUNAN DESA",
                "LAYANAN DESA", "PENDIDIKAN", "PUNGLI", "RASKIN", "SAMPAH, LIMBAH DAN TAMAN"
              );

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
        tvLaporan.setItems(data);
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
    
    public void saveData(){
        
        String sxml = xstream.toXML(data);
        try{
            fout = new FileOutputStream("data.xml");
            byte[] bytes = sxml.getBytes("UTF-8");
            fout.write(bytes);
        }
        catch(Exception e){
            
        }
    }
    
    //method untuk alert
    private boolean validate(){
        String error = "";
        if(tfNIK.getText().toString().isEmpty()){
            error+="Data NIK harus diisi\n";
        }else if(!tfNIK.getText().toString().matches("[0-9]+")){
            error+="Data NIK Harus berupa Angka\n";
        }
        if(tfNama.getText().toString().isEmpty()){
            error="Data Nama harus diisi\n";
        }else if(!tfNama.getText().toString().matches("\\D*")){
            error+="Data Nama harus berupa huruf\n";
        }
        if(tfNoHP.getText().toString().isEmpty()){
            error+="Data No HP/Email harus diisi\n";
        }
        if(tfLokasi.getText().toString().isEmpty()){
            error+="Data Lokasi harus diisi\n";
        }
        if(tfJudul.getText().toString().isEmpty()){
            error+="Data Judul harus diisi\n";
        }
        if(tfLaporan.getText().toString().isEmpty()){
            error+="Data Laporan harus diisi\n";
        }
        try{
            dpTanggal.getValue().toString();
        }catch(Exception e){
            error+="Tanggal harus diisi dengan memilih kalender yang disediakan\n";
        }
        try{
            cbKategori.getValue().toString();
        }catch(Exception e){
            error+="Data Kategori harus diisi\n";
        }
    
        if (!error.isEmpty()){    
            a.setAlertType(AlertType.WARNING);
            a.setTitle("Perhatian!");
            a.setHeaderText("Mohon perhatikan isian data Anda kembali.");
            a.setContentText(error);
            a.show();
        } else{
            return true;
        }
        return false;
    }
}
    



