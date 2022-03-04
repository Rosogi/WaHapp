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

public class MainFrameController {

    private final String settingsFilePath = System.getProperty("user.home") + File.separator + "Waha.properties";
    public File settingsFile = new File(settingsFilePath);
    public Properties properties = new Properties();

    Parser parser;

    public Label tempNumberLabel;
    public Label outsideLabel;
    public Label feelOutsideLabel;
    public TextArea horoscopeTextArea;
    public Label timeLabel;
    public TextArea newsTextArea;

    public void initialize(){
        parser = new Parser(settingsFile);
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
            parser = new Parser(settingsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setTextOnElements();
    }

    private void setTextOnElements(){
        tempNumberLabel.setText(parser.getWeatherDegreeInfo());
        outsideLabel.setText(parser.getWeatherAdditionalInfo());
        if (properties.getProperty("feelLike").equals("ON")){
            feelOutsideLabel.setText(parser.getWeatherFeelsOutside());
        } else {
            feelOutsideLabel.setText("");
        }
        horoscopeTextArea.setText(parser.getHoroscope());
        timeLabel.setText(parser.getTime());
        newsTextArea.setText(parser.getNews());

    }


}