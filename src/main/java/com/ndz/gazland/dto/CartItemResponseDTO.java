package com.ndz.gazland.dto;

public record CartItemResponseDTO(int item_id, int gas_bottle_id,String brand, double price, int quantity) {
}
