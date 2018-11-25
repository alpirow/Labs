import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import by.gsu.pms.XMLHandler;


public class Runner {

	static final String URL = "https://naviny.by/rss/alls.xml";
	public static void main(String[] args) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			XMLHandler handler = new XMLHandler();
			parser.parse(new URL(URL).openStream(), handler);
		}catch (SAXException|ParserConfigurationException|IOException e) {
			System.out.println(e);
		}

	}

}
