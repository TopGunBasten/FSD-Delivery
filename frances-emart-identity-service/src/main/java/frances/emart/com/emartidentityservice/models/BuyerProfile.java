package frances.emart.com.emartidentityservice.models;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "buyer_profile")
public class BuyerProfile {


    public BuyerProfile() {
        createdDate = LocalDateTime.now();
    }

    @Id
    @Column(nullable = false, length = 64)
    @GenericGenerator(name = "user-uuid", strategy = "uuid")
    @GeneratedValue(generator = "user-uuid")
    private String id;

    @Column(name = "mobile_number", length =20 )
    private String mobileNumber;

    @Column(name= "create_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdDate; 
}