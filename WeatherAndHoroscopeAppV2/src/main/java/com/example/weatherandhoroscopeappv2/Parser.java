package com.example.weatherandhoroscopeappv2;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Parser {

    private Document horoscopeDocument;
    private Document weatherDocument;
    private Document newsDocument;

    private Properties properties = new Properties();

    public Parser(File settingsFile) {
        try {
            properties.load(new FileInputStream(settingsFile));
            updateInfo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateInfo() throws IOException {
        horoscopeDocument = Jsoup.connect("https://horoscopes.rambler.ru/" + properties.getProperty("zodiac") + "/").get();
        weatherDocument = Jsoup.connect("https://weather.rambler.ru/" + properties.getProperty("city") + "/").get();
        newsDocument = Jsoup.connect("https://lenta.ru/parts/news/").get();
    }

    public String getWeatherFeelsOutside(){
        Elements feelWeather = weatherDocument.getElementsByClass("_1WjP");
        return feelWeather.text();
    }

    public String getWeatherDegreeInfo(){
        Elements weatherDegree = weatherDocument.getElementsByClass("_1HBR");
        String[] weather = weatherDegree.text().split(" ");
        return weather[0];
    }

    public String getWeatherAdditionalInfo(){
        Elements weatherAdditional = weatherDocument.getElementsByClass("Hixd");
        return weatherAdditional.text();
    }

    public String getHoroscope(){
        Elements horoscope = horoscopeDocument.getElementsByClass("mtZOt");
        return horoscope.first().text();
    }
    public String[] getNews(){
        String result = "";
        Elements news = newsDocument.getElementsByClass("parts-page__item");
        String[] formattedText = news.text().split("[0-9][0-9]:[0-9][0-9]");
        return formattedText;
    }

    public String getTime(){
        Date date = new Date();
        SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm:ss");
        return formatTime.format(date);
    }





}


