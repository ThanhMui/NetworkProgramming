/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVER.Entity;

import SERVER.DAO.CountryInfomation;
import SERVER.Model.City;
import SERVER.Model.CityInfomationFull;
import SERVER.Model.CountryAll;
import SERVER.Model.WeatherCity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;


public class InfomationCity {
	public static void main(String[] args) throws IOException {
		System.out.println(convertKelvinToCelsius((float) 292.26));
//         String search = "Madziwa";
//         CityInfomationFull cityInfo = new CityInfomationFull();
//        List<City> listCitys = getListCityFollowCountry();
//        int i=0;
//        for( City city: listCitys){
//           // if( city.getName().contains(search)){
//            System.out.println("City "+ i+ ": "+ city.getName());
//            float longitude = city.getLongitude(); //kinh do
//            float latitude = city.getLatitude(); // vi do
//            String countryName = city.getCountry();
//            int population = city.getPopulation();
//            String provinceId = city.getIdProvince();
//            String nameProvince = city.getNameProvince();
//            String timeZone = city.getTimezoneId();
//            WeatherCity weatherCity = InfomationWheather.getWeatherCitys(city.getName()); // lay thong tin thoi tiet theo thanh pho
//           // thong tin thoi tiet hien tai cua thanh pho
//            float cloud =  weatherCity.getClouds();
//            String description = weatherCity.getDescriptionWeather();
//            float minTemperature= weatherCity.getMin_Temperature();
//            float maxTemperature = weatherCity.getMax_Temperature();
//            float speedWind = weatherCity.getSpeedWind();
//            float temperature =   weatherCity.getTemperature();
//            cityInfo.setLongitude(longitude);
//            cityInfo.setLatitude(latitude);
//            cityInfo.setCountryName(countryName);
//            cityInfo.setPopulation(population);
//            cityInfo.setProvinceId(provinceId);
//            cityInfo.setNameProvince(nameProvince);
//            cityInfo.setTimeZone(timeZone);
//            cityInfo.setCloud(cloud);
//            cityInfo.setDescription(description);
//            cityInfo.setMinTemperature(minTemperature);
//            cityInfo.setMaxTemperature(maxTemperature);
//            cityInfo.setSpeedWind(speedWind);
//            cityInfo.setTemperature(temperature);
//                System.out.println("Infomation info full: "+ cityInfo.toString());
//                 i++;
//             //   break;
//       // }
//        }
	}

	// Hàm trả về danh sách thành phố dựa vào nước
	public static List<City> getListCityFollowCountry() throws IOException {
		List<City> listCitysWorld = new ArrayList<>();
		// Lấy danh sách mã nước dạng Alpha2Code
		for (CountryAll countryCode : CountryInfomation.getCountrys()) {
			List<City> getInfos = getInfo(countryCode.getAlpha2Code());
			System.out.println("lenght: " + getInfos.size());

			for (City city : getInfos) {
				System.out.println("city: " + city.toString() + "\n\n");
				listCitysWorld.add(city);
			}
		}
		return listCitysWorld;
	}

	// lay thong tin thanh pho theo codeCOuntry
	public static List<City> getInfo(String countryCode) throws MalformedURLException, IOException {
		List<City> listInfoCitys = new ArrayList<>();
		// Gọi api
		String url = "https://spott.p.rapidapi.com/places/autocomplete?limit=100&skip=0&country=" + countryCode
				+ "&type=CITY";
		URL obj = new URL(url);
		City city = new City();
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
		con.connect();
		// optional default is GET
		con.setRequestMethod("GET");
		// add request header
		con.setRequestProperty("x-rapidapi-host", "spott.p.rapidapi.com");
		con.setRequestProperty("x-rapidapi-key", "7c92a2d1d2msh0c1281c094c509ep1e0c35jsnb859fa794570");

		// Kiểm tra trạng thái
		int responseCode = con.getResponseCode();
                
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
                    if( responseCode == 200){
                       // duyệt response
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		

		// print in String
		System.out.println(response.toString());

		// Parse Json
		JSONArray arrInfoCity = new JSONArray(response.toString());
		for (int i = 0; i < arrInfoCity.length(); i++) {
			try {
				JSONObject objectInfo = arrInfoCity.getJSONObject(i);
				String nameCity = objectInfo.getString("name");
				int population = objectInfo.getInt("population");

				if (String.valueOf(population) == null) {
					population = 0;
				} else {
					population = population;
				}

				String timezoneId = objectInfo.getString("timezoneId");
				System.out.println("name: " + nameCity);
				System.out.println("population: " + population);
				System.out.println("imezoneId: " + timezoneId);

				JSONObject objectCountry = objectInfo.getJSONObject("country");
				String idCountry = objectCountry.getString("id");
				String nameCountry = objectCountry.getString("name");
				System.out.println("country: " + idCountry);
				System.out.println("name country: " + nameCountry);

				JSONObject objectProvince = objectInfo.getJSONObject("adminDivision1");
				String idProvince = null;
				String nameProvince = null;
				if (objectProvince != null) {
					idProvince = objectProvince.getString("id");
					nameProvince = objectProvince.getString("name");
				} else {
					idProvince = null;
					nameProvince = null;
				}

				JSONObject objectCoordinates = objectInfo.getJSONObject("coordinates");
				float latitude = objectCoordinates.getFloat("latitude");
				float longitude = objectCoordinates.getFloat("longitude");
				System.out.println("latitude: " + latitude);
				System.out.println("longtitude: " + longitude);
				System.out.println("\n\n");

				// Thêm thông tin vào list city
				listInfoCitys.add(new City(nameCity, latitude, longitude, idCountry, nameCountry, population,
						timezoneId, idProvince, nameProvince));
			} catch (Exception e) {
				System.out.println(e);
			}
		}
                in.close(); 
                    }
		
		return listInfoCitys;
	}

	// Hàm đổi độ K sang độ C
	private static float convertKelvinToCelsius(float kelvin) {
		return (float) (kelvin - 273.15);
	}

}
