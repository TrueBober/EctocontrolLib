package ru.kip.ectocontrol.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import lombok.RequiredArgsConstructor;
import ru.kip.ectocontrol.*;
import ru.kip.ectocontrol.helper.DevicesDetails;
import ru.kip.ectocontrol.helper.DevicesIdsList;
import ru.kip.ectocontrol.helper.DevicesList;
import ru.kip.ectocontrol.helper.DevicesState;
import ru.kip.ectocontrol.utils.JsonCodec;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

/**
 * Реализация доступа к API эктоконтрола через HTTP.
 */
@RequiredArgsConstructor
public class HttpApiEctocontrol implements Ectocontrol {

    /**
     * Хост для доступа к сервису (например, http://my.ectocontrol.ru)
     */
    private final String host;

    /**
     * Токен для доступа к сервису (полученный из личного кабинета)
     */
    private final String token;

    /**
     * Средство доступа к сервису
     */
    private final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    /**
     * Средство для преобразования в JSON и обратно
     */
    private final ObjectMapper objectMapper = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

    @Override
    public List<Device> getAllDevices() {
        var path = "/public_api/v0/devices";
        var method = "GET";
        var body = "";
        var request = this.buildHttpRequest(method, path, body);
        try {
            var response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            var responseString = response.body();
            return objectMapper.readValue(responseString, DevicesList.class)
                    .getDevices();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DeviceDetails> getDeviceDetails(List<Integer> devicesIds) {
        var path = "/public_api/v0/info";
        var method = "POST";
        var body = JsonCodec.toJson(DevicesIdsList.of(devicesIds));
        var request = this.buildHttpRequest(method, path, body);
        try {
            var response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            var responseString = response.body();
            return objectMapper.readValue(responseString, DevicesDetails.class)
                    .getDevicesInfo();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Integer> enableRelay(List<Integer> devicesIds) {
        var path = "/public_api/v0/set_state";
        var method = "POST";
        var body = JsonCodec.toJson(DevicesState.of(devicesIds, List.of()));
        var request = this.buildHttpRequest(method, path, body);
        try {
            var response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            var responseString = response.body();
            return objectMapper.readValue(responseString, EnableDevicesResult.class)
                    .getSuccess();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Integer> disableRelay(List<Integer> devicesIds) {
        var path = "/public_api/v0/set_state";
        var method = "POST";
        var body = JsonCodec.toJson(DevicesState.of(List.of(), devicesIds));
        var request = this.buildHttpRequest(method, path, body);
        try {
            var response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            var responseString = response.body();
            return objectMapper.readValue(responseString, EnableDevicesResult.class)
                    .getSuccess();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpRequest buildHttpRequest(String method, String path, String body) {
        var uri = URI.create(host + path);
        var publisher = this.getBodyPublisher(body);
        return HttpRequest.newBuilder()
                .method(method, publisher)
                .uri(uri)
                .header("Authorization", token)
                .build();
    }

    private HttpRequest.BodyPublisher getBodyPublisher(String body) {
        if (body == null || body.isBlank()) {
            return HttpRequest.BodyPublishers.noBody();
        } else {
            return HttpRequest.BodyPublishers.ofString(body);
        }
    }
}
