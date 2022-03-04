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
        return weatherDegree.text().substring(0,3);
    }

    public String getWeatherAdditionalInfo(){
        Elements weatherAdditional = weatherDocument.getElementsByClass("Hixd");
        return weatherAdditional.text();
    }

    public String getHoroscope(){
        Elements horoscope = horoscopeDocument.getElementsByClass("mtZOt");
        return horoscope.first().text();
    }
    //<li class="parts-page__item"><a class="_parts-news card-full-news ojioi" href="/news/2022/03/03/kushat_kebab/"><h3 class="card-full-news__title wpzlkdc">Жителям регионов России стали доступнее перелеты в Турцию</h3><div class="ccuajngkn card-full-news__info"><time class="card-full-news__info-item card-full-news__date">17:20</time><span class="gbfvqn card-full-news__rubric card-full-news__info-item">Путешествия</span></div></a></li>
    public String getNews(){
        String result = "";
        Elements news = newsDocument.getElementsByClass("parts-page__item");
        //return news.next().text();
        String[] formattedText = news.text().split("[0-9][0-9]:[0-9][0-9]");
        for (int i = 0; i < formattedText.length; i++){
            result = result + formattedText[i] + "\n";
        }
        return result;
    }

    public String getTime(){
        Date date = new Date();
        SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm:ss");
        return formatTime.format(date);
    }





}


