package org.anc.CoRefProject;

import java.util.ArrayList;
import java.util.Iterator;

import org.xces.graf.api.IFeature;
import org.xces.graf.api.IGraph;
import org.xces.graf.api.INode;

public class GraphModifier {

	private IGraph graph;
	private ArrayList<String> missingIDs;
	
	public GraphModifier(IGraph graph){
		this.graph = graph;
	}
	
	public IGraph augmentAnnotations(){
		//Initialize an iterator over the nodes of the IGraph
		ArrayList<INode> matchingNodes = new ArrayList<INode>();
		Iterable<INode> nodeSet = this.graph.nodes();
		Iterator<INode> it = nodeSet.iterator();
		
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
					matchingNodes.add(currNode);
				}
			}
		}
		//For each node that contains a "matches" feature, locate its matches, indicate if a 
		//match ID is not valid (i.e. add it to missingIDs and doNothing), else if the ID is
		//valid, check for a matches feature, if none, add it, if it already exists, check
		//that it contains the original ID number
		for(INode node: matchingNodes){
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
						newMatches.add(node.getId());
						matchingNode.getAnnotation().addFeature("matches", newMatches.toString());
					}
					//Else, node has a "matches" feature, check to see if the node ID is an element
					else{
						ArrayList<String> matches = (ArrayList<String>) matchingNode.getAnnotation().getFeature("matches").getValue();
						//If matches does not already contain the node ID, add it,else do nothing
						if (! (matches.contains(node.getId())) ) {
							matches.add(id);
							matchingNode.getAnnotation().getFeature("matches").setValue(matches.toString());
						}
					}
				}
			}
		}
		return this.graph;
}


	
	
}
