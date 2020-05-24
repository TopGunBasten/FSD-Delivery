package frances.emart.com.emartcart.model;

import java.io.Serializable;
import java.util.Hashtable;

import lombok.Data;

@Data
public class EmartCart implements Serializable {
    private static final long serialVersionUID = 4276318765325081378L;
    private String userId;
    private Hashtable<String, CartItem> items;

    public EmartCart(String userId){
        this.userId = userId;
        this.items = new Hashtable<>();
    }

    public void clearCart() {
        this.items.clear();
    }

    public void addItemToCart(CartItem item) {
        this.items.put(item.getId(), item);
    }

    public void updateItemInCart(CartItem item) {
        this.removeItemFromCart(item);
        this.addItemToCart(item);
    }

    public void removeItemFromCart(CartItem item) {
        this.items.remove(item.getId());
    }
}