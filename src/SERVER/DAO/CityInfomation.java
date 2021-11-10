/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVER.DAO;
import SERVER.Entity.InfomationCity;
import SERVER.Model.City;
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
   public static  List<City> searchByNames(String txtSearch) {
        List<City> list = new ArrayList<>();
        String query = "select * from City\n"
                + "where name like ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
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
   // tim  tp theo ten 
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
   
    public static void main(String[] args) throws IOException {
//        List<City> listCitys = InfomationCity.getListCityFollowCountry();
//        int i=0;
//        for( City city: listCitys){
//            //insert thong tin city vao db sau khi lay duoc tu api
//            insertCity(city.getName(), city.getLatitude(), city.getLongitude(), city.getIdCountry(), city.getCountry(),city.getPopulation(), city.getTimezoneId(), city.getIdProvince(), city.getNameProvince());
//                System.out.println(i +" - City: "+ city.toString());
//                 i++;
//             //   break;
//           
//       // }
//        }
        try {
            City city1 = searchCityByName("Ho Chi Minh City");
            System.out.println("city 1: "+ city1.toString());
             List<City> searchByNames = searchByNames("Ho Chi Minh");
             for( City city : searchByNames){
                 System.out.println("thành phố là : " + city.toString()); 
             }
        } catch (Exception e) {
            System.out.println("Không có thành phố nào");
        }
//       
    }
}
