package fr.lernejo.prediction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class MyHttpException extends RuntimeException {
    public MyHttpException(String message) {
        super(message);
    }
}
