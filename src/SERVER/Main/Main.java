/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVER.Main;

import SERVER.Model.CovidInfoModel;
import SERVER.Model.CovidTopModel;
import SERVER.Entity.InfomationCovid;
import SERVER.Entity.InformationCovidTop;
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
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;

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
    public static void main(String[] args) throws Exception {
        byte[] receiveData = new byte[6588];
        byte[] sendData = new byte[6588];
       Encrypt.RSAUtils.generateKey("./public.key", "./private.key");
        PublicKey publicKey = Encrypt.RSAUtils.getPublicKey("./public.key");
        PrivateKey privateKey = Encrypt.RSAUtils.getPrivateKey("./private.key");
        DatagramPacket receivePacket;
        InetAddress address;
        DatagramPacket sendPacket;
        SecretKey secretKey = null;
        String citySearch = "";
       String countrySearch = "";
        try {
            byte[] decrypted = null ;
             Map<String, PublicKey> listPublicKeys = new HashMap<>();
             DatagramSocket datagramSocket = new DatagramSocket(3333);
             System.out.println("Server connecting........");
            Map<String, SecretKey> clientsSecKeyMap = new HashMap<>();
            
            while (true) {
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            Map<String, List<byte[]>> listDataSends = new HashMap<>();
            Map<String, List<byte[]>> listMesagesSend = new HashMap<>();
            Map<String, List<byte[]>> mapReceiveObj = new HashMap<>();
            /* Receive Data From Client */
            datagramSocket.receive(receivePacket);
        // Create a string from the byte array with "UTF-8" encoding
            System.out.println("Client IP : "+receivePacket.getAddress());   
            System.out.println("Port : "+receivePacket.getPort());  
            String addr = String.valueOf(receivePacket.getAddress());
            String prt = String.valueOf(receivePacket.getPort());
            mapReceiveObj = (HashMap) deserialize(receivePacket.getData());
//               listDataReceives = (HashMap) deserialize(receivePacket.getData());
//               listMesagesReceives = (HashMap) deserialize(receivePacket.getData());
//               listSecretKeys  = (HashMap) deserialize(receivePacket.getData());
                /* Receive Data From Client */
                /* Handle Data From Client */
                //receivePacket.getData() -> String : hello
                //receivePacket.getData() -> Object : publickey (Object)
            if ( mapReceiveObj.containsKey("send1") && mapReceiveObj.size()==1) {
                System.out.println("Server received: " + mapReceiveObj.get("send1") + " from "
                        + receivePacket.getAddress().getHostAddress() + " at port "
                        + datagramSocket.getLocalPort());
                
                listPublicKeys.put("publicKey", publicKey);
                sendData = serialize(listPublicKeys );
                InetAddress add = receivePacket.getAddress();
                int port = receivePacket.getPort();
                sendPacket = new DatagramPacket(sendData, sendData.length, add, port);
                datagramSocket.send(sendPacket);
                     System.out.println("Server sent " + listPublicKeys.get("publicKey") + " to " + receivePacket.getAddress()
                       + " from port " + datagramSocket.getLocalPort());
            }else if( mapReceiveObj.containsKey("secretKey") && mapReceiveObj.size()== 1){
                byte[]encSecrec = mapReceiveObj.get("secretKey").get(0);
                 byte[] encryptSecrectKey = mapReceiveObj.get("secretKey").get(0);
                System.out.println("Server received: " + encryptSecrectKey + " from "
                        + receivePacket.getAddress().getHostAddress() + " at port "
                        + datagramSocket.getLocalPort());
                //giải mã key vừa nhận được a
                 decrypted = Encrypt.RSAUtils.decrypt(privateKey, encryptSecrectKey);
                 String keySecret = new String(decrypted);
                secretKey = Encrypt.Convert.convertStringToSecretKeyto(keySecret);
                clientsSecKeyMap.put(addr+prt,secretKey);
               System.out.println("decrypt: " + decrypted);
               System.out.println("decrypt private key:" + new String(decrypted));
            }else if( mapReceiveObj.containsKey("encMessage") && mapReceiveObj.size()> 0){
                byte[] tmpEncrypt = mapReceiveObj.get("encMessage").get(0);
               System.out.println("public key: " + tmpEncrypt);
                System.out.println("Server received: " + tmpEncrypt + " from "
                        + receivePacket.getAddress().getHostAddress() + " at port "
                        + datagramSocket.getLocalPort());
                secretKey = clientsSecKeyMap.get(addr+prt);
                byte[] tmpDecrypt = Encrypt.AESUtils.decrypt(secretKey, tmpEncrypt);
                System.out.println("Server received message from client: " + new String(tmpDecrypt));
                String result = new String(tmpDecrypt);
                if( result.contains("$city")){
                     citySearch = getNameCitys(result).get(0);
                      List<City> getInfoCityFulls = CityInfomation.getInfoCityFull(citySearch);
                       List<byte[]> listEncrypt = new ArrayList<>();
                    if( getInfoCityFulls.size()>0){
                       
                       for (City city : getInfoCityFulls) {
                    byte[] strEncryptName = Encrypt.AESUtils.encrypt(secretKey, city.getName().getBytes());
                    byte[] strEncryptClouds = Encrypt.AESUtils.encrypt(secretKey,String.valueOf(city.getClouds()) .getBytes());
                    byte[] strEncryptCountry = Encrypt.AESUtils.encrypt(secretKey, city.getCountry().getBytes());
                    byte[] strEncryptWeather = Encrypt.AESUtils.encrypt(secretKey, city.getDescriptionWeather().getBytes());
                    byte[] strEncryptIdCountry = Encrypt.AESUtils.encrypt(secretKey, city.getIdCountry().getBytes());
                    byte[] strEncryptIdProvince = Encrypt.AESUtils.encrypt(secretKey, city.getIdProvince().getBytes());
                    byte[] strEncryptLatitude = Encrypt.AESUtils.encrypt(secretKey, String.valueOf(city.getLatitude()).getBytes());
                    byte[] strEncryptLong = Encrypt.AESUtils.encrypt(secretKey, String.valueOf(city.getLongitude()).getBytes());
                    byte[] strEncryptMax = Encrypt.AESUtils.encrypt(secretKey, String.valueOf(city.getMax_Temperature()).getBytes());
                     byte[] strEncryptMin = Encrypt.AESUtils.encrypt(secretKey, String.valueOf(city.getMin_Temperature()).getBytes());
                      byte[] strEncryptTemp = Encrypt.AESUtils.encrypt(secretKey, String.valueOf(city.getTemperature()).getBytes());
                    byte[] strEncryptProvince = Encrypt.AESUtils.encrypt(secretKey, city.getNameProvince().getBytes());
                      byte[] strEncryptPopu = Encrypt.AESUtils.encrypt(secretKey, String.valueOf(city.getPopulation()).getBytes());
                        byte[] strEncryptSpeed = Encrypt.AESUtils.encrypt(secretKey, String.valueOf(city.getSpeedWind()).getBytes());
                          byte[] strEncryptTimeZ = Encrypt.AESUtils.encrypt(secretKey, city.getTimezoneId().getBytes());
                          
                    listEncrypt.add(strEncryptName);
                    listEncrypt.add(strEncryptClouds);
                    listEncrypt.add(strEncryptCountry);
                    listEncrypt.add(strEncryptWeather);
                    listEncrypt.add(strEncryptIdCountry);
                    listEncrypt.add(strEncryptIdProvince);
                    listEncrypt.add(strEncryptLatitude);
                    listEncrypt.add(strEncryptLong);
                     listEncrypt.add(strEncryptMax);
                    listEncrypt.add(strEncryptMin);
                    listEncrypt.add(strEncryptTemp);
                    listEncrypt.add(strEncryptProvince);
                     listEncrypt.add(strEncryptPopu);
                    listEncrypt.add(strEncryptSpeed);
                    listEncrypt.add(strEncryptTimeZ);
                    
                listMesagesSend.put("sendMessage", listEncrypt);
//                sendData = serialize("test");
                sendData = serialize(listMesagesSend);
                InetAddress add = receivePacket.getAddress();
                int port = receivePacket.getPort();
                sendPacket = new DatagramPacket(sendData, sendData.length,add, port);
                datagramSocket.send(sendPacket);
                }
                }else{
                  listMesagesSend.put("sendMessage", listEncrypt);
//                sendData = serialize("test");
                sendData = serialize(listMesagesSend);
                InetAddress add = receivePacket.getAddress();
                int port = receivePacket.getPort();
                sendPacket = new DatagramPacket(sendData, sendData.length,add, port);
                datagramSocket.send(sendPacket);
                    }
                      
                }else if(result.contains("$country")){
                     countrySearch = getNameCountrys(result).get(0);
                      List<CountryAll> getInfoCountryFulls = CountryInfomation.getInfoCountryFull(countrySearch);
                       List<byte[]> listEncrypt = new ArrayList<>();
                       if( getInfoCountryFulls.size() > 0){
                            for (CountryAll country : getInfoCountryFulls) {
                     byte[] strEncryptTimeZ = Encrypt.AESUtils.encrypt(secretKey, country.getAlpha2Code().getBytes());
                       byte[] strEncryptCap = Encrypt.AESUtils.encrypt(secretKey, country.getCapital().getBytes());
                         byte[] strEncryptClo = Encrypt.AESUtils.encrypt(secretKey, country.getClouds().getBytes());
                           byte[] strEncryptCodeCoun = Encrypt.AESUtils.encrypt(secretKey, country.getCountryCode().getBytes());
                             byte[] strEncryptCurre = Encrypt.AESUtils.encrypt(secretKey, country.getCurrencies().getBytes());
                    byte[] strEncryptDate = Encrypt.AESUtils.encrypt(secretKey, country.getDatetime().getBytes());
                      byte[] strEncryptFlag = Encrypt.AESUtils.encrypt(secretKey, country.getFlag().getBytes());
                      byte[] strEncryptGeo = Encrypt.AESUtils.encrypt(secretKey, String.valueOf(country.getGeonameId()).getBytes());
                        byte[] strEncryptHum = Encrypt.AESUtils.encrypt(secretKey,String.valueOf( country.getHumidity()).getBytes());
                          byte[] strEncryptLanguge = Encrypt.AESUtils.encrypt(secretKey, String.valueOf(country.getLanguages()).getBytes());
                            byte[] strEncryptLat = Encrypt.AESUtils.encrypt(secretKey,String.valueOf(country.getLatitude()) .getBytes());
                              byte[] strEncryptLong = Encrypt.AESUtils.encrypt(secretKey,String.valueOf(country.getLongtitude()) .getBytes());
                          byte[] strEncryptName = Encrypt.AESUtils.encrypt(secretKey,String.valueOf(country.getName()) .getBytes());
                          byte[] strEncryptNeigh = Encrypt.AESUtils.encrypt(secretKey,String.valueOf(country.getNeighbours()).getBytes());
                          byte[] strEncryptPopu = Encrypt.AESUtils.encrypt(secretKey, String.valueOf(country.getPopulation()).getBytes());
                          byte[] strEncryptTemp= Encrypt.AESUtils.encrypt(secretKey,String.valueOf(country.getTemperature() ).getBytes());
                           byte[] strEncryptSpeed= Encrypt.AESUtils.encrypt(secretKey,String.valueOf(country.getWindSpeed() ).getBytes());
                            byte[] strEncryptDesc= Encrypt.AESUtils.encrypt(secretKey,country.getWeatherCondition().getBytes());
                                         
                    listEncrypt.add(strEncryptTimeZ);
                     listEncrypt.add(strEncryptCap);
                      listEncrypt.add(strEncryptClo);
                       listEncrypt.add(strEncryptCodeCoun);
                        listEncrypt.add(strEncryptCurre);
                         listEncrypt.add(strEncryptDate);
                     listEncrypt.add(strEncryptFlag);
                      listEncrypt.add(strEncryptGeo);
                       listEncrypt.add(strEncryptHum);
                        listEncrypt.add(strEncryptLat);
                         listEncrypt.add(strEncryptLong);
                     listEncrypt.add(strEncryptName);
                      listEncrypt.add(strEncryptNeigh);
                       listEncrypt.add(strEncryptPopu);
                        listEncrypt.add(strEncryptTemp);
                        listEncrypt.add(strEncryptSpeed);
                        listEncrypt.add(strEncryptDesc);
                }
                listMesagesSend.put("sendMessage", listEncrypt);
//                sendData = serialize("test");
                sendData = serialize(listMesagesSend);
                InetAddress add = receivePacket.getAddress();
                int port = receivePacket.getPort();
                sendPacket = new DatagramPacket(sendData, sendData.length,add, port);
                datagramSocket.send(sendPacket);
                }else{
                  listMesagesSend.put("sendMessage", listEncrypt);
//                sendData = serialize("test");
                sendData = serialize(listMesagesSend);
                InetAddress add = receivePacket.getAddress();
                int port = receivePacket.getPort();
                sendPacket = new DatagramPacket(sendData, sendData.length,add, port);
                datagramSocket.send(sendPacket);
                    }
                }
            }
               
                /* Handle Data From Client */
                
                /* Send Data To Client */
               
                /* Send Data To Client */
            }
//         byte[] receiveData = new byte[658888536];
//         byte[] sendData = new byte[65888536];
//          
//         String citySearch = "";
//         String countrySearch = "";
//    try (DatagramSocket datagramSocket = new DatagramSocket(3333);)
//    {
//        while (true) {
//            try {
//                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
//                datagramSocket.receive(receivePacket);
//                String tmp =(String) deserialize(receivePacket.getData());
//               if( tmp.contains("$city")){
//                System.out.println("city: "+tmp);
//                citySearch = getNameCitys(tmp).get(0);
//                List<City> getInfoCityFulls = CityInfomation.getInfoCityFull(citySearch);
//                InetAddress address = receivePacket.getAddress();
//                int port = receivePacket.getPort();
//                sendData = serialize(getInfoCityFulls);
//                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
//                datagramSocket.send(sendPacket);
//                }else if( tmp.contains("$country")){
//                System.out.println("country: "+ tmp);
//                countrySearch = getNameCountrys(tmp).get(0);
//                List<CountryAll> getInfoCountryFulls = CountryInfomation.getInfoCountryFull(countrySearch);
//                InetAddress address = receivePacket.getAddress();
//                int port = receivePacket.getPort();
//                sendData = serialize(getInfoCountryFulls);
//                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
//                datagramSocket.send(sendPacket);
//                }
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
        }catch (IOException e) {
             Logger.getLogger(SERVER.Main.Main.class.getName()).log(Level.SEVERE, null, e);
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

	// Hàm chuyển obj sang bye
//	public static byte[] serialize(Object obj) throws IOException {
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		ObjectOutputStream os = new ObjectOutputStream(out);
//		os.writeObject(obj);
//		return out.toByteArray();
//	}
//
//	//Hàm chuyển byte sang object
//	public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
//		ByteArrayInputStream in = new ByteArrayInputStream(data);
//		ObjectInputStream is = new ObjectInputStream(in);
//		return is.readObject();
//	}

//	public static void main(String[] args) {
//		byte[] receiveData = new byte[658888536];
//		byte[] sendData = new byte[65888536];
//
//		String citySearch = "";
//		String countrySearch = "";
//		String covidSearch = "";
//
//		String country, dateStart, dateEnd;
//		try (DatagramSocket datagramSocket = new DatagramSocket(3333);) {
//			while (true) {
//				try {
//					//Nhận dữ liệu từ client
//					DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
//					datagramSocket.receive(receivePacket);
//					String tmp = (String) deserialize(receivePacket.getData());
//					
//					// kiểm tra từ khóa nhập vào
//					//Trường hợp client gửi từ khóa city
//					if (tmp.contains("$city")) {
//						System.out.println("city: " + tmp);
//						citySearch = getNameCitys(tmp).get(0);
//						List<City> getInfoCityFulls = CityInfomation.getInfoCityFull(citySearch);
//						InetAddress address = receivePacket.getAddress();
//						int port = receivePacket.getPort();
//						//Gửi dữ liệu cho client
//						sendData = serialize(getInfoCityFulls);
//						DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
//						datagramSocket.send(sendPacket);
//					}
//
//					//Trường hợp client gửi từ khóa country
//					else if (tmp.contains("$country")) {
//						System.out.println("country: " + tmp);
//						countrySearch = getNameCountrys(tmp).get(0);
//						List<CountryAll> getInfoCountryFulls = CountryInfomation.getInfoCountryFull(countrySearch);
//						InetAddress address = receivePacket.getAddress();
//						int port = receivePacket.getPort();
//						//Gửi dữ liệu cho client
//						sendData = serialize(getInfoCountryFulls);
//						System.err.println(sendData.length);
//						DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
//						datagramSocket.send(sendPacket);
//					}
//					
//					// Trường hợp client gửi từ khóa covid
//					else if (tmp.contains("$covid")) {
//						System.out.println(tmp);
//						covidSearch = getNameCitys(tmp).get(0);
//						System.out.println(covidSearch);
//						String[] str = covidSearch.split(",");
//						country = str[0];
//						dateStart = str[1];
//						dateEnd = str[2];
//						List<CovidInfoModel> listCovid = InfomationCovid.getDataCovid(country, dateStart, dateEnd);
//						InetAddress address = receivePacket.getAddress();
//						int port = receivePacket.getPort();
//						//Gửi dữ liệu cho client
//						sendData = serialize(listCovid);
//						System.out.println(sendData.length);
//						DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
//						datagramSocket.send(sendPacket);
//					}
//					
//					else if (tmp.equals("$topcovid")) {
//						ArrayList<CovidTopModel> dataCovid = new ArrayList<CovidTopModel>();
//						dataCovid = InformationCovidTop.getData2();
//						InetAddress address = receivePacket.getAddress();
//						int port = receivePacket.getPort();
//						//Gửi dữ liệu cho client
//						sendData = serialize(dataCovid);
//						System.out.println(sendData.length);
//						DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
//						datagramSocket.send(sendPacket);
//					}
//
//				} catch (ClassNotFoundException e) {
//					e.printStackTrace();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

//	private static List<String> getNameCitys(String url) {
//		StringTokenizer st = new StringTokenizer(url, "$");
//		List<String> arr = new ArrayList<>();
//		while (st.hasMoreTokens()) {
//			// System.out.println(""+ st.nextToken());
//			arr.add(st.nextToken());
//		}
//		return arr;
//	}
//
//	private static List<String> getNameCountrys(String url) {
//		StringTokenizer st = new StringTokenizer(url, "$");
//		List<String> arr = new ArrayList<>();
//		while (st.hasMoreTokens()) {
//			// System.out.println(""+ st.nextToken());
//			arr.add(st.nextToken());
//		}
//		return arr;
//	}

}
