
class XmlModifier {
	
	private Node node;
	private ArrayList<String> missingIDs;
	private ArrayList<String> matchingIDs;
	private HashMap<String, HashMap<String, String>> nodeData;
	
	
	
	
	public XmlModifier(Node node){
		this.node = node;
		this.nodeData = new HashMap<String, HashMap<String, String>>()
	}
	
	private void initializeHashMap(){
		List<Node> nodeList = this.node.breadthFirst()
		Node firstNode = nodeList.get(0)
		List<Node> wordNodeList = firstNode.children()
		
		
		//Iterate through wordNodes
		for (int i = 0; i < wordNodeList.size(); i++){
			
			//Initialize ID field and a hashMap, featureMap
			String wordID = 'ID_Not_Found'
			HashMap<String, String> featureMap = new HashMap<String,String>()
			
			
			featureMap.put('classification', wordNodeList.get(i).attributes().get('type'))
			featureMap.put('from', wordNodeList.get(i).attributes().get('from'))
			featureMap.put('to', wordNodeList.get(i).attributes().get('to'))
			
			//Isolate the word node's children -- it's features
			List<Node> features = wordNodeList.get(i).children()
	
			
			for (int k = 0; k < features.size(); k++){
				if (features.get(k).attributes().get('name') == 'id'){
					wordID = features.get(k).attributes().get('value')
				}
				else{
					featureMap.put(features.get(k).attributes().get('name'), features.get(k).attributes().get('value'))
				}
			}
			
			
			this.nodeData.put(wordID, featureMap)
		}
		System.out.println(this.nodeData.toMapString())
	}
	
	
	
	
	
	private void addMissingMatchesFeatures(){
		/// Use hashmap, iterate through the key values (first copy them into an arraylist), 
		/// check for those that have existing matches features, then iterate through those 
		/// matches, see if they have existing matches features, if not, add one, with the
		/// same matches list (remove the match itself and add the original node), if it does,
		/// then use a HashSet to combine the two match lists, modifying both the original node's
		/// matches and the match's matches
	}
	
	private void printChains(){
		// print information on each coreference chain
	}
	
	private Node hashMaptoNode(){
		return this.node;
	}
	
	
	
	
	
//	public void xmlAugment(){
//		//Create a list of the nodes
//		List<Node> nodeList = this.node.breadthFirst()
//		
//		//Isolate first node -- it's children will be all of the annotations
//		Node firstNode = nodeList.get(0)
//			List<Node> wordNodeList = firstNode.children()
//			
//			//For each node, make a list of its feature nodes
//			for (Node node: wordNodeList){
//				System.out.println(node.name())
//				List<Node> features = node.children()
//				
//					// For each feature node, check if its name is matches, indicate that the node has matches feature
//					for (Node feature: features){
//					 	if (feature.attributes().get('name') == 'matches'){
//							System.out.println('Node has a matches feature.')
//							System.out.println('Matches are ' + feature.attributes().get('value'))
//					}
//					
//				}
//		}
//	}
}

