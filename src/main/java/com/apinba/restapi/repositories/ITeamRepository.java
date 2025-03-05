package com.apinba.restapi.repositories;

import com.apinba.restapi.models.TeamModel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeamRepository extends JpaRepository<TeamModel, UUID> {}
