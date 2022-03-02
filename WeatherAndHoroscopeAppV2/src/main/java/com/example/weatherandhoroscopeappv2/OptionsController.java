package com.example.weatherandhoroscopeappv2;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToggleButton;

import java.io.*;
import java.util.Properties;

public class OptionsController {

    public ChoiceBox<String> zodiacChoiceBox;
    public ChoiceBox<String> cityChoiceBox;
    public ToggleButton feelToggleButton;

    private final String settingsFilePath = System.getProperty("user.home") + File.separator + "Waha.properties";
    public File settingsFile = new File(settingsFilePath);
    public Properties properties = new Properties();

    public void initialize() throws IOException {
        setItems();
        if(settingsFile.exists()){
            properties.load(new FileInputStream(settingsFile));
            getPropertiesCity();
            getPropertiesZodiac();
            if (properties.getProperty("feelLike").equals("ON")){
                feelToggleButton.setSelected(true);
            } else {
                feelToggleButton.setSelected(false);
            }

        } else {
            settingsFile = new File(settingsFilePath);
        }
    }

    private void setItems(){
        cityChoiceBox.setItems(FXCollections.observableArrayList(
                "Москва",
                "Санкт-Петербург",
                "Новосибирск",
                "Екатеринбург",
                "Нижний Новгород",
                "Казань",
                "Челябинск",
                "Омск",
                "Самара",
                "Ростов-на-Дону",
                "Уфа",
                "Красноярск"));
        zodiacChoiceBox.setItems(FXCollections.observableArrayList(
                "Овен",
                "Телец",
                "Близнецы",
                "Рак",
                "Лев",
                "Дева",
                "Весы",
                "Скорпион",
                "Стрелец",
                "Козерог",
                "Водолей",
                "Рыбы"));
    }

