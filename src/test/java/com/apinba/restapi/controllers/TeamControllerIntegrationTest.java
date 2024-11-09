package com.apinba.restapi.controllers;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

import com.apinba.restapi.AbstractIntegrationTest;
import com.apinba.restapi.controllers.model.CreateTeamRequest;
import com.apinba.restapi.controllers.model.CreateTeamResponse;
import com.apinba.restapi.controllers.model.UpdateTeamRequest;
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
    void returnNotFoundWhenUpdate() {
      var id = UUID.fromString("00000000-0000-0000-0000-000000000000");
      update(anUpdateTeamRequest(), id).then().statusCode(SC_NOT_FOUND);
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

    private CreateTeamResponse persistedTeam;

    @BeforeEach
    void setUp() {
      persistedTeam = create(aCreateTeamRequest()).thenReturn().body().as(CreateTeamResponse.class);
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
      findAll().then().body("[0].id", is(persistedTeam.id().toString()));
    }

    @Test
    void returnsTeamNameWhenFindAll() {
      findAll().then().body("[0].name", is(persistedTeam.name()));
    }

    @Test
    void returnsTeamFullNameWhenFindAll() {
      findAll().then().body("[0].full_name", is(persistedTeam.fullName()));
    }

    @Test
    void returnsTeamAbbreviationWhenFindAll() {
      findAll().then().body("[0].abbreviation", is(persistedTeam.abbreviation()));
    }

    @Test
    void returnsTeamConferenceWhenFindAll() {
      findAll().then().body("[0].conference", is(persistedTeam.conference()));
    }

    @Test
    void returnsTeamDivisionWhenFindAll() {
      findAll().then().body("[0].division", is(persistedTeam.division()));
    }

    @Test
    void returnOkWhenFindById() {
      findById(persistedTeam.id()).then().statusCode(SC_OK);
    }

    @Test
    void returnsIdWhenFindById() {
      findById(persistedTeam.id()).then().body("id", is(persistedTeam.id().toString()));
    }

    @Test
    void returnsTeamNameWhenFindById() {
      findById(persistedTeam.id()).then().body("name", is(persistedTeam.name()));
    }

    @Test
    void returnsTeamFullNameWhenFindById() {
      findById(persistedTeam.id()).then().body("full_name", is(persistedTeam.fullName()));
    }

    @Test
    void returnsTeamAbbreviationWhenFindById() {
      findById(persistedTeam.id()).then().body("abbreviation", is(persistedTeam.abbreviation()));
    }

    @Test
    void returnsTeamConferenceWhenFindById() {
      findById(persistedTeam.id()).then().body("conference", is(persistedTeam.conference()));
    }

    @Test
    void returnsTeamDivisionWhenFindById() {
      findById(persistedTeam.id()).then().body("division", is(persistedTeam.division()));
    }

    @Test
    void returnOkWhenUpdate() {
      update(anUpdateTeamRequest(), persistedTeam.id()).then().statusCode(SC_OK);
    }

    @Test
    void returnTeamNameOnUpdate() {
      var team = anUpdateTeamRequest().setName("Bulls");

      update(team, persistedTeam.id()).then().body("name", is("Bulls"));
    }

    @Test
    void returnTeamFullNameOnUpdate() {
      var team = anUpdateTeamRequest().setFullName("Chicago Bulls");

      update(team, persistedTeam.id()).then().body("full_name", is("Chicago Bulls"));
    }

    @Test
    void returnTeamAbbreviationOnUpdate() {
      var team = anUpdateTeamRequest().setAbbreviation("CHI");

      update(team, persistedTeam.id()).then().body("abbreviation", is("CHI"));
    }

    @Test
    void returnTeamConferenceOnUpdate() {
      var team = anUpdateTeamRequest().setConference("West");

      update(team, persistedTeam.id()).then().body("conference", is("West"));
    }

    @Test
    void returnTeamDivisionOnUpdate() {
      var team = anUpdateTeamRequest().setDivision("Central");

      update(team, persistedTeam.id()).then().body("division", is("Central"));
    }

    @Test
    void updatesTeamNameOnDatabase() {
      var team = anUpdateTeamRequest().setName("Bulls");

      update(team, persistedTeam.id());

      assertThat(teamRepository.findById(persistedTeam.id()))
          .get()
          .extracting(TeamModel::getName)
          .isEqualTo("Bulls");
    }

    @Test
    void updatesTeamFullNameOnDatabase() {
      var team = anUpdateTeamRequest().setFullName("Chicago Bulls");

      update(team, persistedTeam.id());

      assertThat(teamRepository.findById(persistedTeam.id()))
          .get()
          .extracting(TeamModel::getFull_name)
          .isEqualTo("Chicago Bulls");
    }

    @Test
    void updatesTeamAbbreviationOnDatabase() {
      var team = anUpdateTeamRequest().setAbbreviation("CHI");

      update(team, persistedTeam.id());
      assertThat(teamRepository.findById(persistedTeam.id()))
          .get()
          .extracting(TeamModel::getAbbreviation)
          .isEqualTo("CHI");
    }

    @Test
    void updatesTeamConferenceOnDatabase() {
      var team = anUpdateTeamRequest().setConference("West");

      update(team, persistedTeam.id());

      assertThat(teamRepository.findById(persistedTeam.id()))
          .get()
          .extracting(TeamModel::getConference)
          .isEqualTo("West");
    }

    @Test
    void updatesTeamDivisionOnDatabase() {
      var team = anUpdateTeamRequest().setDivision("Central");

      update(team, persistedTeam.id());

      assertThat(teamRepository.findById(persistedTeam.id()))
          .get()
          .extracting(TeamModel::getDivision)
          .isEqualTo("Central");
    }

    @Test
    void returnOkWhenDeleteById() {
      delete(persistedTeam.id()).then().statusCode(SC_OK);
    }

    @Test
    void deleteTeamFromDatabase() {
      delete(persistedTeam.id());

      assertThat(teamRepository.findById(persistedTeam.id())).isEmpty();
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

  private Response update(UpdateTeamRequest body, UUID id) {
    return given().contentType(JSON).body(body).put("/teams/{id}", id);
  }

  private static Response delete(UUID id) {
    return given().delete("/teams/{id}", id);
  }

  private static CreateTeamRequest aCreateTeamRequest() {
    return new CreateTeamRequest("Celtics", "Boston Celtics", "BOS", "Boston", "East", "Atlantic");
  }

  private static UpdateTeamRequest anUpdateTeamRequest() {
    return new UpdateTeamRequest("Celtics", "Boston Celtics", "BOS", "Boston", "East", "Atlantic");
  }
}
