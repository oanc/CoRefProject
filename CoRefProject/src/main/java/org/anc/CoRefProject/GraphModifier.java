package org.anc.CoRefProject;

import java.util.ArrayList;
import java.util.Iterator;

import org.xces.graf.api.IFeature;
import org.xces.graf.api.IGraph;
import org.xces.graf.api.INode;

public class GraphModifier {

	private IGraph graph;
	private ArrayList<String> missingIDs;
	private ArrayList<INode> matchingNodes;
	
	public GraphModifier(IGraph graph){
		this.graph = graph;
	}
	
	/**
	 * This function returns an Iterator over the nodes of this.graph
	 * @return
	 */
	public Iterator<INode> nodeIterator(){
		Iterable<INode> nodeSet = this.graph.nodes();
		return nodeSet.iterator();
	}
	
	/**
	 * This function adds the missing "matches" features to the IGraph nodes.
	 * @return IGraph
	 */
	public IGraph augmentAnnotations(){
		
		//Initialize an iterator over the nodes of the IGraph
		Iterator<INode> it = this.nodeIterator();
		
		//Iterate through the nodes of the graph to find nodes with
		// "matches" feature
		while (it.hasNext()){
			INode currNode = it.next();
			Iterable<IFeature> featureSet = currNode.getAnnotation().features();
			Iterator<IFeature> itFeatures = featureSet.iterator();
			
			//Iterate through the node's features to find "matches" feature, 
			//if not a feature, do nothing
			while(itFeatures.hasNext()){
				if(itFeatures.next().getName().equalsIgnoreCase("matches")){
					this.matchingNodes.add(currNode);
				}
			}
		}
		
		// Retrieve list of matching ID numbers from each node with a "matches" feature
		for(INode node: this.matchingNodes){
			ArrayList<String> matchIDs = (ArrayList<String>) node.getAnnotation().getFeature("matches").getValue();
			for (String id : matchIDs){
		
				//Check if node with given ID exists
				if (this.graph.findNode(id) == null) {
					this.missingIDs.add(id);
				}
				
				//Else, node exists
				else{
					
					INode matchingNode = this.graph.findNode(id);
				
					//Check if node does not yet have a "matches" feature
					if (matchingNode.getAnnotation().getFeature("matches") == null){
						ArrayList<String> newMatches = new ArrayList<String>();
						matchIDs.add(node.getId());
						matchIDs.remove(id);
						matchingNode.getAnnotation().addFeature("matches", newMatches.toString());
					}
					
					//Else, node has a "matches" feature, check to see if the node ID is an element
					else{
					
						ArrayList<String> matches = (ArrayList<String>) matchingNode.getAnnotation().getFeature("matches").getValue();
						
						//If matches does not already contain the node ID, add it,else do nothing
						if (! (matches.contains(node.getId()))) {
							matches.add(node.getId());
							matchingNode.getAnnotation().getFeature("matches").setValue(matches.toString());
						}
					}
				}
			}
		}
		return this.graph;
}
	/**
	 * This function prints information on each co-reference chain, indicating where 
	 * invalid IDs have been listed.
	 */
	public void printSynopsis(){
		//Iterate through arrayList matchingNodes, which will contain the first instance of
		//each coreference chain
		for (INode node: this.matchingNodes){
			System.out.println("Coreference chain starting at ID" + node.getId());
				ArrayList<String> matchList = (ArrayList<String>) node.getAnnotation().getFeature("matches").getValue();
				//Iterate through arraylist of matching IDs
				for (int i = 0; i < matchList.size(); i++) {
					int index = i + 1;
					
					// Check if the ID is valid
					if (! (this.missingIDs.contains(matchList.get(i)))){
					
						//FIGURE OUT HOW TO INCLUDE TO AND FROM VALUES
						INode currNode = this.graph.findNode(matchList.get(i));
						String type = currNode.getAnnotation().getLabel();
						System.out.println("(" + index + ") " + type + " " + currNode.getAnnotation().getFeatureValue("id") + "PUT START AND END HERE");
					}
					
					// Indicate if ID was not valid
					else{
						System.out.println(">>> ID" + matchList.get(i) + "DOES NOT EXIST");
					}
			
		}
	}
}
	
}
