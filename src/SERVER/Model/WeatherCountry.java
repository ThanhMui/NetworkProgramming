/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVER.Model;

/**
 *
 * @author ASUS
 */
public class WeatherCountry {
    private String countryCode;
    private float temperature;
    private String weatherCondition;
    private int humidity;
    private String clouds;
    private String datetime;
    private float windSpeed;

    public WeatherCountry() {
    }

    public WeatherCountry(String countryCode, float temperature, String weatherCondition, int humidity, String clouds, String datetime, float windSpeed) {
        this.countryCode = countryCode;
        this.temperature = temperature;
        this.weatherCondition = weatherCondition;
        this.humidity = humidity;
        this.clouds = clouds;
        this.datetime = datetime;
        this.windSpeed = windSpeed;
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
        return "WeatherCountry{" + "countryCode=" + countryCode + ", temperature=" + temperature + ", weatherCondition=" + weatherCondition + ", humidity=" + humidity + ", clouds=" + clouds + ", datetime=" + datetime + ", windSpeed=" + windSpeed + '}';
    }

    
    
}
