/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVER.DAO;

import SERVER.Entity.InfomationCountry;
import SERVER.Entity.InfomationWheather;
import SERVER.Model.CountryAll;
import SERVER.Model.WeatherCountry;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class CountryInfomation {
     public static Connection conn = null;
     public static PreparedStatement ps = null;
     public static ResultSet rs = null;
     
     //insert country
    public static void insertCountry(String name, int population, String  currencies, double latitude,double longtitude,
        String alpha2Code){
       String query = "INSERT INTO [dbo].[Country]\n" +
                            "([name]\n" +
                            ",[population]\n" +
                            ",[currencies]\n" +
                            ",[latitude]\n" +
                            " ,[longtitude]\n" +
                            " ,[alpha2Code])\n" +
                            "VALUES\n" +
                            " (?,?,?,?,?,?) ";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setInt(2, population);
            ps.setString(3, currencies);
            ps.setFloat(4, (float) latitude);
            ps.setFloat(5, (float) longtitude);
            ps.setString(6, alpha2Code);
            ps.executeUpdate();
        } catch (Exception e) {
        	System.out.println("Lỗi thêm nước từ DAO");
        }
   }
    // insert thoong tin day du ngaoi tru thoi tiets
     public static void insertInformationCountrys(String name, int population, String  currencies, double latitude,double longtitude,
        String alpha2Code,int geonameId , String flag , String capital, String languages, String neighbours ){
       String query = "INSERT INTO [dbo].[CountryInformation]\n" +
                            "([name]\n" +
                            " ,[population]\n" +
                            " ,[currencies]\n" +
                            ",[latitude]\n" +
                            ",[longtitude]\n" +
                            " ,[alpha2Code]\n" +
                            "  ,[geonameId]\n" +
                            " ,[flag]\n" +
                            "  ,[capital]\n" +
                            "  ,[languages]\n" +
                            " ,[neighbours])\n" +
                            " VALUES\n" +
                            " (?, ?, ?,? ,?, ?, ?,? ,? ,? ,? ) ";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setInt(2, population);
            ps.setString(3, currencies);
            ps.setFloat(4, (float) latitude);
            ps.setFloat(5, (float) longtitude);
            ps.setString(6, alpha2Code);
            ps.setInt(7, geonameId);
            ps.setString(8, flag);
            ps.setString(9, capital);
            ps.setString(10, languages);
            ps.setString(11, neighbours);
            ps.executeUpdate();
        } catch (Exception e) {
        	System.out.println("Lỗi thêm thông tin nước từ DAO");
        }
   }
     
     //Trả về danh sách country
    public static List<CountryAll> getCountrys() {
        List<CountryAll> listCountrys = new ArrayList<>();
        String query = "select * from Country";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                listCountrys.add(new CountryAll(
                        rs.getString("name"),
                        rs.getInt("population"),
                        rs.getFloat("longtitude"),
                        rs.getFloat("latitude"),
                        rs.getString("currencies"),
                        rs.getString("alpha2Code")));
            }
        } catch (Exception e) {
        	System.out.println("Lỗi trả về danh sách nước trong DAO");
        }
        return listCountrys;
    }
    
    // tim thong tin country
     public static List<CountryAll> searchByNames(String txtSearch) {
        List<CountryAll> listInfoCountrys = new ArrayList<>();
        String query = "select * from CountryInformation\n"
                + "where name = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1,  txtSearch );
            rs = ps.executeQuery();
            while (rs.next()) {
                listInfoCountrys.add(new CountryAll(rs.getString("name"), 
                        rs.getInt("population"),
                        rs.getDouble("longtitude"),
                        rs.getDouble("latitude"),
                        rs.getString("currencies"),
                        rs.getString("alpha2Code"), 
                        rs.getInt("geonameId"),
                        rs.getString("flag"), 
                        rs.getString("capital"),
                        rs.getString("languages"),
                        rs.getString("neighbours")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listInfoCountrys;
    }
//     private String name;
//    private int population;
//    private double longtitude;
//    private double latitude ;
//    private String currencies;
//    private String alpha2Code;
//    private int geonameId;
//    private String flag;
//    private String capital;
//    private String languages;
//    private String neighbours;
////    thông tin thời tiết 
//    private String countryCode;
//    private float temperature;
//    private String weatherCondition;
//    private int humidity;
//    private String clouds;
//    private String datetime;
//    private float windSpeed;
    // lấy thông tin country đầy đủ
    public static List<CountryAll> getInfoCountryFull(String txtSearch){
        // list de chua list info country day du
        List<CountryAll> listInfoCountryFulls = new ArrayList<>();
        List<CountryAll> searchByNames  = searchByNames(txtSearch);
        for( CountryAll country: searchByNames){
            WeatherCountry weatherCountry = InfomationWheather.getWeatherCountry(country.getLatitude(), country.getLongtitude());
            if( weatherCountry!= null){
                listInfoCountryFulls.add(new CountryAll(country.getName(), country.getPopulation(), country.getLongtitude(),
                        country.getLatitude(), country.getCurrencies(), country.getAlpha2Code(), country.getGeonameId(),
                        country.getFlag(), country.getCapital(),
                        country.getLanguages(), country.getNeighbours(),
                        weatherCountry.getCountryCode(),weatherCountry.getTemperature(), weatherCountry.getWeatherCondition(),
                        weatherCountry.getHumidity(), weatherCountry.getClouds(), weatherCountry.getDatetime(), weatherCountry.getWindSpeed()));
            }else{
                listInfoCountryFulls.add(new CountryAll(country.getName(), country.getPopulation(), country.getLongtitude(),
                        country.getLatitude(), country.getCurrencies(), country.getAlpha2Code(), country.getGeonameId(),
                        country.getFlag(), country.getCapital(),
                        country.getLanguages(), country.getNeighbours(),
                        "rong",-6, "rong",
                        -6, "rong", "rong", -6));
            }
        }
        return listInfoCountryFulls;
    }
    public static void main(String[] args) throws IOException {
        // insert country
//    List<CountryAll> listCountryInfos = InfomationCountry.listCountryInfo();
//       
//       for( CountryAll country : listCountryInfos){
//           insertCountry(country.getName(), country.getPopulation(),
//           country.getCurrencies(), country.getLatitude(), country.getLongtitude(), country.getAlpha2Code());
//       }
//       // select country 
//       List<CountryAll> getAllProducts = getCountrys();
//       int i=1 ;
//       for( CountryAll country: getCountrys()){
//           System.out.println(i+ " coutry: "+ country.toString()+"\n");
//           i++;
//       }
        // insert thong tin country đầy đủ
//        List<CountryAll> listCountryAll = InfomationCountry.getListInformationCountrys();
//        for( CountryAll country : listCountryAll){
//             insertInformationCountrys(country.getName(), country.getPopulation(),
//             country.getCurrencies(), country.getLatitude(),
//             country.getLongtitude(), country.getAlpha2Code(), 
//             country.getGeonameId(), country.getFlag(), country.getCapital(),
//             country.getLanguages(), country.getNeighbours());
//        }
    	List<CountryAll> l= searchByNames("Egypt");
        System.out.println("l: "+ l.size());
    }
}
