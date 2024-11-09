package com.apinba.restapi.controllers;

import com.apinba.restapi.controllers.model.CreateTeamRequest;
import com.apinba.restapi.controllers.model.CreateTeamResponse;
import com.apinba.restapi.controllers.model.FindByIdResponse;
import com.apinba.restapi.models.TeamModel;
import com.apinba.restapi.services.TeamService;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teams")
public class TeamController {

  private final TeamService teamService;

  public TeamController(TeamService teamService) {
    this.teamService = teamService;
  }

  @GetMapping
  public List<TeamModel> getTeams() {
    return this.teamService.getTeams();
  }

  @PostMapping
  public ResponseEntity<CreateTeamResponse> saveTeam(@RequestBody CreateTeamRequest requestBody) {
    var createdTeam = teamService.createTeam(requestBody.toCreateTeam());
    return ResponseEntity.ok(CreateTeamResponse.fromTeamModel(createdTeam));
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<FindByIdResponse> findById(@PathVariable UUID id) {
    var response = teamService.getById(id).map(FindByIdResponse::fromTeamModel);
    return ResponseEntity.of(response);
  }

  @PutMapping(path = "/{id}")
  public TeamModel updateTeamById(@RequestBody TeamModel request, @PathVariable UUID id) {
    return this.teamService.updateById(request, id);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<String> deleteTeamById(@PathVariable UUID id) {
    boolean ok = this.teamService.deleteTeamById(id);

    if (ok) {
      return ResponseEntity.ok("Team with id " + id + "DELETED!");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("Team with id " + id + "HAVEN'T DELETED PA NO SE DELETIO!");
    }
  }
}
