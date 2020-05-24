package frances.emart.com.emartorderservice.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

import org.hibernate.annotations.GenericGenerator;

import frances.emart.com.emartorderservice.viewmodel.OrderLineRequest;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "order_line")
public class OrderLine {

    public OrderLine()
    {
        
    }

    public OrderLine(OrderLineRequest line, Order order) {
        this.itemId = line.getItemId();
        this.itemName = line.getItemName();
        this.price = line.getPrice();
        this.quantity = line.getQuantity();
        this.order = order;
        this.thumbnail = line.getThumbnail();
    }

    @Id
    @Column(nullable = false, length = 64)
    @GenericGenerator(name = "order-line-uuid", strategy = "uuid")
    @GeneratedValue(generator = "order-line-uuid")
    private String id;

    @Column(name="item_id",  length = 64, nullable=false)
    private String itemId;

    @Column(name="item_name", nullable=false)
    private String itemName;

    @Column(name="price",nullable = false)
    private BigDecimal price;

    @Column(name = "quantity", nullable = false, columnDefinition="INT")
    private int quantity;

    @Column(name="thumbnail",nullable = false)
    private String thumbnail;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Order.class)
    @JoinColumn(name="order_id",referencedColumnName ="id", updatable = false)
    private Order order;

}