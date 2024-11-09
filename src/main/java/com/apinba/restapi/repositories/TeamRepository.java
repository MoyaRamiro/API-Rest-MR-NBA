package com.apinba.restapi.repositories;

import com.apinba.restapi.models.Team;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.apinba.restapi.repositories.model.TeamEntity;
import org.springframework.stereotype.Repository;

@Repository
public class TeamRepository {

  private final JpaTeamRepository jpaRepository;

  public TeamRepository(JpaTeamRepository jpaRepository) {
    this.jpaRepository = jpaRepository;
  }

  public Team save(Team team) {
    var teamEntity = TeamEntity.fromTeam(team);
    return jpaRepository.save(teamEntity).toTeam();
  }

  public Optional<Team> findById(UUID id) {
    return jpaRepository.findById(id).map(TeamEntity::toTeam);
  }

  public List<Team> findAll() {
    return jpaRepository.findAll().stream().map(TeamEntity::toTeam).toList();
  }

  public boolean existsById(UUID id) {
    return jpaRepository.existsById(id);
  }

  public void deleteById(UUID id) {
    jpaRepository.deleteById(id);
  }
}
