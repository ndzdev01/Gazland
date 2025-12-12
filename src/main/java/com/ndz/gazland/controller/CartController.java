package com.ndz.gazland.controller;


import com.ndz.gazland.dto.CartResponseDTO;
import com.ndz.gazland.models.Cart;
import com.ndz.gazland.service.CartItemService;
import com.ndz.gazland.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    CartItemService cartItemService;

    @GetMapping("/users/{user_id}")
    public CartResponseDTO displayCart(@PathVariable int user_id)
    {
        return cartService.getCartItem(user_id);
    }

    @PostMapping("/users/{user_id}")
    public ResponseEntity addToCart(@PathVariable int user_id, @RequestParam int gas_bottle_id, @RequestParam int quantity)
    {
        cartService.create(user_id);
       return cartItemService.createCartItem(user_id,gas_bottle_id,quantity);
    }
}
