package edu.wgu.d387_sample_code.sample;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class timeConverterController {

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/time/time-zones")
    public TimeConversionResponse getTimeConversions() {
        String convertedTimes = timeConverter.convertTime();
        return new TimeConversionResponse(convertedTimes);
    }

    // Inner class for the response
    private static class TimeConversionResponse {
        private final String convertedTimes;

        public TimeConversionResponse(String convertedTimes) {
            this.convertedTimes = convertedTimes;
        }

        public String getConvertedTimes() {
            return convertedTimes;
        }
    }
}
