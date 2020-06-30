package com.avasquez.vendingclient;

import com.avasquez.vendingclient.dto.LoginDTO;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class OauthService {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${vendingAdmin.host}")
    private String vendingAdminHost;
    @Value("${vendingMachine.user}")
    private String vendingMachineUser;
    @Value("${vendingMachine.password}")
    private String vendingMachinePassword;
    private final String authEndpoint = "/auth/signin";


    public String getToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        LoginDTO request = new LoginDTO();
        request.setUsername(vendingMachineUser);
        request.setPassword(vendingMachinePassword);

        ResponseEntity<String> response = restTemplate.exchange(
                vendingAdminHost+authEndpoint, HttpMethod.POST, new HttpEntity<>(request, headers), String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = null;
        try {
            node = mapper.readTree(response.getBody());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String token = node.path("accessToken").asText();
        return token;
    }
}
