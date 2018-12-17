import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;

import by.gsu.pms.Response;

public class Runner {
	static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=Homyel&appid=5753974d4f4e8e428ba77f4eb8816501&units=metric";
	public static void main(String[] args) {
		try(Reader reader = new InputStreamReader(new URL(API_URL).openStream())){
			Gson gson = new Gson();
			Response weather = gson.fromJson(reader, Response.class);
			System.out.println(weather);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

	}

}
