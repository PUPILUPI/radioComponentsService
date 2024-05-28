package ru.belov.radioComponentsService.domain.entity.noSql;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
public class AnalogRelationship {

    @Id
    @GeneratedValue
    private Long id;

    private String type;

    @TargetNode
    private Detail target;
}
