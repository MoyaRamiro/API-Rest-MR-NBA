package com.apinba.restapi.services;

import com.apinba.restapi.models.TeamModel;
import com.apinba.restapi.repositories.ITeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeamService {
    @Autowired
    ITeamRepository teamRepository;

    public ArrayList<TeamModel> getTeams() {
        return (ArrayList<TeamModel>) teamRepository.findAll();
    }

    public TeamModel saveTeam(TeamModel team){
        return teamRepository.save(team);
    }

    public Optional<TeamModel> getById(UUID id){
        return teamRepository.findById(id);
    }

    public TeamModel updateById(TeamModel team, @RequestParam UUID id){
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

    public Boolean deleteTeamById(UUID id){
        try {
            teamRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}

