package frances.emart.com.emartidentityservice.services;

import org.springframework.stereotype.Service;

import frances.emart.com.emartidentityservice.models.BuyerProfile;
import frances.emart.com.emartidentityservice.models.EmartUser;
import frances.emart.com.emartidentityservice.models.SellerProfile;

@Service
public interface UserService {
    
   EmartUser findUserByUsernameOrEmail(String usernameOrEmail);
   
   EmartUser registerSeller(EmartUser emartUser);

   EmartUser registerBuyer(EmartUser emartUser);

   BuyerProfile getBuyerProfile(String id);

   SellerProfile getSellerProfile(String id);

   void Remove(EmartUser emartUser);

}