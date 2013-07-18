import java.io.File;

class Main {
	
	public static void main(String[] args){
		
		//Read user input
		String fileName = args[0]
		String outputDirectory = args[1]
				
		String[] fileString = fileName.split('/')
		String root = fileString[fileString.size()-1]
		
		//Parse xml file using XmlParser

		File file = new File(fileName)
		XmlParser parser = new XmlParser()
		Node tree = parser.parse(file)

		XmlModifier modifier = new XmlModifier(tree)
		modifier.initializeHashMap()
		modifier.addMissingMatchesFeatures()
		modifier.printChains()
		Node newTree = modifier.hashMaptoNode()

		XmlNodePrinter printer = new XmlNodePrinter(new PrintWriter(new FileWriter(outputDirectory + '/Modified_' + root)))
		printer.print(newTree)
		
		modifier.printChainsToFile(outputDirectory, root)
	}
}

