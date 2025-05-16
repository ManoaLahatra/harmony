package com.matimi;

import lombok.NoArgsConstructor;
import picocli.CommandLine;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@CommandLine.Command(
        mixinStandardHelpOptions = true,
        description = "Generate Spring Boot CRUD from entities"
)
public class CrudCommand implements Runnable {

    @CommandLine.Parameters(index = "0", description = "Entity name (ex: User)")
    private String className;

    @CommandLine.Parameters(index = "1", description = "Base package")
    private String basePackage;

    @CommandLine.Parameters(index = "2", description = "Endpoint")
    private String endpoint;

    @CommandLine.Parameters(index = "3", description = "Table name")
    private String tableName;

    @CommandLine.Parameters(index = "4..*", description = "Entity field (ex: name:string age:int)")
    private List<String> fields;

    @Override
    public void run() {
        System.out.println("🛠 CRUD generation for: " + className);

        Map<String, String> fieldMap = new LinkedHashMap<>();
        for (String field : fields) {
            String[] parts = field.split(":");
            if (parts.length == 2) {
                fieldMap.put(parts[0], parts[1]);
            }
        }

        TemplateEngine engine = new TemplateEngine("src/main/resources/templates");

        engine.generate("Entity.tpl", "output/" + className + ".java", Map.of(
                "ClassName", className,
                "fields", TemplateEngine.generateFields(fieldMap),
                "basePackage", basePackage,
                "tableName", tableName
        ));

        engine.generate("Repository.tpl", "output/" + className + "Repository.java", Map.of(
                "ClassName", className,
                "basePackage", basePackage
        ));

        engine.generate("Service.tpl", "output/" + className + "Service.java", Map.of(
                "ClassName", className,
                "basePackage", basePackage
        ));

        engine.generate("Controller.tpl", "output/" + className + "Controller.java", Map.of(
                "ClassName", className,
                "basePackage", basePackage,
                "endpoint", endpoint
        ));
    }
}
