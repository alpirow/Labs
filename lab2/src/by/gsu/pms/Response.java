
package by.gsu.pms;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("coord")
    @Expose
    private Coord coord;
    @SerializedName("weather")
    @Expose
    private List<Weather> weather = null;
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("visibility")
    @Expose
    private int visibility;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("dt")
    @Expose
    private int dt;
    @SerializedName("sys")
    @Expose
    private Sys sys;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cod")
    @Expose
    private int cod;

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
    @Override
    public String toString() {
    	StringBuilder result = new StringBuilder();
    	result.append("\nНазвание города: " + name);
    	result.append("\nКод страны: " + sys.getId());
    	result.append("\nПогода: " + weather.get(0).getMain() + " " +weather.get(0).getDescription());
    	result.append("\nТемпература: " + main.getTemp());
    	result.append("\nДавление: " + main.getPressure());
    	result.append("\nВлажность: " + main.getHumidity());
    	result.append("\nМинимальная температура: " + main.getTempMin());
    	result.append("\nМаксимальная температура: " + main.getTempMax());
    	result.append("\nВетер: " + wind.getSpeed() + " " + getWindDescription());
    	result.append("\nОблачность: " + clouds.getAll());
    	return result.toString();
    	
    	
    }
    public String getWindDescription() {
    	String[] values= {
    			"N","NE","E","SE","S","SW","W","NW","N"
    	};
    	return values[(int)(wind.getDeg()+22.5)/45];
    }
}
