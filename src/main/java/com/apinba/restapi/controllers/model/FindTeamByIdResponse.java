package com.apinba.restapi.controllers.model;

import com.apinba.restapi.models.TeamModel;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record FindTeamByIdResponse(
    UUID id,
    String name,
    String fullName,
    String abbreviation,
    String city,
    String conference,
    String division) {

  public static FindTeamByIdResponse fromTeamModel(TeamModel teamModel) {
    return new FindTeamByIdResponse(
        teamModel.getId(),
        teamModel.getName(),
        teamModel.getFullName(),
        teamModel.getAbbreviation(),
        teamModel.getCity(),
        teamModel.getConference(),
        teamModel.getDivision());
  }
}
