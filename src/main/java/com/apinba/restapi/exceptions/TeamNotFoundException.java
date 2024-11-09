package com.apinba.restapi.exceptions;

import java.util.UUID;

public class TeamNotFoundException extends RuntimeException {

  private final UUID teamId;

  public TeamNotFoundException(UUID teamId) {
    super("Team with id " + teamId + " not found");
    this.teamId = teamId;
  }

    public UUID getTeamId() {
        return teamId;
    }
}
