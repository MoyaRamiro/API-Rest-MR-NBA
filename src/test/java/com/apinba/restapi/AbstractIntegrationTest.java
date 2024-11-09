package com.apinba.restapi;

import com.apinba.restapi.repositories.JpaTeamRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestcontainersConfiguration.class)
public class AbstractIntegrationTest {

    @Autowired
    private JpaTeamRepository teamRepository;

    @LocalServerPort
    private Integer port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        teamRepository.deleteAll();
    }
}
