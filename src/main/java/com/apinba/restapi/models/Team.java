package com.apinba.restapi.models;

import java.util.UUID;

public record Team(
    UUID id,
    String abbreviation,
    String city,
    String conference,
    String division,
    String fullName,
    String name) {

  public Team updateWith(UpdateTeam updateTeam) {
    return new Team(
        id,
        updateTeam.abbreviation(),
        updateTeam.city(),
        updateTeam.conference(),
        updateTeam.division(),
        updateTeam.fullName(),
        updateTeam.name());
  }
}
