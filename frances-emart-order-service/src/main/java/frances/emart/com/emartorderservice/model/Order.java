package frances.emart.com.emartorderservice.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import frances.emart.com.emartorderservice.viewmodel.OrderRequest;
import lombok.Data;

@Data
@Entity
@Table(name = "emart_order")
public class Order {

    public Order(OrderRequest request){
        this.buyerId = request.getBuyerId();
        this.discountCode = request.getDiscountCode();
        this.discountPercentage = request.getDiscountPercentage();
        this.status= OrderStatus.ACCEPT;
        this.lines = new ArrayList<>();
        createDate = LocalDateTime.now();
    }
    
    public Order(){
        createDate = LocalDateTime.now();
        this.lines = new ArrayList<>();
    }

    @Id
    @Column(nullable = false, length = 64)
    @GenericGenerator(name = "emart-order-uuid", strategy = "uuid")
    @GeneratedValue(generator = "emart-order-uuid")
    private String id;

    @Column(name="buyer_id",nullable = false, length = 64)
    private String buyerId;

    @Column(name="discount_code",nullable = false, length = 64)
    private String discountCode;

    @Column(name="discount_percentage",nullable = false)
    private int discountPercentage;

    @Column(name= "create_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime createDate;

    @Column(name= "status",  nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
   
    @Transient
    private List<OrderLine> lines;

}