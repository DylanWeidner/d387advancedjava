package edu.wgu.d387_sample_code.rest;

import edu.wgu.d387_sample_code.TimeZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.util.List;

@RestController
@CrossOrigin
public class TimeZoneRestController {

    private final TimeZoneService timeZoneService;

    @Autowired
    TimeZoneRestController(TimeZoneService timeZoneService){
        this.timeZoneService = timeZoneService;
    }

    @GetMapping("/times")
    public List<String> getDates(){ return timeZoneService.getDates(); }
}
