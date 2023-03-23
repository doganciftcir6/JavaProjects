package sample;

import java.io.Serializable;

public class Hasta implements Serializable {

    private String adSoyad;
    private String cinsiyet;
    private double boy;
    private double kilo;

    //konstraktır

    public Hasta(String adSoyad, String cinsiyet, double boy, double kilo) {
        super();
        this.adSoyad = adSoyad;
        this.cinsiyet = cinsiyet;
        this.boy = boy;
        this.kilo = kilo;
    }


    //getır setır
    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public double getBoy() {
        return boy;
    }

    public void setBoy(double boy) {
        this.boy = boy;
    }

    public double getKilo() {
        return kilo;
    }

    public void setKilo(double kilo) {
        this.kilo = kilo;
    }
}
