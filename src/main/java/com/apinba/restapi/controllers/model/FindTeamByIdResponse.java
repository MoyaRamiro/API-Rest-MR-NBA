package com.apinba.restapi.controllers.model;

import com.apinba.restapi.models.Team;
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

  public static FindTeamByIdResponse fromTeamModel(Team team) {
    return new FindTeamByIdResponse(
        team.id(),
        team.name(),
        team.fullName(),
        team.abbreviation(),
        team.city(),
        team.conference(),
        team.division());
  }
}
