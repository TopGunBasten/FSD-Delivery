package frances.emart.com.emartidentityservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import frances.emart.com.emartidentityservice.models.EmartUser;
import frances.emart.com.emartidentityservice.repositories.EmartUserRepository;
import frances.emart.com.emartidentityservice.viewmodel.EmartUserDetails;

@Service("EmartUserDetailService")
public class EmartUserDetailService implements UserDetailsService {

    @Autowired
    private EmartUserRepository userRepository;

    @Override
    public EmartUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmartUser user = userRepository.findByUsernameOrEmail(username, username).
        orElseThrow(() -> new UsernameNotFoundException("not found : " + username));
        return EmartUserDetails.create(user);
    }
    
}