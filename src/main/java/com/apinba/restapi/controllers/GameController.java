package com.apinba.restapi.controllers;

import com.apinba.restapi.controllers.model.GameDto;
import com.apinba.restapi.models.GameModel;
import com.apinba.restapi.services.GameService;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "https://mr-nba.vercel.app")
@RestController
@RequestMapping("/games")
public class GameController {

  private final GameService gameService;

  public GameController(GameService gameService) {
    this.gameService = gameService;
  }

  @GetMapping
  public Page<GameDto> getGames(
      Pageable pageable,
      @RequestParam(required = false) UUID id,
      @RequestParam(required = false) String search) {
    return gameService.getGames(pageable, id, search).map(GameDto::from);
  }

  @PostMapping
  public GameDto saveGame(@RequestBody GameModel game) {
    return GameDto.from(gameService.saveGame(game));
  }

  @PostMapping("/batch")
  public List<GameDto> saveGameList(@RequestBody List<GameModel> games) {
    return gameService.saveGamesList(games).stream().map(GameDto::from).toList();
  }
}
