package SERVER.Model;

import javax.swing.ImageIcon;
import java.io.Serializable;
public class CovidTopModel implements Serializable{
	String country;
	String flag;
	int cases, deaths, recovered, active, casesPerOneMillion, deathsPerOneMillion,population;
	public CovidTopModel(String country, String flag, int cases, int deaths, int recovered, int active,
			int casesPerOneMillion, int deathsPerOneMillion, int population) {
		super();
		this.country = country;
		this.flag = flag;
		this.cases = cases;
		this.deaths = deaths;
		this.recovered = recovered;
		this.active = active;
		this.casesPerOneMillion = casesPerOneMillion;
		this.deathsPerOneMillion = deathsPerOneMillion;
		this.population = population;
	}
	
	public CovidTopModel(String country, int cases, int deaths, int recovered, int active, int population, String flag) {
		super();
		this.country = country;
		this.cases = cases;
		this.deaths = deaths;
		this.recovered = recovered;
		this.active = active;
		this.population = population;
		this.flag = flag;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public int getCases() {
		return cases;
	}

	public void setCases(int cases) {
		this.cases = cases;
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

	public int getCasesPerOneMillion() {
		return casesPerOneMillion;
	}

	public void setCasesPerOneMillion(int casesPerOneMillion) {
		this.casesPerOneMillion = casesPerOneMillion;
	}

	public int getDeathsPerOneMillion() {
		return deathsPerOneMillion;
	}

	public void setDeathsPerOneMillion(int deathsPerOneMillion) {
		this.deathsPerOneMillion = deathsPerOneMillion;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}
	
	
}
