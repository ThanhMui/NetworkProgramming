/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVER.Entity;

import SERVER.Model.Country;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author ASUS
 */
public class InfomationCountry {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
       // System.out.println("Country name: "+ getInformation().getContinentName()+ " Population: "+ getInformation().getPopulation());
        List<String> getCodeCountrys = getCodeCountrys();
        for ( int i = 0 ;i< getCodeCountrys.size(); i++){
           
             List<Country> getInformations;
            getInformations = getInformations(getCodeCountrys.get(i));
            for( Country info : getInformations){
                System.out.println("Country name: "+ info.getContinentName()+ "\n "+ "Population: "+ info.getPopulation());
            }
        }
                  System.out.println("convert Kelvin to Cencius"+ convertKelvinToCelsius((float) 305.15));
                      
    }
    // lay thong tin api cua 1 quoc gia
    public static Country getInformation() throws IOException{
        
         String url ="http://api.geonames.org/countryInfoJSON?formatted=true&lang=it&country=TH&username=thanhmui&style=full";
       
            URL obj;
            Country info = null;
        try {
            obj = new URL(url);
             HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            int responseCode = connection.getResponseCode();
            System.out.println("response= "+ responseCode);
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine  ;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine())!= null) {                
                response.append(inputLine);
            }
            System.out.println("parse json"+ response.toString());
            JSONObject  object = new JSONObject(response.toString());
            System.out.println(object);
            JSONArray ja_data = object.getJSONArray("geonames");
            int length = ja_data.length();
        for(int i=0; i<length; i++) {
          JSONObject jsonObj = ja_data.getJSONObject(i);
          // getting inner array Ingredients
            int ds = jsonObj.getInt("population");
            String countryName = jsonObj.getString("countryName");
            System.out.println("ds"+ ds);
            System.out.println("coutry"+ countryName);           
             info = new Country(ds, countryName);
            System.out.println("ìno"+ info.getContinentName()+ "ds: "+ info.getPopulation());
        }
            in.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(InfomationCountry.class.getName()).log(Level.SEVERE, null, ex);
        }
        return info;
    
    }
    // lay thong tin api cua nhieu quoc gia
       public static List<Country> getInformations(String code) throws IOException{
         
          List< Country> listInfos = new ArrayList<>();
            BufferedReader in = null;
       
            System.out.println("code countnry: "+ code);
             String url ="http://api.geonames.org/countryInfoJSON?formatted=true&lang=it&username=thanhmui&style=full&country="+code;       
            URL obj;
            Country info = null;
        try {
            obj = new URL(url);
             HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            int responseCode = connection.getResponseCode();
            System.out.println("response= "+ responseCode);
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine  ;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine())!= null) {                
                response.append(inputLine);
            }
            System.out.println("parse json"+ response.toString());
            JSONObject  object = new JSONObject(response.toString());
            System.out.println(object);
            JSONArray ja_data = object.getJSONArray("geonames");
            int length = ja_data.length();
        for(int i=0; i<length; i++) {
          JSONObject jsonObj = ja_data.getJSONObject(i);
          // getting inner array Ingredients
            int ds = jsonObj.getInt("population");
            String countryName = jsonObj.getString("countryName");
            System.out.println("ds"+ ds);
            System.out.println("coutry"+ countryName);           
             info = new Country(ds, countryName);
            System.out.println("ìno"+ info.getContinentName()+ "ds: "+ info.getPopulation()+ "\n");
            listInfos.add(info);
        }
             in.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(InfomationCountry.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listInfos;
    
    }
       // lay macode quoc gia de noi chuoi lay thong tin nhieu quoc gia
    public static List<String> getCodeCountrys() throws IOException{
        
         String url ="https://api.printful.com/countries";      
            URL obj;
           
            List<String> listCodes = new ArrayList<>();
        try {
            obj = new URL(url);
             HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            int responseCode = connection.getResponseCode();
            System.out.println("response= "+ responseCode);
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine  ;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine())!= null) {                
                response.append(inputLine);
            }
            System.out.println("parse json"+ response.toString());
            JSONObject  object = new JSONObject(response.toString());
            System.out.println(object);
            JSONArray ja_data = object.getJSONArray("result");
            int length = ja_data.length();
        for(int i=0; i<length; i++) {
          JSONObject jsonObj = ja_data.getJSONObject(i);
          // getting inner array Ingredients
           
            String code = jsonObj.getString("code");
          
            System.out.println("code"+ code);    
            
            listCodes.add(code);
          
        }
            in.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(InfomationCountry.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCodes;
    
    }
    // convert Kelvin to Celsius
    private static float convertKelvinToCelsius(float kelvin) {
        return (float) (kelvin - 273.15);
    }
}
