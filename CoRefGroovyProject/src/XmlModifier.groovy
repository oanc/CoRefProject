
class XmlModifier {
	
	private Node node;
	private ArrayList<String> missingIDs;
	private ArrayList<String> matchingIDs;
	
	public XmlModifier(Node node){
		this.node = node;
	}
	
	public void xmlAugment(){
		//Create a list of the nodes
		List<Node> nodeList = this.node.breadthFirst()
		
		//Isolate first node -- it's children will be all of the annotations
		Node firstNode = nodeList.get(0)
			List<Node> wordNodeList = firstNode.children()
			
			//For each node, make a list of its feature nodes
			for (Node node: wordNodeList){
				System.out.println(node.name())
				List<Node> features = node.children()
				
					// For each feature node, check if its name is matches, indicate that the node has matches feature
					for (Node feature: features){
					 	if (feature.attributes().get('name') == 'matches'){
							System.out.println('Node has a matches feature.')
							System.out.println('Matches are ' + feature.attributes().get('value'))
					}
					
				}
		}
	}
}

