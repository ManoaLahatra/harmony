package {{basePackage}}.service;

import {{basePackage}}.model.{{ClassName}};
import {{basePackage}}.model.exception.NotFoundException;
import {{basePackage}}.repository.{{ClassName}}Repository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class {{ClassName}}Service {
    private final {{ClassName}}Repository repository;

    public List<{{ClassName}}> getAll() {
        return repository.findAll();
    }

    public {{ClassName}} getByName(String name) {
        return repository.find{{ClassName}}ByName(name);
    }

    @Transactional
    public List<{{ClassName}}> crupdate(List<{{ClassName}}> list) {
        return repository.saveAll(list);
    }

    public {{ClassName}} getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("{{ClassName}} with id: " + id + " not found."));
    }
}
