package frances.emart.com.emartapigateway.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import frances.emart.com.emartapigateway.filter.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().logout().disable().formLogin().disable().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().anonymous().and().exceptionHandling()
                .authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED)).and()
                .addFilterBefore(jwtAuthenticationFilter,
                            UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                    .antMatchers("/identity/api/v1/auth/**").permitAll()
                    .antMatchers("/identity/api/v1/users/**").permitAll()
                    .antMatchers("/identity/api/v1/profiles/buyerProfile").hasAuthority("BUYER")
                    .antMatchers("/identity/api/v1/profiles/sellerProfile").hasAuthority("SELLER") 
                    .antMatchers(HttpMethod.GET, "/inventory/api/v1/catagories").hasAnyAuthority("SELLER","ADMIN")
                    .antMatchers(HttpMethod.GET, "/inventory/api/v1/catagories/**").hasAnyAuthority("SELLER","ADMIN")
                    .antMatchers(HttpMethod.POST,"/inventory/api/v1/items/**").hasAuthority("SELLER") 
                    .antMatchers(HttpMethod.PUT,"/inventory/api/v1/items/**").hasAuthority("SELLER")    
                    .antMatchers(HttpMethod.GET,"/inventory/api/v1/items/**").hasAnyAuthority("BUYER","SELLER")
                    .antMatchers("/cart/api/v1/carts/**").hasAuthority("BUYER")
                    .antMatchers("/order/api/v1/orders/**").hasAuthority("BUYER")
                    .antMatchers(HttpMethod.GET,"/financial/api/v1/discounts/**").hasAuthority("BUYER")
                    .antMatchers(HttpMethod.POST, "/financial/api/v1/transcations/seller").hasAuthority("SELLER")
                    

                .anyRequest().authenticated()
                .and()
                .headers().cacheControl();
    }
}

