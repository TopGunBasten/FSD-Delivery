package frances.emart.com.emartidentityservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import frances.emart.com.emartidentityservice.filters.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class EmartSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private EmartJwtAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    @Qualifier("EmartUserDetailService")
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
    
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
       return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
          .csrf().disable()
          .formLogin().disable()
          .httpBasic().disable()
          .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
          .authorizeRequests()
             .antMatchers("/profiles/buyerProfile").hasAuthority("BUYTER")
             .antMatchers("/profiles/sellerProfile").hasAuthority("SELLER")    
             .antMatchers("/auth/**").permitAll()
             .antMatchers("/users/**").permitAll()  
          .anyRequest().authenticated()
          .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler).and()
          .headers().cacheControl();
         httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }

}