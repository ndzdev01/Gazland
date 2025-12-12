package com.ndz.gazland.mapper;


import com.ndz.gazland.dto.CartItemResponseDTO;
import com.ndz.gazland.models.CartItem;
import com.ndz.gazland.models.GasBottle;

public class CartItemMapper {
    public static CartItemResponseDTO mapToResponseDTO(CartItem cartItem)
    {
        GasBottle gasBottle = cartItem.getGasBottle();
        CartItemResponseDTO cartItemResponseDTO = new CartItemResponseDTO(cartItem.getId(),gasBottle.getId(), gasBottle.getBrand(), gasBottle.getPrice(), cartItem.getQuantiy());
        return  cartItemResponseDTO;
    }
}
