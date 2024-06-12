package ru.belov.radioComponentsService.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import ru.belov.radioComponentsService.domain.dto.noSql.AnalogDTORes;
import ru.belov.radioComponentsService.domain.entity.noSql.Detail;

import java.util.List;

@Repository
public interface DetailRepository extends Neo4jRepository<Detail, String> {
    @Query("MATCH (d:Detail) WHERE $category IN d.categories RETURN d SKIP $skip LIMIT $limit")
    List<Detail> findByCategory(String category, Pageable  pageable);
    @Query("MATCH (d:Detail {name: $name})-[r:ANALOG {type: $type}]->(a:Detail)" +
            " RETURN a.name AS name, a.manufacturer AS manufacturer, r.type AS analogType")
    List<AnalogDTORes> findAnalogsByNameAndType(String name, String type);

}
