package com.ndz.gazland.controller;



import com.ndz.gazland.models.GasBottle;
import com.ndz.gazland.service.GasBottleService;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;


@RestController
@RequestMapping("/api/v1/gasbottle")
@Slf4j
public class GasBottleController {


    GasBottleService gasBottleService;
    public GasBottleController(GasBottleService gasBottleService)
    {

        this.gasBottleService = gasBottleService;
    }

    @GetMapping
    public ResponseEntity getAllGasBottle()
    {
        log.info("Fetching all gas bottle");
        return ResponseEntity.status(HttpStatus.OK).body(gasBottleService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getGasBottleByID(@PathVariable int id)
    {
        log.info("Find a gas bottle by his id {}", id);
        return gasBottleService.getById(id);
    }

    @PostMapping
    public ResponseEntity addGasBottle(@RequestBody GasBottle gasBottle)
    {
        log.info("Add {} bottle in db", gasBottle.getBrand());
        return ResponseEntity.status(HttpStatus.CREATED).body(gasBottleService.addProduct(gasBottle));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateGasBottle(@PathVariable int id, @RequestBody GasBottle gasBottle)
    {
        log.info("Updating {}", gasBottle.getBrand());
        return ResponseEntity.status(HttpStatus.CREATED).body(gasBottleService.updateProduct(id, gasBottle));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteGasBottle(@PathVariable int id)
    {
        log.info("deleting {}", id);
        gasBottleService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body("Suppression reussie");
    }

    @PatchMapping("/increase/{id}/stock")
    public ResponseEntity increaseStock(@PathVariable int id, @RequestParam int quantity)
    {
        gasBottleService.increaseStock(id, quantity);
        return ResponseEntity.status(HttpStatus.OK).body("Augmentation du stock reussie");
    }

    @PatchMapping("/decrease/{id}/stock")
    public ResponseEntity decreaseStock(@PathVariable int id,@RequestParam int quantity)
    {
        log.info("Diminution du stock de la bouteille avec pour id {} de {} unites",id, quantity);
       return gasBottleService.decreaseStock(id, quantity);
    }
}
