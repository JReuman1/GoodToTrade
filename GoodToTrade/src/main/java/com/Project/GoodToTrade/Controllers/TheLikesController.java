package com.Project.GoodToTrade.Controllers;

import com.Project.GoodToTrade.Models.TheLikes;
import com.Project.GoodToTrade.Services.TheLikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
public class TheLikesController {

    private final TheLikesService theLikesService;

    @Autowired
    public TheLikesController(TheLikesService theLikesService) {
        this.theLikesService = theLikesService;
    }

    @GetMapping
    public ResponseEntity<List<TheLikes>> getLikes() {
        return new ResponseEntity<>(theLikesService.getLikes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TheLikes> getLike(@PathVariable Long id) {
        return new ResponseEntity<>(theLikesService.getLike(id), HttpStatus.OK);
    }

    @GetMapping("/likedBy/{userId}")
    public ResponseEntity<List<TheLikes>> getLikesByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(theLikesService.getLikesByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/liked/{productId}")
    public ResponseEntity<List<TheLikes>> getLikesByProductId(@PathVariable Long productId) {
        return new ResponseEntity<>(theLikesService.getLikesByProductId(productId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TheLikes> saveLike(@RequestBody TheLikes like) {
        return new ResponseEntity<>(theLikesService.saveLike(like), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TheLikes> updateLike(@PathVariable Long id, @RequestBody TheLikes updatedLike) {
        TheLikes currentLike = theLikesService.getLike(id);
        currentLike.setLikedBy(updatedLike.getLikedBy());
        currentLike.setLiked(updatedLike.getLiked());
        return new ResponseEntity<>(theLikesService.saveLike(currentLike), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLike(@PathVariable Long id) {
        theLikesService.deleteLike(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
