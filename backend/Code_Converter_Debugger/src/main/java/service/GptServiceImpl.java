package service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import exception.GptServiceException;
import util.GptClient;
import util.GptPromptBuilder;

@Service
public class GptServiceImpl implements GptService {

    @Value("${openai.apiKey}") // Load your OpenAI API key from application.properties
    private String apiKey;

    @Override
    public String convertCode(String code, String targetLanguage) {
        try {
            String prompt = GptPromptBuilder.buildCodeConversionPrompt(code, targetLanguage);
            return GptClient.invokeGptApi(apiKey, prompt);
        } catch (Exception e) {
            throw new GptServiceException("Error in code conversion service: " + e.getMessage());
        }
    }

    @Override
    public String debugCode(String code) {
        try {
            String prompt = GptPromptBuilder.buildCodeDebuggingPrompt(code);
            return GptClient.invokeGptApi(apiKey, prompt);
        } catch (Exception e) {
            throw new GptServiceException("Error in code debugging service: " + e.getMessage());
        }
    }

    @Override
    public String checkCodeQuality(String code) {
        try {
            String prompt = GptPromptBuilder.buildCodeQualityCheckPrompt(code);
            return GptClient.invokeGptApi(apiKey, prompt);
        } catch (Exception e) {
            throw new GptServiceException("Error in code quality check service: " + e.getMessage());
        }
    }
}
