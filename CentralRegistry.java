import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CentralRegistry {
	private static ArrayList<Airport> airports = new ArrayList<>();
	private static ArrayList<Flight> flights = new ArrayList<>();
	
	public static void addFlight(Flight aFlight) {
		flights.add(aFlight);
		aFlight.getAirportA().addConnection(aFlight.getAirportB());
		aFlight.getAirportB().addConnection(aFlight.getAirportA());
		aFlight.getAirportA().addCompany(aFlight.getCompany());
		aFlight.getAirportB().addCompany(aFlight.getCompany());
		
	}
	
	public static void addAirport(Airport anAirport) {
		airports.add(anAirport);
	}
	
	public static Airport getLargestHub() {
		int max = airports.get(0).getDirectConnection().size();
		Airport largest = airports.get(0);
		for(Airport a : airports) {
			if(a.getDirectConnection().size() > max) {
				max = a.getDirectConnection().size();
				largest = a;
			}
		}
		return largest;
	}
	
	public static Flight getLongestFlight() {
		int max = flights.get(0).getMinutes();
		Flight longest = flights.get(0);
		for(Flight flight : flights) {
			if(flight.getMinutes() > max) {
				max = flight.getMinutes();
				longest = flight;
			}
		}
		return longest;
	}
	
	public static Airport getAirport(String cityName) {
		
		Airport rightAirport = null;
		
		for (Airport airport : airports) {
			
			if(cityName.compareTo(airport.getCity()) == 0) {
				rightAirport = airport;
				break;
			}
			
		}
		return rightAirport;
	}
	
	public static ArrayList<String> getDirectFlightsDetails(Airport a, Airport b) {
		
		ArrayList<String> toBePrinted = new ArrayList<>();
		
		toBePrinted.add("DIRECT FLIGHTS DETAILS\n");
		int counter = 0;
		
		for(Flight flight : flights) {
			if(flight.getAirportA().getName().equals(a.getName()) &&
					flight.getAirportB().getName().equals(b.getName())) {
				counter += 1;
				toBePrinted.add("[" + counter + "]" + flight.toString());
			}
			
			if(flight.getAirportB().getName().equals(a.getName()) &&
					flight.getAirportA().getName().equals(b.getName())) {
				counter += 1;
				toBePrinted.add("[" + counter + "]" + flight.toString());
			}
			
		}
		
		return toBePrinted;
	}
	
	
	public static ArrayList<String> getInDirectFlightsDetails(Airport a, Airport b) {
		
		ArrayList<Airport> inDirectConnections = new ArrayList<>();
		ArrayList<String> toBePrinted = new ArrayList<>();
		inDirectConnections = a.getInDirectConnection(b);

		toBePrinted.add("INDIRECT FLIGHTS Through...\n");
		int counter = 0;
		
		Set<Airport> airSet = new HashSet<Airport>(); 
		
		//Removing duplicates
		for(Airport airport : inDirectConnections) {
			airSet.add(airport);
		}
		inDirectConnections.clear();
				
		Iterator<Airport> iterator = airSet.iterator();
				
		while(iterator.hasNext()) {
			inDirectConnections.add(iterator.next());
		}				
		
		for(Airport airport : inDirectConnections){
			
			counter ++;
			toBePrinted.add("[" + counter + "]" + airport.getCity() + ", " 
					+ airport.getCode() + " airport\n");
			
		}
		
		return toBePrinted;
	}

	public static ArrayList<Airport> getAirports() {
		return airports;
	}
	
	
}
