package frances.emart.com.emartinventoryservice.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import frances.emart.com.emartinventoryservice.viewmodel.ItemRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "item")
@NoArgsConstructor
@AllArgsConstructor
public class Item {


    public Item(ItemRequest request){
        this.catagoryId = request.getCatagoryId();
        this.subCatagoryId = request.getSubcatagoryId();
        this.thumbnail = request.getThumbnail();
        this.description = request.getDescription();
        this.detailImage1 = request.getDetailImage1();
        this.detailImage2 = request.getDetailImage2();
        this.detailImage3 = request.getDetailImage3();
        this.detailImage4 = request.getDetailImage4();
        this.detailImage5 = request.getDetailImage5();
        this.price = request.getPrice();
        this.remark = request.getRemark();
        this.sellerId = request.getSellerId();
        this.stockNumber = request.getStockNumber();
        this.manufacturer =  request.getManufacturer();
        this.name = request.getName();
    }

    @Id
    @Column(nullable = false, length = 64)
    @GenericGenerator(name = "item-uuid", strategy = "uuid")
    @GeneratedValue(generator = "item-uuid")
    private String id;

    @Column(name = "catagory_id", length=64 ,nullable = false)
    private String catagoryId;
    @Column(name = "sub_catagory_id", length=64, nullable = false)
    private String subCatagoryId;
    @Column(name = "name" , nullable = false)
    private String name;
    @Column(name = "price" , nullable = false)
    private BigDecimal price;
    @Column(name = "stock_number", nullable = false, columnDefinition="INT")
    private int stockNumber;
    @Column(name = "remark", length=5000, nullable = false)
    private String remark;
    @Column(name = "description", length=2000, nullable = false)
    private String description;
    @Column(name = "seller_id", nullable = false)
    private String sellerId;
    @Column(name = "thumbnail", length=1000)
    private String thumbnail;
    @Column(name = "detail_image_1", length=1000) 
    private String detailImage1;
    @Column(name = "detail_image_2", length=1000)
    private String detailImage2;
    @Column(name = "detail_image_3", length=1000)
    private String detailImage3;
    @Column(name = "detail_image_4", length=1000)
    private String detailImage4;
    @Column(name = "detail_image_5", length=1000)
    private String detailImage5;
    @Column(name = "manufacturer", length=200)
    private String manufacturer; 
 
}