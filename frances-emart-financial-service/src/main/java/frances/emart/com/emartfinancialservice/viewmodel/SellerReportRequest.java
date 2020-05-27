package frances.emart.com.emartfinancialservice.viewmodel;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class SellerReportRequest {
   
   @NotBlank
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
   private LocalDateTime startDate; 
   @NotBlank
   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")

   private LocalDateTime endDate;
   @NotBlank

   private String buyerId;
}