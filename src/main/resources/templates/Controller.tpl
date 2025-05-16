package {{basePackage}}.rest.controller;

import {{basePackage}}.model.{{ClassName}};
import {{basePackage}}.service.{{ClassName}}Service;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {
    "http://localhost:3000",
    "https://accounts.google.com",
    "http://localhost:5173"
}, allowCredentials = "true")
@RestController
@AllArgsConstructor
@Tag(name = "{{ClassName}}")
public class {{ClassName}}Controller {
    private final {{ClassName}}Service service;

    @PutMapping("/{{endpoint}}")
    public List<{{ClassName}}> crupdate(@RequestBody List<{{ClassName}}> list) {
        return service.crupdate(list);
    }

    @GetMapping("/{{endpoint}}")
    public List<{{ClassName}}> getAll() {
        return service.getAll();
    }

    @GetMapping("/{{endpoint}}/{id}")
    public {{ClassName}} getById(@PathVariable String id) {
        return service.getById(id);
    }
}
