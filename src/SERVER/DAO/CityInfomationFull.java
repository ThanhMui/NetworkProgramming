/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVER.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ASUS
 */
public class CityInfomationFull {
   public static Connection conn = null;
   public static PreparedStatement ps = null;
   public static ResultSet rs = null;
   
   //insert city
   public static void insertCity(float longitude, float latitude, String  countryName, int population, String provinceId,
         String nameProvince, String timeZone,  float cloud, String description, float minTemperature,float maxTemperature , float speedWind,float temperature ){
       String query = "INSERT INTO [dbo].[City]\n" +
                        "([longitude]\n" +
                        ",[latitude]\n" +
                        ",[countryName]\n" +
                        ",[population]\n" +
                        ",[provinceId]\n" +
                        " ,[nameProvince]\n" +
                        ",[timeZone]\n" +
                        " ,[cloud]\n" +
                        " ,[description]\n" +
                        " ,[minTemperature]\n" +
                        " ,[maxTemperature]\n" +
                        ",[speedWind]\n" +
                        ",[temperature])\n" +
                        " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setFloat(1, latitude);
            ps.setFloat(2, longitude);
            ps.setString(3, countryName);
            ps.setInt(4, population);
            ps.setString(5, provinceId);
            ps.setString(6, nameProvince);
            ps.setString(7, timeZone);
            ps.setFloat(8, cloud);
            ps.setString(9, description);
            ps.setFloat(10, minTemperature);
            ps.setFloat(11, maxTemperature);
            ps.setFloat(12, speedWind);
            ps.setFloat(13, temperature);
            ps.executeUpdate();
        } catch (Exception e) {
        	System.out.println("Lỗi thêm tất cả thông tin thành phố từ DAO");
        }
   }
    public static void main(String[] args) {
        
    }
}
