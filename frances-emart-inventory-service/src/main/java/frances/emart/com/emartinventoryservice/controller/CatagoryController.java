package frances.emart.com.emartinventoryservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import frances.emart.com.emartinventoryservice.model.Catagory;
import frances.emart.com.emartinventoryservice.model.SubCatagory;
import frances.emart.com.emartinventoryservice.service.CatagoryService;
import frances.emart.com.emartinventoryservice.viewmodel.CatagoryRequest;
import frances.emart.com.emartinventoryservice.viewmodel.SubCatagoryRequest;



@RestController
public class CatagoryController {

    @Autowired
    private CatagoryService catagoryService;
    
    @RequestMapping(path="/catagories" ,method = RequestMethod.POST)
    public ResponseEntity<?> createCatagory(@Valid @RequestBody CatagoryRequest catagory) {
       return new ResponseEntity<>(this.catagoryService.createCatagory(new Catagory(catagory)),HttpStatus.CREATED);
    }

    @RequestMapping(path="/catagories/subcatagories" ,method = RequestMethod.POST)
    public ResponseEntity<?> createSubCatagory(@Valid @RequestBody SubCatagoryRequest catagory) {
      return new ResponseEntity<>(this.catagoryService.createSubCatagory(new SubCatagory(catagory)),HttpStatus.CREATED);
   }

   @RequestMapping(path="/catagories/subcatagories" ,method = RequestMethod.PUT)
    public ResponseEntity<?> updateSubCatagory(@Valid  @RequestBody SubCatagory catagory) {
      return new ResponseEntity<>(this.catagoryService.updateSubCatagory(catagory),HttpStatus.OK);
   }

   @RequestMapping(path="/catagories" ,method = RequestMethod.PUT)
   public ResponseEntity<?> updateCatagory(@Valid @RequestBody Catagory catagory) {
       return new ResponseEntity<>(this.catagoryService.updateCatagory(catagory),HttpStatus.OK);
   }

   @RequestMapping(path="/catagories" ,method = RequestMethod.GET)
   @ResponseBody
   public List<Catagory> getCatagories() {
      return this.catagoryService.getCatagories();
   }

   @RequestMapping(path="/catagories/subcatagories" ,method = RequestMethod.GET)
   @ResponseBody
   public  List<SubCatagory> getSubCatagories(@RequestParam("catagoryId") String catagoryId) {
      return this.catagoryService.getSubCatagories(catagoryId);
   }

}