import java.util.ArrayList;

public class Airport {
	private String name;
	private String code;
	private String city;
	private String country;
	private ArrayList<String> companies = new ArrayList<>();
	private ArrayList<Airport> directConnection = new ArrayList<>();
	
	
	public Airport(String name, String code, String city, String country) {
		this.name = name;
		this.code = code;
		this.city = city;
		this.country = country;
	}
	
	public void addConnection(Airport anAirport) {
		directConnection.add(anAirport);
	}
	
	public void addCompany(String aCompany) {
		companies.add(aCompany);
	}
	
	public boolean isDirectlyConnectedTo(Airport anAirport) {
		if(this.directConnection.contains(anAirport)) {
			return true;
		}
		return false;
	}
	
	public boolean isInDirectlyConnectedTo(Airport anAirport) {
		for(Airport a : this.directConnection) {
			if(anAirport.getDirectConnection().contains(a)) {
				return true;
			}
		}
		return false;
	}

	
	public ArrayList<Airport> getInDirectConnection(Airport anAirport) {
		ArrayList<Airport> inDirectConnections = new ArrayList<>();
		for(Airport a : this.directConnection) {
			if(anAirport.getDirectConnection().contains(a)) {
				inDirectConnections.add(a);
			}
		}
		return inDirectConnections;
	}
	
	public ArrayList<Airport> getCommonConnections(Airport anAirport){
		ArrayList<Airport> common = new ArrayList<>();
		for(Airport a : this.directConnection) {
			if(anAirport.getDirectConnection().contains(a)) {
				common.add(a);
			}
		}
		return common;
	}
	
	public void printCompanies() {
		for(String company : this.companies) {
			System.out.println(company);
		}
	}
	
	public ArrayList<Airport> getDirectConnection() {
		return directConnection;
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	public String getCode() {
		return code;
	}

	public ArrayList<String> getCompanies() {
		return companies;
	}

	public String getCountry() {
		return country;
	}
	
	
	
	
}
