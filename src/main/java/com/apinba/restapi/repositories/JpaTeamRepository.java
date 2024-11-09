package com.apinba.restapi.repositories;

import java.util.UUID;

import com.apinba.restapi.repositories.model.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTeamRepository extends JpaRepository<TeamEntity, UUID> {}
