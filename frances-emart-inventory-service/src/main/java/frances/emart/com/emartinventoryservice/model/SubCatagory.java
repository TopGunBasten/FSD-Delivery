package frances.emart.com.emartinventoryservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.GenericGenerator;

import frances.emart.com.emartinventoryservice.viewmodel.SubCatagoryRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
@Table(name="sub_catagory")
public class SubCatagory {
    public SubCatagory(SubCatagoryRequest request) {
        this.brief = request.getBrief();
        this.catagory = new Catagory();
        this.catagory.setId(request.getCatagoryId());
        this.gts = request.getGts();
        this.name = request.getName();
    }

    public SubCatagory(){
        
    }
    

    @Id
    @Column(nullable = false, length = 64)
    @GenericGenerator(name = "sub-catagory-uuid", strategy = "uuid")
    @GeneratedValue(generator = "catagory-uuid")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Catagory.class)
    @JoinColumn(name="catagory_id",referencedColumnName ="id", updatable = false)
    @JsonIgnore
    private Catagory catagory;

    @Column(name="brief", length = 500)
    private String brief;
    @Column(name="gts", length = 200)
    private String gts;
    @Column(name="name", length = 200)
    private String name;
}