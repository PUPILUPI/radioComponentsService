package ru.belov.radioComponentsService.domain.entity.noSql;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;
import java.util.Set;

@Node
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Detail {
    @Id
    private String name;
    private List<String> categories;
    private String manufacturer;

    @Relationship(type = "ANALOG")
    private Set<AnalogRelationship> analogs;

}
