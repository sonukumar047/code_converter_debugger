package util;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class GptClient {

    private static final String GPT_API_URL = "https://api.openai.com/v1/engines/davinci-codex/completions"; // Update with the correct API endpoint
    private static final String GPT_API_VERSION = "2021-08-01"; // Update with the API version you are using

    public static String invokeGptApi(String apiKey, String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<String> request = new HttpEntity<>(buildRequestBody(prompt), headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(GPT_API_URL, HttpMethod.POST, request, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to invoke GPT API. Status code: " + response.getStatusCode());
        }
    }

    private static String buildRequestBody(String prompt) {
        return "{\"prompt\": \"" + prompt + "\", \"max_tokens\": 150}"; // You can adjust max_tokens as needed
    }
}