    public void saveButton(ActionEvent actionEvent) {
        try{
            setPropertiesCity();
            setPropertiesZodiac();
            if (feelToggleButton.isSelected()){
                properties.setProperty("feelLike", "ON");
            } else {
                properties.setProperty("feelLike", "OFF");
            }
            properties.store(new FileOutputStream(settingsFile), null);
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    private void setPropertiesZodiac(){
        if (zodiacChoiceBox.getValue().equals("Овен")){
            properties.setProperty("zodiac" ,"aries");
        }
        else if(zodiacChoiceBox.getValue().equals("Телец")){
            properties.setProperty("zodiac" ,"taurus");
        }
        else if(zodiacChoiceBox.getValue().equals("Близнецы")){
            properties.setProperty("zodiac" ,"gemini");
        }
        else if(zodiacChoiceBox.getValue().equals("Рак")){
            properties.setProperty("zodiac" ,"cancer");
        }
        else if(zodiacChoiceBox.getValue().equals("Лев")) {
            properties.setProperty("zodiac", "leo");
        }
        else if(zodiacChoiceBox.getValue().equals("Дева")){
            properties.setProperty("zodiac" ,"virgo");
        }
        else if(zodiacChoiceBox.getValue().equals("Весы")){
            properties.setProperty("zodiac" ,"libra");
        }
        else if(zodiacChoiceBox.getValue().equals("Скорпион")){
            properties.setProperty("zodiac" ,"scorpio");
        }
        else if(zodiacChoiceBox.getValue().equals("Стрелец")){
            properties.setProperty("zodiac" ,"sagittarius");
        }
        else if(zodiacChoiceBox.getValue().equals("Козерог")){
            properties.setProperty("zodiac" ,"capricorn");
        }
        else if(zodiacChoiceBox.getValue().equals("Водолей")){
            properties.setProperty("zodiac" ,"aquarius");
        }
        else if(zodiacChoiceBox.getValue().equals("Рыбы")){
            properties.setProperty("zodiac" ,"pisces");
        }
    }

    private void setPropertiesCity(){
        if (cityChoiceBox.getValue().equals("Москва")){
            properties.setProperty("city" ,"v-moskve");
        }
        else if (cityChoiceBox.getValue().equals("Санкт-Петербург")){
            properties.setProperty("city" ,"v-sankt-peterburge");
        }
        else if (cityChoiceBox.getValue().equals("Новосибирск")){
            properties.setProperty("city" ,"v-novosibirske");
        }
        else if (cityChoiceBox.getValue().equals("Екатеринбург")){
            properties.setProperty("city" ,"v-ekaterinburge");
        }
        else if (cityChoiceBox.getValue().equals("Нижний Новгород")){
            properties.setProperty("city" ,"v-nizhnem-novgorode");
        }
        else if (cityChoiceBox.getValue().equals("Казань")){
            properties.setProperty("city" ,"v-kazani");
        }
        else if (cityChoiceBox.getValue().equals("Челябинск")){
            properties.setProperty("city" ,"v-chelyabinske");
        }
        else if (cityChoiceBox.getValue().equals("Омск")){
            properties.setProperty("city" ,"v-omske");
        }
        else if (cityChoiceBox.getValue().equals("Самара")){
            properties.setProperty("city" ,"v-samare");
        }
        else if (cityChoiceBox.getValue().equals("Ростов-на-Дону")){
            properties.setProperty("city" ,"v-rostove-na-donu");
        }
        else if (cityChoiceBox.getValue().equals("Уфа")){
            properties.setProperty("city" ,"v-ufe");
        }
        else if (cityChoiceBox.getValue().equals("Красноярск")){
            properties.setProperty("city" ,"v-krasnoyarske");
        }
    }

    private void getPropertiesCity(){
        if (properties.getProperty("city").equals("v-moskve")){
            cityChoiceBox.setValue("Москва");
        }
        else if (properties.getProperty("city").equals("v-sankt-peterburge")){
            cityChoiceBox.setValue("Санкт-Петербург");
        }
        else if (properties.getProperty("city").equals("v-novosibirske")){
            cityChoiceBox.setValue("Новосибирск");
        }
        else if (properties.getProperty("city").equals("v-ekaterinburge")){
            cityChoiceBox.setValue("Екатеринбург");
        }
        else if (properties.getProperty("city").equals("v-nizhnem-novgorode")){
            cityChoiceBox.setValue("Нижний Новгород");
        }
        else if (properties.getProperty("city").equals("v-kazani")){
            cityChoiceBox.setValue("Казань");
        }
        else if (properties.getProperty("city").equals("v-chelyabinske")){
            cityChoiceBox.setValue("Челябинск");
        }
        else if (properties.getProperty("city").equals("v-omske")){
            cityChoiceBox.setValue("Омск");
        }
        else if (properties.getProperty("city").equals("v-samare")){
            cityChoiceBox.setValue("Самара");
        }
        else if (properties.getProperty("city").equals("v-rostove-na-donu")){
            cityChoiceBox.setValue("Ростов-на-Дону");
        }
        else if (properties.getProperty("city").equals("v-ufe")){
            cityChoiceBox.setValue("Уфа");
        }
        else if (properties.getProperty("city").equals("v-krasnoyarske")){
            cityChoiceBox.setValue("Красноярск");
        }

    }

    private void getPropertiesZodiac(){
        if (properties.getProperty("zodiac").equals("aries")){
            zodiacChoiceBox.setValue("Овен");
        }
        else if (properties.getProperty("zodiac").equals("taurus")){
            zodiacChoiceBox.setValue("Телец");
        }
        else if (properties.getProperty("zodiac").equals("gemini")){
            zodiacChoiceBox.setValue("Близнецы");
        }
        else if (properties.getProperty("zodiac").equals("cancer")){
            zodiacChoiceBox.setValue("Рак");
        }
        else if (properties.getProperty("zodiac").equals("leo")){
            zodiacChoiceBox.setValue("Лев");
        }
        else if (properties.getProperty("zodiac").equals("virgo")){
            zodiacChoiceBox.setValue("Дева");
        }
        else if (properties.getProperty("zodiac").equals("libra")){
            zodiacChoiceBox.setValue("Весы");
        }
        else if (properties.getProperty("zodiac").equals("scorpio")){
            zodiacChoiceBox.setValue("Скорпион");
        }
        else if (properties.getProperty("zodiac").equals("sagittarius")){
            zodiacChoiceBox.setValue("Стрелец");
        }
        else if (properties.getProperty("zodiac").equals("capricorn")){
            zodiacChoiceBox.setValue("Козерог");
        }
        else if (properties.getProperty("zodiac").equals("aquarius")){
            zodiacChoiceBox.setValue("Водолей");
        }
        else if (properties.getProperty("zodiac").equals("pisces")){
            zodiacChoiceBox.setValue("Рыбы");
        }
    }
}
