package util;

public class GptPromptBuilder {

    public static String buildCodeConversionPrompt(String code, String targetLanguage) {
        // Build a GPT prompt for code conversion
        return "Translate the following " + targetLanguage + " code to Python:\n\n" + code;
    }

    public static String buildCodeDebuggingPrompt(String code) {
        // Build a GPT prompt for code debugging
        return "Debug the following code:\n\n" + code;
    }

    public static String buildCodeQualityCheckPrompt(String code) {
        // Build a GPT prompt for code quality check
        return "Evaluate the quality of the following code:\n\n" + code;
    }
}
