package com.example.servico.meteorologia.modules.meteorology.services;

import com.example.servico.meteorologia.modules.meteorology.models.Locale;
import com.example.servico.meteorologia.modules.meteorology.models.Weather;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class MeteorologyService {

    public ResponseEntity<Float> getTemperature(Locale locale) {
        RestTemplate restTemplate = new RestTemplate();

        String url = UriComponentsBuilder.fromHttpUrl(
                        String.format(
                                "https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&current=temperature_2m,wind_speed_10m&hourly=temperature_2m,relative_humidity_2m,wind_speed_10m",
                                locale.latitude().toString(),
                                locale.longitude().toString()
                        ))
                .toUriString();

        Weather weather = restTemplate.getForObject(url, Weather.class);
        Float temperature = weather.current().temperature_2m();

        return ResponseEntity.ok(temperature);
    }
}
