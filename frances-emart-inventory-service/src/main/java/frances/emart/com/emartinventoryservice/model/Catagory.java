package frances.emart.com.emartinventoryservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import frances.emart.com.emartinventoryservice.viewmodel.CatagoryRequest;
import lombok.Data;

@Data
@Entity
@Table(name = "catagory")
public class Catagory {

    public Catagory(){

    }

    public Catagory(CatagoryRequest request) {
        this.brief = request.getBrief();
        this.name = request.getName();
    }

    public Catagory(String catagoryId) {
        this.id = catagoryId;
	}



	@Id
    @Column(nullable = false, length = 64)
    @GenericGenerator(name = "catagory-uuid", strategy = "uuid")
    @GeneratedValue(generator = "catagory-uuid")
    private String id;

    @Column(name = "name", nullable = false, length =200 )
    private String name;
    
    @Column(name = "brief", nullable = false, length =500 )
    private String brief;
}