package frances.emart.com.emartfinancialservice.viewmodel;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class DiscountRequest {

    @NotBlank
    private LocalDateTime startDate;

    @NotBlank
    private LocalDateTime endDate;

    @NotBlank
    private String description;

    @NotBlank
    private int percent;

    private String buyerId;
}