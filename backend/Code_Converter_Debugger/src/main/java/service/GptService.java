package service;

public interface GptService {

	String convertCode(String code, String targetLanguage);
    String debugCode(String code);
    String checkCodeQuality(String code);
}
