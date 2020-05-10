package frances.emart.com.emartidentityservice.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import frances.emart.com.emartidentityservice.utlis.JwtUtil;
import frances.emart.com.emartidentityservice.viewmodel.EmartUserDetails;
import frances.emart.com.emartidentityservice.viewmodel.LoginRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;


@RestController
public class AuthController {
    
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("EmartUserDetailService")
    private UserDetailsService userDetailService;


    @RequestMapping(path = "/auth/tokens", method = RequestMethod.GET)
    public ResponseEntity<?> getUserByToken(@RequestParam String token) {
        if(token==null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        
        String username =  jwtUtil.getUsernameFromJWT(token);
        if(username == null ) return new ResponseEntity<>("Invalidate Token",HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(
            (EmartUserDetails)this.userDetailService.loadUserByUsername(username),HttpStatus.OK);
    }

    @RequestMapping(path="/auth/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager
        .authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsernameOrEmail(), 
                loginRequest.getPassword()));
                SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        String jwt = jwtUtil.createJWT(authentication);
        return new ResponseEntity<>(jwt,HttpStatus.OK);
    }

    @RequestMapping(path="/auth/logout", method = RequestMethod.POST)
    public ResponseEntity<?> logout(HttpServletRequest request){
        jwtUtil.removeJWT(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}