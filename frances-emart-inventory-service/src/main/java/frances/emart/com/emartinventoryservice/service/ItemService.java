package frances.emart.com.emartinventoryservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import frances.emart.com.emartinventoryservice.model.Item;
import frances.emart.com.emartinventoryservice.repository.ItemRepository;
import frances.emart.com.emartinventoryservice.viewmodel.UpdateItemRequest;

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

    public List<Item> getItemPerSeller(String sellerId) {
        return this.itemRepository.findBySellerId(sellerId);
    }

    public Item createItem(Item item) {
        return this.itemRepository.save(item);
    }

    public  Item updateItem(UpdateItemRequest updateItem) {
        Optional<Item> item = this.itemRepository.findById(updateItem.getId());
        if(!item.isPresent()) return null;
        item.get().setCatagoryId(updateItem.getCatagoryId());
        item.get().setDescription(updateItem.getDescription());
        item.get().setThumbnail(updateItem.getThumbnail());
        item.get().setDetailImage1(updateItem.getDetailImage1());
        item.get().setDetailImage2(updateItem.getDetailImage2());
        item.get().setDetailImage3(updateItem.getDetailImage3());
        item.get().setDetailImage4(updateItem.getDetailImage4());
        item.get().setDetailImage5(updateItem.getDetailImage5());
        item.get().setManufacturer(updateItem.getManufacturer());
        item.get().setName(updateItem.getName());
        item.get().setPrice(updateItem.getPrice());
        item.get().setRemark(updateItem.getRemark());
        item.get().setSubCatagoryId(updateItem.getSubcatagoryId());
        return this.itemRepository.save(item.get());
    }

    public Item updateStockNumber(String id, int number) {
        Optional<Item> item = this.itemRepository.findById(id);
        if(!item.isPresent()) return null;
        item.get().setStockNumber(number);
        return this.itemRepository.save(item.get());
    }
    
}