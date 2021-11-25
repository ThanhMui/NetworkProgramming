/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVER.Model;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class CountryAll implements Serializable{
    private String name;
    private int population;
    private double longtitude;
    private double latitude ;
    private String currencies;
    private String alpha2Code;
    private int geonameId;
    private String flag;
    private String capital;
    private String languages;
    private String neighbours;
//    thông tin thời tiết 
    private String countryCode;
    private float temperature;
    private String weatherCondition;
    private int humidity;
    private String clouds;
    private String datetime;
    private float windSpeed;
    

    public CountryAll() {
    }

    public CountryAll(String name, int population, double longtitude, double latitude, String currencies, String alpha2Code) {
        this.name = name;
        this.population = population;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.currencies = currencies;
        this.alpha2Code = alpha2Code;
    }

    public CountryAll(String name, int population, double longtitude, double latitude, String currencies, String alpha2Code, int geonameId, String flag, String capital, String languages) {
        this.name = name;
        this.population = population;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.currencies = currencies;
        this.alpha2Code = alpha2Code;
        this.geonameId = geonameId;
        this.flag = flag;
        this.capital = capital;
        this.languages = languages;
    }

    public CountryAll(String name, int population, double longtitude, double latitude, String currencies, String alpha2Code, int geonameId, String flag, String capital, String languages, String neighbours) {
        this.name = name;
        this.population = population;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.currencies = currencies;
        this.alpha2Code = alpha2Code;
        this.geonameId = geonameId;
        this.flag = flag;
        this.capital = capital;
        this.languages = languages;
        this.neighbours = neighbours;
    }

    public CountryAll(String name, int population, double longtitude, double latitude, String currencies, String alpha2Code, int geonameId, String flag, String capital, String languages, String neighbours, String countryCode, float temperature, String weatherCondition, int humidity, String clouds, String datetime, float windSpeed) {
        this.name = name;
        this.population = population;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.currencies = currencies;
        this.alpha2Code = alpha2Code;
        this.geonameId = geonameId;
        this.flag = flag;
        this.capital = capital;
        this.languages = languages;
        this.neighbours = neighbours;
        this.countryCode = countryCode;
        this.temperature = temperature;
        this.weatherCondition = weatherCondition;
        this.humidity = humidity;
        this.clouds = clouds;
        this.datetime = datetime;
        this.windSpeed = windSpeed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getCurrencies() {
        return currencies;
    }

    public void setCurrencies(String currencies) {
        this.currencies = currencies;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public int getGeonameId() {
        return geonameId;
    }

    public void setGeonameId(int geonameId) {
        this.geonameId = geonameId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(String neighbours) {
        this.neighbours = neighbours;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    @Override
    public String toString() {
        return "CountryAll{" + "name=" + name + ", population=" + population + ", longtitude=" + longtitude + ", latitude=" + latitude + ", currencies=" + currencies + ", alpha2Code=" + alpha2Code + '}';
    }

   
    public String toString1() {
        return "CountryAll{" + "name=" + name + ", population=" + population + ", longtitude=" + longtitude + ", latitude=" + latitude + ", currencies=" + currencies + ", alpha2Code=" + alpha2Code + ", geonameId=" + geonameId + ", flag=" + flag + ", capital=" + capital + ", languages=" + languages + ", neighbours=" + neighbours + ", countryCode=" + countryCode + ", temperature=" + temperature + ", weatherCondition=" + weatherCondition + ", humidity=" + humidity + ", clouds=" + clouds + ", datetime=" + datetime + ", windSpeed=" + windSpeed + '}';
    }
    
}
