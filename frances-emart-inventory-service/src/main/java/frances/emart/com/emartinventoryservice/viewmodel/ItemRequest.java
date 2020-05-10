package frances.emart.com.emartinventoryservice.viewmodel;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ItemRequest {

    @NotBlank
    private String catagoryId;
 
    @NotBlank
    private String subcatagoryId;

    @NotBlank
    private BigDecimal price;

    @NotBlank
    private Number stockNumber;

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