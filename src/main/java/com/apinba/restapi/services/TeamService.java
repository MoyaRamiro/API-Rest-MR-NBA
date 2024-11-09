package com.apinba.restapi.services;

import com.apinba.restapi.exceptions.TeamNotFoundException;
import com.apinba.restapi.models.CreateTeam;
import com.apinba.restapi.models.Team;
import com.apinba.restapi.models.UpdateTeam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.apinba.restapi.repositories.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class TeamService {

  private final TeamRepository teamRepository;

  public TeamService(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }

  public List<Team> getTeams() {
    return teamRepository.findAll();
  }

  public Team createTeam(CreateTeam createTeam) {
    var team = new Team(
            UUID.randomUUID(),
            createTeam.abbreviation(),
            createTeam.city(),
            createTeam.conference(),
            createTeam.division(),
            createTeam.fullName(),
            createTeam.name());
    return teamRepository.save(team);
  }

  public Optional<Team> getById(UUID id) {
    return teamRepository.findById(id);
  }

  public Team updateById(UpdateTeam updateTeam, @RequestParam UUID id) {
    var team = teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException(id));
    var updatedTeam = team.updateWith(updateTeam);
    teamRepository.save(updatedTeam);
    return updatedTeam;
  }

  public void deleteTeamById(UUID id) {
    if (!teamRepository.existsById(id)) {
      throw new TeamNotFoundException(id);
    }

    teamRepository.deleteById(id);
  }
}
