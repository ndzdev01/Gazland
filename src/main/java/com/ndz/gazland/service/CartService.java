package com.ndz.gazland.service;

import com.ndz.gazland.dto.CartResponseDTO;
import com.ndz.gazland.mapper.CartMapper;
import com.ndz.gazland.models.Cart;

import com.ndz.gazland.models.User;
import com.ndz.gazland.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
@Slf4j
public class CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserService userService;

    /**
     * creer un panier
     * @param user_id
     * @return
     */
    public Cart create(int user_id) //Le panier est caracterisé par son id et le user a qui sera associee le panier
    {

        /**
         * etant donné que le user_id doit etre unique dans le panier au risque d'une violation de la contrainte
         * d'integrite UNIQUE il faut s'assuser que le user ne possede pas encore de panier
         * pour cela on fait un getCart sur l'objet user associé à l'id passée en parametre de notre fonction
         */

        /**
         * on recupere le user qui est associe a l'id passée en parametre
         */
        User user = (User) userService.readUserByID(user_id).getBody();
        /**
         * ensuite on recupere son panier
         */
        Cart userCart = user.getCart();
        /**
         * si userCart == null
         * on cree un panier
         */
        if(userCart==null)
        {
            log.info("CREATING A CART FOR THE USER WITH ID {} ", user_id);
            Cart cart = new Cart();
            cart.setUser(user); //ici on associe la cart au user <=> foreign key en sql
           userCart =  cartRepository.save(cart);  //enregistrement dans la base de données
        }
        /**
         * Si userCart != null
         * il sera tout simplement retourné
         */

        return userCart;
    }

    /**
     *
     * @param user_id
     * @return
     * afficher le contenu du panier d'un user
     * on passe le user_id en parametre de notre fonction
     */
    public CartResponseDTO getCartItem(int user_id)
    {

        //on recupere le user qui est associe a l'id passée en parametre
        User user = (User) userService.readUserByID(user_id).getBody();
        if(user.getCart()==null)
        {
            log.info("CARTE NULL");
            Cart cart = new Cart();
            cart.setCartItemList(new ArrayList<>());
            return  CartMapper.mapToResponseDTO(cart);
        }
        //on recupere et retourne le panier associé  au user
        return CartMapper.mapToResponseDTO(user.getCart());
    }

}
