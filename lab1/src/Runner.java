import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.gsu.pms.News;


public class Runner {

	static final String URL = "https://naviny.by/rss/alls.xml";
	public static void main(String[] args) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new URL(URL).openStream());
			List<News> news = new ArrayList<>();
			NodeList items =  doc.getDocumentElement().getElementsByTagName("item");
			for(int i = 0 ; i < items.getLength();i++) {
				Node node = items.item(i);
				News newNews = new News();
				newNews.setTitle(node.getChildNodes().item(1).getTextContent());
				newNews.setLink(node.getChildNodes().item(3).getTextContent());
				newNews.setDescription(node.getChildNodes().item(5).getTextContent()); 
				newNews.setTime(node.getChildNodes().item(7).getTextContent());
				news.add(newNews);
			}
			for(News item:news) {
				System.out.printf("%s\n",item.toString());
			}
		}catch (IOException|ParserConfigurationException|SAXException e) {
			System.out.println(e);
		}

	}
}
