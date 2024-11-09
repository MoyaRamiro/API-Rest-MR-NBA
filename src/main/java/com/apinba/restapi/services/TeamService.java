package com.apinba.restapi.services;

import com.apinba.restapi.models.CreateTeam;
import com.apinba.restapi.models.TeamModel;
import com.apinba.restapi.models.UpdateTeam;
import com.apinba.restapi.repositories.ITeamRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class TeamService {

  private final ITeamRepository teamRepository;

  public TeamService(ITeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }

  public List<TeamModel> getTeams() {
    return teamRepository.findAll();
  }

  public TeamModel createTeam(CreateTeam createTeam) {
    var team = new TeamModel();
    team.setName(createTeam.name());
    team.setCity(createTeam.city());
    team.setAbbreviation(createTeam.abbreviation());
    team.setConference(createTeam.conference());
    team.setDivision(createTeam.division());
    team.setFull_name(createTeam.fullName());
    return teamRepository.save(team);
  }

  public Optional<TeamModel> getById(UUID id) {
    return teamRepository.findById(id);
  }

  public TeamModel updateById(UpdateTeam updateTeam, @RequestParam UUID id) {
    TeamModel team = teamRepository.findById(id).orElseThrow();
    team.updateWith(updateTeam);
    teamRepository.save(team);
    return team;
  }

  public Boolean deleteTeamById(UUID id) {
    if (!teamRepository.existsById(id)) {
      return false;
    }

    teamRepository.deleteById(id);
    return true;
  }
}
