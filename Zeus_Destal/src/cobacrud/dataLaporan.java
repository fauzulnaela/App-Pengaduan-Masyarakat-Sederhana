/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cobacrud;

import java.time.LocalDate;

/**
 *
 * @author naelafm
 */
public class dataLaporan {
    LocalDate Tanggal;
    String NIK;
    String Nama;
    String NoHP;
    String Kategori;
    String Lokasi;
    String Judul;
    String Laporan;
    
    
    public dataLaporan(LocalDate a, String b, String c, String d, String e, String f, String g, String h ){
        this.Tanggal = a;
        this.NIK = b;
        this.Nama = c;
        this.NoHP = d;
        this.Kategori = e;
        this.Lokasi = f;
        this.Judul = g;
        this.Laporan = h;
    
}


    public LocalDate getTanggal() {
        return Tanggal;
    }

    public String getNIK() {
        return NIK;
    }

    public String getNama() {
        return Nama;
    }

    public String getNoHP() {
        return NoHP;
    }

    public String getKategori() {
        return Kategori;
    }

    public String getLaporan() {
        return Laporan;
    }

    public String getLokasi() {
        return Lokasi;
    }

    public String getJudul() {
        return Judul;
    }
    
    public void setTanggal(LocalDate Tanggal) {
        this.Tanggal = Tanggal;
    }

    public void setNIK(String NIK) {
        this.NIK = NIK;
    }

    public void setNama(String Nama) {
        this.Nama = Nama;
    }

    public void setNoHP(String NoHP) {
        this.NoHP = NoHP;
    }

    public void setKategori(String Kategori) {
        this.Kategori = Kategori;
    }

    public void setLaporan(String Laporan) {
        this.Laporan = Laporan;
    }

    public void setLokasi(String Lokasi) {
        this.Lokasi = Lokasi;
    }

    public void setJudul(String Judul) {
        this.Judul = Judul;
    }
    
}

    

