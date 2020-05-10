package frances.emart.com.emartidentityservice.utlis;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import frances.emart.com.emartidentityservice.config.JwtConfig;
import frances.emart.com.emartidentityservice.viewmodel.EmartUserDetails;

import javax.servlet.http.HttpServletRequest;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


@EnableConfigurationProperties(JwtConfig.class)
@Configuration
@Slf4j
@Component
public class JwtUtil {

    private final static String REDIS_JWT_KEY_PREFIX="security:jwt:";

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public String createJWT(String id, String subject,
    List<String> roles, Collection<? extends GrantedAuthority> authorities) {
        Date now = new Date();
        JwtBuilder builder = Jwts.builder()
                .setId(id.toString())
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getKey())
                .claim("roles", roles)
                .claim("authorities", authorities);

        Long ttl = jwtConfig.getTtl();
        if (ttl > 0) {
            builder.setExpiration(new Date(System.currentTimeMillis() + ttl * 1000));
        }

        String jwt = builder.compact();
        //Save to Redis
        stringRedisTemplate.opsForValue()
                .set(REDIS_JWT_KEY_PREFIX + subject, jwt, ttl, TimeUnit.MILLISECONDS);
        return jwt;
    }

    public String createJWT(Authentication authentication) {
        EmartUserDetails user = (EmartUserDetails) authentication.getPrincipal();
        return createJWT(user.getId(), user.getUsername(),
                user.getRoles(), user.getAuthorities());
    }


    public Claims parseJWT(String jwt) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getKey())
                    .parseClaimsJws(jwt)
                    .getBody();

            String username = claims.getSubject();
            String redisKey = REDIS_JWT_KEY_PREFIX + username;


            Long expire = stringRedisTemplate.getExpire(redisKey, TimeUnit.MILLISECONDS);
            if (Objects.isNull(expire) || expire <= 0) {
                log.error("token %s is expired", jwt);
                return null;
            }

            String redisToken = stringRedisTemplate.opsForValue()
                    .get(redisKey);
            if (!jwt.equals(redisToken)) {
                log.error("token %s is invliad", jwt);
                return null;
            }
            return claims;
        } catch (ExpiredJwtException e) {
            log.error("token %s is expired", jwt);
            return null;
        } catch (UnsupportedJwtException e) {
            log.error("unsupport token %s", jwt);
            return null;
        } catch (MalformedJwtException e) {
            log.error("token %s is invliad", jwt);
            return null;
        } catch (SignatureException e) {
            log.error("invalid token sigature");
            return null;
        } catch (IllegalArgumentException e) {
            log.error("Token is not extied");
            return null;     
        }
    }


    public void removeJWT(HttpServletRequest request) {
        String jwt = getJwtFromRequest(request);
        String username = getUsernameFromJWT(jwt);
        stringRedisTemplate.delete(REDIS_JWT_KEY_PREFIX + username);
    }

    public String getUsernameFromJWT(String jwt) {
        Claims claims = parseJWT(jwt);
        return claims==null? null:claims.getSubject();
    }

    public String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken!=null && !bearerToken.isEmpty() && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
