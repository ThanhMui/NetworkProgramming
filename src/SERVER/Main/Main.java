/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVER.Main;


import SERVER.Model.CovidInfoModel;
import SERVER.Entity.InfomationCovid;
import SERVER.DAO.CityInfomation;
import SERVER.DAO.CountryInfomation;
import SERVER.Model.City;
import SERVER.Model.CountryAll;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author ASUS
 */
public class Main {
     public static byte[] serialize(Object obj) throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ObjectOutputStream os = new ObjectOutputStream(out);
    os.writeObject(obj);
    return out.toByteArray();
}
    public static Object deserialize(byte[] data) throws IOException,ClassNotFoundException {
    ByteArrayInputStream in = new ByteArrayInputStream(data);
    ObjectInputStream is = new ObjectInputStream(in);
    return is.readObject();
}
    public static void main(String[] args) {
         byte[] receiveData = new byte[658888536];
         byte[] sendData = new byte[65888536];
       
         String citySearch = "";
         String countrySearch = "";
         String covidSearch="";
         
         String country, dateStart, dateEnd;
    try (DatagramSocket datagramSocket = new DatagramSocket(3333);)
    {
        while (true) {
            try {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                datagramSocket.receive(receivePacket);
                String tmp =(String) deserialize(receivePacket.getData());
// kiểm tra từ khóa               
                if( tmp.contains("$city")){
                System.out.println("city: "+tmp);
                citySearch = getNameCitys(tmp).get(0);
                List<City> getInfoCityFulls = CityInfomation.getInfoCityFull(citySearch);
                InetAddress address = receivePacket.getAddress();
                int port = receivePacket.getPort();
                sendData = serialize(getInfoCityFulls);
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
                datagramSocket.send(sendPacket);
                }
                
                else if( tmp.contains("$country")){
                System.out.println("country: "+ tmp);
                countrySearch = getNameCountrys(tmp).get(0);
                List<CountryAll> getInfoCountryFulls = CountryInfomation.getInfoCountryFull(countrySearch);
                InetAddress address = receivePacket.getAddress();
                int port = receivePacket.getPort();
                sendData = serialize(getInfoCountryFulls);
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
                datagramSocket.send(sendPacket);
                }
                // gọi server xử lý covid chart
                else if (tmp.contains("$covid")) {
                System.out.println(tmp);
                covidSearch = getNameCitys(tmp).get(0);
                System.out.println(covidSearch);
                String [] str = covidSearch.split(",");
				country = str[0];
				dateStart = str[1];
				dateEnd = str [2];
				ArrayList<CovidInfoModel> listCovid = InfomationCovid.getDataCovid(country, dateStart, dateEnd);				
				InetAddress address = receivePacket.getAddress();
                int port = receivePacket.getPort();
                sendData = serialize(listCovid);
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
                datagramSocket.send(sendPacket);
                }
                
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    private static List<String> getNameCitys(String url){
        StringTokenizer st = new StringTokenizer(url, "$");   
        List<String> arr = new ArrayList<>();
         while (st.hasMoreTokens()) {
           //  System.out.println(""+ st.nextToken());
             arr.add(st.nextToken());
         }
       return arr;
    }
    private static List<String> getNameCountrys(String url){
         StringTokenizer st = new StringTokenizer(url, "$");   
        List<String> arr = new ArrayList<>();
         while (st.hasMoreTokens()) {
           //  System.out.println(""+ st.nextToken());
             arr.add(st.nextToken());
         }
       return arr;
    }
}
