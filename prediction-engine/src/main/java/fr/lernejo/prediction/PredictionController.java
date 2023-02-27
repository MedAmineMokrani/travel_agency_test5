package fr.lernejo.prediction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class PredictionController {
    private final TemperatureService TemperatureService = new TemperatureService();
    private final DateUtils DateUtils = new DateUtils();
    @RequestMapping(value = "/api/temperature", method = RequestMethod.GET, headers="Accept=application/json")
    public ResponseEntity<Object> getTemperatureData(@RequestParam String country) {
        List<Map<String, Object>> temperatures = new ArrayList<>();
        try {
            Map<String, Object> response = null;
            for (int i = 1; i < 2; i++) {
                Map<String, Object> temp1 = new HashMap<>();
                temp1.put("date", DateUtils.GetDateOfLastDays(i));
                temp1.put("temperature", TemperatureService.getTemperature(country));
                temperatures.add(temp1);
                Map<String, Object> temp2 = new HashMap<>();
                temp2.put("date", DateUtils.GetDateOfLastDays(i+1));
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
