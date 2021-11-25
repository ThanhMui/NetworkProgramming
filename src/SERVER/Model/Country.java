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
public class Country implements Serializable{
    private int population;
    private String countryName;

    public Country(int population, String continentName) {
        this.population = population;
        this.countryName = continentName;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getContinentName() {
        return countryName;
    }

    public void setContinentName(String continentName) {
        this.countryName = continentName;
    }
    
}
