package frances.emart.com.emartinventoryservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import frances.emart.com.emartinventoryservice.model.Item;
import frances.emart.com.emartinventoryservice.repository.ItemRepository;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> searchItem(String keywords) {
        return this.itemRepository.searchItemByKeyWords(keywords);
    }

    public Item getItem(String id){
        Optional<Item> item = this.itemRepository.findById(id);
        if(item.isPresent()) {
           return item.get();
        }
        return null;
    }

    public Item createItem(Item item) {
        return this.itemRepository.save(item);
    }

    public  Item updateItem(Item item) {
        return this.itemRepository.save(item);
    }

    public Item updateStockNumber(String id, Number number) {
        Optional<Item> item = this.itemRepository.findById(id);
        if(!item.isPresent()) return null;
        item.get().setStockNumber(number);
        return this.itemRepository.save(item.get());
    }
    
}