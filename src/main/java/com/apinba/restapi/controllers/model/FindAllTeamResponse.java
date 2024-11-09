package com.apinba.restapi.controllers.model;

import com.apinba.restapi.models.TeamModel;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record FindAllTeamResponse(
    UUID id,
    String name,
    String fullName,
    String abbreviation,
    String city,
    String conference,
    String division) {

  public static FindAllTeamResponse fromTeamModel(TeamModel teamModel) {
    return new FindAllTeamResponse(
        teamModel.getId(),
        teamModel.getName(),
        teamModel.getFullName(),
        teamModel.getAbbreviation(),
        teamModel.getCity(),
        teamModel.getConference(),
        teamModel.getDivision());
  }
}
