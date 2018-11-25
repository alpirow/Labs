import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import by.gsu.pms.News;


public class Runner {

	static final String URL = "https://naviny.by/rss/alls.xml";
	public static void main(String[] args) {
		try {
			List<News> news = new ArrayList<>();
			News newNews = new News();
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLStreamReader reader = factory.createXMLStreamReader(new URL(URL).openStream());
			while(reader.hasNext()) {
				switch (reader.next()) {
				case XMLStreamConstants.START_ELEMENT:
					switch (reader.getLocalName()) {
					case "item":
						newNews = new News();
						break;
					case "title":
						newNews.setTitle(reader.getElementText());
						break;
					case "description":
						newNews.setDescription(reader.getElementText());
						break;
					case "link":
						newNews.setLink(reader.getElementText());
						break;
					case "pubDate":
						newNews.setTime(reader.getElementText());
					}
					break;
				case XMLStreamConstants.END_ELEMENT:
					if(reader.getLocalName().equals("item")) {
						news.add(newNews);
					}
					break;
				}

			}

			for(News item:news) {
				System.out.printf("%s\n",item.toString());
			}
		}catch (IOException|XMLStreamException e) {
			System.out.println(e);
		}

	}

}
