package com.apinba.restapi.services;

import com.apinba.restapi.models.TeamModel;
import com.apinba.restapi.repositories.ITeamRepository;
import java.util.ArrayList;
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

  public ArrayList<TeamModel> getTeams() {
    return (ArrayList<TeamModel>) teamRepository.findAll();
  }

  public TeamModel saveTeam(TeamModel team) {
    return teamRepository.save(team);
  }

  public Optional<TeamModel> getById(UUID id) {
    return teamRepository.findById(id);
  }

  public TeamModel updateById(TeamModel team, @RequestParam UUID id) {
    TeamModel teamModel = teamRepository.findById(id).get();

    teamModel.setName(team.getName());
    teamModel.setCity(team.getCity());
    teamModel.setAbbreviation(team.getAbbreviation());
    teamModel.setConference(team.getConference());
    teamModel.setDivision(team.getDivision());
    teamModel.setFull_name(team.getFull_name());

    teamRepository.save(teamModel);

    return teamModel;
  }

  public Boolean deleteTeamById(UUID id) {
    if (!teamRepository.existsById(id)) {
      return false;
    }

    teamRepository.deleteById(id);
    return true;
  }
}
