package org.anc.CoRefProject;

import java.io.File;
import java.io.IOException;
import org.xces.graf.api.GrafException;
import org.xces.graf.api.IGraph;
import org.xces.graf.api.INode;
import org.xces.graf.io.GrafLoader;
import org.xces.graf.io.GrafParser;
import org.xces.graf.io.GrafRenderer;
import org.xml.sax.SAXException;

public class Main {
	
	public static void main(String[] args) throws SAXException, IOException, GrafException{
		
		// Initialize a graph from xml file using GrafParser
		File file = new File("CoRefFiles/110CYL067-coRef.xml");
		GrafParser parser = new GrafParser();
		IGraph graph = parser.parse(file);
		
		// Initialize an instance of GraphModifier for graph
		GraphModifier gm = new GraphModifier(graph);
		IGraph augmentedGraph = gm.augmentAnnotations();
		gm.printSynopsis();

		// HOW DO I CONVERT AUGMENTED GRAPH BACK INTO XML FORMAT?
	}
}
