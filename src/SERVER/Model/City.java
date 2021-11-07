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
public class City {
   private String name;
   private float latitude;
   private float longitude; 
   private String idCountry;
   private String country; 
   private int population; 
   private String timezoneId;
   private String idProvince;  //adminDivision1- name
    private String nameProvince;  //adminDivision1- name

   
    public City() {
    }

    public City(String name, float latitude, float longitude, String idCountry, String country, int population, String timezoneId, String idProvince, String nameProvince) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.idCountry = idCountry;
        this.country = country;
        this.population = population;
        this.timezoneId = timezoneId;
        this.idProvince = idProvince;
        this.nameProvince = nameProvince;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(String idCountry) {
        this.idCountry = idCountry;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getTimezoneId() {
        return timezoneId;
    }

    public void setTimezoneId(String timezoneId) {
        this.timezoneId = timezoneId;
    }

    public String getIdProvince() {
        return idProvince;
    }

    public void setIdProvince(String idProvince) {
        this.idProvince = idProvince;
    }

    public String getNameProvince() {
        return nameProvince;
    }

    public void setNameProvince(String nameProvince) {
        this.nameProvince = nameProvince;
    }

    @Override
    public String toString() {
        return "CityTest{" + "name=" + name + ", latitude=" + latitude + ", longitude=" + longitude + ", idCountry=" + idCountry + ", country=" + country + ", population=" + population + ", timezoneId=" + timezoneId + ", idProvince=" + idProvince + ", nameProvince=" + nameProvince + '}';
    }

   
}
