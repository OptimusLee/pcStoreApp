package cuanlee.pcstore_application.repository.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import cuanlee.pcstore_application.model.RAM;
import cuanlee.pcstore_application.repository.RestAPI;

/**
 * Created by CuanL on 29/08/2016.
 */
public class RestRAMAPI implements RestAPI<RAM,Long>{

    final String BASE_URL="http://pcstore-cuanlee.rhcloud.com/";

    final HttpHeaders requestHeaders = RestMethods.getHeaders();
    final RestTemplate restTemplate = RestMethods.getRestTemplate();

    @Override
    public RAM get(Long id) {
        final String url = BASE_URL+"ram/"+id.toString();
        HttpEntity<RAM> requestEntity = new HttpEntity<RAM>(requestHeaders);
        ResponseEntity<RAM> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, RAM.class);
        RAM ram = responseEntity.getBody();
        return ram;
    }

    @Override
    public String post(RAM entity) {
        final String url = BASE_URL+"ram/";
        HttpEntity<RAM> requestEntity = new HttpEntity<RAM>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String put(RAM entity) {
        final String url = BASE_URL+"ram/"+entity.getId().toString();
        HttpEntity<RAM> requestEntity = new HttpEntity<RAM>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        String result = responseEntity.getBody();
        return result;
    }

    @Override
    public String delete(RAM entity) {
        final String url = BASE_URL+"ram/delete/"+entity.getId().toString();
        HttpEntity<RAM> requestEntity = new HttpEntity<RAM>(entity, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
        return responseEntity.getBody();
    }

    @Override
    public List<RAM> getAll() {
        List<RAM> ramList = new ArrayList<>();
        final String url = BASE_URL+"ram/";
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        ResponseEntity<RAM[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, RAM[].class);
        RAM[] results = responseEntity.getBody();

        for (RAM ram : results) {
            ramList.add(ram);
        }
        return ramList;
    }
}
