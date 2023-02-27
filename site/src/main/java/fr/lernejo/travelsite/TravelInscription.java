package fr.lernejo.travelsite;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;
import retrofit2.http.Headers;

public class TravelInscription {


    @NotNull
    @Email
    private final String userEmail;
    @NotNull
    private final String userName;
    @NotNull
    private final String userCountry;


    @NotNull
    @Min(value = 0)
    @Max(value = 40)

    private final int minimumTemperatureDistance;




    enum WeatherExpectation {
        WARMER, COLDER;
    }


    @NotNull
    @JsonProperty("weatherExpectation")
    @Pattern(regexp = "WARMER|COLDER")
    private final WeatherExpectation weatherExpectation;


    @JsonCreator
    public TravelInscription( @JsonProperty("userEmail") String userEmail,  @JsonProperty("userName") String userName,  @JsonProperty("userCountry") String userCountry,  @JsonProperty("minimumTemperatureDistance") int minimumTemperatureDistance,  @JsonProperty("weatherExpectation") WeatherExpectation weatherExpectation) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userCountry = userCountry;
        this.minimumTemperatureDistance = minimumTemperatureDistance;
        this.weatherExpectation = weatherExpectation;

    }

    public String getUserEmail() {
        return userEmail;
    }


    public String getUserName() {
        return userName;
    }



    public String getUserCountry() {
        return userCountry;
    }



    public int getMinimumTemperatureDistance() {
        return minimumTemperatureDistance;
    }


    public WeatherExpectation getWeatherExpectation() {
        return weatherExpectation;
    }










}
