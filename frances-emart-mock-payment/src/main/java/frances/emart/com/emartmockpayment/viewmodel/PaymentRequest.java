package frances.emart.com.emartmockpayment.viewmodel;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PaymentRequest {

    @NotBlank
    private String clientId;

    @NotBlank
    private BigDecimal total;

    @NotBlank
    private String orderId;

    @NotBlank
    private String remarks;
}