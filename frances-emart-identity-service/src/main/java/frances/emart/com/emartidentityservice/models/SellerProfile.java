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
@Table(name = "seller_profile")
public class SellerProfile {

    public SellerProfile() {
        createdDate = LocalDateTime.now();
    }

    @Id
    @GenericGenerator(name = "seller-uuid", strategy = "uuid")
    @GeneratedValue(generator = "seller-uuid")
    private String id;
    
    @Column(name="company_name",length =256, nullable = false)
    private String companyName;

    @Column(name="gstin",length =128, nullable = false)
    private String GSTIN;

    @Column(name="brief",length =4000, nullable = false)
    private String brief;

    @Column(name="postal_address",length =1000, nullable = false)
    private String postalAddress;

    @Column(name="website",length =500, nullable = true)
    private String webSite;

    @Column(name="contract_number",length =20, nullable = false)
    private String contractNumber;

    @Column(name= "create_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdDate; 

}
