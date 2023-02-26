package fr.lernejo.prediction;

import java.time.LocalDate;


public class DateUtils {


    public LocalDate GetDateOfLastNDay (int Minus) {

        LocalDate today = LocalDate.now();
        LocalDate date = today.minusDays(Minus);

        return date;

    }
}
