package com.example.weatherandhoroscopeappv2;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;

public class MainFrameController {

    private final String settingsFilePath = System.getProperty("user.home") + File.separator + "Waha.properties";
    public File settingsFile = new File(settingsFilePath);
    public Properties properties = new Properties();

    public Label tempNumberLabel;
    public Label outsideLabel;
    public Label feelOutsideLabel;
    public TextArea horoscopeTextArea;

    public void initialize(){
        try {
            properties.load(new FileInputStream(settingsFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setTextOnElements();
    }

    public void settingsMenuItem(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainFrameController.class.getResource("Options-view.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.tempNumberLabel.getScene().getWindow());
        stage.showAndWait();

    }

    public void aboutMenuItem(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainFrameController.class.getResource("About-view.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.tempNumberLabel.getScene().getWindow());
        stage.showAndWait();
    }

    public void updateButton(ActionEvent actionEvent) {
        try {
            properties.load(new FileInputStream(settingsFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setTextOnElements();
    }

    private void setTextOnElements(){
        tempNumberLabel.setText(getWeatherDegreeInfo());
        outsideLabel.setText(getWeatherAdditionalInfo());
        tempNumberLabel.setText(getWeatherDegreeInfo());

        if (properties.getProperty("feelLike").equals("ON")){
            feelOutsideLabel.setText(getWeatherFeelsOutside());
        } else {
            feelOutsideLabel.setText("");
        }
        horoscopeTextArea.setText(getHoroscope());
    }

    private String getWeatherFeelsOutside(){
        System.out.println("OUTSIDEIS" + getTextByClass("_1WjP"));
        return getTextByClass("_1WjP");
    }

    private String getWeatherDegreeInfo(){
       return getTextByClass("_1HBR").substring(0,3);
    }

    private String getWeatherAdditionalInfo(){
        return getTextByClass("Hixd");
    }

    private String getHoroscope(){
        try {
            Document document = Jsoup.connect("https://horoscopes.rambler.ru/" + properties.getProperty("zodiac") + "/").get();
            Elements elementsByClass = document.getElementsByClass("mtZOt");
            return elementsByClass.first().text();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Произошла ошибка. Попробуйте позже.";
    }

    private String getTextByClass(String classTeg){
        try {
            Document document = Jsoup.connect(
                    "https://weather.rambler.ru/" + properties.getProperty("city") + "/").get();
            Elements elementsByClass = document.getElementsByClass(classTeg);
            return  elementsByClass.text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Произошла ошибка. Попробуйте позже.";
    }
}