package ru.belov.radioComponentsService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RadioComponentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RadioComponentsApplication.class, args);
    }

//    @Override
//    public void run(String... args) {
//        Liquigraph liquigraph = new Liquigraph();
//        liquigraph.runMigrations(new ConfigurationBuilder()
//                .withMasterChangelogLocation("db/changelog/noSql/changelog.xml")
//                .withUri("jdbc:neo4j:bolt://localhost:7474")
//                .withUsername("neo4j")
//                .withPassword("aA5914190")
//                .build());
//    }
}

