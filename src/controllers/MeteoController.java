/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import meteo.Apiconnector;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class MeteoController implements Initializable {

    @FXML
    private TextField cityInput;
    @FXML
    private Text weatherText;
     private final String cityAPI = "https://www.metaweather.com/api/location/search/?query=";

    private final String weatherAPI = "https://www.metaweather.com/api/location/";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void getWeatherData(ActionEvent event)throws MalformedURLException  {
        JSONObject todaysWeather = GetTodaysWeatherInformation(getWoeid());

        System.out.println(todaysWeather);

        weatherText.setText(
            "Min temperature: " + todaysWeather.get("min_temp") +
            "\nCurrent temperature: " + todaysWeather.get("the_temp") +
            "\nMax temperature: " + todaysWeather.get("max_temp")
        );
    }
    public String getWoeid() throws MalformedURLException {
        Apiconnector apiConnectorCity = new Apiconnector(cityAPI);

        JSONObject jsonData = (JSONObject) (apiConnectorCity.getJSONArray(cityInput.getText())).get(0);

        return jsonData.get("woeid").toString();
    }

    public JSONObject GetTodaysWeatherInformation(String woeid) throws MalformedURLException {
        Apiconnector apiConnectorWeather = new Apiconnector(weatherAPI);

        JSONObject weatherJSONObject = apiConnectorWeather.getJSONObject(woeid + "/");

        JSONArray weatherArray = (JSONArray) weatherJSONObject.get("consolidated_weather");

        return  (JSONObject) weatherArray.get(0);
    }
    
}
