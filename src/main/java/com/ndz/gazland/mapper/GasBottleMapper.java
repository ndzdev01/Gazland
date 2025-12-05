package com.ndz.gazland.mapper;

import com.ndz.gazland.dto.GasBottleDTO;
import com.ndz.gazland.models.GasBottle;



public class GasBottleMapper {


    public static GasBottleDTO mapToGasBottleDTO(GasBottle gasBottle)
    {
        return  new GasBottleDTO(gasBottle.getId(), gasBottle.getBrand(), gasBottle.getMass(), gasBottle.getPrice(), gasBottle.getStock());
    }
}
