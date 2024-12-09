package com.apinba.restapi.controllers;

import com.apinba.restapi.controllers.model.TeamDto;
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
  public List<TeamDto> getTeams() {
    return this.teamService.getTeams().stream().map(TeamDto::from).toList();
  }

  @PostMapping
  public TeamDto saveTeam(@RequestBody TeamModel team) {
    var newTeam = teamService.saveTeam(team);
    return TeamDto.from(newTeam);
  }

  @PostMapping("/batch")
  public List<TeamDto> savePlayersList(@RequestBody List<TeamModel> teams){
    return teamService.savePlayersList(teams).stream().map(TeamDto::from).toList();
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<TeamModel> getTeamById(@PathVariable UUID id) {
    return ResponseEntity.of(teamService.getById(id));
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
