import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class AirportPageFrame extends JFrame{
	
	private JTextField nameField = new JTextField();
	private JTextField codeField = new JTextField();
	private JTextField cityField = new JTextField();
	private JTextField countryField = new JTextField();
	private JTextField searchCityField = new JTextField("Search");
	
	private JButton findButton = new JButton("Find Flights");
	private JButton returnButton = new JButton("Return To Search Screen");
	
	private JPanel upperPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JPanel centerListsPanel = new JPanel();
	private JPanel listPanel = new JPanel();
	private JPanel lowerPanel = new JPanel();
	
	private JList companyList = new JList();
	private JList directFlightsList = new JList();
	private JList inDirectFlightsList = new JList();

	private Set<String> companiesSet = new HashSet<String>();
	
	private ArrayList<String> companiesList;
	
	private DefaultListModel model1 = new DefaultListModel();
	private DefaultListModel model2 = new DefaultListModel();
	private DefaultListModel model3 = new DefaultListModel();
	
	private JScrollPane companiesScrollPane;
	private JScrollPane directFlightsScrollPane;
	private JScrollPane inDirectFlightsScrollPane;
	
	Airport airport1;
	
	public AirportPageFrame(Airport airport) {
		
		airport1 = airport;
		Box verticalBox = Box.createVerticalBox();
		companiesList = airport.getCompanies();
		
		
		//Removing duplicates
		for(String company : companiesList) {
			companiesSet.add(company);
		}
		companiesList.clear();
		
		Iterator<String> iterator = companiesSet.iterator();
		
		while(iterator.hasNext()) {
			companiesList.add(iterator.next());
		}
		
		Collections.sort(companiesList);
		
		for(String company : companiesList) {
			model1.addElement(company);
		}
		
		companyList.setModel(model1);
		
		//Setting text fields
		nameField.setText(airport.getName());
		codeField.setText(airport.getCode());
		cityField.setText(airport.getCity());
		countryField.setText(airport.getCountry());
		
		upperPanel.add(nameField);
		upperPanel.add(codeField);
		upperPanel.add(cityField);
		upperPanel.add(countryField);
		companiesScrollPane = new JScrollPane(companyList);
		upperPanel.add(companiesScrollPane);
		
		centerPanel.add(searchCityField);
		centerPanel.add(findButton);
		directFlightsScrollPane = new JScrollPane(directFlightsList);
		inDirectFlightsScrollPane = new JScrollPane(inDirectFlightsList);
		centerListsPanel.add(directFlightsScrollPane);
		centerListsPanel.add(inDirectFlightsScrollPane);
		
		FindListener listener1 = new FindListener();
		findButton.addActionListener(listener1);
		
		ReturnListener listener2 = new ReturnListener();
		returnButton.addActionListener(listener2);
		
		
		lowerPanel.add(returnButton);
		
		verticalBox.add(upperPanel);
		verticalBox.add(Box.createVerticalGlue());
		verticalBox.add(centerPanel);
		verticalBox.add(Box.createVerticalGlue());
		verticalBox.add(centerListsPanel);
		verticalBox.add(Box.createVerticalGlue());
		verticalBox.add(lowerPanel);
		this.setContentPane(verticalBox);
		this.setSize(800, 500);
		this.setTitle("Airport Page");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class FindListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String cityName = searchCityField.getText();
			Airport airport2 = CentralRegistry.getAirport(cityName);
			
			if(airport2 == null) {
				JOptionPane.showMessageDialog(null, "" + cityName +
						" does not have an airport, try again.");
			}else if(cityName.equals(airport1.getCity())){
				JOptionPane.showMessageDialog(null, "Arrival and departure "
						+ "city cannot be the same!");
			}else {
				ArrayList<String> directFlights;
				directFlights = CentralRegistry.getDirectFlightsDetails(airport1, airport2);
				String direct = "";
				String inDirect = "";
				
				for(String str : directFlights) {
					direct += str;
					model2.addElement(str);
				}
				
				directFlightsList.setModel(model2);
				
				ArrayList<String> inDirectFlights;
				inDirectFlights = CentralRegistry.getInDirectFlightsDetails(airport1, airport2);
				
				for(String str : inDirectFlights) {
					inDirect += str;
					model3.addElement(str);
				}
				
				inDirectFlightsList.setModel(model3);
				
				String txtName = airport1.getCity() + "To" + airport2.getCity();
				
				try {
					FileWriter writer = new FileWriter(txtName);
					
					BufferedWriter buffer = new BufferedWriter(writer);
					
					buffer.write("CITY: " + airport1.getCity() + ", " + 
									airport1.getCountry() + "\n");
					
					buffer.write("AIRPORT: " + airport1.getName() +
									" (" + airport1.getCode() + ")\n\n");
					buffer.write("DESTINATION: " + airport2.getCity() + "\n\n");
					
					buffer.write(direct + "\n");
					
					buffer.write(inDirect);
					
					buffer.close();
					
					System.out.println("File created and sentences written successfully.");
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		}
		
	}
	
	class ReturnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			AirportFindFrame AirportFrame = new AirportFindFrame();
		}
		
	}
	
}