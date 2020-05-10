package frances.emart.com.emartfinancialservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import frances.emart.com.emartfinancialservice.model.EmartTranscation;
import frances.emart.com.emartfinancialservice.service.EmartTranscationService;
import frances.emart.com.emartfinancialservice.viewmodel.SellerReportRequest;

@RestController
public class EmartTranscationCotroller {

    @Autowired
    private EmartTranscationService transcationService;

    @RequestMapping(path="/transcations/seller" ,method = RequestMethod.POST)
    public List<EmartTranscation> getSellerReport(SellerReportRequest request){
       return this.transcationService.getSellerReport(request.getStartDate(), request.getEndDate(), request.getBuyerId());      
    }
    
}