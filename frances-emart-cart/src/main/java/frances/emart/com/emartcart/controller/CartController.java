package frances.emart.com.emartcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import frances.emart.com.emartcart.model.EmartCart;
import frances.emart.com.emartcart.service.CartService;
import frances.emart.com.emartcart.viewmodel.ItemRequest;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(path = "/carts" , method=RequestMethod.GET)
    public EmartCart getCart(@RequestParam("userId") String userId){
        return this.cartService.getCart(userId);
    }

    @RequestMapping(path="/carts/items", method=RequestMethod.POST)
    public ResponseEntity<?> addItemToCart(@RequestBody ItemRequest request){
        this.cartService.addItemToCart(request.getUserId(), request.getCart());
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @RequestMapping(path="/carts/items", method=RequestMethod.PUT)
    public ResponseEntity<?> updateItemToCart(@RequestBody ItemRequest request){
        this.cartService.updateItemInCart(request.getUserId(), request.getCart());
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @RequestMapping(path="/carts/items", method=RequestMethod.DELETE)
    public ResponseEntity<?> removeItemToCart(@RequestBody ItemRequest request){
        this.cartService.removeItemFromCart(request.getUserId(), request.getCart());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}