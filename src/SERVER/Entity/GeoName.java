/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVER.Entity;

/**
 *
 * @author ASUS
 */
public class GeoName {
     private  int geonameId ;
     private String flag;
     private String capital;
     private String languages;
    public GeoName() {
    }

    public GeoName(int geonameId, String flag, String capital, String languages) {
        this.geonameId = geonameId;
        this.flag = flag;
        this.capital = capital;
        this.languages = languages;
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

    @Override
    public String toString() {
        return "GeoName{" + "geonameId=" + geonameId + ", flag=" + flag + ", capital=" + capital + ", languages=" + languages + '}';
    }

    
     
}
