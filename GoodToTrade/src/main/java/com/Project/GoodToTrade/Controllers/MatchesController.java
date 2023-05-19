package com.Project.GoodToTrade.Controllers;

import com.Project.GoodToTrade.Models.Matches;
import com.Project.GoodToTrade.Models.Products;
import com.Project.GoodToTrade.Models.Users;
import com.Project.GoodToTrade.Services.MatchesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchesController {

    private final MatchesService matchesService;

    @Autowired
    public MatchesController(MatchesService matchesService) {
        this.matchesService = matchesService;
    }

    @GetMapping
    public ResponseEntity<List<Matches>> getMatches() {
        return new ResponseEntity<>(matchesService.getMatches(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matches> getMatch(@PathVariable Long id) {
        return new ResponseEntity<>(matchesService.getMatch(id), HttpStatus.OK);
    }

    @GetMapping("/user1/{user1Id}")
    public ResponseEntity<List<Matches>> getMatchesByUser1Id(@PathVariable Long user1Id) {
        return new ResponseEntity<>(matchesService.getMatchesByUser1Id(user1Id), HttpStatus.OK);
    }

    @GetMapping("/user2/{user2Id}")
    public ResponseEntity<List<Matches>> getMatchesByUser2Id(@PathVariable Long user2Id) {
        return new ResponseEntity<>(matchesService.getMatchesByUser2Id(user2Id), HttpStatus.OK);
    }

    @GetMapping("/product1/{product1Id}")
    public ResponseEntity<List<Matches>> getMatchesByProduct1Id(@PathVariable Long product1Id) {
        return new ResponseEntity<>(matchesService.getMatchesByProduct1Id(product1Id), HttpStatus.OK);
    }

    @GetMapping("/product2/{product2Id}")
    public ResponseEntity<List<Matches>> getMatchesByProduct2Id(@PathVariable Long product2Id) {
        return new ResponseEntity<>(matchesService.getMatchesByProduct2Id(product2Id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Matches> createMatch(@RequestBody Users user1, @RequestBody Users user2,
                                               @RequestBody Products product1, @RequestBody Products product2) {
        Matches newMatch = matchesService.createMatch(user1, user2, product1, product2);
        return new ResponseEntity<>(newMatch, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMatch(@PathVariable Long id) {
        matchesService.deleteMatch(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
