package com.ndz.gazland.dto;

import java.util.List;

public record CartResponseDTO(int cart_id, List<CartItemResponseDTO> cartItemList) {
}
