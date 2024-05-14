import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.shortestpath.DistanceStatistics;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

public class GraphFrame extends JFrame{
	// Create an undirected graph
	private Graph<String, String> graph = new UndirectedSparseGraph<>();
	
	private ArrayList<Airport> airports;
	
	private JPanel panel = new JPanel();
	private JPanel panel2 = new JPanel();
	
	public GraphFrame(){
		
		Box verticalBox = Box.createVerticalBox();
		ArrayList<Airport> directConnections;
		airports = CentralRegistry.getAirports();
		
		for(Airport airport: airports) {
			graph.addVertex(airport.getCity());
		}
		
		for(Airport airport: airports) {
			directConnections = airport.getDirectConnection();
			for(Airport directlyConnected: directConnections) {
				if(graph.findEdge(airport.getCity(), directlyConnected.getCity()) == null) {
					String edgeName = airport.getCity() + " " + directlyConnected.getCity();
					graph.addEdge(edgeName, airport.getCity(), directlyConnected.getCity());
				}
			}
			
		}
		Layout<String, String> layout = new CircleLayout<String, String>(graph);
        layout.setSize(new Dimension(300,300));
		
		BasicVisualizationServer<String, String> visualizationServer =
                new BasicVisualizationServer<>(layout);
        visualizationServer.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<>());
        visualizationServer.setPreferredSize(new Dimension(350, 350));
		
        panel.add(visualizationServer);
        
        double diameter = DistanceStatistics.diameter(graph);
        String printing = "Diameter = " + diameter;
        
        JTextArea textArea = new JTextArea(printing);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        
        
        panel2.add(textArea);
        
        verticalBox.add(panel);
        verticalBox.add(Box.createVerticalGlue());
        verticalBox.add(panel2);
        this.setVisible(true);
        this.setTitle("City Airport Connections Network");
        this.setContentPane(verticalBox);
        this.setSize(500, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
	}

}
