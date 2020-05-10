package frances.emart.com.emartapigateway.filter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import frances.emart.com.emartapigateway.service.EmartUserDetails;
import frances.emart.com.emartapigateway.service.UserDetailsProxyService;
import frances.emart.com.emartapigateway.util.JwtUtil;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsProxyService userDetailsProxyService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

         String jwt = jwtUtil.getJwtFromRequest(request);
         if(jwt!=null&&!jwt.isEmpty()) {
           EmartUserDetails userDetails = userDetailsProxyService.loadUserByToken(jwt);
           List<SimpleGrantedAuthority> authorities  = userDetails.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
           UsernamePasswordAuthenticationToken authentication = 
           new UsernamePasswordAuthenticationToken(userDetails.getUsername(), 
           null,authorities);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);
         }

         filterChain.doFilter(request, response);
    }
    
}