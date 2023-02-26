package fr.lernejo.travelsite;


import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TravelController {
    @PostMapping("/api/inscription")
    public TravelInscription postInscription( @RequestBody TravelInscription SiteInscription) {
        SiteInscription.setUserEmail("machin@truc.com");
        SiteInscription.setUserName("machin");
        SiteInscription.setUserCountry("France");
        SiteInscription.setWeatherExpectation(TravelInscription.WeatherExpectation.COLDER);
        SiteInscription.setMinimumTemperatureDistance(20);
        return SiteInscription;
    }

    @GetMapping("api/travels")
    public List<Map<String, Object>> getTravels(@RequestParam String userName) {
        List<Map<String, Object>> temperatures = new ArrayList<>();
        Path destFile = Paths.get("site/src/main/resources/countries.txt");

        // read countries from file
        List<String> countries = new ArrayList<>();
        try {
            countries = Files.readAllLines(Paths.get(String.valueOf(destFile)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // add data to the list of maps
        for (String country : countries) {
            Map<String, Object> temperature = new HashMap<>();
            temperature.put("country", country);
            temperature.put("temperature", 24.5); // dummy temperature
            temperatures.add(temperature);
        }

        return temperatures;
    }
}
