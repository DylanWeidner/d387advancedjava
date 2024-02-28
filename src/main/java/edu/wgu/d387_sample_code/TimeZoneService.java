package edu.wgu.d387_sample_code;

import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class TimeZoneService {

    private final List<String> dates = new CopyOnWriteArrayList<>();


    @PostConstruct
    public void loadTimeZones() {
        ZoneId zEastern=ZoneId.of("America/New_York");
        ZoneId zMountain = ZoneId.of("America/Phoenix");
        ZoneId zUTC = ZoneId.of("UTC");
        ZoneId zoneId=ZoneId.systemDefault();

        // Define a formatter for time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm z");

        // Formatter for just Date
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");

        // Local Time
        LocalDateTime localDateTime= LocalDateTime.now();
        ZonedDateTime zonedDateTime=localDateTime.atZone(zoneId);
        String localTime = "local time "+formatter.format(zonedDateTime);
        System.out.println("local time "+formatter.format(zonedDateTime));

        // Tomorrow's Date
        LocalDateTime localDayMonthYear = LocalDateTime.now().plusDays(1);
        ZonedDateTime zonedDayMonthYear = localDayMonthYear.atZone(zoneId);
        String todaysDateOnly = dateFormatter.format(zonedDayMonthYear);
        dates.add(todaysDateOnly);
        System.out.println("Date of Event: " + dateFormatter.format(zonedDayMonthYear));

        // Eastern Time
        ZonedDateTime zonedDateTimeEastern=zonedDateTime.withZoneSameInstant(zEastern);
        LocalDateTime localDateTimeEastern=zonedDateTimeEastern.toLocalDateTime();
        String easternTime = formatter.format(zonedDateTimeEastern);
        System.out.println("Eastern time "+ formatter.format(zonedDateTimeEastern));
        dates.add(easternTime);

        // Mountain Time
        ZonedDateTime zonedDateTimeMountain=zonedDateTime.withZoneSameInstant(zMountain);
        LocalDateTime localDateTimeMountain=zonedDateTimeMountain.toLocalDateTime();
        String mountainTime = formatter.format(zonedDateTimeMountain);
        System.out.println("Mountain time " + formatter.format(zonedDateTimeMountain));
        dates.add(mountainTime);

        // UTC Time
        ZonedDateTime zonedDateTimeUTC=zonedDateTime.withZoneSameInstant(zUTC);
        LocalDateTime localDateTimePacific=zonedDateTimeUTC.toLocalDateTime();
        String utcTime = formatter.format(zonedDateTimeUTC);
        System.out.println("UTC time " + formatter.format(zonedDateTimeUTC));
        dates.add(utcTime);

        System.out.println("This is the array of dates/Times " + dates);
    }


    public List<String> getDates() {
        return this.dates;
    }
}
