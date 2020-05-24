package frances.emart.com.emartinventoryservice.viewmodel;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ItemRequest {

    @NotBlank
    private String catagoryId;
 
    @NotBlank
    private String subcatagoryId;

    @NotBlank
    private String name;

    @NotNull
    private BigDecimal price;

    @NotNull
    private int stockNumber;

    @NotBlank
    private String remark;

    @NotBlank
    private String description;

    @NotBlank
    private String sellerId;

    @NotBlank
    private String thumbnail;
   
    @NotBlank
    private String detailImage1;
   
    @NotBlank
    private String detailImage2;

    @NotBlank
    private String detailImage3;

    @NotBlank
    private String detailImage4;

    @NotBlank
    private String detailImage5;

    @NotBlank
    private String  manufacturer; 
}