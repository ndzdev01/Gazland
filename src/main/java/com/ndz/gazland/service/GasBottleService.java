package com.ndz.gazland.service;

import com.ndz.gazland.dto.GasBottleDTO;
import com.ndz.gazland.mapper.GasBottleMapper;
import com.ndz.gazland.models.GasBottle;
import com.ndz.gazland.repository.GasBottleRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class GasBottleService {
    //Injection avec @AllArgsConstructor de Lombock
    GasBottleRepository gasBottleRepository;

    //Recuperer tous les produits par quantite en stock  decroissant
    public List<GasBottleDTO> getAll()
    {
        List<GasBottle> gasBottleList = gasBottleRepository.findAll();
        return gasBottleList.stream()
                .sorted(Comparator.comparing(GasBottle::getStock).reversed())
                .distinct()
                .map(GasBottleMapper::mapToGasBottleDTO)
                .collect(Collectors.toList());
    }

    //Recuperer un produit par ID
    public GasBottleDTO getById(int id)
    {
        return GasBottleMapper.mapToGasBottleDTO(gasBottleRepository.findById(id).orElseThrow(()->{
            log.error("Boutteille  GasBottle {} indisponible", id);
           return new RuntimeException("Bouteille de gaz indisponible");
        }));
    }

    //Ajouter un produit
    public GasBottleDTO addProduct(GasBottle gasBottle)
    {
        log.info("Enregistrement d'une bouteille {} dans la base de donnees", gasBottle.getBrand());
        return GasBottleMapper.mapToGasBottleDTO(gasBottleRepository.save(gasBottle));
    }

    //supprimer un produit
    @Transactional
    public void deleteProduct(int id)
    {
        //on recupere le produit correspondant à l'id passé parametre
        GasBottle gasBottle = gasBottleRepository.findById(id).orElseThrow(()->{
            log.error("Bouteille GasBottle{} indisponible", id);
            return new RuntimeException("Impossible de supprimer une bouteille  non disponible dans la base de donnees");});
        gasBottleRepository.delete(gasBottle);
    }

    //Modifier un produit
    @Transactional
    public GasBottleDTO updateProduct(int id, GasBottle gasBottle)
    {
            GasBottle gasBottle_ = gasBottleRepository.findById(id).orElseThrow(()->new RuntimeException("Produit non trouvé"));
            //Mise a jour des attributs de gasBottle2 grace aux setters
            gasBottle_.setBrand(gasBottle.getBrand());
            gasBottle_.setMass(gasBottle.getMass());
            gasBottle_.setPrice(gasBottle.getPrice());
            gasBottle_.setStock(gasBottle.getStock());
            //Sauvegarde dans la base de donnees
        log.info("Mise a jour de la bouteille {} ", id);
            return  GasBottleMapper.mapToGasBottleDTO(gasBottleRepository.save(gasBottle_));
    }

    //Augmenter le stock d'un produit
    @Transactional
    public void increaseStock(int id, int quantity)
    {

        GasBottle gasBottle = gasBottleRepository.findById(id).orElseThrow(()-> new RuntimeException("Produit non trouvé"));
        int currentStock = gasBottle.getStock();
        int newStock = currentStock + quantity;
        gasBottle.setStock(newStock);
        log.info("Augmentation du stock de la bouteille {} de {} unites", id, quantity);
    }

    //Diminuer le stock d'un produit
    @Transactional
    public void decreaseStock(int id, int quantity)
    {

        GasBottle gasBottle = gasBottleRepository.findById(id).orElseThrow(()->new RuntimeException("Produit non trouvé"));
        int currentStock = gasBottle.getStock();
        int newStock = currentStock - quantity;
        if(currentStock < quantity)
        {
            log.warn("Stock insuffisant, impossible d'effectuer une decrementation de {} unites sur la bouteille {} ", quantity, id);
            throw new RuntimeException("Stock insuffisant");
        }
        gasBottle.setStock(newStock);
    }

    //Vider le stock
    @Transactional
    public void clearTheStock(int id)
    {
        GasBottle gasBottle = gasBottleRepository.findById(id).orElseThrow(()->new RuntimeException("Produit non trouvé"));
        gasBottle.setStock(0);
        log.info("Mise a jour du stock de la bouteille {} a zero ", id);
    }
}
