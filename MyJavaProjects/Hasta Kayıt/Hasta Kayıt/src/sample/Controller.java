package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private ComboBox<String> cmbCinsiyet;

    @FXML
    private ComboBox<String> cmbHastaSecimi;

    @FXML
    private Spinner<Double> spnBoy;

    @FXML
    private Spinner<Double> spnKilo;

    @FXML
    private TextField txtAdSoyad;

    @FXML
    private TextField txtBoy;

    @FXML
    private TextField txtCinsiyet;

    @FXML
    private TextField txtKilo;

    private ArrayList<Hasta> hastalar = new ArrayList<Hasta>();

    @FXML
    void hastaSil(ActionEvent event) {
        int secilen = this.cmbHastaSecimi.getSelectionModel().getSelectedIndex();
        if (secilen >= 0){
            String adSoyad = ((Hasta)this.hastalar.get(secilen)).getAdSoyad();
            this.cmbHastaSecimi.getItems().remove(secilen);
            this.txtBoy.clear();
            this.txtKilo.clear();
            this.txtCinsiyet.clear();
            this.hastalar.remove(secilen);

            try(FileOutputStream fos = new FileOutputStream("hastabilgileri.dat");
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(this.hastalar);
                Alert bilgilendirme = new Alert(Alert.AlertType.INFORMATION);
                bilgilendirme.setTitle("Bilgi");
                bilgilendirme.setHeaderText(adSoyad + " Kişisi silindi...");
                bilgilendirme.show();

            }catch (Exception e){
                e.printStackTrace();

            }





        }


    }

    @FXML
    void hastaGoster(ActionEvent event) {
        int secilen = cmbHastaSecimi.getSelectionModel().getSelectedIndex();
        txtBoy.setText(hastalar.get(secilen).getBoy() + " m");
        txtKilo.setText(hastalar.get(secilen).getKilo() + " m,kg");
        txtCinsiyet.setText(hastalar.get(secilen).getCinsiyet() + " m,kg");

    }

    @FXML
    void hastaKayit(ActionEvent event) {
        String adSoyad = txtAdSoyad.getText();
        for (int i = 0; i<hastalar.size();i++){
            if (adSoyad.equals(hastalar.get(i).getAdSoyad())){
                Alert hastaMesaji = new Alert(Alert.AlertType.ERROR);
                hastaMesaji.setTitle("Hata");
                hastaMesaji.setHeaderText("Bu isimde bir hasta kayıtlı...");
                hastaMesaji.show();
                return;

            }
        }
        String cinsiyet = cmbCinsiyet.getValue();
        double boy = spnBoy.getValue();
        double kilo = spnKilo.getValue();

        Hasta hasta = new Hasta(adSoyad,cinsiyet,boy,kilo);
        hastalar.add(hasta);

        try(FileOutputStream fos = new FileOutputStream("hastabilgileri.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(hastalar);
            Alert bilgilendirMesaji = new Alert(Alert.AlertType.INFORMATION);
            bilgilendirMesaji.setTitle("Bilgi");
            bilgilendirMesaji.setHeaderText(hasta.getAdSoyad() + " Kişisi Kaydedildi...");
            bilgilendirMesaji.show();

            cmbHastaSecimi.getItems().add(hasta.getAdSoyad());


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbCinsiyet.getItems().add("Erkek");
        cmbCinsiyet.getItems().add("Kadın");
        spnBoy.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0,3,1.75,0.01));
        spnKilo.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0,300,75,01));



        try(FileInputStream fis = new FileInputStream("hastabilgileri.dat");
            ObjectInputStream ois = new ObjectInputStream(fis)){
            hastalar = (ArrayList<Hasta>) ois.readObject();

            for (int i = 0; i < hastalar.size();i++){
              cmbHastaSecimi.getItems().add(hastalar.get(i).getAdSoyad());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
