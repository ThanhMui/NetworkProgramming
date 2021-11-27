package SERVER.Model;

public class CovidInfo {
	private int confirmed;
	private int deaths;
	private int recovered;
	private int active;
	private String date;
	private int population;
	private int confirmedDaily;
	private int deathsDaily;
	private int recoveredDaily;
	
	

	public CovidInfo(int confirmed, int deaths, int recovered, int active, String date, int population,
			int confirmedDaily, int deathsDaily, int recoveredDaily) {
		super();
		this.confirmed = confirmed;
		this.deaths = deaths;
		this.recovered = recovered;
		this.active = active;
		this.date = date;
		this.population = population;
		this.confirmedDaily = confirmedDaily;
		this.deathsDaily = deathsDaily;
		this.recoveredDaily = recoveredDaily;
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

	public int getConfirmedDaily() {
		return confirmedDaily;
	}

	public void setConfirmedDaily(int confirmedDaily) {
		this.confirmedDaily = confirmedDaily;
	}

	public int getDeathsDaily() {
		return deathsDaily;
	}

	public void setDeathsDaily(int deathsDaily) {
		this.deathsDaily = deathsDaily;
	}

	public int getRecoveredDaily() {
		return recoveredDaily;
	}

	public void setRecoveredDaily(int recoveredDaily) {
		this.recoveredDaily = recoveredDaily;
	}


	@Override
	public String toString() {
		return "CovidInfo \n confirmed=" + confirmed + "\n deaths=" + deaths + "\n recovered=" + recovered + "\n active="
				+ active + "\n population=" + population + "\n confirmedDaily=" + confirmedDaily
				+ "\n deathsDaily=" + deathsDaily + "\n recoveredDaily=" + recoveredDaily + "\n date=" + date + "\n\n////////\n\n";
	}
	
	
	
	
}
