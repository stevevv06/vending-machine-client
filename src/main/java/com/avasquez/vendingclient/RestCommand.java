package com.avasquez.vendingclient;

import com.avasquez.vendingclient.dto.RestResponsePage;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;
import org.springframework.http.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;

public abstract class RestCommand {
    @Autowired
    private OauthService oauthService;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${vendingAdmin.host}")
    private String vendingAdminHost;

    public <T> T executePost(String endpoint, Object request, Class<T> responseType, Object... pathVariables){
        String token = oauthService.getToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        ResponseEntity<T> response = null;
        try {
            response = restTemplate.exchange(
                    vendingAdminHost + endpoint, HttpMethod.POST, new HttpEntity<>(request, headers), responseType, pathVariables);
        } catch(HttpStatusCodeException e){
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = null;
            try {
                node = mapper.readTree(e.getResponseBodyAsString());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            String message = node.path("message").asText();
            System.out.println("Error: " + message);
        }
        return response != null ? response.getBody() : null;
    }

    public <T> T executeGet(String endpoint, Class<T> responseType, Object... pathVariables){
        String token = oauthService.getToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        headers.set("page", "0");
        headers.set("size", ""+Integer.MAX_VALUE);
        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<T> response = restTemplate.exchange(
                vendingAdminHost+endpoint, HttpMethod.GET, request, responseType, pathVariables);
        return response.getBody();
    }

    public <T> List<T> executeGetPagedContent(String endpoint, Class<T> responseType, Object... pathVariables){
        String token = oauthService.getToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        headers.set("page", "0");
        headers.set("size", ""+Integer.MAX_VALUE);
        HttpEntity request = new HttpEntity(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(vendingAdminHost+endpoint)
            .queryParam("page", "0")
            .queryParam("size", ""+Integer.MAX_VALUE);

        ResponseEntity<RestResponsePage<T>> response = restTemplate.exchange(
            builder.build().toString(), HttpMethod.GET, request,
            ParameterizedTypeReference.forType(ResolvableType.forClassWithGenerics(RestResponsePage.class, responseType).getType()),
            pathVariables);
        return response.getBody().getContent();
    }

}
