package SERVER.Entity;

import SERVER.Model.Country;
import java.io.IOException;
import java.net.URI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONObject;
import SERVER.Model.CovidTopModel;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InformationCovidTop {
	static ArrayList<CovidTopModel> dataCovid;

	public static void main(String[] args) throws IOException, InterruptedException {
//		getData2();
//		sortListbyDeaths(dataCovid);
//		getValue("Vietnam");
		dataCovid = getData2();
//		System.out.println(dataCovid);
//		for (CovidTopInfor covidTopInfor: dataCovid) {
//			System.out.println(covidTopInfor.country+": "+covidTopInfor.flag);
//		}

		for (int i =0; i< dataCovid.size();i++) {
			System.out.println(dataCovid.get(i).getCountry());
		}

	}

//	public static void getData () throws IOException, InterruptedException{
//		HttpRequest request = HttpRequest.newBuilder()
//				.uri(URI.create("https://corona.lmao.ninja/v2/countries?yesterday&sort"))
//				.method("GET", HttpRequest.BodyPublishers.noBody())
//				.build();
//		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());	
//		dataCovid = new ArrayList<CovidTopModel>();
//		JSONArray arr = new JSONArray(response.body());
//		CovidTopModel data;
//		for (int i =0; i<arr.length()-1;i++) {
//			JSONObject ob = arr.getJSONObject(i);
//			String country = ob.getString("country");
//			int cases = ob.getInt("cases");
//			int deaths = ob.getInt("deaths");
//			int recovered = ob.getInt("recovered");
//			int active = ob.getInt("active");
//			int population = ob.getInt("population");
//			
//			JSONObject jsonCountry = ob.getJSONObject("countryInfo");			
//			String flag = jsonCountry.getString("flag");
//			data = new CovidTopModel(country, cases, deaths, recovered, active,population, flag);
//			dataCovid.add(data);
//			
//		}
//	}

	public static void getData() throws IOException, InterruptedException {
		String url = "https://corona.lmao.ninja/v2/countries?yesterday&sort";
		URL obj;

		try {

			obj = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
			connection.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			connection.connect();

			int responseCode = connection.getResponseCode();
			System.out.println("response= " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			System.out.println("parse json" + response.toString());

			// parse json
			JSONArray arr = new JSONArray(response.toString());
			CovidTopModel data;
		    ArrayList<CovidTopModel> dataCovid = new ArrayList<CovidTopModel>();
			for (int i = 0; i < arr.length() - 1; i++) {
				JSONObject ob = arr.getJSONObject(i);
				String country = ob.getString("country");
				int cases = ob.getInt("cases");
				int deaths = ob.getInt("deaths");
				int recovered = ob.getInt("recovered");
				int active = ob.getInt("active");
				int population = ob.getInt("population");

				JSONObject jsonCountry = ob.getJSONObject("countryInfo");
				String flag = jsonCountry.getString("flag");
				data = new CovidTopModel(country, cases, deaths, recovered, active, population, flag);
				dataCovid.add(data);
			}
			in.close();
		} catch (MalformedURLException ex) {
			Logger.getLogger(InfomationCountry.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static ArrayList<CovidTopModel> getData2 () throws IOException, InterruptedException{
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://corona.lmao.ninja/v2/countries?yesterday&sort"))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());	
		ArrayList<CovidTopModel> dataCovid = new ArrayList<CovidTopModel>();
		JSONArray arr = new JSONArray(response.body());
		CovidTopModel data;
		for (int i =0; i<arr.length()-1;i++) {
			JSONObject ob = arr.getJSONObject(i);
			String country = ob.getString("country");
			int cases = ob.getInt("cases");
			int deaths = ob.getInt("deaths");
			int recovered = ob.getInt("recovered");
			int active = ob.getInt("active");
			int population = ob.getInt("population");
			
			JSONObject jsonCountry = ob.getJSONObject("countryInfo");			
			String flag = jsonCountry.getString("flag");
			data = new CovidTopModel(country, cases, deaths, recovered, active, population,flag);
			dataCovid.add(data);			
		}
//		System.out.println(dataCovid.toString());
		return dataCovid;
	}
	public static ArrayList<CovidTopModel> getData22() throws IOException, InterruptedException {
		String url = "https://corona.lmao.ninja/v2/countries?yesterday&sort";
		URL obj;
		Country info = null;
		ArrayList<CovidTopModel> dataCovid = new ArrayList<CovidTopModel>();
		try {

			obj = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
			connection.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			connection.connect();
			int responseCode = connection.getResponseCode();
			System.out.println("response= " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			System.out.println("parse json" + response.toString());

			// parse json
			JSONArray arr = new JSONArray(response.toString());
			CovidTopModel data;
			JSONArray arr1 = new JSONArray(response.toString());
			CovidTopModel data1;
			for (int i = 0; i < arr1.length() - 1; i++) {
				JSONObject ob = arr1.getJSONObject(i);
				String country = ob.getString("country");
				int cases = ob.getInt("cases");
				int deaths = ob.getInt("deaths");
				int recovered = ob.getInt("recovered");
				int active = ob.getInt("active");
				int population = ob.getInt("population");

				JSONObject jsonCountry = ob.getJSONObject("countryInfo");
				String flag = jsonCountry.getString("flag");
				data1 = new CovidTopModel(country, cases, deaths, recovered, active, population, flag);
				dataCovid.add(data1);
			}

			in.close();
		} catch (MalformedURLException ex) {
			Logger.getLogger(InfomationCountry.class.getName()).log(Level.SEVERE, null, ex);
		}
		return dataCovid;

	}

	public static void sortListByCases(ArrayList<CovidTopModel> list) {
		Collections.sort(list, new Comparator<CovidTopModel>() {
			public int compare(CovidTopModel c1, CovidTopModel c2) {
				Integer case1 = c1.getCases();
				Integer case2 = c2.getCases();
				return case2.compareTo(case1);
			}
		});
	}

	public static void sortListbyDeaths(ArrayList<CovidTopModel> list) {
		Collections.sort(list, new Comparator<CovidTopModel>() {
			public int compare(CovidTopModel c1, CovidTopModel c2) {
				Integer case1 = c1.getDeaths();
				Integer case2 = c2.getDeaths();
				return case2.compareTo(case1);
			}
		});
	}

	public static void getValue(String tennuoc) {
		double act = 0, dea = 0, rec = 0, cases = 0;
		double res_per = 0, act_per = 0, dea_per = 0, rec_per = 0;
		for (int i = 0; i < dataCovid.size(); i++) {
			if (dataCovid.get(i).getCountry().equals(tennuoc)) {
				cases = dataCovid.get(i).getCases();
				act = (dataCovid.get(i).getActive() / cases) * 100;
				dea = (dataCovid.get(i).getDeaths() / cases) * 100;
				rec = (dataCovid.get(i).getRecovered() / cases) * 100;
			}
		}
		act_per = ((double) Math.round(act * 100) / 100);
		dea_per = ((double) Math.round(dea * 100) / 100);
		rec_per = ((double) Math.round(rec * 100) / 100);

		System.out.println("cases " + cases);
		System.out.println("act: " + act);
		System.out.println("dea: " + dea);
		System.out.println("rec: " + rec);
		System.out.println();
		System.out.println("act_per: " + act_per);
		System.out.println("dea_per: " + dea_per);
		System.out.println("rec_per: " + rec_per);
	}

}

//https://covidtrackerapi.bsg.ox.ac.uk/api/v2/stringency/date-range/2021-11-01/2021-11-30
//https://corona.lmao.ninja/v2/countries?yesterday&sort