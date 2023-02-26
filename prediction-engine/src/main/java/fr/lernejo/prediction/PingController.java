package fr.lernejo.prediction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PingController {
    private final TemperatureService TemperatureService = new TemperatureService();
    private final DateUtils DateUtils = new DateUtils();
    @GetMapping(path = "/api/ping")
    String ping() {
        return "OK";
    }


    @RequestMapping(value = "/api/temperature", method = RequestMethod.GET, headers="Accept=application/json")
    public ResponseEntity<Object> getTemperatureData(@RequestParam String country) {
        List<Map<String, Object>> temperatures = new ArrayList<>();
        Path destFile = Paths.get("site/src/main/resources/countries.txt");

        // read countries from file
        List<String> countries = new ArrayList<>();
        try {
            Map<String, Object> response = null;
            for (int i = 1; i < 2; i++) {

                Map<String, Object> temp1 = new HashMap<>();
                temp1.put("date", DateUtils.GetDateOfLastNDay(i));
                temp1.put("temperature", TemperatureService.getTemperature(country));
                temperatures.add(temp1);
                Map<String, Object> temp2 = new HashMap<>();
                temp2.put("date", DateUtils.GetDateOfLastNDay(i+1));
                temp2.put("temperature", TemperatureService.getTemperature(country));
                temperatures.add(temp2);

                response = new HashMap<>();
                response.put("country", country);
                response.put("temperatures", temperatures);

            }
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (HttpClientErrorException ex) {

            MyHttpException errorResponse = new MyHttpException(ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.EXPECTATION_FAILED);
        }
        }



}

