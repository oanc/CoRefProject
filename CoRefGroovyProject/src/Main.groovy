import java.io.File;

class Main {
	
	public static void main(String[] args){
		
		//Read user input
		String fileName = args[0];
		
		//Parse xml file using XmlParser

		File file = new File(fileName)
		XmlParser parser = new XmlParser()
		Node tree = parser.parse(file)

		XmlModifier modifier = new XmlModifier(tree)
		modifier.initializeHashMap()
		modifier.addMissingMatchesFeatures()
		modifier.printChains()
		Node newTree = modifier.hashMaptoNode()

		XmlNodePrinter printer = new XmlNodePrinter(new PrintWriter(new FileWriter('Modified_' + fileName)))
		printer.print(newTree)
		
		
	}
}

