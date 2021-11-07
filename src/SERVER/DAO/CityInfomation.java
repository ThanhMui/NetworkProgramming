/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVER.DAO;

import static SERVER.Entity.InfomationCity.getListCityFollowCountry;
import SERVER.Model.City;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public static void main(String[] args) throws IOException {
        List<City> listCitys = getListCityFollowCountry();
        int i=0;
        for( City city: listCitys){
            //insert thong tin city vao db sau khi lay duoc tu api
            insertCity(city.getName(), city.getLatitude(), city.getLongitude(), city.getIdCountry(), city.getCountry(),city.getPopulation(), city.getTimezoneId(), city.getIdProvince(), city.getNameProvince());
                System.out.println(i +" - City: "+ city.toString());
                 i++;
             //   break;
           
       // }
        }

        
        
        
    }
}
