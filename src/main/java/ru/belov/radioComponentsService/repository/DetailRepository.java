package ru.belov.radioComponentsService.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import ru.belov.radioComponentsService.domain.dto.noSql.AnalogResponseDTO;
import ru.belov.radioComponentsService.domain.entity.noSql.Detail;

import java.util.List;

@Repository
public interface DetailRepository extends Neo4jRepository<Detail, String> {
    //    @Query("MATCH (d:Detail) WHERE $category IN d.categories RETURN d")
    @Query("MATCH (d:Detail) WHERE $category IN d.categories RETURN d SKIP $skip LIMIT $limit")
    List<Detail> findByCategory(String category, Pageable  pageable);

//    @Query(""
//            + "MATCH (d:Detail) WHERE $category IN d.categories "
//            + "RETURN d "
//            + "ORDER BY d.name " // или любое другое поле для сортировки
//            + "SKIP $skip LIMIT $limit"
//    )
//    Slice<Detail> findSliceByCategory(String category, Pageable pageable);

    @Query("MATCH (d:Detail {name: $name})-[r:ANALOG {type: $type}]->(a:Detail)" +
            " RETURN a.name AS name, a.manufacturer AS manufacturer, r.type AS analogType")
    List<AnalogResponseDTO> findAnalogsByNameAndType(String name, String type);

}