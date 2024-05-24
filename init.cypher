//ограничение на имя
CREATE CONSTRAINT unique_name IF NOT EXISTS FOR (d:Detail)
REQUIRE d.name IS UNIQUE;

// Создание индексов для быстрого поиска
CREATE INDEX detail_name_index IF NOT EXISTS FOR (d:Detail) ON (d.name);

CREATE (:Detail {name: 'деталь_D', category: 'Категория1'});

CREATE (:Detail {name: 'Деталь A', categories: ['Категория1', 'Категория2']});

CREATE (:Detail {name: 'Деталь B', categories: ['Категория1']});

CREATE (:Detail {name: 'Деталь C', categories: ['Категория2', 'Категория3']});

// Шаг 2: Создание рёбер аналогий между деталями с типами аналогий
MATCH (p2:Detail {name: 'деталь_D'}), (p1:Detail {name: 'Деталь B'})
CREATE (p1)-[:ANALOG {type: 'PIN_TO_PIN'}]->(p2);
MATCH (p2:Detail {name: 'деталь_D'}), (p1:Detail {name: 'Деталь B'})
CREATE (p1)-[:ANALOG {type: 'PIN_TO_PIN'}]->(p2);

MATCH (p1:Detail {name: 'Деталь C'}), (p2:Detail {name: 'Деталь B'})
CREATE (p1)-[:ANALOG {type: 'NEARLY_ANALOG'}]->(p2);

