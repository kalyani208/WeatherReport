package com.weather.restservice;

import com.weather.model.Response;
import com.weather.model.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class WeatherResource {
    @Autowired
    ReportService reportService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    RestTemplate restTemplate = new RestTemplate();
    @GetMapping(value= "/api/weather", produces= "application/vnd.api+json")
    public String weather(@RequestParam(required = true, defaultValue = "Melbourne AU") String cityId, @RequestHeader("x-api-key") String appId)  throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity entity = new HttpEntity(headers);
        String url = "http://api.openweathermap.org/data/2.5/weather?{q}&{APPID}";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url).queryParam("q", cityId).queryParam("APPID",appId);
        ResponseEntity<Response> responseEntity = restTemplate.getForEntity(builder.build().toUri(), Response.class);
        Response weatherResult = responseEntity.getBody();
        String description = weatherResult.getWeather().get(0).getDescription();
        Report report = new Report(cityId,description);
        reportService.saveOrUpdate(report);
        String result = reportService.getWeather(cityId).getDescription() ;
        logger.info("Response:" + responseEntity);
        return result;
    }
}
