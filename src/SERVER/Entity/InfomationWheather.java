
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVER.Entity;

import SERVER.Model.WeatherCity;
import SERVER.Model.Country;
import SERVER.Model.WeatherCountry;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;


public class InfomationWheather {
	// thong tin thoi tiet theo city
	public static WeatherCity getWeatherCitys(String cityName) throws IOException {
		String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName
				+ "&APPID=8578a4285a58470878a525bb5df4ec47";
		URL obj;
		WeatherCity weatherCity = null;
		Country info = null;
		try {
			obj = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
			int responseCode = connection.getResponseCode();
			System.out.println("response= " + responseCode);

			if (responseCode == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				System.out.println("parse json" + response.toString());

				// parse json
				JSONObject object = new JSONObject(response.toString());
				System.out.println(object);
				JSONObject coord = object.getJSONObject("coord");
				float longitude = coord.getFloat("lon");
				float latitude = coord.getFloat("lat");
				System.out.println("Kinh do: " + coord.getFloat("lon"));
				System.out.println("Vi do: " + coord.getFloat("lat"));

				JSONObject weather = object.getJSONArray("weather").getJSONObject(0); // trong cai Object co cai mang,
																						// trong mang co object thi lay
																						// object dau tien ne moi dien 0
				int length = weather.length();
				System.out.println("weather: " + weather.getString("description"));
				String description = weather.getString("description");
				// lay temperature
				JSONObject mainTemp = object.getJSONObject("main");
//            float temp = convertKelvinToCelsius(mainTemp.getFloat("temp"));
//            float temp_min =convertKelvinToCelsius(mainTemp.getFloat("temp_min"));
//            float temp_max = convertKelvinToCelsius(mainTemp.getFloat("temp_max"));

				float temp = (float) (Math.ceil(convertKelvinToCelsius(mainTemp.getFloat("temp")) * 10) / 10);
				float temp_min = (float) (Math.ceil(convertKelvinToCelsius(mainTemp.getFloat("temp_min")) * 10) / 10);
				float temp_max = (float) (Math.ceil(convertKelvinToCelsius(mainTemp.getFloat("temp_max")) * 10) / 10);
				System.out.println("temp: " + Math.ceil(temp * 10) / 10);
				System.out.println("temp_min: " + Math.ceil(temp_min * 10) / 10);
				System.out.println("temp_max: " + Math.ceil(temp_max * 10) / 10);
				// get wind
				JSONObject wind = object.getJSONObject("wind");
				float speed = wind.getFloat("speed");
				System.out.println("speed: " + speed);
				float clouds = object.getJSONObject("clouds").getFloat("all");
				System.out.println("clouds: " + clouds);
				// get country
				String countryName = object.getJSONObject("sys").getString("country");
				System.out.println("country Name: " + countryName);
				String city = object.getString("name");
				System.out.println("city: " + city);

				// Tạo list có weather city
				weatherCity = new WeatherCity(longitude, latitude, description, temp, temp_min, temp_max, speed, clouds,
						countryName, city);
				in.close();

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return weatherCity;
	}

	public List<InfomationWheather> infoWheather() {
		List<InfomationWheather> listWeathers = new ArrayList<>();
		return null;
	}

	// thong tin thoi tiet theo country
	// http://api.geonames.org/findNearByWeatherJSON?formatted=true&lat=18.4313831329346&lng=-64.623046875&username=thanhmui&style=full
	public static WeatherCountry getWeatherCountry(double latitude, double longitude) {
		String url = "http://api.geonames.org/findNearByWeatherJSON?formatted=true&lat=" + latitude + "&lng="
				+ longitude + "&username=thanhmui&style=full";
		URL obj;
		WeatherCountry weatherCountry = null;
		try {
			obj = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
			int responseCode = connection.getResponseCode();
			System.out.println("response= " + responseCode);
			if (responseCode == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				System.out.println("parse json" + response.toString());

				// parse json
				JSONObject object = new JSONObject(response.toString());
				System.out.println(object);
				JSONObject weatherObservationObj = object.getJSONObject("weatherObservation");
				String countryCode = weatherObservationObj.getString("countryCode");
				float temperature = weatherObservationObj.getFloat("temperature");
				String weatherCondition = weatherObservationObj.getString("weatherCondition");
				int humidity = weatherObservationObj.getInt("humidity");
				String clouds = weatherObservationObj.getString("clouds");
				String datetime = weatherObservationObj.getString("datetime");
				float windSpeed = weatherObservationObj.getFloat("windSpeed");

				// tạo list weather country
				weatherCountry = new WeatherCountry(countryCode, temperature, weatherCondition, humidity, clouds,
						datetime, windSpeed);
				in.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return weatherCountry;
	}

	public static void main(String[] args) throws IOException {
//        WeatherCity getWeatherCity = getWeatherCitys("Kandahār");
//        System.out.println("result: "+getWeatherCity.toString());
		WeatherCountry getWeatherCountry = getWeatherCountry(16, -64.623046875);
		if (getWeatherCountry != null) {
			System.out.println("countryCode: " + getWeatherCountry.getCountryCode());
			System.out.println("temperature: " + getWeatherCountry.getTemperature());
			System.out.println("weatherCondition: " + getWeatherCountry.getWeatherCondition());
			System.out.println("humidity: " + getWeatherCountry.getHumidity());
			System.out.println("clouds: " + getWeatherCountry.getClouds());
			System.out.println("datetime: " + getWeatherCountry.getDatetime());
			System.out.println("windSpeed: " + getWeatherCountry.getWindSpeed());
		}
//        System.out.println("weather country: "+ getWeatherCountry.toString());

	}

	//Đổi độ k sang độ c
	private static float convertKelvinToCelsius(float kelvin) {
		return (float) (kelvin - 273.15);
	}
}
