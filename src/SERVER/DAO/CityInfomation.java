/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVER.DAO;
import SERVER.Entity.InfomationCity;
import SERVER.Entity.InfomationWheather;
import SERVER.Model.City;
import SERVER.Model.WeatherCity;
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
public class CityInfomation {
   public static Connection conn = null;
   public static PreparedStatement ps = null;
   public static ResultSet rs = null;
   public static void insertCity(String name, float latitude, float  longitude, String idCountry, String country,
         int population, String timezoneId,  String idProvince, String nameProvince){
       String query = "INSERT INTO [dbo].[City]\n" +
                                " ([name]\n" +
                                " ,[latitude]\n" +
                                ",[longitude]\n" +
                                ",[idCountry]\n" +
                                ",[country]\n" +
                                ",[population]\n" +
                                ",[timezoneId]\n" +
                                " ,[idProvince]\n" +
                                " ,[nameProvince])\n" +
                                "VALUES\n" +
                                " (?, ?,? ,? ,? ,? , ?, ?, ?) ";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setFloat(2, latitude);
            ps.setFloat(3, longitude);
            ps.setString(4, idCountry);
            ps.setString(5, country);
            ps.setInt(6, population);
            ps.setString(7, timezoneId);
            ps.setString(8, idProvince);
            ps.setString(9, nameProvince);
            ps.executeUpdate();
        } catch (Exception e) {
        }
   }
   // tim list tp theo ten gan dung
   public static List<City> searchByNames(String txtSearch) {
        List<City> list = new ArrayList<>();
        String query = "select * from City\n"
                + "where name = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1,  txtSearch );
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new City(
                        rs.getString("name"),
                        rs.getFloat("latitude"),
                        rs.getFloat("longitude"),
                        rs.getString("idCountry"),
                        rs.getString("country"),
                        rs.getInt("population"),
                        rs.getString("timezoneId"),
                        rs.getString("idProvince"),
                        rs.getString("nameProvince")));
            }
        } catch (Exception e) {
            System.out.println("thoat!");
        }
        return list;
    }
  
   // đây là thông tin đầy đủ bao gồm thời tiết
   // tim  tp theo ten , goi api thời tiết qua đưa vào 1 cái list để gọi qua giao diện client 
    public static List<City> getInfoCityFull(String nameCity) throws IOException {
        List<City> listInfos= new ArrayList<>();
        List<City> citysSearch = searchByNames(nameCity);
        for( City city : citysSearch){
            WeatherCity weather = InfomationWheather.getWeatherCitys(city.getName());
            if( weather!= null){
            listInfos.add(new City(city.getName(), city.getLatitude(), city.getLongitude(), city.getIdCountry(), city.getCountry(), 
                    city.getPopulation(), city.getTimezoneId(), city.getIdProvince(), city.getNameProvince(), 
                    weather.getDescriptionWeather(), weather.getTemperature(),
                    weather.getMin_Temperature(), weather.getMax_Temperature()
                    , weather.getSpeedWind(), weather.getClouds()));
            }else{// do api thoi tieet co nhieu tinh khong co nen phai xet null api do. Để dễ set -6 để khi qua GUI dễ dùng if hơn !
                 listInfos.add(new City(city.getName(), city.getLatitude(), city.getLongitude(), city.getIdCountry(), city.getCountry(), 
                    city.getPopulation(), city.getTimezoneId(), city.getIdProvince(), city.getNameProvince(), 
                    "", -6,
                    6, -6
                    , -6, -6));
            }
            
        }
        return listInfos;
    }
   public static City searchCityByName(String nameCity){
       String query = "select * from City\n"
                + "where name = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1,  nameCity );
            rs = ps.executeQuery();
            while (rs.next()) {
                return new City(                       
                        rs.getString("name"),
                        rs.getFloat("latitude"),
                        rs.getFloat("longitude"),
                        rs.getString("idCountry"),
                        rs.getString("country"),
                        rs.getInt("population"),
                        rs.getString("timezoneId"),
                        rs.getString("idProvince"),
                        rs.getString("nameProvince"));
            }
        } catch (Exception e) {
        }
        return null;
   }
//this.name = name;
//        this.latitude = latitude;
//        this.longitude = longitude;
//        this.idCountry = idCountry;
//        this.country = country;
//        this.population = population;
//        this.timezoneId = timezoneId;
//        this.idProvince = idProvince;
//        this.nameProvince = nameProvince;
//        this.descriptionWeather = descriptionWeather;
//        this.temperature = temperature;
//        this.min_Temperature = min_Temperature;
//        this.max_Temperature = max_Temperature;
//        this.speedWind = speedWind;
//        this.clouds = clouds;
    public static void main(String[] args) throws IOException {
//        List<City> listCitys = InfomationCity.getListCityFollowCountry();
//        int i=0;
//        for( City city: listCitys){
//            //insert thong tin city vao db sau khi lay duoc tu api
//            insertCity(city.getName(), city.getLatitude(), city.getLongitude(), city.getIdCountry(), city.getCountry()
//                    ,city.getPopulation(), city.getTimezoneId(), city.getIdProvince(), city.getNameProvince());
//                System.out.println(i +" - City: "+ city.toString());
//                 i++;
//             //   break;
//       // }
//        }
//        System.out.println("i: "+ i);
//        try {
//            City city1 = searchCityByName("Hội An");
//            System.out.println("city 1: "+ city1.toString());
//             List<City> searchByNames = searchByNames("Ho Chi Minh");
//             for( City city : searchByNames){
//                 System.out.println("thành phố là : " + city.toString()); 
//             }
//        } catch (Exception e) {
//            System.out.println("Không có thành phố nào");
//        }
//
//            List<City> citysFull =  getInfoCityFull("Jalalabad");
//            for( City city: citysFull){
//                System.out.println("name: "+ city.getName());
//                System.out.println("latitude: "+ city.getLatitude());
//                System.out.println("longitude: "+ city.getLongitude());
//                System.out.println("idCountry: "+ city.getIdCountry());
//                System.out.println("country: "+ city.getCountry());
//                System.out.println("population: "+ city.getPopulation());
//                System.out.println("timezoneId: "+ city.getTimezoneId());
//                System.out.println("idProvince: "+ city.getIdProvince());
//                System.out.println("nameProvince: "+ city.getNameProvince());
//                System.out.println("descriptionWeather: "+ city.getDescriptionWeather());
//                System.out.println("temperature: "+ city.getTemperature());
//                System.out.println("min_Temperature: "+ city.getMin_Temperature());
//                System.out.println("max_Temperature: "+ city.getMax_Temperature());
//                System.out.println("speedWind: "+ city.getSpeedWind());
//                System.out.println("clouds: "+ city.getClouds());
//            }
    }
}
