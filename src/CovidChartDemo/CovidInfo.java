package CovidChartDemo;

public class CovidInfo {

	int confirmed;
	int deaths;
	int recovered;
	int active;
	String date;
	int population;
	int confirmed_daily;
	int deaths_daily;
	int recovered_daily;
	public CovidInfo(int confirmed, int deaths, int recovered, int active, String date, int population,
			int confirmed_daily, int deaths_daily, int recovered_daily) {
		super();
		this.confirmed = confirmed;
		this.deaths = deaths;
		this.recovered = recovered;
		this.active = active;
		this.date = date;
		this.population = population;
		this.confirmed_daily = confirmed_daily;
		this.deaths_daily = deaths_daily;
		this.recovered_daily = recovered_daily;
	}
	public int getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(int confirmed) {
		this.confirmed = confirmed;
	}
	public int getDeaths() {
		return deaths;
	}
	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}
	public int getRecovered() {
		return recovered;
	}
	public void setRecovered(int recovered) {
		this.recovered = recovered;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public int getConfirmed_daily() {
		return confirmed_daily;
	}
	public void setConfirmed_daily(int confirmed_daily) {
		this.confirmed_daily = confirmed_daily;
	}
	public int getDeaths_daily() {
		return deaths_daily;
	}
	public void setDeaths_daily(int deaths_daily) {
		this.deaths_daily = deaths_daily;
	}
	public int getRecovered_daily() {
		return recovered_daily;
	}
	public void setRecovered_daily(int recovered_daily) {
		this.recovered_daily = recovered_daily;
	}
	
	
	
	
}
