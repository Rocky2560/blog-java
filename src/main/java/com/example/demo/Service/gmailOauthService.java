//package com.example.demo.Service;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class gmailOauthService {
//
//    private static final String CLIENT_ID = "730380857877-u3abr6d5q2cai5adj8llda7g8h9n9b5n.apps.googleusercontent.com";
//    private static final String CLIENT_SECRET = "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";
//    private static final String REFRESH_TOKEN = "1//04bdAp_8LNJ1cCgYIARAAGAQSNwF-L9IrfUMOKbL2CMDSBSLkl-BpsXKlD01DaudaqT0M8oubM1-i-6y23kU4GxLiH_Di1G7fh2g";
//    private static final String TOKEN_URL = "https://oauth2.googleapis.com/token";
//
//    public String getAccessToken() {
//        RestTemplate restTemplate = new RestTemplate();
//        Map<String, String> params = new HashMap<>();
//        params.put("client_id", CLIENT_ID);
//        params.put("client_secret", CLIENT_SECRET);
//        params.put("refresh_token", REFRESH_TOKEN);
//        params.put("grant_type", "refresh_token");
//
//        ResponseEntity<String> response = restTemplate.postForEntity(TOKEN_URL, params, String.class);
//
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            JsonNode root = mapper.readTree(response.getBody());
//            return root.path("access_token").asText();
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to fetch access token", e);
//        }
//    }
//}
