package {{basePackage}}.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"{{tableName}}\"")
public class {{ClassName}} {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

{{fields}}
}
