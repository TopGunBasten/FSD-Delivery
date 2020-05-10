package frances.emart.com.emartidentityservice.viewmodel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import frances.emart.com.emartidentityservice.models.EmartUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmartUserDetails implements UserDetails {

    private static final String ADMIN = "ADMIN";
    private static final String BUYER = "BUYER";
    private static final String SELLER = "SELLER";

    private static final long serialVersionUID = 3201696571538609174L;

    private String id;

    private String username;

    @JsonIgnore
    private String password;

    private String email;

    private List<String> roles;

    private boolean isActive;

    private LocalDateTime createdDate;

    private String buyerId;

    private String sellerId;

    private Collection<? extends GrantedAuthority> authorities;


    public static EmartUserDetails create(EmartUser user) {
        List<String> roles = new ArrayList<String>();
        
        if(user.getBuyerProfile()!=null) roles.add(BUYER);
        if(user.getSellerProfile()!=null) roles.add(SELLER);
        if(user.isAdmin()) roles.add(ADMIN);

        List<GrantedAuthority> authorities = roles.stream().
        map(role-> new SimpleGrantedAuthority(role))
        .collect(Collectors.toList());

        String buyerId = user.getBuyerProfile()==null? null: user.getBuyerProfile().getId();
        String sellerId= user.getSellerProfile()==null? null: user.getSellerProfile().getId();

        return new EmartUserDetails(user.getId(),user.getUsername(),
                               user.getPassword(), user.getEmail(),
                               roles, user.isActive(),user.getCreatedDate(),
                               buyerId, sellerId,
                               authorities
                              );
    }

   @Override
   public boolean isAccountNonExpired() {
       return true;
   }

   @Override
   public boolean isAccountNonLocked() {
       return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
       return true;
   }

   @Override
   public boolean isEnabled() {
       return isActive;
   }



    
}