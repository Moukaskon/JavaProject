import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AirportFindFrame extends JFrame{
	
	
	private JButton findButton;
	private JButton visualizeNetworkButton;
	private JTextField cityName;
	private JPanel Panel;
	
	public AirportFindFrame() {
		
		
		findButton = new JButton("Find");
		visualizeNetworkButton = new JButton("Visualize Network");
		Panel = new JPanel();
		cityName = new JTextField("Enter city name");
		Panel.add(cityName);
		Panel.add(findButton);
		Panel.add(visualizeNetworkButton);
		ButtonListener listener = new ButtonListener();
		ButtonListener2 listener2 = new ButtonListener2();
		visualizeNetworkButton.addActionListener(listener2);
		findButton.addActionListener(listener);
		
		this.setContentPane(Panel);
		this.setSize(300, 300);
		this.setTitle("Find Airport");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	class ButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String city = cityName.getText();
			
			Airport searchedAirport;
			
			searchedAirport = CentralRegistry.getAirport(city);
			
			if(searchedAirport == null) {
				JOptionPane.showMessageDialog(null, city +
						" does not have an airport");
			}else {
				AirportPageFrame frame = new AirportPageFrame(searchedAirport);
			}
			
			
		}
		
	}
	
	class ButtonListener2 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			GraphFrame graph = new GraphFrame();
		}
		
	}
	
}
