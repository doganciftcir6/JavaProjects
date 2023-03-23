package com.example.vizesuleymancinar;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private ComboBox<String > cmbMeslek;

    @FXML
    private ComboBox<String> cmbMusterisecimi;

    @FXML
    private ComboBox<String> cmbUrun;

    @FXML
    private Spinner<Integer> spnAdet;

    @FXML
    private TextField txtAdet;

    @FXML
    private TextField txtAdsoyad;

    @FXML
    private TextField txtDogumGoster;

    @FXML
    private TextField txtDogumtarihi;

    @FXML
    private TextField txtKimlikGoster;

    @FXML
    private TextField txtKimlikno;

    @FXML
    private TextField txtMeslek;

    @FXML
    private TextField txtUrun;

    private ArrayList<Musteri> musteriler=new ArrayList<Musteri>();

    @FXML
    void musteriGoster(ActionEvent event) {
        int secilen=cmbMusterisecimi.getSelectionModel().getSelectedIndex();

        if (secilen<0)
            return;

        txtKimlikGoster.setText(String.valueOf(musteriler.get(secilen).getKimlikNo()));
        txtMeslek.setText(musteriler.get(secilen).getMeslek());
        txtDogumGoster.setText(musteriler.get(secilen).getDogumTarihi());
        txtUrun.setText(musteriler.get(secilen).getUrun());
        txtAdet.setText(String.valueOf(musteriler.get(secilen).getUrunAdet()));

    }

    @FXML
    void musteriKayit(ActionEvent event) {
        String adSoyad=txtAdsoyad.getText();
        String meslek=cmbMeslek.getValue();
        String dogumTarihi=txtDogumtarihi.getText();
        String urun=cmbUrun.getValue();

        int urunAdet= spnAdet.getValue();

        if (txtKimlikno.getText().isEmpty() || txtAdsoyad.getText().isEmpty()){
            Alert musterimesaji=new Alert(Alert.AlertType.ERROR);
            musterimesaji.setTitle("Hata");
            musterimesaji.setHeaderText("Kimlik numarası veya Adsoyad girilmedi...");
            musterimesaji.show();
            return;
        }

        int kimlikNo = Integer.parseInt(txtKimlikno.getText());

        for (int i = 0; i < musteriler.size(); i++) {
            if (kimlikNo==musteriler.get(i).getKimlikNo()){
                Alert musterimesaji=new Alert(Alert.AlertType.ERROR);
                musterimesaji.setTitle("Hata");
                musterimesaji.setHeaderText(musteriler.get(i).getKimlikNo()+" numaralı müşteri kayıtlı...");
                musterimesaji.show();
                return;
            }
        }

        Musteri musteri=new Musteri(adSoyad, meslek,urun, dogumTarihi, kimlikNo, urunAdet);
        musteriler.add(musteri);

        try {
            FileOutputStream fos=new FileOutputStream("musteribilgileri.dat");
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(musteriler);
            fos.close();
            oos.close();
            Alert bilgilendirmemesaji=new Alert(Alert.AlertType.CONFIRMATION);
            bilgilendirmemesaji.setTitle("Bilgi");
            bilgilendirmemesaji.setHeaderText(musteri.getAdSoyad()+" kişisi kaydedildi...");
            bilgilendirmemesaji.show();

            cmbMusterisecimi.getItems().add(musteri.getAdSoyad());

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    @FXML
    void musteriSil(ActionEvent event) {
        try {
            int secilen=cmbMusterisecimi.getSelectionModel().getSelectedIndex();

            if (secilen < 0 ) {
                Alert bilgilendirmemesaji=new Alert(Alert.AlertType.ERROR);
                bilgilendirmemesaji.setTitle("Hata");
                bilgilendirmemesaji.setHeaderText("Silinecek kayıt bulunamadı...");
                bilgilendirmemesaji.show();
                return;
            }

            String adsoyad=musteriler.get(secilen).getAdSoyad();

            cmbMusterisecimi.getItems().remove(secilen);
            txtKimlikGoster.clear();
            txtMeslek.clear();
            txtDogumGoster.clear();
            txtUrun.clear();
            txtAdet.clear();

            musteriler.remove(secilen);

            FileOutputStream fos=new FileOutputStream("musteribilgileri.dat");
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(musteriler);
            fos.close();
            oos.close();
            Alert bilgilendirmemesaji=new Alert(Alert.AlertType.INFORMATION);
            bilgilendirmemesaji.setTitle("Bilgi");
            bilgilendirmemesaji.setHeaderText(adsoyad+" kişisi silindi...");
            bilgilendirmemesaji.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //cmbMeslek.getItems().add("Bilgisayar Programcısı");
        cmbMeslek.setItems(FXCollections.observableArrayList("Bilgisayar Programcısı","Mühendis","Doktor",
                "Esnaf","İşçi","Memur","Polis","Öğretmen"));
        cmbUrun.setItems(FXCollections.observableArrayList("Dolap","Masa","Oturma Odası","Buzdolabı"));
        spnAdet.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,300,1,1));

        try {
            FileInputStream fis=new FileInputStream("musteribilgileri.dat");
            ObjectInputStream ois=new ObjectInputStream(fis);
            musteriler=(ArrayList<Musteri>) ois.readObject();
            ois.close();

            for (int i = 0; i < musteriler.size(); i++) {
                cmbMusterisecimi.getItems().add(musteriler.get(i).getAdSoyad());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
