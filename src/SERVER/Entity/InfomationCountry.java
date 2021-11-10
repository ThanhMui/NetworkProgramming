/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVER.Entity;

import SERVER.DAO.CountryInfomation;

import SERVER.Model.Country;
import SERVER.Model.CountryAll;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.in;
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
//        List<String> getCodeCountrys = getCodeCountrys();
//        for ( int i = 0 ;i< getCodeCountrys.size(); i++){
//           
//             List<Country> getInformations;
//            getInformations = getInformations(getCodeCountrys.get(i));
//            for( Country info : getInformations){
//                System.out.println("Country name: "+ info.getContinentName()+ "\n "+ "Population: "+ info.getPopulation());
//            }
//        }
//                  System.out.println("convert Kelvin to Cencius"+ convertKelvinToCelsius((float) 305.15));
      //  List<CountryAll> listCountryInfo = listCountryInfo();
//        System.out.println("size: "+ listCountryInfo.size());
//            List<String> codes=  getCodeCountrys();
//            for( int i =0 ; i< codes.size(); i++){
//                System.out.println((i)+ " code: "+ codes.get(i));
//            }
// get geoname
//        List<String> getCodeCountrys = getCodeCountrys();
//        for( String code:getCodeCountrys ){
//             List<GeoName> geonameId = getGeonameIdCountrys( code);
//             for( GeoName id : geonameId){
//                 System.out.println("id"+ id.toString());
//             }
//        }
           
//        for( CountryAll countryAll : listCountryInfo){
//            System.out.println("size: "+ );
//        }
//            List<String> neighbour = getNeighbours(290557);
//            for( String str : neighbour){
//                System.out.println("neighbour: "+ str);
//            }
//            List<GeoName> ids =getGeonameIdCountrys("vn");
//            for( GeoName id : ids){
//                System.out.println("sss"+ id.toString());
//            }
// list thong tin country thieu cái neighbour
//        List<CountryAll> getAllProducts = CountryInfomation.getCountrys();
//        List<CountryAll> listCountrys = getInfomationsCountrys(getAllProducts) ;
//        for( int i  =0 ; i< listCountrys.size(); i++){
//            System.out.println("country info: "+ i);
//            System.out.println("name: "+ listCountrys.get(i).getName());
//            System.out.println("population: "+ listCountrys.get(i).getPopulation());
//            System.out.println("longtitude: "+ listCountrys.get(i).getLongtitude());
//            System.out.println("latitude: "+ listCountrys.get(i).getLatitude());
//            System.out.println("currencies: "+ listCountrys.get(i).getCurrencies());
//            System.out.println("alpha2Code: "+ listCountrys.get(i).getAlpha2Code());
//            System.out.println("geonameId: "+ listCountrys.get(i).getGeonameId());
//            System.out.println("flag: "+ listCountrys.get(i).getFlag());
//            System.out.println("capital: "+ listCountrys.get(i).getCapital());
//            System.out.println("languages: "+ listCountrys.get(i).getLanguages());
//        }
            // lay thông tin đầy đủ được ghép lại từ những thoong tin thiếu
            int i =1;
            List<CountryAll> listCountryFull = getListInformationCountrys();
            for( CountryAll country: listCountryFull){
                System.out.print(i+ "Country: -");
                System.out.println("name: "+ country.getName());
                System.out.println("population: "+ country.getPopulation());
                System.out.println("longtitude: "+ country.getLongtitude());
                System.out.println("latitude: "+ country.getLatitude());
                System.out.println("currencies: "+ country.getCurrencies());
                System.out.println("alpha2Code: "+ country.getAlpha2Code());
                System.out.println("geonameId: "+ country.getGeonameId());
                System.out.println("flag: "+ country.getFlag());
                System.out.println("capital: "+ country.getCapital());
                System.out.println("languages: "+ country.getLanguages());
                System.out.println("neighbours: "+ country.getNeighbours());
                
            }
           // api list long&lat
