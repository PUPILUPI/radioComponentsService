//ограничение на имя
CREATE CONSTRAINT unique_name IF NOT EXISTS FOR (d:Detail)
REQUIRE d.name IS UNIQUE;

// Создание индексов для быстрого поиска
CREATE INDEX detail_name_index IF NOT EXISTS FOR (d:Detail) ON (d.name);

CREATE (:Detail {name: 'R10k', categories: ['Passive_Components'], manufacturer: 'Vishay'});
CREATE (:Detail {name: 'C100uF', categories: ['Passive_Components'], manufacturer: 'Panasonic'});
CREATE (:Detail {name: 'L1mH', categories: ['Passive_Components'], manufacturer: 'TDK'});
CREATE (:Detail {name: 'R1k', categories: ['Passive_Components'], manufacturer: 'Yageo'});
CREATE (:Detail {name: 'C1uF', categories: ['Passive_Components'], manufacturer: 'Murata'});
CREATE (:Detail {name: 'C0.1uF', categories: ['Passive_Components'], manufacturer: 'KEMET'});

CREATE (:Detail {name: 'ATmega328P', categories: ['Integrated_Circuits'], manufacturer: 'Microchip'});
CREATE (:Detail {name: 'LM358', categories: ['Integrated_Circuits'], manufacturer: 'Texas_Instruments'});
CREATE (:Detail {name: '7805', categories: ['Integrated_Circuits'], manufacturer: 'ON_Semiconductor'});
CREATE (:Detail {name: '74HC00', categories: ['Integrated_Circuits'], manufacturer: 'Nexperia'});
CREATE (:Detail {name: 'CD4051', categories: ['Integrated_Circuits'], manufacturer: 'Texas_Instruments'});
CREATE (:Detail {name: 'NE555', categories: ['Integrated_Circuits'], manufacturer: 'STMicroelectronics'});

CREATE (:Detail {name: '2N2222', categories: ['Semiconductors'], manufacturer: 'ON_Semiconductor'});
CREATE (:Detail {name: 'BC557', categories: ['Semiconductors'], manufacturer: 'Fairchild_Semiconductor'});
CREATE (:Detail {name: 'IRF540N', categories: ['Semiconductors'], manufacturer: 'Infineon'});
CREATE (:Detail {name: '1N4007', categories: ['Semiconductors'], manufacturer: 'Vishay'});
CREATE (:Detail {name: '5.1V_Zener', categories: ['Semiconductors'], manufacturer: 'Rohm'});
CREATE (:Detail {name: 'BT136', categories: ['Semiconductors'], manufacturer: 'NXP_Semiconductors'});

CREATE (:Detail {name: 'SSD1306', categories: ['Display_Modules'], manufacturer: 'Adafruit'});
CREATE (:Detail {name: 'HD44780', categories: ['Display_Modules'], manufacturer: 'Hitachi'});
CREATE (:Detail {name: 'FND500', categories: ['Display_Modules'], manufacturer: 'Kingbright'});
CREATE (:Detail {name: 'ILI9341', categories: ['Display_Modules'], manufacturer: 'Waveshare'});
CREATE (:Detail {name: 'ED029TC1', categories: ['Display_Modules'], manufacturer: 'Pervasive_Displays'});
CREATE (:Detail {name: 'HT16K33', categories: ['Display_Modules'], manufacturer: 'Adafruit'});

CREATE (:Detail {name: 'B3F-1000', categories: ['Electromechanical'], manufacturer: 'Omron'});
CREATE (:Detail {name: '2-1825137-6', categories: ['Electromechanical'], manufacturer: 'TE_Connectivity'});
CREATE (:Detail {name: 'SRD-05VDC-SL-C', categories: ['Electromechanical'], manufacturer: 'Songle'});
CREATE (:Detail {name: 'PEC11R-4220F-S0018', categories: ['Electromechanical'], manufacturer: 'Bourns'});
CREATE (:Detail {name: 'CEM-1203(42)', categories: ['Electromechanical'], manufacturer: 'CUI_Devices'});
CREATE (:Detail {name: '310-101', categories: ['Electromechanical'], manufacturer: 'Precision_Microdrives'});

MATCH (p1:Detail {name: 'R10k'}), (p2:Detail {name: 'C100uF'})
CREATE (p1)-[:ANALOG {type: 'NEARLY_ANALOG'}]->(p2),
(p2)-[:ANALOG {type: 'NEARLY_ANALOG'}]->(p1);
MATCH (p1:Detail {name: 'R10k'}), (p2:Detail {name: 'L1mH'})
CREATE (p1)-[:ANALOG {type: 'NEARLY_ANALOG'}]->(p2),
(p2)-[:ANALOG {type: 'NEARLY_ANALOG'}]->(p1);
MATCH (p1:Detail {name: 'R10k'}), (p2:Detail {name: 'R1k'})
CREATE (p1)-[:ANALOG {type: 'FUNCTIONAL_ANALOG'}]->(p2),
(p2)-[:ANALOG {type: 'FUNCTIONAL_ANALOG'}]->(p1);
MATCH (p1:Detail {name: 'R10k'}), (p2:Detail {name: 'C1uF'})
CREATE (p1)-[:ANALOG {type: 'FUNCTIONAL_ANALOG'}]->(p2),
(p2)-[:ANALOG {type: 'FUNCTIONAL_ANALOG'}]->(p1);
MATCH (p1:Detail {name: 'R10k'}), (p2:Detail {name: 'C0.1uF'})
CREATE (p1)-[:ANALOG {type: 'POSSIBLE_ANALOG'}]->(p2),
(p2)-[:ANALOG {type: 'POSSIBLE_ANALOG'}]->(p1);

