package com.apinba.restapi.services;

import com.apinba.restapi.models.GameModel;
import com.apinba.restapi.repositories.IGameRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GameService {

  private final IGameRepository gameRepository;

  public GameService(IGameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }

  public Page<GameModel> getGames(Pageable pageable, UUID id, String name) {
    if (id != null) {
      return gameRepository.findByTeamId(id, pageable);
    } else if (name != null) {
      return gameRepository.searchByName(name, pageable);
    } else {
      return gameRepository.findAll(pageable);
    }
  }

  public GameModel saveGame(GameModel gameModel) {
    return gameRepository.save(gameModel);
  }

  public List<GameModel> saveGamesList(List<GameModel> games) {
    return gameRepository.saveAll(games);
  }
}
