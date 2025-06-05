package com.matimi.infrastructure.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class TemplateEngine {
    private final String templateDir;

    public TemplateEngine(String templateDir) {
        this.templateDir = templateDir;
    }

    public String render(String templateName, Map<String, String> data) throws IOException {
        String templateContent = loadTemplate(templateName);
        return processTemplate(templateContent, data);
    }

    private String loadTemplate(String templateName) throws IOException {
        Path templatePath = Path.of(templateDir, templateName);
        return Files.readString(templatePath);
    }

    private String processTemplate(String content, Map<String, String> data) {
        String processed = content;
        for (Map.Entry<String, String> entry : data.entrySet()) {
            processed = processed.replace("{{" + entry.getKey() + "}}", entry.getValue());
        }
        return processed;
    }
}
