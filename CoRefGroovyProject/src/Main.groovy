import java.io.File;




class Main {
	
	public static void main(String[] args){
		
		//Parse xml file using XmlParser
		String originalFiles = 'CoRefFiles/'
		String fileName  = '110CYL068-coRef.xml'
		File file = new File(originalFiles + fileName)
		XmlParser parser = new XmlParser()
		Node tree = parser.parse(file)

		XmlModifier modifier = new XmlModifier(tree)
		modifier.initializeHashMap()
		modifier.addMissingMatchesFeatures()
		Node newTree = modifier.hashMaptoNode()

		XmlNodePrinter printer = new XmlNodePrinter(new PrintWriter(new FileWriter('Modified_' + originalFiles + 'Modified_' + fileName)))
		printer.print(newTree)
		
		
	}
}

