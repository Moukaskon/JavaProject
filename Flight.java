public class Flight {
	private Airport airportA;
	private Airport airportB;
	private int minutes;
	private String company;
	
	public Flight(Airport airport_1, Airport airport_2, int minutes, String company) {
		this.airportA = airport_1;
		this.airportB = airport_2;
		this.minutes = minutes;
		this.company = company;
		airport_1.addConnection(airport_2);
		airport_2.addConnection(airport_1);
	}

	
	
	public String getCompany() {
		return company;
	}



	public Airport getAirportA() {
		return airportA;
	}

	public Airport getAirportB() {
		return airportB;
	}



	public int getMinutes() {
		return minutes;
	}
	
	public String toString() {
		return "Flight operated by " + this.company + ", duration "
				+ this.minutes + "minutes\n";
	}
	
}
