package com.apinba.restapi.controllers.model;

import com.apinba.restapi.models.CreateTeam;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateTeamRequest {
  private String name;
  private String fullName;
  private String abbreviation;
  private String city;
  private String conference;
  private String division;

  public CreateTeamRequest(
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

  public CreateTeamRequest setName(String name) {
    this.name = name;
    return this;
  }

  public String getFullName() {
    return fullName;
  }

  public CreateTeamRequest setFullName(String fullName) {
    this.fullName = fullName;
    return this;
  }

  public String getAbbreviation() {
    return abbreviation;
  }

  public CreateTeamRequest setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
    return this;
  }

  public String getCity() {
    return city;
  }

  public CreateTeamRequest setCity(String city) {
    this.city = city;
    return this;
  }

  public String getConference() {
    return conference;
  }

  public CreateTeamRequest setConference(String conference) {
    this.conference = conference;
    return this;
  }

  public String getDivision() {
    return division;
  }

  public CreateTeamRequest setDivision(String division) {
    this.division = division;
    return this;
  }

  public CreateTeam toCreateTeam() {
    return new CreateTeam(name, fullName, abbreviation, city, conference, division);
  }
}
