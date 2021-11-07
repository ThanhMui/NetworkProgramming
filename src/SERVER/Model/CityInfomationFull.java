/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVER.Model;

import SERVER.Entity.InfomationWheather;

/**
 *
 * @author ASUS
 */
public class CityInfomationFull {
            float longitude ;
            float latitude ; // vi do
            String countryName ;
            int population ;
            String provinceId ;
            String nameProvince ;
            String timeZone ;
          
            float cloud ;
            String description ;
            float minTemperature;
            float maxTemperature ;
            float speedWind ;
            float temperature ;

    public CityInfomationFull() {
    }

    public CityInfomationFull(float longitude, float latitude, String countryName, int population, String provinceId, String nameProvince, String timeZone, WeatherCity weatherCity, float cloud, String description, float minTemperature, float maxTemperature, float speedWind, float temperature) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.countryName = countryName;
        this.population = population;
        this.provinceId = provinceId;
        this.nameProvince = nameProvince;
        this.timeZone = timeZone;
       
        this.cloud = cloud;
        this.description = description;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.speedWind = speedWind;
        this.temperature = temperature;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getNameProvince() {
        return nameProvince;
    }

    public void setNameProvince(String nameProvince) {
        this.nameProvince = nameProvince;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

 

    public float getCloud() {
        return cloud;
    }

    public void setCloud(float cloud) {
        this.cloud = cloud;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(float minTemperature) {
        this.minTemperature = minTemperature;
    }

    public float getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(float maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public float getSpeedWind() {
        return speedWind;
    }

    public void setSpeedWind(float speedWind) {
        this.speedWind = speedWind;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "CityInfomationFull{" + "longitude=" + longitude + ", latitude=" + latitude + ", countryName=" + countryName + ", population=" + population + ", provinceId=" + provinceId + ", nameProvince=" + nameProvince + ", timeZone=" + timeZone + ", cloud=" + cloud + ", description=" + description + ", minTemperature=" + minTemperature + ", maxTemperature=" + maxTemperature + ", speedWind=" + speedWind + ", temperature=" + temperature + '}';
    }
            
}
