package com.apinba.restapi.repositories.model;

import com.apinba.restapi.models.Team;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "team")
public class TeamEntity {
  @Id @Column private UUID id;
  @Column private String abbreviation;
  @Column private String city;
  @Column private String conference;
  @Column private String division;
  @Column private String fullName;
  @Column private String name;

  public TeamEntity(
      UUID id,
      String name,
      String fullName,
      String abbreviation,
      String city,
      String conference,
      String division) {
    this.id = id;
    this.name = name;
    this.fullName = fullName;
    this.abbreviation = abbreviation;
    this.city = city;
    this.conference = conference;
    this.division = division;
  }

  public TeamEntity() {
    // Required by JPA
  }

  public static TeamEntity fromTeam(Team team) {
    return new TeamEntity(
        team.id(),
        team.name(),
        team.fullName(),
        team.abbreviation(),
        team.city(),
        team.conference(),
        team.division());
  }

  public String getAbbreviation() {
    return abbreviation;
  }

  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getConference() {
    return conference;
  }

  public void setConference(String conference) {
    this.conference = conference;
  }

  public String getDivision() {
    return division;
  }

  public void setDivision(String division) {
    this.division = division;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String full_name) {
    this.fullName = full_name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UUID getId() {
    return id;
  }

  public Team toTeam() {
    return new Team(id, abbreviation, city, conference, division, fullName, name);
  }
}
