package com.ndz.gazland.mapper;

import com.ndz.gazland.dto.CartItemResponseDTO;
import com.ndz.gazland.dto.CartResponseDTO;
import com.ndz.gazland.models.Cart;
import com.ndz.gazland.models.CartItem;

import java.util.List;
import java.util.stream.Collectors;

public class CartMapper {
    public static CartResponseDTO mapToResponseDTO(Cart cart)
    {
        List<CartItem> cartItemList = cart.getCartItemList();
        List<CartItemResponseDTO> cartItemResponseDTOList = cartItemList.stream().map(CartItemMapper::mapToResponseDTO).collect(Collectors.toList());
        return new CartResponseDTO(cart.getId(), cartItemResponseDTOList);
    }
}
