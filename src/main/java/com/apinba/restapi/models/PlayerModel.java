package com.apinba.restapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;
import java.util.UUID;
import org.hibernate.proxy.HibernateProxy;

@Entity
@Table(name = "player")
public class PlayerModel {
  @Id private UUID id = UUID.randomUUID();
  private String first_name;
  private String last_name;
  private String position;
  private String height;
  private String weight;
  private String jersey_number;
  private String college;
  private String country;
  private int draft_year;
  private int draft_round;
  private int draft_number;

  @ManyToOne
  @JoinColumn(name = "team_id")
  private TeamModel team;

  public PlayerModel() {}

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getFirst_name() {
    return first_name;
  }

  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  public String getLast_name() {
    return last_name;
  }

  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getHeight() {
    return height;
  }

  public void setHeight(String height) {
    this.height = height;
  }

  public String getWeight() {
    return weight;
  }

  public void setWeight(String weight) {
    this.weight = weight;
  }

  public String getJersey_number() {
    return jersey_number;
  }

  public void setJersey_number(String jersey_number) {
    this.jersey_number = jersey_number;
  }

  public String getCollege() {
    return college;
  }

  public void setCollege(String college) {
    this.college = college;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public int getDraft_year() {
    return draft_year;
  }

  public void setDraft_year(int draft_year) {
    this.draft_year = draft_year;
  }

  public int getDraft_round() {
    return draft_round;
  }

  public void setDraft_round(int draft_round) {
    this.draft_round = draft_round;
  }

  public int getDraft_number() {
    return draft_number;
  }

  public void setDraft_number(int draft_number) {
    this.draft_number = draft_number;
  }

  public TeamModel getTeam() {
    return team;
  }

  public void setTeam(TeamModel team) {
    this.team = team;
  }

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    Class<?> oEffectiveClass =
        o instanceof HibernateProxy
            ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
            : o.getClass();
    Class<?> thisEffectiveClass =
        this instanceof HibernateProxy
            ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
            : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    PlayerModel that = (PlayerModel) o;
    return getId() != null && Objects.equals(getId(), that.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy
        ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
        : getClass().hashCode();
  }
}
