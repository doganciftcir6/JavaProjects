package com.example.vizesuleymancinar;

import java.io.Serializable;

public class Musteri implements Serializable {
    private String adSoyad, meslek, urun, dogumTarihi;
    private Integer kimlikNo, urunAdet;

    public Musteri(String adSoyad, String meslek, String urun, String dogumTarihi, Integer kimlikNo, Integer urunAdet) {
        super();
        this.adSoyad = adSoyad;
        this.meslek = meslek;
        this.urun = urun;
        this.dogumTarihi = dogumTarihi;
        this.kimlikNo = kimlikNo;
        this.urunAdet = urunAdet;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getMeslek() {
        return meslek;
    }

    public void setMeslek(String meslek) {
        this.meslek = meslek;
    }

    public String getUrun() {
        return urun;
    }

    public void setUrun(String urun) {
        this.urun = urun;
    }

    public String getDogumTarihi() {
        return dogumTarihi;
    }

    public void setDogumTarihi(String dogumTarihi) {
        this.dogumTarihi = dogumTarihi;
    }

    public Integer getKimlikNo() {
        return kimlikNo;
    }

    public void setKimlikNo(Integer kimlikNo) {
        this.kimlikNo = kimlikNo;
    }

    public Integer getUrunAdet() {
        return urunAdet;
    }

    public void setUrunAdet(Integer urunAdet) {
        this.urunAdet = urunAdet;
    }
}
