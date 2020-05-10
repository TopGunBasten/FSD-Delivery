package frances.emart.com.emartfinancialservice.viewmodel;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SellerReportRequest {
   
   @NotBlank
   private LocalDateTime startDate; 
   @NotBlank
   private LocalDateTime endDate;
   @NotBlank
   private String buyerId;
}