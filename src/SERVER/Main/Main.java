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
       String covidSearch = "";
       String topCovidSearch = "";
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
                       //Chuyển list city thành mảng byte
                       byte[] encryptList = serialize(getInfoCityFulls);
                       //Mã hóa list city đã được đưa về byte
                       byte[] listCityEncrypt = Encrypt.AESUtils.encrypt(secretKey, encryptList);
                       listEncrypt.add(listCityEncrypt);

                    listMesagesSend.put("sendMessage", listEncrypt);
//                sendData = serialize("test");
                sendData = serialize(listMesagesSend);
                InetAddress add = receivePacket.getAddress();
                int port = receivePacket.getPort();
                sendPacket = new DatagramPacket(sendData, sendData.length,add, port);
                datagramSocket.send(sendPacket);
                }
                      
                else if(result.contains("$country")){
                     countrySearch = getNameCountrys(result).get(0);
                      List<CountryAll> getInfoCountryFulls = CountryInfomation.getInfoCountryFull(countrySearch);
                      List<byte[]> listEncrypt = new ArrayList<>();
                      byte[] encryptList = serialize(getInfoCountryFulls);
                      //Mã hóa list country đã được đưa về byte
                      byte[] listCityEncrypt = Encrypt.AESUtils.encrypt(secretKey, encryptList);
                      listEncrypt.add(listCityEncrypt);
                     
                listMesagesSend.put("sendMessage", listEncrypt);
//                sendData = serialize("test");
                sendData = serialize(listMesagesSend);
                InetAddress add = receivePacket.getAddress();
                int port = receivePacket.getPort();
                sendPacket = new DatagramPacket(sendData, sendData.length,add, port);
                datagramSocket.send(sendPacket);
                }
                
                else if(result.contains("$covid")){
                	
                	System.out.println(result);
					covidSearch = getNameCitys(result).get(0);
					System.out.println(covidSearch);
					String[] str = covidSearch.split(",");
					String country = str[0];
					String dateStart = str[1];
					String dateEnd = str[2];
                    covidSearch = getNameCountrys(result).get(0);
                     List<CovidInfoModel> getInfoCovidFulls = InfomationCovid.getDataCovid(country, dateStart, dateEnd);
                     List<byte[]> listEncrypt = new ArrayList<>();
                     byte[] encryptList = serialize(getInfoCovidFulls);
                     //Mã hóa list country đã được đưa về byte
                     byte[] listCovidEncrypt = Encrypt.AESUtils.encrypt(secretKey, encryptList);
                     listEncrypt.add(listCovidEncrypt);
                    
               listMesagesSend.put("sendMessage", listEncrypt);
//               sendData = serialize("test");
               sendData = serialize(listMesagesSend);
               InetAddress add = receivePacket.getAddress();
               int port = receivePacket.getPort();
               sendPacket = new DatagramPacket(sendData, sendData.length,add, port);
               datagramSocket.send(sendPacket);
               }

	            else if(result.contains("$topcovid")){
	            	
	            	ArrayList<CovidTopModel> dataCovid = new ArrayList<CovidTopModel>();
					dataCovid = InformationCovidTop.getData2();
	                  List<byte[]> listEncrypt = new ArrayList<>();
	                     byte[] encryptList = serialize(dataCovid);
	                     //Mã hóa list country đã được đưa về byte
	                     byte[] listCovidEncrypt = Encrypt.AESUtils.encrypt(secretKey, encryptList);
	                     listEncrypt.add(listCovidEncrypt);
	                
			           listMesagesSend.put("sendMessage", listEncrypt);
			//           sendData = serialize("test");
			           sendData = serialize(listMesagesSend);
			           InetAddress add = receivePacket.getAddress();
			           int port = receivePacket.getPort();
			           sendPacket = new DatagramPacket(sendData, sendData.length,add, port);
			           datagramSocket.send(sendPacket);
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
