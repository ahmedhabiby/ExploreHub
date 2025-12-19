package com.example.explurerhub.ChatBot;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Service
public class GeminiService {


    private final String pythonUrl = "http://127.0.0.1:5000"; // رابط Flask

    private final RestTemplate restTemplate = new RestTemplate();

    public String askChatBot(String query) {
        String url = pythonUrl + "/";
        Map<String, String> body = new HashMap<>();
        body.put("chat_query", query);

        return postRequest(url, body);
    }

    public String generateTourPlan(String location, int duration_days, String interests) {
        String url = pythonUrl + "/";
        Map<String, String> body = new HashMap<>();
        body.put("location", location);
        body.put("duration_days", String.valueOf(duration_days));
        body.put("interests", interests);

        return postRequest(url, body);
    }

    private String postRequest(String url, Map<String, String> payload) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            StringBuilder formBody = new StringBuilder();
            for (Map.Entry<String, String> entry : payload.entrySet()) {
                if (formBody.length() > 0) formBody.append("&");
                formBody.append(entry.getKey()).append("=").append(entry.getValue());
            }

            HttpEntity<String> request = new HttpEntity<>(formBody.toString(), headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return "خطأ في الاتصال بـ Python Server";
        }
    }
}
