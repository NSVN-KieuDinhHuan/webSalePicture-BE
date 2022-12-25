package com.newhouse.controller.cart;

import com.newhouse.model.dto.cart.CartDto;
import com.newhouse.model.entity.*;
import com.newhouse.model.entity.dish.Product;
import com.newhouse.service.cartgroup.ICartGroupService;
import com.newhouse.service.cart.ICartService;
import com.newhouse.service.product.IProductService;
import com.newhouse.service.option.IOptionService;
import com.newhouse.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class CartController {
    @Autowired
    IUserService userService;

    @Autowired
    ICartGroupService cartGroupService;
    @Autowired
    ICartService cartService;
    @Autowired
    IProductService dishService;

    @Autowired
    IOptionService productOptService;

    @PostMapping("/cart-group")
    public ResponseEntity<CartGroup> createCartGroup(@RequestBody Long id) {
        CartGroup cartGroup = new CartGroup();
        cartGroup.setId(id);
        return new ResponseEntity<>(cartGroupService.save(cartGroup), HttpStatus.OK);
    }
    @GetMapping("/cart-group")
    public ResponseEntity<?> findAllCartGroup(){
        Iterable<CartGroup> cartAll = cartGroupService.findAll();
        return new ResponseEntity<>(cartAll, HttpStatus.OK);
    }

    @PostMapping("/cart-group/addDish")
    public ResponseEntity<?> addDishToCart(@RequestBody CartDto cartDto) {
        Optional<Product> findDish = dishService.findById(cartDto.getDishId());
        if (!findDish.isPresent()) {
            ErrorMessage errorMessage = new ErrorMessage("sản phẩm không tồn tại");
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        List<Option> optionList=new ArrayList<>();
        if(cartDto.getOptionList() != null) {
            for (int i = 0; i < cartDto.getOptionList().size(); i++) {
                Optional<Option> proOpt = productOptService.findById(cartDto.getOptionList().get(i));
                proOpt.ifPresent(optionList::add);
            }
        }
        Optional<CartGroup> cartGroup = cartGroupService.findById(cartDto.getCartGroupId());
        Cart cart= new Cart();
        cart.setDishId(findDish.get().getId());
        cart.setQuantity(cartDto.getQuantity());
        cart.setOptions(optionList);
        cart.setCartGroup(cartGroup.get());
        cartService.save(cart);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/cart/cart-group/{id}")
    public ResponseEntity<?> getAllCartByCartGroupId(@PathVariable Long id){
       return new ResponseEntity<>(cartService.getCartDetailByCartID(id), HttpStatus.OK);
    }

    @GetMapping("/cart/delete/{id}")
    public ResponseEntity<?> deleteDetailCart( @PathVariable Long id) {
        cartService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/delete/cart/cart-group/{id}")
    public ResponseEntity<?> deleteCartByCartGroup( @PathVariable Long id) {
        cartService.deleteCartByCartGroupId(id.intValue());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/cart-group/{id}")
    public ResponseEntity<?> getCartGroupById(@PathVariable Long id) {
        Optional<CartGroup> CartGroupOptional =cartGroupService.findById(id);
        if (!CartGroupOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(CartGroupOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/cart/increase/{cartId}")
    public ResponseEntity<?> increaseDishQuantityInCart( @PathVariable Long cartId) {
        Cart cart= cartService.findById(cartId).get();
        cart.setQuantity(cart.getQuantity()+1);
        return new ResponseEntity<>(cartService.save(cart), HttpStatus.OK);
    }

    @GetMapping("/cart/decrease/{cartId}")
    public ResponseEntity<?> decreaseDishQuantityInCart(@PathVariable Long cartId) {
        Cart cart= cartService.findById(cartId).get();
        cart.setQuantity(cart.getQuantity()-1);
        return new ResponseEntity<>(cartService.save(cart), HttpStatus.OK);
    }
}
