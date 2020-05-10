package frances.emart.com.emartidentityservice.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import frances.emart.com.emartidentityservice.models.BuyerProfile;
import frances.emart.com.emartidentityservice.models.EmartUser;
import frances.emart.com.emartidentityservice.models.SellerProfile;
import frances.emart.com.emartidentityservice.repositories.BuyerProfileRespository;
import frances.emart.com.emartidentityservice.repositories.EmartUserRepository;
import frances.emart.com.emartidentityservice.repositories.SellerProfileRespository;

@Service("EmartUserService")
public class EmartUserService implements UserService {

    @Autowired
    private EmartUserRepository userRepository;

    @Autowired
    private SellerProfileRespository sellerProfileRespository;

    @Autowired
    private BuyerProfileRespository buyerProfileRespository;

    @Override
    public EmartUser findUserByUsernameOrEmail(String usernameOrEmail) {
        Optional<EmartUser> user = this.userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    @Override
    public EmartUser registerSeller(EmartUser emartUser) {
        return saveUser(emartUser);
    }

    @Override
    public EmartUser registerBuyer(EmartUser emartUser) {
        return saveUser(emartUser);
    }

    @Transactional
    private EmartUser saveUser(EmartUser emartUser) {
        if (isExist(emartUser))
            return null;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        emartUser.setPassword(encoder.encode(emartUser.getPassword()));
        emartUser.setBuyerProfile(this.saveBuyerProfile(emartUser));
        emartUser.setSellerProfile(this.saveSellerProfile(emartUser));
        return this.userRepository.save(emartUser);

    }

    private SellerProfile saveSellerProfile(EmartUser emartUser) {
        if (emartUser.getSellerProfile() != null) {
            return this.sellerProfileRespository.save(emartUser.getSellerProfile());
        }
        return null;
    }

    private BuyerProfile saveBuyerProfile(EmartUser emartUser) {
        if (emartUser.getBuyerProfile() != null) {
            return this.buyerProfileRespository.save(emartUser.getBuyerProfile());
        }
        return null;
    }

    private boolean isExist(EmartUser emartUser) {
        Optional<EmartUser> user = this.userRepository.findByUsernameOrEmail(emartUser.getUsername(),
                emartUser.getEmail());
        return user.isPresent();
    }


    @Override
    public void Remove(EmartUser emartUser) {
        this.userRepository.delete(emartUser);
    }

    @Override
    public BuyerProfile getBuyerProfile(String id) {
        Optional<BuyerProfile> profile = this.buyerProfileRespository.findById(id);
        return profile.isPresent()? profile.get(): null;
    }

    @Override
    public SellerProfile getSellerProfile(String id) {
        Optional<SellerProfile> profile = this.sellerProfileRespository.findById(id);
        return profile.isPresent()? profile.get(): null;
    }

}