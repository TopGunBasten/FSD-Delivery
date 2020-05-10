package frances.emart.com.emartinventoryservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import frances.emart.com.emartinventoryservice.model.Catagory;
import frances.emart.com.emartinventoryservice.model.SubCatagory;
import frances.emart.com.emartinventoryservice.service.CatagoryService;
import frances.emart.com.emartinventoryservice.viewmodel.CatagoryRequest;
import frances.emart.com.emartinventoryservice.viewmodel.SubCatagoryRequest;
import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
public class CatagoryController {

    @Autowired
    private CatagoryService catagoryService;
    
    @RequestMapping(path="/catagories" ,method = RequestMethod.POST)
    public ResponseEntity<?> createCatagory(@RequestBody CatagoryRequest catagory) {
       return new ResponseEntity<>(this.catagoryService.createCatagory(new Catagory(catagory)),HttpStatus.CREATED);
    }

    @RequestMapping(path="/catagories/subcatagories" ,method = RequestMethod.POST)
    public ResponseEntity<?> createSubCatagory(@RequestBody SubCatagoryRequest catagory) {
      return new ResponseEntity<>(this.catagoryService.createSubCatagory(new SubCatagory(catagory)),HttpStatus.CREATED);
   }

   @RequestMapping(path="/catagories/subcatagories" ,method = RequestMethod.PUT)
    public ResponseEntity<?> updateSubCatagory(@RequestBody SubCatagory catagory) {
      return new ResponseEntity<>(this.catagoryService.updateSubCatagory(catagory),HttpStatus.OK);
   }

   @RequestMapping(path="/catagories" ,method = RequestMethod.PUT)
   public ResponseEntity<?> updateCatagory(@RequestBody Catagory catagory) {
       return new ResponseEntity<>(this.catagoryService.updateCatagory(catagory),HttpStatus.OK);
   }

   @RequestMapping(path="/catagories" ,method = RequestMethod.GET)
   public List<Catagory> getCatagories() {
      return this.catagoryService.getCatagories();
   }

   @RequestMapping(path="/catagories/subcatagories" ,method = RequestMethod.GET)
   public List<SubCatagory> getSubCatagories(@RequestParam("catagoryId") String catagoryId) {
      return this.catagoryService.getSubCatagories(catagoryId);
   }

}