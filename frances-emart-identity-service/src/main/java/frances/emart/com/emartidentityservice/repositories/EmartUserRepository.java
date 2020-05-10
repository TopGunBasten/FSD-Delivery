package frances.emart.com.emartidentityservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import frances.emart.com.emartidentityservice.models.EmartUser;

@Repository
public interface EmartUserRepository extends JpaRepository<EmartUser, String> {
  
  Optional<EmartUser> findByUsernameOrEmail(String username, String email);

}