package ru.belov.radioComponentsService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import ru.belov.radioComponentsService.domain.dto.noSql.AnalogResponseDTO;
import ru.belov.radioComponentsService.domain.entity.noSql.Detail;

import java.util.List;

@Repository
public interface DetailRepository extends Neo4jRepository<Detail, String> {
    @Query("MATCH (d:Detail) WHERE $category IN d.categories RETURN d")
    List<Detail> findByCategory(String category);

    @Query("MATCH (d:Detail {name: $name})-[r:ANALOG {type: $type}]->(a:Detail)" +
            " RETURN a.name AS name, a.manufacturer AS manufacturer, r.type AS analogType")
    List<AnalogResponseDTO> findAnalogsByNameAndType(String name, String type);

}
