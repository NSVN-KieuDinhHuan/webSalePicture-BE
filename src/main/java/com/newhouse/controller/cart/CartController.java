package com.newhouse.controller.cart;

import com.newhouse.model.dto.cart.CartDetailDto;
import com.newhouse.model.dto.cart.CartDto;
import com.newhouse.model.entity.*;
import com.newhouse.model.entity.dish.Dish;
import com.newhouse.model.entity.user.User;
import com.newhouse.service.cart.ICartService;
import com.newhouse.service.cart_detail.ICartDetailService;
import com.newhouse.service.dish.IDishService;
import com.newhouse.service.option.IProductOptService;
import com.newhouse.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    IUserService userService;

    @Autowired
    ICartService cartService;
    @Autowired
    ICartDetailService cartDetailService;
    @Autowired
    IDishService dishService;

    @Autowired
    IProductOptService productOptService;

    @PostMapping("")
    public ResponseEntity<Cart> createCart(@RequestBody Long id) {
        Cart cart = new Cart();
        cart.setId(id);
        return new ResponseEntity<>(cartService.save(cart), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> findAllCart(){
        Iterable<Cart> cartAll = cartService.findAll();
        return new ResponseEntity<>(cartAll, HttpStatus.OK);
    }

    @PostMapping("/addDish/{id}")
    public ResponseEntity<?> addDishToCart(@RequestBody CartDetailDto cartDetailDto, @PathVariable Long id) {
        Optional<Dish> findDish = dishService.findById(cartDetailDto.getDishId());
        if (!findDish.isPresent()) {
            ErrorMessage errorMessage = new ErrorMessage("Món ăn không tồn tại");
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        List<ProductOption> optionList=new ArrayList<>();
        for (int i = 0; i < cartDetailDto.getProductOption().size(); i++) {
            Optional<ProductOption> proOpt= productOptService.findById( cartDetailDto.getProductOption().get(i));
            proOpt.ifPresent(optionList::add);
        }
        CartDetail cartDetail= new CartDetail();
        cartDetail.setDishId(findDish.get().getId());
        cartDetail.setQuantity(cartDetailDto.getQuantity());
        cartDetail.setProductOptions(optionList);
        cartDetailService.save(cartDetail);
        Cart cart = cartService.addDishToCart(id,cartDetail);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllDetailByCartId(@PathVariable Long id){
       return new ResponseEntity<>(cartDetailService.getCartDetailByCartID(id), HttpStatus.OK);
    }
}
