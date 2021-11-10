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
public class CountryAll {
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
//    private String capital;

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

    @Override
    public String toString() {
        return "CountryAll{" + "name=" + name + ", population=" + population + ", longtitude=" + longtitude + ", latitude=" + latitude + ", currencies=" + currencies + ", alpha2Code=" + alpha2Code + '}';
    }

   
   
}
