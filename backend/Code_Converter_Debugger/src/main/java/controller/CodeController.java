package controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.CodeRequest;
import model.CodeResponse;
import service.GptService;

@RestController
@RequestMapping("/api")
public class CodeController {

    private final GptService gptService;

    @Autowired
    public CodeController(GptService gptService) {
        this.gptService = gptService;
    }

    @PostMapping("/convert")
    public ResponseEntity<CodeResponse> convertCode(@RequestBody CodeRequest request) {
        String convertedCode = gptService.convertCode(request.getCode(), request.getTargetLanguage());
        CodeResponse response = new CodeResponse();
        response.setOutput(convertedCode);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/debug")
    public ResponseEntity<CodeResponse> debugCode(@RequestBody CodeRequest request) {
        String debugInfo = gptService.debugCode(request.getCode());
        CodeResponse response = new CodeResponse();
        response.setOutput(debugInfo);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/quality")
    public ResponseEntity<CodeResponse> checkCodeQuality(@RequestBody CodeRequest request) {
        String qualityReport = gptService.checkCodeQuality(request.getCode());
        CodeResponse response = new CodeResponse();
        response.setOutput(qualityReport);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred: " + e.getMessage());
    }
}
