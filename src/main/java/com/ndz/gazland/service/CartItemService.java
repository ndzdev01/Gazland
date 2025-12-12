package com.ndz.gazland.service;

import com.ndz.gazland.mapper.CartItemMapper;
import com.ndz.gazland.models.Cart;
import com.ndz.gazland.models.CartItem;
import com.ndz.gazland.models.GasBottle;
import com.ndz.gazland.models.User;
import com.ndz.gazland.repository.CartItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class CartItemService {
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    UserService userService;
    @Autowired
    GasBottleService gasBottleService;


    /**
     * creation d'un cartItem caracterisé par son id, cart_id, gas_bottle_id et la quantité
     *  ici je peux juste avoir besoin de l'id du user, l'id du gasBottle selectionné et la quantite
     * @param user_id
     * @param gasBottleId
     * @param quantity
     * @return
     */
    @Transactional
    public ResponseEntity createCartItem(int user_id, int gasBottleId, int quantity)
    {
        log.info("CREATING A CARTITEM BY USER WITH ID {} CONTAINING {} GASBOTTLE {}", user_id, quantity, gasBottleId);

        /**
         * à partir de user_id on va recuperer l'objet User associé et ensuite recuperer le panier du user
         */
        User user = (User) userService.readUserByID(user_id).getBody();
        Cart cart = user.getCart();


        /**
         * je recupere maintenant l'objet GasBottle avec pour id gasBottleId
         */
        GasBottle gasBottle = (GasBottle) gasBottleService.getById(gasBottleId).getBody();


        /**
         *  maintenant on va creer un objet CartItem avec construteur par defaut et par la suite utiliser les setters
         *  pour initialiser ses attributs
         */
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setGasBottle(gasBottle);


        /**
         * pour pouvoir initialiser l'attribut quantity il faut que le stock soit suffisant et ensuite decrementer le stock
         */
        ResponseEntity responseEntity = gasBottleService.decreaseStock(gasBottleId, quantity); //decrementation du stock qui renvoie un ok si le stock est suffisant et a ete bien décrementé

        if(responseEntity.getStatusCode().is2xxSuccessful())
        {
            //Si 200 OK on initialise l'attribut de notre objet et on save dans la base de données
            log.info("SAVING THE CARTITEM IN THE DATABASE");
            cartItem.setQuantiy(quantity);
            cartItemRepository.save(cartItem);
        }
        else
        {
            log.warn("STOCK INSUFFISANT");
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Stock insuffisant");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(cartItem);
    }

}
