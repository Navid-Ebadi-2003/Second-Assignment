import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.util.Scanner;

public class WeatherApp {
    // Copy your API-KEY here
    public final static String apiKey = "11fd3fe2528747249b761306232702";
    // TODO: Write main function
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String city = input.nextLine();

        JSONObject data = new JSONObject(getWeatherData(city));

        System.out.println("Temperature: "+ getTemperature(data) + " F");
        System.out.println("Humidity: " + getHumidity(data));

    }

    /**
     * Retrieves weather data for the specified city.
     *
     * @param city the name of the city for which weather data should be retrieved
     * @return a string representation of the weather data, or null if an error occurred
     */
    public static String getWeatherData(String city) {
        try {
            URL url = new URL("http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + city);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    // TODO: Write getTemperature function returns celsius temperature of city by given json string
    public static double getTemperature(JSONObject weatherJson){

        double answer = 0.0;
        answer = weatherJson.getJSONObject("current").getDouble("temp_f");
        return answer;
    }

    // TODO: Write getHumidity function returns humidity percentage of city by given json string
    public static int getHumidity(JSONObject weatherJson){
        int answer = 0;
        answer = weatherJson.getJSONObject("current").getInt("humidity");
        return answer;
    }
}
