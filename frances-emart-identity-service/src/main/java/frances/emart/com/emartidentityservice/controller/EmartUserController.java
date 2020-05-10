package frances.emart.com.emartidentityservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import frances.emart.com.emartidentityservice.models.BuyerProfile;
import frances.emart.com.emartidentityservice.models.EmartUser;
import frances.emart.com.emartidentityservice.models.SellerProfile;
import frances.emart.com.emartidentityservice.services.UserService;
import frances.emart.com.emartidentityservice.viewmodel.Buyer;
import frances.emart.com.emartidentityservice.viewmodel.Seller;

@RestController
public class EmartUserController {

    @Autowired
    @Qualifier("EmartUserService")
    private UserService userService;

    @RequestMapping(path = "/users/buyer", method = RequestMethod.POST)
    public ResponseEntity<?> registerBuyer(@RequestBody Buyer buyer) {
        EmartUser user = this.userService.registerBuyer(new EmartUser(buyer));
        if(user==null) {
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(user.getBuyerProfile()==null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(path = "/profiles/buyerProfile", method = RequestMethod.GET)
    public ResponseEntity<?> getBuyerProfile(@RequestParam String id) {
        if(id==null) {
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BuyerProfile profile = this.userService.getBuyerProfile(id);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }


    @RequestMapping(path = "/profiles/sellerProfile", method = RequestMethod.GET)
    public ResponseEntity<?> getSellerProfile(@RequestParam String id) {
        if(id==null) {
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        SellerProfile profile = this.userService.getSellerProfile(id);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @RequestMapping(path = "/users/seller", method = RequestMethod.POST)
    public ResponseEntity<?> registerSeller(@RequestBody Seller seller) {
        EmartUser user = this.userService.registerSeller(new EmartUser(seller));
        if(user==null) {
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(user.getSellerProfile()==null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}