//         List<CountryAll> list = listCountryInfo();
//         for( CountryAll country: list){
//             System.out.println("country: -name"+ country.getName());
//             System.out.println("longtitude: "+ country.getLongtitude());
//             System.out.println("lattitude: "+ country.getLatitude());
//             System.out.println("code: "+ country.getAlpha2Code());
//             System.out.println("languges: "+ country.getLanguages());
//             
//         }
         
    }
    // lay thong tin day du country chỉ thiếu thời tiết
    public static List<CountryAll> getListInformationCountrys() throws IOException{
        List<CountryAll> infomationFullCountrys = new ArrayList<>();
        //  lay list thong tin của các cua gia
        List<CountryAll> getCountrys = CountryInfomation.getCountrys();
        List<CountryAll> listCountrys = getInfomationsCountrys(getCountrys);
            for ( CountryAll country: listCountrys){
                 List<String> neighbours = getNeighbours(country.getGeonameId());
                  for( String neighbour: neighbours){
                        infomationFullCountrys.add(new CountryAll(country.getName(), country.getPopulation(), country.getLongtitude(),
                                country.getLatitude(), country.getCurrencies(), country.getAlpha2Code(),
                                country.getGeonameId(), country.getFlag(), country.getCapital(), country.getLanguages(), neighbour));
                  }
            }
        return infomationFullCountrys;
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
    //wearther
    //http://api.geonames.org/findNearByWeatherJSON?formatted=true&lat=42.45&lng=-2.3166666666666664&username=thanhmui&style=full
    //country
    //https://restcountries.com/v2/all
    public static List<CountryAll> listCountryInfo() throws IOException{
          int l =1;
          String url ="https://restcountries.com/v2/all";      
            URL obj = null;
             BufferedReader in = null;
            List<CountryAll> listInfoCountrys = new ArrayList<>();
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
           // System.out.println("parse json"+ response.toString());
            JSONArray  arrCountry = new JSONArray(response.toString());
            for( int i = 0 ;i< arrCountry.length(); i++){
            JSONObject obCountrys = arrCountry.getJSONObject(i);
            String name = obCountrys.getString("name");
            String alpha2Code = obCountrys.getString("alpha2Code").toLowerCase();
              
            //String capital = obCountrys.getString("capital");
            int population = obCountrys.getInt("population");
           String currencies = null;
           try{
                JSONArray json = obCountrys.getJSONArray("currencies");
                 for( int k = 0 ;k < json.length(); k++){
               JSONObject obN = json.getJSONObject(k);
               currencies= obN.getString("name");
           }
           }catch(Exception e ){
           }
            double latitude =0;
            double longtitude = 0;
          // JSONArray json = obCountrys.getJSONArray("currencies");
          try{
               JSONArray arrayJ = obCountrys.getJSONArray("latlng");
           for( int o = 0 ; o< arrayJ.length(); o++){
              latitude = arrayJ.getDouble(0);
              longtitude = arrayJ.getDouble(1);
           }
          }catch(Exception e){
          }
                System.out.println("lan: "+ l);
                System.out.println("name: "+ name);
               // System.out.println("capital: "+ capital);
                System.out.println("population: "+ population);
                System.out.println("currencies: "+ currencies);
                System.out.println("latitude: "+ latitude);
                System.out.println("longtitude: "+ longtitude);
                System.out.println("alpha2Code"+ alpha2Code);
                listInfoCountrys.add(new CountryAll(name, population, longtitude, latitude, currencies, alpha2Code));
           l++;
            }
            in.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(InfomationCountry.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listInfoCountrys;
    }
    //neighbor : lay ten cac nuoc lan can theo geonameId
    //http://api.geonames.org/neighboursJSON?formatted=true&geonameId=2658434&username=thanhmui&style=full
    public static List<String> getNeighbours(int geonameId) throws IOException{
         String url ="http://api.geonames.org/neighboursJSON?formatted=true&geonameId="+geonameId+"&username=thanhmui&style=full";      
            URL obj;
            List<String> listGeonameId = new ArrayList<>();
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
            try{
                  JSONArray ja_data = object.getJSONArray("geonames");
            int length = ja_data.length();
            String neighbours = "";
        for(int i=0; i<length; i++) {
          JSONObject jsonObj = ja_data.getJSONObject(i);
            String name = jsonObj.getString("countryName");
            if( i<length-1){
                neighbours +=name +",";
            }else if( i ==length-1){
                neighbours +=name;
            }
        }
        listGeonameId.add(neighbours);
            }catch(Exception e){
                
            }
          
            in.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(InfomationCountry.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listGeonameId;
    }
    //flag country theo codename
    //https://flagpedia.net/data/flags/normal/ae.png
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
           try{
                String code = jsonObj.getString("code");
            System.out.println("code"+ code);    
            listCodes.add(code);
           }catch(Exception e){
           }
        }
            in.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(InfomationCountry.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCodes;
    }
    // get country info excluding weather and neighbors
    // list country con thieu
    // list truyen vao lay tu db 
    public static List<CountryAll> getInfomationsCountrys(List<CountryAll> listCountrys) throws IOException{
        List<CountryAll> countryAlls = new ArrayList<>();
        for( CountryAll country : listCountrys){
            // get 1 geonameId(mac du de list nhung no chi tra ve 1 geonameId) 
            List<GeoName> getGeonameIdCountrys = getGeonameIdCountrys(country.getAlpha2Code());
             for( GeoName ge : getGeonameIdCountrys){
                 countryAlls.add(new CountryAll(country.getName(), country.getPopulation(), country.getLongtitude(),
                 country.getLatitude(), country.getCurrencies(), country.getAlpha2Code(),ge.getGeonameId(),ge.getFlag(), ge.getCapital(), ge.getLanguages()));
             }
        }
        return countryAlls;
    }
    //get geonameId Country
     public static List<GeoName> getGeonameIdCountrys(String codeName) throws IOException{
         String url ="http://api.geonames.org/countryInfoJSON?formatted=true&lang=it&username=thanhmui&style=full&country="+codeName+"";      
            URL obj;
            List<GeoName> listGeonameId = new ArrayList<>();
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
            int geonameId = jsonObj.getInt("geonameId");
            String capital = jsonObj.getString("capital");
            String language = jsonObj.getString("languages");
            String flag = "https://flagpedia.net/data/flags/normal/"+codeName+".png";
            System.out.println("geonameId"+ geonameId);   
            System.out.println("flag: "+ flag);
            listGeonameId.add(new GeoName(geonameId, flag, capital, language));
        }
            in.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(InfomationCountry.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listGeonameId;
    }
     
    // convert Kelvin to Celsius
    private static float convertKelvinToCelsius(float kelvin) {
        return (float) (kelvin - 273.15);
    }
}
