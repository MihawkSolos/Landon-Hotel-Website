package edu.wgu.d387_sample_code.sample;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class timeConverter {

    public static String convertTime() {
        ZoneId zEastern = ZoneId.of("America/New_York");
        ZoneId zMountain = ZoneId.of("America/Denver");
        ZoneId zUTC = ZoneId.of("UTC");

        // Set the time to 12:30 PM in the local timezone
        LocalDateTime localDateTime = LocalDateTime.now().withHour(5).withMinute(45);

        String localTime = formatLocalDateTime(localDateTime);

        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());

        ZonedDateTime zonedDateTimeMountain = zonedDateTime.withZoneSameInstant(zMountain);
        String mountainTime = formatLocalDateTime(zonedDateTimeMountain.toLocalDateTime());

        ZonedDateTime zonedDateTimeUTC = zonedDateTime.withZoneSameInstant(zUTC);
        String utcTime = formatLocalDateTime(zonedDateTimeUTC.toLocalDateTime());

        return localTime + " Eastern Time | " +
                mountainTime +" Mountain Time | " +
                utcTime + " UTC";
    }

    // Method to format LocalDateTime using DateTimeFormatter
    private static String formatLocalDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        return localDateTime.format(formatter);
    }
}
