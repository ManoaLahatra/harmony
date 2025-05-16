package {{basePackage}}.repository;

import {{basePackage}}.model.{{ClassName}};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface {{ClassName}}Repository extends JpaRepository<{{ClassName}}, String> {
    {{ClassName}} find{{ClassName}}ByName(String name);
    boolean existsByName(String name);
}
