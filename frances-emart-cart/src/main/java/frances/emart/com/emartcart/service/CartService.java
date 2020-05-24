package frances.emart.com.emartcart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import frances.emart.com.emartcart.model.CartItem;
import frances.emart.com.emartcart.model.EmartCart;

@Service
public class CartService {
    
    @Autowired
    private RedisTemplate<String, EmartCart> cartRepository;

    public EmartCart getCart(String userId){
        EmartCart cart = this.cartRepository.opsForValue().get(userId);
        if(cart ==null){
            cart = new EmartCart(userId);
        }
        return cart;
    }

    public void addItemToCart(String userId, CartItem item){
        EmartCart cart =this.getCart(userId);
        cart.addItemToCart(item);
        this.cartRepository.opsForValue().set(userId, cart);
    }

    public void updateItemInCart(String userId, CartItem item){
        EmartCart cart =this.getCart(userId);
        cart.updateItemInCart(item);
        this.cartRepository.opsForValue().set(userId, cart);
    }

    public void removeItemFromCart(String userId, CartItem item){
        EmartCart cart =this.getCart(userId);
        cart.removeItemFromCart(item);
        this.cartRepository.opsForValue().set(userId, cart);
    }

    public void cleanCart(String userId){
        EmartCart cart =this.getCart(userId);
        cart.clearCart();
        this.cartRepository.opsForValue().set(userId, cart);
    }
}