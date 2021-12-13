package SERVER.Entity;

import SERVER.Model.Country;
import java.io.IOException;
import java.net.URI;
import java.sql.Date;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import SERVER.Model.CovidInfoModel;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InfomationCovid {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		String country="Vietnam";
		String minDate= "2021-08-01", maxDate="2021-08-05"; //dinh dang yyyy-mm-dd
		
//		try {
//			ArrayList<CovidInfoModel> dataCovidOneDay = getDataCovid(country, minDate, maxDate);
////			System.out.println(dataCovidOneDay.toString());
//			for (CovidInfoModel covidInfor: dataCovidOneDay) {
//				System.out.println(covidInfor.date);
//				System.out.println("Số ca nhiễm: "+covidInfor.confirmed);
//				System.out.println("Số ca tử vong: "+covidInfor.deaths);
//				System.out.println("Số ca hồi phục: "+covidInfor.recovered+"\n");
//			}
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
                    ArrayList<CovidInfoModel>getDataCovid =  getDataCovid2("Vietnam", "2021-08-01", "2021-08-05");
                    System.out.println("result: "+ getDataCovid.get(0).getDate());

	}

// lấy dữ liệu từ api theo quốc gia, ngày, và trả về ds Covid
//	public static ArrayList<CovidInfoModel> getDataCovid(String country, String minDate, String maxDate) throws IOException, InterruptedException {
//		HttpRequest request = HttpRequest.newBuilder()
//				.uri(URI.create("https://webhooks.mongodb-stitch.com/api/client/v2.0/app/covid-19-qppza/service/REST-API/incoming_webhook/countries_summary?country=" + country +"&min_date=" + minDate + "T00:00:00.000Z&max_date="+ maxDate +"T00:00:00.000Z&hide_fields=_id,%20uids,%20country_iso2s,%20states,%20combined_names,%20country_codes,%20country_iso3s"))
//				.method("GET", HttpRequest.BodyPublishers.noBody())
//				.build();
//		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//		
//		ArrayList<CovidInfoModel> dataCovid = new ArrayList<CovidInfoModel>();
//		CovidInfoModel data;
//		
//		JSONArray ar = new JSONArray(response.body());
//		for(int i = 0; i< ar.length()-1; i++) {
//			JSONObject oneDayData = ar.getJSONObject(i);
//			
//			int confirmed = oneDayData.getInt("confirmed");
//			int deaths = oneDayData.getInt("deaths");
//			int recovered = oneDayData.getInt("recovered");
//			int active =  confirmed - deaths - recovered;
//			String date = oneDayData.getString("date");
//			int population = oneDayData.getInt("population");
//			int confirmed_daily = oneDayData.getInt("confirmed_daily");
//			int deaths_daily = oneDayData.getInt("deaths_daily");
//			int recovered_daily = oneDayData.getInt("population");
//			data = new CovidInfoModel(confirmed, deaths, recovered, active, date, population, confirmed_daily, deaths_daily, recovered_daily);
//			dataCovid.add(data);
//		}	
//		return dataCovid;
//	}
        public static ArrayList<CovidInfoModel> getDataCovid2(String country, String minDate, String maxDate) throws IOException, InterruptedException {
		String url = "https://webhooks.mongodb-stitch.com/api/client/v2.0/app/covid-19-qppza/service/REST-API/incoming_webhook/countries_summary?country=" + country +"&min_date=" + minDate + "T00:00:00.000Z&max_date="+ maxDate +"T00:00:00.000Z&hide_fields=_id,%20uids,%20country_iso2s,%20states,%20combined_names,%20country_codes,%20country_iso3s";
		URL obj;
//		Country info = null;
                ArrayList<CovidInfoModel> dataCovid = new ArrayList<CovidInfoModel>();
		try {
			obj = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
                        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
                        connection.connect();
			int responseCode = connection.getResponseCode();
//			System.out.println("response= " + responseCode);
                        if( responseCode == 200){
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
//			System.out.println("parse json" + response.toString());
//                         ArrayList<CovidInfoModel> dataCovid = new ArrayList<CovidInfoModel>();
		CovidInfoModel data;
		
		JSONArray ar = new JSONArray(response.toString());
		for(int i = 0; i< ar.length()-1; i++) {
			JSONObject oneDayData = ar.getJSONObject(i);
			
			int confirmed = oneDayData.getInt("confirmed");
			int deaths = oneDayData.getInt("deaths");
			int recovered = oneDayData.getInt("recovered");
			int active =  confirmed - deaths - recovered;
			String date = oneDayData.getString("date");
			int population = oneDayData.getInt("population");
			int confirmed_daily = oneDayData.getInt("confirmed_daily");
			int deaths_daily = oneDayData.getInt("deaths_daily");
			int recovered_daily = oneDayData.getInt("population");
			data = new CovidInfoModel(confirmed, deaths, recovered, active, date, population, confirmed_daily, deaths_daily, recovered_daily);
			dataCovid.add(data);
		}	
			in.close();
                        }
		} catch (MalformedURLException ex) {
			Logger.getLogger(InfomationCountry.class.getName()).log(Level.SEVERE, null, ex);
		}
		return dataCovid;
	}
}
