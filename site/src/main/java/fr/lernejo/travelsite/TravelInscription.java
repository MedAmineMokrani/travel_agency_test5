package fr.lernejo.travelsite;

import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TravelInscription {


    @NotNull
    @Email
    private String userEmail;
    @NotNull
    private String userName;
    @NotNull
    private String userCountry;

    @NotNull
    @Min(value = 0)
    @Max(value = 40)

    private int minimumTemperatureDistance;

    enum WeatherExpectation {
        WARMER, COLDER;
    }


    @NotNull
    @JsonProperty("weatherExpectation")
    @Pattern(regexp = "WARMER|COLDER")
    private WeatherExpectation weatherExpectation;


    // Constructor of this class
    public TravelInscription() {
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }


    public int getMinimumTemperatureDistance() {
        return minimumTemperatureDistance;
    }

    public void setMinimumTemperatureDistance(int minimumTemperatureDistance) {
        this.minimumTemperatureDistance = minimumTemperatureDistance;
    }
    public WeatherExpectation getWeatherExpectation() {
        return weatherExpectation;
    }

    public void setWeatherExpectation (WeatherExpectation weatherExpectation) {
        this.weatherExpectation = weatherExpectation;
    }








}
