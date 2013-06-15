import java.io.File;




class Main {
	
	public static void main(String[] args){
		
		//Parse xml file using XmlParser
		File file = new File("CoRefFiles/110CYL067-coRef.xml")
		XmlParser parser = new XmlParser()
		Node tree = parser.parse(file)

		XmlModifier modifier = new XmlModifier(tree)
		modifier.xmlAugment()
		
		}
	}


