package by.gsu.pms;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler {

	private News news = new News();
	private String lastElement = "";
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if(qName.equals("item")) {
			news = new News();
		}
		lastElement = qName;

	}
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String result = new String(ch, start, length).replace("\n", "");
		switch (lastElement) {
		case "title":
			news.setTitle(result);
			break;

		case "description":
			news.setDescription(result);
			break;
		case "link":
			news.setLink(result);
			break;
		case "pubDate":
			news.setTime(result);
			break;
		}
	}
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName.equals("item")) {
			System.out.println(news);
		}
		lastElement = "";
	}
}
