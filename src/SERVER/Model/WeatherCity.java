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
public class WeatherCity {
    private float lon; // kinh do
    private float lat;
    private String descriptionWeather;
    private float temperature ;
    private float min_Temperature ;
    private float max_Temperature ;
    private float speedWind;
    private float clouds;
    private String country;
    private String city;

    public WeatherCity() {
    }

    public WeatherCity(float lon, float lat, String descriptionWeather, float temperature, float min_Temperature, float max_Temperature, float speedWind, float clouds, String country, String city) {
        this.lon = lon;
        this.lat = lat;
        this.descriptionWeather = descriptionWeather;
        this.temperature = temperature;
        this.min_Temperature = min_Temperature;
        this.max_Temperature = max_Temperature;
        this.speedWind = speedWind;
        this.clouds = clouds;
        this.country = country;
        this.city = city;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public String getDescriptionWeather() {
        return descriptionWeather;
    }

    public void setDescriptionWeather(String descriptionWeather) {
        this.descriptionWeather = descriptionWeather;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getMin_Temperature() {
        return min_Temperature;
    }

    public void setMin_Temperature(float min_Temperature) {
        this.min_Temperature = min_Temperature;
    }

    public float getMax_Temperature() {
        return max_Temperature;
    }

    public void setMax_Temperature(float max_Temperature) {
        this.max_Temperature = max_Temperature;
    }

    public float getSpeedWind() {
        return speedWind;
    }

    public void setSpeedWind(float speedWind) {
        this.speedWind = speedWind;
    }

    public float getClouds() {
        return clouds;
    }

    public void setClouds(float clouds) {
        this.clouds = clouds;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "WeatherCity{" + "lon=" + lon + ", lat=" + lat + ", descriptionWeather=" + descriptionWeather + ", temperature=" + temperature + ", min_Temperature=" + min_Temperature + ", max_Temperature=" + max_Temperature + ", speedWind=" + speedWind + ", clouds=" + clouds + ", country=" + country + ", city=" + city + '}';
    }
    
    
    
}
