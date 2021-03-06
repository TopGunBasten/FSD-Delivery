package frances.emart.com.emartinventoryservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import frances.emart.com.emartinventoryservice.model.Item;
import frances.emart.com.emartinventoryservice.service.ItemService;
import frances.emart.com.emartinventoryservice.viewmodel.ItemRequest;
import frances.emart.com.emartinventoryservice.viewmodel.StockRequest;
import frances.emart.com.emartinventoryservice.viewmodel.UpdateItemRequest;

@RestController
public class ItemController {
  
   @Autowired
   private ItemService itemService;

    @RequestMapping(path="/items" ,method = RequestMethod.POST)
    public ResponseEntity<?> createItem(@Valid @RequestBody ItemRequest item){
       return new ResponseEntity<>(this.itemService.createItem(new Item(item)),HttpStatus.CREATED);
    }
    
    @RequestMapping(path="/items" ,method = RequestMethod.GET)
    public Item getItem(@RequestParam("id") String id){
       return this.itemService.getItem(id);
    }

    @RequestMapping(path="/items/bySeller" ,method = RequestMethod.GET)
    public List<Item> getItemPerSeller(@RequestParam("sellerId") String sellerId){
       return this.itemService.getItemPerSeller(sellerId);
    }

    @RequestMapping(path="/items/search" ,method = RequestMethod.GET)
    public List<Item> searchItem(@RequestParam("text") String text) {
       return this.itemService.searchItem(text);
    }

    @RequestMapping(path="/items" ,method = RequestMethod.PUT)
    public ResponseEntity<?> updateItem(@Valid @RequestBody UpdateItemRequest item) {
       return new ResponseEntity<>(this.itemService.updateItem(item),HttpStatus.OK);
    }

    @RequestMapping(path="/items/stock" ,method = RequestMethod.PUT)
    public ResponseEntity<?>  updateStock(@Valid @RequestBody StockRequest item) {
        return new ResponseEntity<>(this.itemService.updateStockNumber(item.getId(), item.getStockNumber()), HttpStatus.OK);
    }
    
}