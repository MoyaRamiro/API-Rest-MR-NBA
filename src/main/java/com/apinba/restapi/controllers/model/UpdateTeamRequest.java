package com.apinba.restapi.controllers.model;

import com.apinba.restapi.models.UpdateTeam;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UpdateTeamRequest {
  private String name;
  private String fullName;
  private String abbreviation;
  private String city;
  private String conference;
  private String division;

  public UpdateTeamRequest(
      String name,
      String fullName,
      String abbreviation,
      String city,
      String conference,
      String division) {
    this.name = name;
    this.fullName = fullName;
    this.abbreviation = abbreviation;
    this.city = city;
    this.conference = conference;
    this.division = division;
  }

  public String getName() {
    return name;
  }

  public UpdateTeamRequest setName(String name) {
    this.name = name;
    return this;
  }

  public String getFullName() {
    return fullName;
  }

  public UpdateTeamRequest setFullName(String fullName) {
    this.fullName = fullName;
    return this;
  }

  public String getAbbreviation() {
    return abbreviation;
  }

  public UpdateTeamRequest setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
    return this;
  }

  public String getCity() {
    return city;
  }

  public UpdateTeamRequest setCity(String city) {
    this.city = city;
    return this;
  }

  public String getConference() {
    return conference;
  }

  public UpdateTeamRequest setConference(String conference) {
    this.conference = conference;
    return this;
  }

  public String getDivision() {
    return division;
  }

  public UpdateTeamRequest setDivision(String division) {
    this.division = division;
    return this;
  }

  public UpdateTeam toUpdateTeam() {
    return new UpdateTeam(name, fullName, abbreviation, city, conference, division);
  }
}
