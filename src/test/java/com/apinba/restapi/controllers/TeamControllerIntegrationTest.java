package com.apinba.restapi.controllers;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

import com.apinba.restapi.AbstractIntegrationTest;
import com.apinba.restapi.controllers.model.CreateTeamRequest;
import com.apinba.restapi.models.TeamModel;
import com.apinba.restapi.repositories.ITeamRepository;
import io.restassured.response.Response;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class TeamControllerIntegrationTest extends AbstractIntegrationTest {

  @Autowired private ITeamRepository teamRepository;

  @Nested
  class GivenNoPersistedTeamsShould {

    @Test
    void returnOkWhenFindAll() {
      findAll().then().statusCode(SC_OK);
    }

    @Test
    void returnEmptyArrayWhenFindAll() {
      findAll().then().body("$.size()", is(0));
    }

    @Test
    void returnNotFoundWhenFindById() {
      var id = UUID.fromString("00000000-0000-0000-0000-000000000000");
      findById(id).then().statusCode(SC_NOT_FOUND);
    }

    @Test
    void returnNotFoundWhenDeleteById() {
      var id = UUID.fromString("00000000-0000-0000-0000-000000000000");
      delete(id).then().statusCode(SC_NOT_FOUND);
    }

    @Test
    void returnOkWhenCreate() {
      create(aCreateTeamRequest()).then().statusCode(SC_OK);
    }

    @Test
    void saveTeamInDatabaseWhenCreate() {
      create(aCreateTeamRequest()).then().statusCode(SC_OK);
      assertThat(teamRepository.findAll()).hasSize(1);
    }

    @Test
    void saveTeamNameWhenCreate() {
      var team = aCreateTeamRequest().setName("Bulls");

      create(team);

      assertThat(teamRepository.findAll())
          .singleElement()
          .extracting(TeamModel::getName)
          .isEqualTo("Bulls");
    }

    @Test
    void saveTeamCityWhenCreate() {
      var team = aCreateTeamRequest().setCity("Chicago");

      create(team);

      assertThat(teamRepository.findAll())
          .singleElement()
          .extracting(TeamModel::getCity)
          .isEqualTo("Chicago");
    }

    @Test
    void saveTeamAbbreviationWhenCreate() {
      var team = aCreateTeamRequest().setAbbreviation("CHI");

      create(team);

      assertThat(teamRepository.findAll())
          .singleElement()
          .extracting(TeamModel::getAbbreviation)
          .isEqualTo("CHI");
    }

    @Test
    void saveTeamDivisionWhenCreate() {
      var team = aCreateTeamRequest().setDivision("Central");

      create(team);

      assertThat(teamRepository.findAll())
          .singleElement()
          .extracting(TeamModel::getDivision)
          .isEqualTo("Central");
    }

    @Test
    void saveTeamConferenceWhenCreate() {
      var team = aCreateTeamRequest().setConference("East");

      create(team);

      assertThat(teamRepository.findAll())
          .singleElement()
          .extracting(TeamModel::getConference)
          .isEqualTo("East");
    }

    @Test
    void saveTeamFullNameWhenCreate() {
      var team = aCreateTeamRequest().setFullName("Chicago Bulls");

      create(team);

      assertThat(teamRepository.findAll())
          .singleElement()
          .extracting(TeamModel::getFull_name)
          .isEqualTo("Chicago Bulls");
    }
  }

  @Nested
  class GivenPersistedTeamsShould {

    private TeamModel persistedTeam;

    @BeforeEach
    void setUp() {
      persistedTeam = create(aCreateTeamRequest()).thenReturn().body().as(TeamModel.class);
    }

    @Test
    void returnOkWhenFindAll() {
      findAll().then().statusCode(SC_OK);
    }

    @Test
    void returnAllPersistedTeamsWhenFindAll() {
      findAll().then().body("size()", is(1));
    }

    @Test
    void returnsIdWhenFindAll() {
      findAll().then().body("[0].id", is(persistedTeam.getId().toString()));
    }

    @Test
    void returnsTeamNameWhenFindAll() {
      findAll().then().body("[0].name", is(persistedTeam.getName()));
    }

    @Test
    void returnsTeamFullNameWhenFindAll() {
      findAll().then().body("[0].full_name", is(persistedTeam.getFull_name()));
    }

    @Test
    void returnsTeamAbbreviationWhenFindAll() {
      findAll().then().body("[0].abbreviation", is(persistedTeam.getAbbreviation()));
    }

    @Test
    void returnsTeamConferenceWhenFindAll() {
      findAll().then().body("[0].conference", is(persistedTeam.getConference()));
    }

    @Test
    void returnsTeamDivisionWhenFindAll() {
      findAll().then().body("[0].division", is(persistedTeam.getDivision()));
    }

    @Test
    void returnOkWhenFindById() {
      findById(persistedTeam.getId()).then().statusCode(SC_OK);
    }

    @Test
    void returnsIdWhenFindById() {
      findById(persistedTeam.getId()).then().body("id", is(persistedTeam.getId().toString()));
    }

    @Test
    void returnsTeamNameWhenFindById() {
      findById(persistedTeam.getId()).then().body("name", is(persistedTeam.getName()));
    }

    @Test
    void returnsTeamFullNameWhenFindById() {
      findById(persistedTeam.getId()).then().body("full_name", is(persistedTeam.getFull_name()));
    }

    @Test
    void returnsTeamAbbreviationWhenFindById() {
      findById(persistedTeam.getId())
          .then()
          .body("abbreviation", is(persistedTeam.getAbbreviation()));
    }

    @Test
    void returnsTeamConferenceWhenFindById() {
      findById(persistedTeam.getId()).then().body("conference", is(persistedTeam.getConference()));
    }

    @Test
    void returnsTeamDivisionWhenFindById() {
      findById(persistedTeam.getId()).then().body("division", is(persistedTeam.getDivision()));
    }

    @Test
    void returnOkWhenUpdate() {
      update(aValidTeam(), persistedTeam.getId()).then().statusCode(SC_OK);
    }

    @Test
    void returnTeamNameOnUpdate() {
      var team = aValidTeam();
      team.setName("Bulls");

      update(team, persistedTeam.getId()).then().body("name", is(team.getName()));
    }

    @Test
    void returnTeamFullNameOnUpdate() {
      var team = aValidTeam();
      team.setFull_name("Chicago Bulls");

      update(team, persistedTeam.getId()).then().body("full_name", is(team.getFull_name()));
    }

    @Test
    void returnTeamAbbreviationOnUpdate() {
      var team = aValidTeam();
      team.setAbbreviation("CHI");

      update(team, persistedTeam.getId()).then().body("abbreviation", is(team.getAbbreviation()));
    }

    @Test
    void returnTeamConferenceOnUpdate() {
      var team = aValidTeam();
      team.setConference("West");

      update(team, persistedTeam.getId()).then().body("conference", is(team.getConference()));
    }

    @Test
    void returnTeamDivisionOnUpdate() {
      var team = aValidTeam();
      team.setDivision("Central");

      update(team, persistedTeam.getId()).then().body("division", is(team.getDivision()));
    }

    @Test
    void updatesTeamNameOnDatabase() {
      var team = aValidTeam();
      team.setName("Bulls");

      update(team, persistedTeam.getId());

      assertThat(teamRepository.findById(persistedTeam.getId()))
          .get()
          .extracting(TeamModel::getName)
          .isEqualTo("Bulls");
    }

    @Test
    void updatesTeamFullNameOnDatabase() {
      var team = aValidTeam();
      team.setFull_name("Chicago Bulls");

      update(team, persistedTeam.getId());

      assertThat(teamRepository.findById(persistedTeam.getId()))
          .get()
          .extracting(TeamModel::getFull_name)
          .isEqualTo("Chicago Bulls");
    }

    @Test
    void updatesTeamAbbreviationOnDatabase() {
      var team = aValidTeam();
      team.setAbbreviation("CHI");

      update(team, persistedTeam.getId());
      assertThat(teamRepository.findById(persistedTeam.getId()))
          .get()
          .extracting(TeamModel::getAbbreviation)
          .isEqualTo("CHI");
    }

    @Test
    void updatesTeamConferenceOnDatabase() {
      var team = aValidTeam();
      team.setConference("West");

      update(team, persistedTeam.getId());

      assertThat(teamRepository.findById(persistedTeam.getId()))
          .get()
          .extracting(TeamModel::getConference)
          .isEqualTo("West");
    }

    @Test
    void updatesTeamDivisionOnDatabase() {
      var team = aValidTeam();
      team.setDivision("Central");

      update(team, persistedTeam.getId());

      assertThat(teamRepository.findById(persistedTeam.getId()))
          .get()
          .extracting(TeamModel::getDivision)
          .isEqualTo("Central");
    }

    @Test
    void returnOkWhenDeleteById() {
      delete(persistedTeam.getId()).then().statusCode(SC_OK);
    }

    @Test
    void deleteTeamFromDatabase() {
      delete(persistedTeam.getId());

      assertThat(teamRepository.findById(persistedTeam.getId())).isEmpty();
    }
  }

  private static Response create(CreateTeamRequest body) {
    return given().contentType(JSON).body(body).when().post("/teams");
  }

  private static Response findById(UUID id) {
    return given().get("/teams/{id}", id);
  }

  private static Response findAll() {
    return given().get("/teams");
  }

  private Response update(TeamModel team, UUID id) {
    return given().contentType(JSON).body(team).put("/teams/{id}", id);
  }

  private static Response delete(UUID id) {
    return given().delete("/teams/{id}", id);
  }

  private static TeamModel aValidTeam() {
    var team = new TeamModel();
    team.setAbbreviation("BOS");
    team.setCity("Boston");
    team.setConference("East");
    team.setDivision("Atlantic");
    team.setFull_name("Boston Celtics");
    team.setName("Celtics");
    return team;
  }

  private static CreateTeamRequest aCreateTeamRequest() {
    return new CreateTeamRequest(
            "Celtics", "Boston Celtics", "BOS", "Boston", "East", "Atlantic"
    );
  }
}