MATCH (p1:Detail {name: 'ATmega328P'}), (p2:Detail {name: 'LM358'})
CREATE (p1)-[:ANALOG {type: 'NEARLY_ANALOG'}]->(p2),
(p2)-[:ANALOG {type: 'NEARLY_ANALOG'}]->(p1);
MATCH (p1:Detail {name: 'ATmega328P'}), (p2:Detail {name: '7805'})
CREATE (p1)-[:ANALOG {type: 'FUNCTIONAL_ANALOG'}]->(p2),
(p2)-[:ANALOG {type: 'FUNCTIONAL_ANALOG'}]->(p1);
MATCH (p1:Detail {name: 'ATmega328P'}), (p2:Detail {name: '74HC00'})
CREATE (p1)-[:ANALOG {type: 'POSSIBLE_ANALOG'}]->(p2),
(p2)-[:ANALOG {type: 'POSSIBLE_ANALOG'}]->(p1);
MATCH (p1:Detail {name: 'ATmega328P'}), (p2:Detail {name: 'CD4051'})
CREATE (p1)-[:ANALOG {type: 'NEARLY_ANALOG'}]->(p2),
(p2)-[:ANALOG {type: 'NEARLY_ANALOG'}]->(p1);
MATCH (p1:Detail {name: 'ATmega328P'}), (p2:Detail {name: 'NE555'})
CREATE (p1)-[:ANALOG {type: 'FUNCTIONAL_ANALOG'}]->(p2),
(p2)-[:ANALOG {type: 'FUNCTIONAL_ANALOG'}]->(p1);

MATCH (p1:Detail {name: '2N2222'}), (p2:Detail {name: 'BC557'})
CREATE (p1)-[:ANALOG {type: 'NEARLY_ANALOG'}]->(p2),
(p2)-[:ANALOG {type: 'NEARLY_ANALOG'}]->(p1);
MATCH (p1:Detail {name: '2N2222'}), (p2:Detail {name: 'IRF540N'})
CREATE (p1)-[:ANALOG {type: 'NEARLY_ANALOG'}]->(p2),
(p2)-[:ANALOG {type: 'NEARLY_ANALOG'}]->(p1);
MATCH (p1:Detail {name: '2N2222'}), (p2:Detail {name: '1N4007'})
CREATE (p1)-[:ANALOG {type: 'FUNCTIONAL_ANALOG'}]->(p2),
(p2)-[:ANALOG {type: 'FUNCTIONAL_ANALOG'}]->(p1);
MATCH (p1:Detail {name: '2N2222'}), (p2:Detail {name: '5.1V_Zener'})
CREATE (p1)-[:ANALOG {type: 'POSSIBLE_ANALOG'}]->(p2),
(p2)-[:ANALOG {type: 'POSSIBLE_ANALOG'}]->(p1);
MATCH (p1:Detail {name: '2N2222'}), (p2:Detail {name: 'BT136'})
CREATE (p1)-[:ANALOG {type: 'PIN_TO_PIN'}]->(p2),
(p2)-[:ANALOG {type: 'PIN_TO_PIN'}]->(p1);

MATCH (p1:Detail {name: 'SSD1306'}), (p2:Detail {name: 'HD44780'})
CREATE (p1)-[:ANALOG {type: 'NEARLY_ANALOG'}]->(p2),
(p2)-[:ANALOG {type: 'NEARLY_ANALOG'}]->(p1);
MATCH (p1:Detail {name: 'SSD1306'}), (p2:Detail {name: 'FND500'})
CREATE (p1)-[:ANALOG {type: 'NEARLY_ANALOG'}]->(p2),
(p2)-[:ANALOG {type: 'NEARLY_ANALOG'}]->(p1);
MATCH (p1:Detail {name: 'SSD1306'}), (p2:Detail {name: 'ILI9341'})
CREATE (p1)-[:ANALOG {type: 'PIN_TO_PIN'}]->(p2),
(p2)-[:ANALOG {type: 'PIN_TO_PIN'}]->(p1);
MATCH (p1:Detail {name: 'SSD1306'}), (p2:Detail {name: 'ED029TC1'})
CREATE (p1)-[:ANALOG {type: 'NEARLY_ANALOG'}]->(p2),
(p2)-[:ANALOG {type: 'NEARLY_ANALOG'}]->(p1);
MATCH (p1:Detail {name: 'SSD1306'}), (p2:Detail {name: 'HT16K33'})
CREATE (p1)-[:ANALOG {type: 'NEARLY_ANALOG'}]->(p2),
(p2)-[:ANALOG {type: 'NEARLY_ANALOG'}]->(p1);



