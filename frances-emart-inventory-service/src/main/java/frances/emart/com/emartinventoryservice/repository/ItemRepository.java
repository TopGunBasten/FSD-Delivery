package frances.emart.com.emartinventoryservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import frances.emart.com.emartinventoryservice.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

    @Query("select new Item(I.id, I.catagoryId, I.subCatagoryId, I.name, I.price, I.stockNumber, I.remark, I.description, I.sellerId, I.thumbnail, I.detailImage1, I.detailImage2, I.detailImage3, I.detailImage4, I.detailImage5, I.manufacturer) from Item as I join Catagory as C on I.catagoryId = C.id join SubCatagory as S on I.subCatagoryId = S.id where S.name like CONCAT('%', :keyWords, '%') or S.brief like CONCAT('%', :keyWords, '%') or C.name like CONCAT('%', :keyWords, '%') or C.brief like CONCAT('%', :keyWords, '%')")
    List<Item> searchItemByKeyWords(String keyWords);

    List<Item> findBySellerId(String sellerId);
    
}