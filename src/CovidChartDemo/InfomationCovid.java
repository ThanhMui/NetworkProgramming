package CovidChartDemo;

import SERVER.Entity.InfomationCountry;
import SERVER.Model.Country;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class InfomationCovid {
	public static void main(String[] args) {
		
		String country="Vietnam";
		String minDate= "2021-08-01", maxDate="2021-08-05"; //dinh dang yyyy-mm-dd
		
		try {
			ArrayList<CovidInfo> dataCovidOneDay = getDataCovid(country, minDate, maxDate);
//			System.out.println(dataCovidOneDay.toString());
			for (CovidInfo covidInfor: dataCovidOneDay) {
				System.out.println(covidInfor.date);
				System.out.println("Số ca nhiễm: "+covidInfor.confirmed);
				System.out.println("Số ca tử vong: "+covidInfor.deaths);
				System.out.println("Số ca hồi phục: "+covidInfor.recovered+"\n");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<CovidInfo> getDataCovid(String country, String minDate, String maxDate) throws IOException, InterruptedException {
		long startTime = System.currentTimeMillis();
//		Connection.Response res = Jsoup.connect("https://webhooks.mongodb-stitch.com/api/client/v2.0/app/covid-19-qppza/service/REST-API/incoming_webhook/countries_summary?country=" + country +"&min_date=" + minDate + "T00:00:00.000Z&max_date="+ maxDate +"T00:00:00.000Z&hide_fields=_id,%20uids,%20country_iso2s,%20states,%20combined_names,%20country_codes,%20country_iso3s")
//				.method(Connection.Method.GET)
//				.ignoreContentType(true)
//				.execute();
//		System.out.println(res.body());
            String url ="https://webhooks.mongodb-stitch.com/api/client/v2.0/app/covid-19-qppza/service/REST-API/incoming_webhook/countries_summary?country=" + country +"&min_date=" + minDate + "T00:00:00.000Z&max_date="+ maxDate +"T00:00:00.000Z&hide_fields=_id,%20uids,%20country_iso2s,%20states,%20combined_names,%20country_codes,%20country_iso3s";
            URL obj;
            Country info = null;
            ArrayList<CovidInfo> dataCovid = null;
        try {
            obj = new URL(url);
             HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            int responseCode = connection.getResponseCode();
            System.out.println("response= "+ responseCode);
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine  ;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine())!= null) {                
                response.append(inputLine);
            }
            System.out.println("parse json"+ response.toString());
//		HttpRequest request = HttpRequest.newBuilder()
//				.uri(URI.create("https://webhooks.mongodb-stitch.com/api/client/v2.0/app/covid-19-qppza/service/REST-API/incoming_webhook/countries_summary?country=" + country +"&min_date=" + minDate + "T00:00:00.000Z&max_date="+ maxDate +"T00:00:00.000Z&hide_fields=_id,%20uids,%20country_iso2s,%20states,%20combined_names,%20country_codes,%20country_iso3s"))
//				.method("GET", HttpRequest.BodyPublishers.noBody())
//				.build();
//		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//		System.out.println(response.body());
		 dataCovid = new ArrayList<CovidInfo>();
		CovidInfo data;
		 JSONArray  ar = new JSONArray(response.toString());
		//JSONArray ar = new JSONArray(response.body());
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
			data = new CovidInfo(confirmed, deaths, recovered, active, date, population, confirmed_daily, deaths_daily, recovered_daily);
			dataCovid.add(data);
		}
//		final long endTime = System.currentTimeMillis();
//		 
//		System.out.println("Total execution time: " + (endTime - startTime));
		 in.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(InfomationCountry.class.getName()).log(Level.SEVERE, null, ex);
        }
		return dataCovid;
	}
}
